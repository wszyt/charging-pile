package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeRecordChangeReq;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.entity.request.ChargeRecordQueryReq;
import com.zyt.charging.api.entity.vo.ChargeRecordVO;

import java.util.List;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
public interface ChargeRecordService {

    /**
     * 根据条件查询充电记录数量
     *
     * @param request
     * @return
     */
    BaseResult<Integer> countChargeRecord(ChargeRecordCountReq request);

    /**
     * 充电完成记录充电信息
     *
     * @param request
     * @return
     */
    BaseResult<Integer> insertChargeRecord(ChargeRecordChangeReq request);

    /**
     * 根据用户id查询充电记录
     *
     * @param request
     * @return
     */
    BaseResult<List<ChargeRecordVO>> selectRecordByUserId(ChargeRecordQueryReq request);

    BaseResult<ChargeRecordVO> selectRecordById(ChargeRecordQueryReq request);

    /**
     * 根据充电桩id查询充电记录
     *
     * @param request
     * @return
     */
    BaseResult<List<ChargeRecordVO>> selectRecordByChargeInfoId(ChargeRecordQueryReq request);

    /**
     * 根据充电桩id查询该充电桩一共所使用人数
     *
     * @param request
     * @return
     */
    BaseResult<Integer> countChargeRecordByUser(ChargeRecordCountReq request);

    /**
     * 开始充电
     *
     * @param chargeRecordVO
     * @return
     */
    BaseResult<ChargeRecordVO> startCharge(ChargeRecordVO chargeRecordVO);

    /**
     * 结束充电
     *
     * @param chargeRecordVO
     * @return
     */
    BaseResult<Void> endCharge(ChargeRecordVO chargeRecordVO);
}
