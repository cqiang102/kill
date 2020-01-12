package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 你是电脑
 * @create 2020/1/12 - 13:18
 */
@SpringBootTest
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testSelectAllItemKll(){
        try {
            itemService.getKillItems().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
