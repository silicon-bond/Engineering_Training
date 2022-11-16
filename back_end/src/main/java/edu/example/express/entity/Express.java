package edu.example.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 快递单
 * </p>
 *
 * @author zxy
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Express extends Model<Express> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "express_id", type = IdType.AUTO)
    private Integer expressId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("order_date")
    private LocalDate orderDate;

    private Integer state;

    @TableField("network_id")
    private Integer networkId;

    @TableField("deliver_name")
    private String deliverName;

    @TableField("deliver_province")
    private String deliverProvince;

    @TableField("deliver_municipal")
    private String deliverMunicipal;

    @TableField("deliver_country")
    private String deliverCountry;

    @TableField("deliver_street")
    private String deliverStreet;

    @TableField("deliver_detail_address")
    private String deliverDetailAddress;

    @TableField("deliver_phone_number")
    private String deliverPhoneNumber;

    @TableField("receipt_name")
    private String receiptName;

    @TableField("receipt_province")
    private String receiptProvince;

    @TableField("receipt_municipal")
    private String receiptMunicipal;

    @TableField("receipt_country")
    private String receiptCountry;

    @TableField("receipt_street")
    private String receiptStreet;

    @TableField("receipt_detail_address")
    private String receiptDetailAddress;

    @TableField("receipt_phone_number")
    private String receiptPhoneNumber;

    @TableField("collect_id")
    private Integer collectId;

    @TableField("delivery_id")
    private Integer deliveryId;

    @TableField("description")
    private String description;

    @TableField("orderer_id")
    private Integer ordererId;


    @Override
    protected Serializable pkVal() {
        return this.expressId;
    }

}
