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
 * @author csk
 * @since 2022-11-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String email;

    private String password;

    private String username;

    private LocalDate registerDate;

    private String province;

    private String municipal;

    private String country;

    private String street;

    private String detailAddress;

    private String phoneNumber;

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
