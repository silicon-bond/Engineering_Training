package edu.example.express.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Announce extends Model<Announce> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "announce_id",type = IdType.AUTO)
    private Integer announceId;

    @TableId(value = "publish_date")
    private LocalDate publishDate;

    private Integer publisher;

    private String content;

    private Integer state;

    @Override
    protected Serializable pkVal() {
        return this.announceId;
    }


}






