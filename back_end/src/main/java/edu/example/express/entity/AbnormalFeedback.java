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
 * 异常反馈表
 * </p>
 *
 * @author zlh
 * @since 2022-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AbnormalFeedback extends Model<AbnormalFeedback> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "abnormal_feedback_id", type = IdType.AUTO)
    private Integer abnormalFeedbackId;

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
