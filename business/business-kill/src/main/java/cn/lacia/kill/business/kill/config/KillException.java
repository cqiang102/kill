package cn.lacia.kill.business.kill.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 你是电脑
 * @create 2020/2/1 - 21:36
 */
@Slf4j
public class KillException extends Exception {
    public KillException(String message){
        super(message);
        log.info("new Err");
    }
}
