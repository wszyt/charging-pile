package com.zyt.charging.api.entity.request;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author: zyt
 * @Date: 2020/4/7
 */
@Data
public class ChargeRecordCountReq implements Serializable {
    private Date startTime;
    private Date endTime;
}
