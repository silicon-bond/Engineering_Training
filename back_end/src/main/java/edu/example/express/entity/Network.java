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
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Network extends Model<Network> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "network_id", type = IdType.AUTO)
    private Integer networkId;

    private LocalDate registerDate;

    @TableField(value = "network_name")
    private String networkName;

    @Override
    protected Serializable pkVal() {
        return this.networkId;
    }
}
