package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.enums.UserTypeEnum;
import com.zyt.charging.web.utlis.CookieUtil;
import com.zyt.charging.web.utlis.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Resource
    RedisService redisService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password,
                        HttpServletRequest request, HttpServletResponse response) {
        // 数据库查询是否存在改用户

        String token = UUID.randomUUID().toString();
        redisService.setString(token, UserTypeEnum.ADMIN.getCode(), 60 * 60 * 24L);
        CookieUtil.setCookie(request, response, "token", token, 60 * 60 * 24);
        return "redirect:";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.GET)
    public String webLogin() {
        return "/web/login";
    }

    @RequestMapping(value = "/web/login", method = RequestMethod.POST)
    public String webLogin(String username, String password,
                        HttpServletRequest request, HttpServletResponse response) {
        // 数据库查询是否存在改用户

        String token = UUID.randomUUID().toString();
        redisService.setString(token, UserTypeEnum.USER.getCode(), 60 * 60 * 24L);
        CookieUtil.setCookie(request, response, "token", token, 60 * 60 * 24);
        return "redirect:";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession ().invalidate ();
        return "redirect:/login";
    }

    @RequestMapping(value = "/web/logout", method = RequestMethod.GET)
    public String webLogout(HttpServletRequest request) {
        request.getSession ().invalidate ();
        return "redirect:/web/login";
    }

}
