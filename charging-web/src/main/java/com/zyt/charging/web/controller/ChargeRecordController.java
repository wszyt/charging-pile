package com.zyt.charging.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zyt.charging.api.entity.enums.ChargeRecordEnum;
import com.zyt.charging.api.entity.enums.ChargeStatusEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.request.ChargeRecordChangeReq;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.entity.request.ChargeRecordQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.entity.vo.ChargeRecordVO;
import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.api.service.ChargeRecordService;
import com.zyt.charging.api.service.UserInfoService;
import com.zyt.charging.web.config.AlipayConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/4/8
 */
@Controller
public class ChargeRecordController {

    @Reference(version = "${service.version}")
    ChargeRecordService chargeRecordService;
    @Reference(version = "${service.version}")
    ChargeInfoService chargeInfoService;
    @Reference(version = "${service.version}")
    UserInfoService userInfoService;

    @ModelAttribute
    public ChargeRecordVO getChargeRecordVO(Long id) {
        ChargeRecordVO chargeRecordVO = null;
        ChargeRecordQueryReq req = new ChargeRecordQueryReq();
        //id不为空，则从数据库获取
        if (id != null) {
            req.setId(id);
            BaseResult<ChargeRecordVO> chargeInfoVOBaseResult = chargeRecordService.selectRecordById(req);
            chargeRecordVO = chargeInfoVOBaseResult.getData();
        } else {
            chargeRecordVO = new ChargeRecordVO();
        }
        return chargeRecordVO;
    }

    @RequestMapping(value = "countChargeRecord", method = RequestMethod.GET)
    public BaseResult<Integer> countChargeRecord(ChargeRecordCountReq chargeRecordCountReq) {
        return chargeRecordService.countChargeRecord(chargeRecordCountReq);
    }

    @RequestMapping(value = "charging", method = RequestMethod.POST)
    public String charging(ChargeRecordChangeReq request) {
        if (request == null || !request.checkParam()) {
            return "缺少必要参数";
        }

        BaseResult<Integer> baseResult = chargeRecordService.insertChargeRecord(request);
        return null;
    }

    @RequestMapping(value = "/startCharge")
    public String startCharge(ChargeRecordVO chargeRecordVO, Model model, RedirectAttributes redirectAttributes) {
        chargeRecordVO.setStartTime(new Date());
        chargeRecordVO.setIsPaid(ChargeRecordEnum.IsPaidEnum.NO.getDesc());
        BaseResult<ChargeRecordVO> baseResult = chargeRecordService.startCharge(chargeRecordVO);
        model.addAttribute("baseResult", baseResult);
        if (baseResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
            return "charge";
        }
        chargeRecordVO.setId(baseResult.getData().getId());
        return "endCharge";
    }

    @RequestMapping(value = "/endCharge")
    public void endCharge(ChargeRecordVO chargeRecordVO, HttpServletResponse response) throws AlipayApiException, IOException {
        chargeRecordVO.setIsPaid(ChargeRecordEnum.IsPaidEnum.YES.getDesc());
        BaseResult<ChargeRecordVO> baseResult = chargeRecordService.endCharge(chargeRecordVO);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(baseResult.getData().getId().toString());
        //付款金额，必填
        String total_amount = new String(baseResult.getData().getCost().toString());
        //订单名称，必填
        String subject = new String("充电桩充电付款");
        //商品描述，可空
        String body = new String("充电桩充电消费");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //输出
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result); //直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping(value = "/endChargePage")
    public String endChargePage() {
        return "endCharge";
    }

    @RequestMapping(value = "/charge")
    public String charge() {
        return "charge";
    }

    @RequestMapping(value = "/chargeResult")
    public String chargeResult(Long id, Model model) {
        BaseResult<ChargeRecordVO> chargeRecordVOBaseResult = chargeRecordService.selectRecordById(ChargeRecordQueryReq.builder().id(id).build());
        model.addAttribute("chargeRecordVO", chargeRecordVOBaseResult.getData());
        return "chargeResult";
    }
}
