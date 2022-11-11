package edu.example.express.mapper;

import edu.example.express.entity.AbnormalFeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 异常反馈表 Mapper 接口
* </p>
*
* @author zlh
* @since 2022-11-11
*/
@Mapper
@Repository
public interface AbnormalFeedbackMapper extends BaseMapper<AbnormalFeedback> {

}
