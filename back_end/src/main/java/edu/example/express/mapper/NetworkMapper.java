package edu.example.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.example.express.entity.Network;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NetworkMapper extends BaseMapper<Network> {
}
