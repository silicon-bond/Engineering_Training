package edu.example.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统远离员
 * </p>
 *
 * @author csk
 * @since 2022-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemAdministrator extends Model<SystemAdministrator> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "system_administrator_id", type = IdType.AUTO)
    private Integer systemAdministratorId;

    private String account;

    private String password;

    private String nickname;


    @Override
    protected Serializable pkVal() {
        return this.systemAdministratorId;
    }

}
