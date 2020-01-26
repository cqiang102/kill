package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.service.ItemKillService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 你是电脑
 * @create 2020/1/25 - 21:39
 */
@SpringBootTest
@Slf4j
public class ItemKillServiceTest {

    @Autowired
    private ItemKillService itemKillService;

    @Test
    public void testKillItem(){

        for (int i = 0; i < 1000; i++) {
            log.info("{} -> {}",i,itemKillService.killItem(6, i));
        }

    }
}
