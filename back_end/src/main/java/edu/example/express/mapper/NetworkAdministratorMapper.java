package edu.example.express.mapper;

import edu.example.express.entity.NetworkAdministrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 网点管理员 Mapper 接口
* </p>
*
* @author csk
* @since 2022-11-14
*/
@Mapper
@Repository
public interface NetworkAdministratorMapper extends BaseMapper<NetworkAdministrator> {

}
