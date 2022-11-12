package edu.example.express.mapper;

import edu.example.express.entity.VerificationCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
*  Mapper 接口
* </p>
*
* @author lx
* @since 2022-11-12
*/
@Mapper
@Repository
public interface VerificationCodeMapper extends BaseMapper<VerificationCode> {

}
