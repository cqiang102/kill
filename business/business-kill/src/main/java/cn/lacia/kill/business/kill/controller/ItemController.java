package cn.lacia.kill.business.kill.controller;

import cn.lacia.kill.business.kill.domain.ItemKill;
import cn.lacia.kill.business.kill.domain.SuccessInfo;
import cn.lacia.kill.business.kill.service.ItemKillService;
import cn.lacia.kill.business.kill.service.ItemKillSuccessService;
import cn.lacia.kill.business.kill.service.ItemService;
import cn.lacia.kill.commons.dto.KillDTO;
import cn.lacia.kill.commons.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 你是电脑
 * @create 2020/1/12 - 12:33
 */
@Controller
@RequestMapping("item")
public class ItemController {
    public static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Resource
    private ItemService itemService;
    @Resource
    private ItemKillService itemKillService;

    @Autowired
    private ItemKillSuccessService itemKillSuccessService;
    @GetMapping("list")
    public String list(Model model){
        try {
            List<ItemKill> killItems = itemService.getKillItems();
            model.addAttribute("killItems",killItems);
        } catch (Exception e) {
            log.error("list error : {}",e.fillInStackTrace().toString());
            return "redirect:/base/error";
        }
        return "index";
    }

    @GetMapping("{itemId}")
    public  String show(@PathVariable String itemId,Model model){
        try {
            ItemKill itemDetailById = itemService.getItemDetailById(itemId);
            model.addAttribute("item",itemDetailById);
            log.info("{}",itemDetailById);
        } catch (Exception e) {
            log.error("list error : {}",e.fillInStackTrace().toString());
            return "redirect:/base/error";
        }

        return "detail";
    }

    @PostMapping("kill")
    @ResponseBody
    public Result kill(@Validated @RequestBody KillDTO killDTO){

        boolean b = false;
        try {
             b = itemKillService.killItem(Integer.parseInt(killDTO.getKillId()),Integer.parseInt(killDTO.getUserId()));
        } catch (Exception e) {
//            e.printStackTrace();
            log.info("抢购失败 -> {}",killDTO);
        }
        return b ? new Result("200","ok",null) : new Result("500","notOk",null);
    }
    @GetMapping("detail/{code}")
    public  String detail(@PathVariable String code,Model model){
        try {
            SuccessInfo itemKillSuccess = itemKillSuccessService.selectItemSuccessByCode(code);
            model.addAttribute("item",itemKillSuccess);
            log.info("{}",itemKillSuccess);
        } catch (Exception e) {
            log.error("list error : {}",e.fillInStackTrace().toString());
            return "redirect:/base/error";
        }

        return "info";
    }

}
