package edu.example.express.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lx
 * @since 2022-11-12
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("VerificationCode")
public class VerificationCode extends Model<VerificationCode> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "email", type = IdType.AUTO)
    private String email;

    private String code;


    @Override
    protected Serializable pkVal() {
        return this.email;
    }

}
