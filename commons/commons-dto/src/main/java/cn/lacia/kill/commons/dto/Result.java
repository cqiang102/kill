package cn.lacia.kill.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 你是电脑
 * @create 2020/1/14 - 20:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private String status;
    private String message;
    private Object data;
}
