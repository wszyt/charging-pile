package com.zyt.charging.api.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/5/17
 */
@Data
public class NodeInfoVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 节点id
     */
    private String nodeId;

    /**
     * 第一条路由
     */
    private String route1;

    /**
     * 第二条路由
     */
    private String route2;

    /**
     * 第三条路由
     */
    private String route3;

    /**
     * 第四条路由
     */
    private String route4;

    /**
     * 第五条路由
     */
    private String route5;

    /**
     * 第六条路由
     */
    private String route6;

    /**
     * 第七条路由
     */
    private String route7;

    /**
     * 第八条路由
     */
    private String route8;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 下一条路由
     */
    private String nextRoute;

    /**
     * 预留1
     */
    private String reserve1;

    /**
     * 预留2
     */
    private String reserve2;

    /**
     * 预留3
     */
    private String reserve3;

    /**
     * 预留4
     */
    private String reserve4;

    /**
     * 预留5
     */
    private String reserve5;

    /**
     * 预留6
     */
    private String reserve6;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
