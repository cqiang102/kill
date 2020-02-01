package cn.lacia.kill.business.kill.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
@create 2020/1/11 - 19:07
@author    你是电脑
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
@Builder
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 商品编号
     */
    @Column(name = "code")
    private String code;

    /**
     * 库存
     */
    @Column(name = "stock")
    private Long stock;

    /**
     * 采购时间
     */
    @Column(name = "purchase_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date purchaseTime;

    /**
     * 是否有效 （1 = 是 , 0 = 否）
     */
    @Column(name = "active")
    private Integer active;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date updateTime;
}
