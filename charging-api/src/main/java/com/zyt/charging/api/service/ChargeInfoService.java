package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoChangeReq;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.entity.vo.ReceiveReportVO;

import java.util.List;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
public interface ChargeInfoService {

    /**
     * 插入充电桩信息
     *
     * @param request
     * @return
     */
    BaseResult<Void> insertChargeInfo(ChargeInfoChangeReq request);

    /**
     * 更新充电桩信息
     *
     * @param request
     * @return
     */
    BaseResult<Void> updateChargeInfo(ChargeInfoChangeReq request);

    /**
     * 根据条件查询充电桩信息
     *
     * @param request
     * @return
     */
    BaseResult<List<ChargeInfoVO>> selectChargeInfo(ChargeInfoQueryReq request);

    /**
     * 根据Id查询充电桩信息
     *
     * @param request
     * @return
     */
    BaseResult<ChargeInfoVO> selectChargeInfoById(ChargeInfoQueryReq request);

    /**
     * 根据充电桩编号code查询充电桩信息
     *
     * @param request
     * @return
     */
    BaseResult<ChargeInfoVO> selectChargeInfoByCode(ChargeInfoQueryReq request);

    /**
     * 刷新redis中充电桩坐标
     *
     * @return
     */
    BaseResult<Void> flashPlaceCode();

    /**
     * 校验充电桩参数
     * @param chargeInfoVO
     * @return
     */
    BaseResult<Void> checkChargeInfo(ChargeInfoVO chargeInfoVO);
}
