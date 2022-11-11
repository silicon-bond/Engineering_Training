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
 * 公告表
 * </p>
 *
 * @author zlh
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Announce extends Model<Announce> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "announce_id", type = IdType.AUTO)
    private Integer announceId;

    private LocalDate publishDate;

    private Integer publisher;

    private String content;

    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.announceId;
    }

}
