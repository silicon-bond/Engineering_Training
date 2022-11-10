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
public class AbnormalFeedback extends Model<AbnormalFeedback> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "abnormal_feedback_id",type = IdType.AUTO)
    private Integer abnormalFeedbackId;

    @TableId(value = "feedback_date")
    private LocalDate feedbackDate;

    private Integer provider;

    private Integer type;

    private Integer state;

    private Integer principal;

    private String description;

    @Override
    protected Serializable pkVal() {
        return this.abnormalFeedbackId;
    }
}
