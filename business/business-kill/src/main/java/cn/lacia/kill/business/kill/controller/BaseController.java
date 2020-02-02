package cn.lacia.kill.business.kill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 你是电脑
 * @create 2020/1/12 - 12:39
 */
@Controller
public class BaseController {

    @GetMapping("base/error")
    public String error(){
        return "error";
    }
    @GetMapping(value = {"index.html","","/","index"})
    public String index(){
        return "redirect:/item/list";
    }
}
