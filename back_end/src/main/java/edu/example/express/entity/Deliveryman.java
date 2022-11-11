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
 * 用户
 * </p>
 *
 * @author zlh
 * @since 2022-11-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Deliveryman extends Model<Deliveryman> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "deliveryman_id", type = IdType.AUTO)
    private Integer deliverymanId;

    private String email;

    private String password;

    private String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "register_date")
    private LocalDate registerDate;

    @TableField(value = "network_id")
    private Integer networkId;

    @TableField(value = "phone_number")
    private String phoneNumber;


    @Override
    protected Serializable pkVal() {
        return this.deliverymanId;
    }

}
