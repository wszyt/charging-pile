package com.zyt.charging.api.entity.request;

import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import java.io.Serializable;
import lombok.Data;

/**
 * @author: zyt
 * @Date: 2020/4/7
 */
@Data
public class ChargeInfoChangeReq implements Serializable {
    ChargeInfoVO chargeInfoVO;
}
