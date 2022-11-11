package edu.example.express.mapper;

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
}
