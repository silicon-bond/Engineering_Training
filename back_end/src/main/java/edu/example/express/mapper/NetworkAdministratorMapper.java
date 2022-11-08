package edu.example.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.example.express.entity.NetworkAdministrator;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

@Resource
@Mapper
public interface NetworkAdministratorMapper extends BaseMapper<NetworkAdministrator> {
}
