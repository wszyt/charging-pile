package com.zyt.charging.web.resp;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @Author: zyt
 * @Date: 2020/4/9
 */
@Data
public class PlaceCodeResp implements Serializable {
  private BigDecimal 	xCoordinate;
  private BigDecimal 	yCoordinate;
}
