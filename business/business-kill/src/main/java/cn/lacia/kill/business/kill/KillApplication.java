package cn.lacia.kill.business.kill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 你是电脑
 * @create 2020/1/11 - 19:01
 */
@SpringBootApplication(scanBasePackages = {"cn.lacia.kill.commons","cn.lacia.kill.business.kill"})
@MapperScan("cn.lacia.kill.business.kill.mapper")
@EnableScheduling
@EnableTransactionManagement
public class KillApplication {
    public static void main(String[] args) {
        SpringApplication.run(KillApplication.class,args);
    }
}
