package cn.lacia.kill.business.kill.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 你是电脑
 * @create 2020/2/1 - 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_kill")
@Builder
@ToString
public class SuccessInfo {
    /**
     * 秒杀成功的订单编号
     */
    @Id
    @Column(name = "code")
    private String code;
    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 商品名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createTime;

    /**
     * 秒杀结果 （-1 = 无效 , 0 = 成功-未付款 , 1 = 已付款 , 2 = 取消）
     */
    @Column(name = "`status`")
    private Byte status;
}
