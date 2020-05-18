package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.enums.UserTypeEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import com.zyt.charging.api.service.RedisService;
import com.zyt.charging.api.service.UserInfoService;
import com.zyt.charging.web.utlis.CookieUtil;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Reference(version = "${service.version}")
    RedisService redisService;

    @Reference(version = "${service.version}")
    UserInfoService userInfoService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String isLogin = (String) request.getSession().getAttribute("token");
        String requestURI = request.getRequestURI();
        if (StringUtils.hasLength(isLogin)) {
            return "redirect:/chargeRecordTotal";
        }
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password,
                        HttpServletRequest request, HttpServletResponse response) {
        // 数据库查询是否存在该用户
        UserInfoQueryReq userInfoQueryReq = new UserInfoQueryReq();
        userInfoQueryReq.setUsername(username);
        userInfoQueryReq.setPassword(password);
        BaseResult<List<UserInfoVO>> listBaseResult = userInfoService.selectUserInfo(userInfoQueryReq);
        UserInfoVO userInfoVO = listBaseResult.getData().get(0);

        // 登录成功
        if (userInfoVO.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            redisService.setString(token, UserTypeEnum.ADMIN.getCode(), 60 * 60 * 24L);
            request.getSession().setAttribute("token", token);
            CookieUtil.setCookie(request, response, "token", token, 60 * 60 * 24);
            return "redirect:/chargeRecordTotal";
        }

        return "login";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.GET)
    public String webLogin() {
        return "/web/login";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public String webLogin(String username, String password,
                        HttpServletRequest request, HttpServletResponse response) {
        // 数据库查询是否存在该用户
        UserInfoQueryReq userInfoQueryReq = new UserInfoQueryReq();
        userInfoQueryReq.setUsername(username);
        BaseResult<List<UserInfoVO>> listBaseResult = userInfoService.selectUserInfo(userInfoQueryReq);
        UserInfoVO userInfoVO = listBaseResult.getData().get(0);
        // 登录成功
        if (userInfoVO.getPassword().equals(password)) {
            String token = UUID.randomUUID().toString();
            redisService.setString(token, UserTypeEnum.USER.getCode(), 60 * 60 * 24L);
            request.getSession().setAttribute("token", token);
            CookieUtil.setCookie(request, response, "token", token, 60 * 60 * 24);
            return "redirect:";
        }

        return "/web/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession ().invalidate ();
        String token = CookieUtil.getCookieValue(request, "token");
        redisService.del(token);
        return "redirect:/login";
    }

    @RequestMapping(value = "/web/logout", method = RequestMethod.GET)
    public String webLogout(HttpServletRequest request) {
        request.getSession ().invalidate ();
        return "redirect:/web/login";
    }

}
