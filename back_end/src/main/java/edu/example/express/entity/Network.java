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
 * 网点
 * </p>
 *
 * @author csk
 * @since 2022-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Network extends Model<Network> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "network_id", type = IdType.AUTO)
    private Integer networkId;

    private LocalDate registerDate;

    private String networkName;


    @Override
    protected Serializable pkVal() {
        return this.networkId;
    }

}
