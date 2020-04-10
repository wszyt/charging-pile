package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.enums.RedisEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.reponse.PlaceCodeResp;
import com.zyt.charging.api.entity.request.ChargeInfoChangeReq;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.web.utlis.RedisService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChargeController {

  @Reference(version = "${service.version}")
  ChargeInfoService chargeInfoService;
  @Resource
  RedisService redisService;

    @RequestMapping(value = "/selectChargeInfo", method = RequestMethod.POST)
    public BaseResult<ChargeInfoVO> selectChargeInfoById(ChargeInfoQueryReq request) {
        return chargeInfoService.selectChargeInfoById(request);
    }

  @RequestMapping(value = "/selectChargeInfo", method = RequestMethod.POST)
  public BaseResult<List<ChargeInfoVO>> selectChargeInfo(ChargeInfoQueryReq request) {
    return chargeInfoService.selectChargeInfo(request);
  }

  @RequestMapping(value = "/modifyChargeInfo", method = RequestMethod.POST)
  public BaseResult<Void> modifyChargeInfo(@RequestBody ChargeInfoChangeReq request) {
    if (request == null || request.getChargeInfoVO() == null) {
      return BaseResult.fail("参数不能为空");
    }

    ChargeInfoVO chargeInfoVO = request.getChargeInfoVO();
    // 存在Id时修改
    if (chargeInfoVO.getId() != null) {
      ChargeInfoQueryReq chargeInfoQueryReq = new ChargeInfoQueryReq();
      chargeInfoQueryReq.setId(chargeInfoVO.getId());
      BaseResult<ChargeInfoVO> chargeInfoVOBaseResult = chargeInfoService.selectChargeInfoById(chargeInfoQueryReq);
      if (chargeInfoVOBaseResult.getStatus() == BaseResult.STATUS_FAIL) {
        return BaseResult.fail(chargeInfoVOBaseResult.getMessage());
      }

      if (chargeInfoVOBaseResult.getData() == null) {
          return BaseResult.fail("该Id没有对应充电桩信息");
      }
      ChargeInfoChangeReq chargeInfoChangeReq = new ChargeInfoChangeReq();
      chargeInfoChangeReq.setChargeInfoVO(request.getChargeInfoVO());
      return chargeInfoService.updateChargeInfo(chargeInfoChangeReq);
    } else {
      ChargeInfoChangeReq chargeInfoChangeReq = new ChargeInfoChangeReq();
      chargeInfoChangeReq.setChargeInfoVO(request.getChargeInfoVO());
      return chargeInfoService.insertChargeInfo(chargeInfoChangeReq);
    }
  }

  @RequestMapping(value = "/getPlaceCode")
  public String getChargingPilePlaceCode() {
    List<String> listString = redisService.getListString(RedisEnum.PLACE_CODE.getCode(), 0L, -1L);
    List<PlaceCodeResp> placeCodeRespList = new ArrayList<>();
    listString.forEach(code -> {
      String[] split = code.split(",");
      PlaceCodeResp placeCodeResp = new PlaceCodeResp();
      placeCodeResp.setXCoordinate(new BigDecimal(split[0]));
      placeCodeResp.setYCoordinate(new BigDecimal(split[1]));
      placeCodeRespList.add(placeCodeResp);
    });
    return null;
  }

  @RequestMapping(value = "/flashPlaceCode")
  public String flashPlaceCode() {
    BaseResult<Void> baseResult = chargeInfoService.flashPlaceCode();
    return null;
  }
}
