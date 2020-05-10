package com.zyt.charging.api.entity.reponse;

import com.zyt.charging.api.entity.vo.ChargeCountVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: zyt
 * @Date: 2020/5/10
 */
@Data
public class ChargeCountResp implements Serializable {
    private Integer chargeTotal;
    private Integer userTotal;
    private Integer chargeChange;
    private Integer userChange;
    private Integer chargeTotalAdd;
    private Integer userTotalAdd;
    private Integer chargeChangeAdd;
    private Integer userChangeAdd;
    List<ChargeCountVO> chargeCountVOS;
}
