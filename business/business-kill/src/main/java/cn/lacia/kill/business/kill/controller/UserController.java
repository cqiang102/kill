package cn.lacia.kill.business.kill.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * @author 你是电脑
 * @create 2020/2/3 - 17:15
 */
@Controller
@Slf4j
public class UserController {

    public static final String salt="debug";
    @GetMapping({"toLogin","unauth","login"})
    public String toLogin(){
        return "login";
    }
    @GetMapping("logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model model){
        String errMsg = "";
        try {
            if (! SecurityUtils.getSubject().isAuthenticated()){
                String newPass = new Md5Hash(password,salt).toString();
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,newPass);
                SecurityUtils.getSubject().login(usernamePasswordToken);
            }
        } catch (UnknownAccountException | DisabledAccountException | IncorrectCredentialsException e){
            errMsg=e.getMessage();
        } catch (Exception e) {
            errMsg="登录异常请联系管理员";
            e.printStackTrace();
        }
        if (StringUtils.isBlank(errMsg)){
            return "redirect:/item/list";
        }else {
            model.addAttribute("errMsg",errMsg);
            model.addAttribute("userName",userName);
            return "login";
        }
    }
}
