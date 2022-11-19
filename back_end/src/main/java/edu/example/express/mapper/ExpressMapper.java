package edu.example.express.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.example.express.entity.Express;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
* <p>
* 快递单 Mapper 接口
* </p>
*
* @author zxy
* @since 2022-11-11
*/
@Mapper
@Repository
public interface ExpressMapper extends BaseMapper<Express> {
    @Select("select state_name from express_state where state_id = #{state_id}")
    String getExpressStateNameById(Integer state_id);

    @Select("SELECT express.*,network.network_name from express left join network on express.network_id = network.network_id where express.express_id = #{expressId};")
    List<Express> getExpressAndNetworkName(int expressId);


}
