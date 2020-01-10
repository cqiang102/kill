package cn.lacia.kill.provider.email.controller;

import cn.lacia.kill.provider.email.domain.EmailVo;
import cn.lacia.kill.provider.email.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 你是电脑
 * @create 2020/1/8 - 16:08
 */
@RestController
public class EmailController {
    @Resource
    private EmailService emailService;
    @PostMapping("email")
    public String sendEmail(@RequestBody EmailVo emailVo){
        emailService.receive(emailVo.getBody(),emailVo.getTo());
        return "发送成功";
    }
}
