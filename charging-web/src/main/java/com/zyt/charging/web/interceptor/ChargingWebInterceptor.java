package com.zyt.charging.web.interceptor;

import com.zyt.charging.api.entity.enums.UserTypeEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.service.RedisService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChargingWebInterceptor implements HandlerInterceptor {

    @Reference(version = "${service.version}")
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = (String) request.getSession().getAttribute("token");
        String requestURI = request.getRequestURI();
        // 已登录
        if (StringUtils.hasLength(token)) {
            BaseResult<String> strResult = redisService.getString(token);
            String userType = strResult.getData();
            if (StringUtils.hasLength(userType)) {
                // 请求用户页面
                if (requestURI.startsWith("/web")) {
                    // 当前身份为用户则通过
                    if (UserTypeEnum.USER.getCode().equals(userType)) {
                        return true;
                    }
                }
                // 请求管理员页面
                else {
                    // 当前身份为管理员则放行
                    if (UserTypeEnum.ADMIN.getCode().equals(userType)) {
                        return true;
                    } else {
                        response.sendRedirect("/login");
                    }
                }
            }
        } else {
            // 请求用户地址
            if (requestURI.contains("web")) {
                response.sendRedirect("web/login");
            }
            // 请求管理员地址
            else {
                response.sendRedirect("/login");
            }
        }
        return false;
    }
}
