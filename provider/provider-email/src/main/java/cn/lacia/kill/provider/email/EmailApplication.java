package cn.lacia.kill.provider.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 你是电脑
 * @create 2020/1/8 - 16:05
 */
@SpringBootApplication
@EnableAsync
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class , args);
    }
}
