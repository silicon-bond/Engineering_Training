package edu.example.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class NetworkAdministrator extends Model<NetworkAdministrator> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "network_administrator_id", type = IdType.AUTO)
    private Integer networkAdministratorId;

    private String account;

    private String password;

    private String nickname;

    private Integer outlet;

    @TableId(value = "phone_number")
    private String phoneNumber;


    @Override
    protected Serializable pkVal() {
        return this.networkAdministratorId;
    }

}
