package edu.example.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网点管理员
 * </p>
 *
 * @author csk
 * @since 2022-11-14
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

    private Integer networkId;

    private String phoneNumber;

    @TableField(exist = false)
    private String networkName;


    @Override
    protected Serializable pkVal() {
        return this.networkAdministratorId;
    }

}
