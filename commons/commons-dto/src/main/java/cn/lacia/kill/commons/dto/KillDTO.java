package cn.lacia.kill.commons.dto;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author 你是电脑
 * @create 2020/1/26 - 14:14
 */
@Data
@ToString
public class KillDTO {
    @NotNull
    private String killId;
    @NotNull
    private String userId;
}
