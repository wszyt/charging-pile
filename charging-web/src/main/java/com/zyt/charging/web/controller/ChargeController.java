package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.enums.RedisEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import com.zyt.charging.api.service.RedisService;
import com.zyt.charging.web.resp.PlaceCodeResp;
import com.zyt.charging.api.entity.request.ChargeInfoChangeReq;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.service.ChargeInfoService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zyt.charging.web.utlis.JsonUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChargeController {

    @Reference(version = "${service.version}")
    ChargeInfoService chargeInfoService;
    @Reference(version = "${service.version}")
    RedisService redisService;

    @ModelAttribute
    public ChargeInfoVO getChargeInfoVO(Long id) {
        ChargeInfoVO chargeInfoVO = null;

        ChargeInfoQueryReq req = new ChargeInfoQueryReq();
        //id不为空，则从数据库获取
        if (id != null) {
            req.setId(id);
            BaseResult<ChargeInfoVO> chargeInfoResult = chargeInfoService.selectChargeInfoById(req);
            chargeInfoVO = chargeInfoResult.getData();
        } else {
            chargeInfoVO = new ChargeInfoVO();
        }
        return chargeInfoVO;
    }

    @RequestMapping(value = "/selectChargeInfo", method = RequestMethod.POST)
    public BaseResult<ChargeInfoVO> selectChargeInfoById(ChargeInfoQueryReq request) {
        return chargeInfoService.selectChargeInfoById(request);
    }

    @RequestMapping(value = "/selectChargeInfoList", method = RequestMethod.POST)
    public BaseResult<List<ChargeInfoVO>> selectChargeInfoList(ChargeInfoQueryReq request) {
        return chargeInfoService.selectChargeInfo(request);
    }

    @RequestMapping(value = "/selectAllChargeInfoList", method = RequestMethod.GET)
    public String selectAllChargeInfoList(Model model) {
        BaseResult<List<ChargeInfoVO>> listBaseResult = chargeInfoService.selectChargeInfo(new ChargeInfoQueryReq());
        model.addAttribute("baseResult", listBaseResult);
        return "chargeInfoList";
    }

    @RequestMapping(value = "/modifyChargeInfoPage", method = RequestMethod.GET)
    public String modifyChargeInfoPage() {
        return "editorChargeInfo";
    }

    @RequestMapping(value = "/modifyChargeInfo", method = RequestMethod.POST)
    public String modifyChargeInfo(ChargeInfoVO chargeInfoVO, Model model) {
        // 存在Id时修改
        if (chargeInfoVO.getId() != null) {
            ChargeInfoQueryReq chargeInfoQueryReq = new ChargeInfoQueryReq();
            chargeInfoQueryReq.setId(chargeInfoVO.getId());
            BaseResult<ChargeInfoVO> chargeInfoVOBaseResult = chargeInfoService.selectChargeInfoById(chargeInfoQueryReq);
            if (BaseResult.STATUS_FAIL.equals(chargeInfoVOBaseResult.getStatus())) {
                model.addAttribute("baseResult", chargeInfoVOBaseResult);
                return "editorChargeInfo";
            }

            if (chargeInfoVOBaseResult.getData() == null) {
                model.addAttribute("baseResult", BaseResult.fail("该Id没有对应充电桩信息"));
                return "editorChargeInfo";
            }
            ChargeInfoChangeReq chargeInfoChangeReq = new ChargeInfoChangeReq();
            chargeInfoChangeReq.setChargeInfoVO(chargeInfoVO);
            BaseResult<Void> baseResult = chargeInfoService.updateChargeInfo(chargeInfoChangeReq);
            model.addAttribute("baseResult", baseResult);
            if (baseResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
                return "editorChargeInfo";
            }
            return "redirect:/selectAllChargeInfoList";
        } else {
            ChargeInfoChangeReq chargeInfoChangeReq = new ChargeInfoChangeReq();
            chargeInfoChangeReq.setChargeInfoVO(chargeInfoVO);
            BaseResult<Void> baseResult = chargeInfoService.insertChargeInfo(chargeInfoChangeReq);
            model.addAttribute("baseResult", baseResult);
            if (baseResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
                return "editorChargeInfo";
            }
            return "redirect:/selectAllChargeInfoList";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getPlaceCode", method = RequestMethod.GET)
    public String getChargingPilePlaceCode() throws Exception {
        BaseResult<List<String>> stringListResult = redisService.getStringList(RedisEnum.PLACE_CODE.getCode(), 0L, -1L);
        List<String> listString = stringListResult.getData();
        List<PlaceCodeResp> placeCodeRespList = new ArrayList<>();
        listString.forEach(code -> {
            String[] split = code.split(",");
            PlaceCodeResp placeCodeResp = new PlaceCodeResp();
            placeCodeResp.setXCoordinate(new BigDecimal(split[0]));
            placeCodeResp.setYCoordinate(new BigDecimal(split[1]));
            placeCodeResp.setBrands(split[2]);
            placeCodeResp.setType(split[3]);
            placeCodeResp.setStatus(split[4]);
            placeCodeRespList.add(placeCodeResp);
        });
        return JsonUtils.obj2json(placeCodeRespList);
    }

    @ResponseBody
    @RequestMapping(value = "/flashPlaceCode")
    public BaseResult<Void> flashPlaceCode() {
        BaseResult<Void> baseResult = chargeInfoService.flashPlaceCode();
        return BaseResult.success();
    }
}
