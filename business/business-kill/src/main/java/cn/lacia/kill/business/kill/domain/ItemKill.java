package cn.lacia.kill.business.kill.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
@create 2020/1/11 - 19:09
@author    你是电脑
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_kill")
@Builder
@ToString
public class ItemKill {
    /**
     * 主键 ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品 ID
     */
    @Column(name = "item_id")
    private Integer itemId;

    /**
     * 可被秒杀的数量
     */
    @Column(name = "total")
    private Integer total;

    /**
     * 秒杀开始时间
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date startTime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date endTime;

    /**
     * 是否有效 （1 = 是 , 0 = 否）
     */
    @Column(name = "active")
    private Integer active;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createTime;

    private String itemName;

    private Integer canKill;
}
