package edu.example.express.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Express extends Model<Express> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    @TableField(value = "order_date")
    private LocalDate orderDate;

    private Integer state;

    @TableField(value = "network_id")
    private Integer networkId;

    @TableField(value = "delivery_province")
    private String deliveryProvince;

    @TableField(value = "delivery_municipal")
    private String deliveryMunicipal;

    @TableField(value = "delivery_country")
    private String deliveryCountry;

    @TableField(value = "delivery_street")
    private String deliveryStreet;

    @TableField(value = "delivery_detail_address")
    private String deliveryDetailAddress;

    @TableField(value = "delivery_phone_number")
    private String deliveryPhoneNumber;

    @TableField(value = "receipt_province")
    private String receiptProvince;

    @TableField(value = "receipt_municipal")
    private String receiptMunicipal;

    @TableField(value = "receipt_country")
    private String receiptCountry;

    @TableField(value = "receipt_street")
    private String receiptStreet;

    @TableField(value = "receipt_detail_address")
    private String receiptDetailAddress;

    @TableField(value = "receipt_phone_number")
    private String receiptPhoneNumber;

    @TableField(value = "collect_id")
    private Integer collectId;

    @TableField(value = "deliveryman_id")
    private Integer deliverymanId;

    @TableField(value = "orderer_id")
    private Integer ordererId;

    private String description;


    @Override
    protected Serializable pkVal() {
        return this.expressId;
    }
}
