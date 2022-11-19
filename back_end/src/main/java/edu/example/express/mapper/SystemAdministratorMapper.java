package edu.example.express.mapper;

import edu.example.express.entity.SystemAdministrator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


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
    @Select("select phone_number from user")
    public List<String> getUserPhoneNumber();

    @Select("select phone_number from deliveryman")
    public List<String> getDeliverymanPhoneNumber();

    @Select("select phone_number from network_administrator")
    public List<String> getNetworkAdministratorPhoneNumber();

    @Select("select phone_number from ${table} where ${role} = #{id}")
    public String getPhoneByRoleAndId(String table,String role,int id);
}
