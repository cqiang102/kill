package cn.lacia.kill.commons.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_kill_success")
@Builder
public class ItemKillSuccess implements Serializable {

    private static final long serialVersionUID = 9202521213672095478L;
    /**
     * 秒杀成功的订单编号
     */
    @Id
    @Column(name = "code")
    private String code;

    /**
     * 商品 ID
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 秒杀 ID
     */
    @Column(name = "kill_id")
    private Integer killId;

    /**
     * 用户 ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 秒杀结果 （-1 = 无效 , 0 = 成功-未付款 , 1 = 已付款 , 2 = 取消）
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createTime;

    private Integer gap;
}
