package com.example.security.controller;

import com.example.security.commons.Result;
import com.example.security.commons.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-08 20:30
 * @Description:
 */
@Controller
public class LoginController {

    @Autowired
    private SessionRegistry sessionRegistry;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result invalid() {
        return new Result(false, StatusCode.LOGIN_ERROR, "Session 已过期，请重新登录");
    }

    @GetMapping("/")
    public String homeView(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登录用户: " + name);
        return "home.html";
    }

    @GetMapping("/me")
    @ResponseBody
    public Result me(@AuthenticationPrincipal UserDetails userDetails) {
        return new Result(true, StatusCode.SUCCESS, "已查询个人信息~", userDetails);
    }

    @GetMapping("/login/error")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        AuthenticationException exception =
                (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        try {
            response.getWriter().write(exception.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/kick")
    @ResponseBody
    public String removeUserSessionByUsername(@RequestParam String username) {
        int count = 0;

        // 获取session中所有的用户信息
        List<Object> users = sessionRegistry.getAllPrincipals();
        for (Object principal : users) {
            if (principal instanceof User) {
                String principalName = ((User)principal).getUsername();
                if (principalName.equals(username)) {
                    // 参数二：是否包含过期的Session
                    List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
                    if (null != sessionsInfo && sessionsInfo.size() > 0) {
                        for (SessionInformation sessionInformation : sessionsInfo) {
                            sessionInformation.expireNow();
                            count++;
                        }
                    }
                }
            }
        }
        return "操作成功，清理session共" + count + "个";
    }


    @GetMapping("/login")
    public String loginView(){
        return "login.html";
    }

    @GetMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin', 'r')")
    public Result adminViewR(){
        return new Result(true, StatusCode.SUCCESS, "验证 /admin 和 r 权限");
    }

    @GetMapping("/admin/c")
    @ResponseBody
    @PreAuthorize("hasPermission('/admin', 'c')")
    public Result adminViewC(){
        return new Result(true, StatusCode.SUCCESS, "验证 /admin 和 c 权限");

    }

    @GetMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public Result userView(){
        return new Result(true, StatusCode.SUCCESS, "Your SRole is ROLE_USER");
    }

}
