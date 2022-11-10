package edu.example.express.entity;


import com.baomidou.mybatisplus.annotation.IdType;
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

    @TableId(value = "order_date")
    private LocalDate orderDate;

    private Integer state;

    @TableId(value = "network_id")
    private Integer networkId;

    @TableId(value = "delivery_province")
    private String deliveryProvince;

    @TableId(value = "delivery_municipal")
    private String deliveryMunicipal;

    @TableId(value = "delivery_country")
    private String deliveryCountry;

    @TableId(value = "delivery_street")
    private String deliveryStreet;

    @TableId(value = "delivery_detail_address")
    private String deliveryDetailAddress;

    @TableId(value = "delivery_phone_number")
    private String deliveryPhoneNumber;

    @TableId(value = "receipt_province")
    private String receiptProvince;

    @TableId(value = "receipt_municipal")
    private String receiptMunicipal;

    @TableId(value = "receipt_country")
    private String receiptCountry;

    @TableId(value = "receipt_street")
    private String receiptStreet;

    @TableId(value = "receipt_detail_address")
    private String receiptDetailAddress;

    @TableId(value = "receipt_phone_number")
    private String receiptPhoneNumber;

    @TableId(value = "collect_id")
    private Integer collectId;

    @TableId(value = "deliveryman_id")
    private Integer deliverymanId;

    @TableId(value = "orderer_id")
    private Integer ordererId;

    private String description;


    @Override
    protected Serializable pkVal() {
        return this.expressId;
    }
}
