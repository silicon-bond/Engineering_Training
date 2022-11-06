package edu.example.express.mapper;

import edu.example.express.entity.SystemAdministrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 系统远离员 Mapper 接口
* </p>
*
* @author csk
* @since 2022-11-05
*/
@Mapper
@Repository
public interface SystemAdministratorMapper extends BaseMapper<SystemAdministrator> {

}
