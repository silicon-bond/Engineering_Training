package edu.example.express.mapper;

import edu.example.express.entity.Network;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 网点 Mapper 接口
* </p>
*
* @author csk
* @since 2022-11-16
*/
@Mapper
@Repository
public interface NetworkMapper extends BaseMapper<Network> {

}
