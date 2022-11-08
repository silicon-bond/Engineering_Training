package edu.example.express.service.impl;

import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.SystemAdministrator;
import edu.example.express.entity.User;
import edu.example.express.mapper.SystemAdministratorMapper;
import edu.example.express.service.SystemAdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.example.express.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

import javax.annotation.Resource;

/**
* <p>
* 系统远离员 服务实现类
* </p>
*
* @author csk
* @since 2022-11-05
*/
@Slf4j
@Service
public class SystemAdministratorServiceImpl extends ServiceImpl<SystemAdministratorMapper, SystemAdministrator> implements SystemAdministratorService {

    @Resource
    UserService userService;

    /**
     * 模块一：对用户，快递员，网点管理员的增删改查
     */

    @Override
    public int addOneUser(User user) {
        int result = userService.insertUser(user);
        return result;
    }

    @Override
    public int deleteUserById(int id) {
        int result = userService.deleteUserById(id);
        return result;
    }

    @Override
    public int updateUser(User user) {
        int result = userService.updateUser(user);
        return result;
    }

    @Override
    public User getUserById(int id) {
        User user = userService.getUserById(id);
        return user;
    }

    @Override
    public Page<User> getUserByPage(int page, int pageSize, String factor) {
        Page<User> userList = userService.listUsersByPage(page, pageSize, factor);
        return userList;
    }

    @Override
    public int addOneDeliveryman(Deliveryman deliveryman) {
        return 0;
    }

    @Override
    public int deleteDeliverymanById(int id) {
        return 0;
    }

    @Override
    public int updateDeliverymanById(int id) {
        return 0;
    }

    @Override
    public Deliveryman getDeliverymanById(int id) {
        return null;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByPage() {
        return null;
    }

    @Override
    public int addOneNetworkAdministrator(int id) {
        return 0;
    }

    @Override
    public int deleteNetworkAdministratorById(int id) {
        return 0;
    }

    @Override
    public int updateNetworkAdministratorById(int id) {
        return 0;
    }

    @Override
    public Deliveryman getNetworkAdministratorById(int id) {
        return null;
    }

    @Override
    public Page<Deliveryman> getNetworkAdministratorByPage() {
        return null;
    }

    @Override
    public Page<SystemAdministrator> listSystemAdministratorsByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询systemAdministrator: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<SystemAdministrator> queryWrapper =  new QueryWrapper<SystemAdministrator>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SystemAdministrator> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询systemAdministrator完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public SystemAdministrator getSystemAdministratorById(int id) {
        log.info("正在查询systemAdministrator中id为{}的数据",id);
        SystemAdministrator systemAdministrator = super.getById(id);
        log.info("查询id为{}的systemAdministrator{}",id,(null == systemAdministrator?"无结果":"成功"));
        return systemAdministrator;
    }

    @Override
    public int insertSystemAdministrator(SystemAdministrator systemAdministrator) {
        log.info("正在插入systemAdministrator");
        if (super.save(systemAdministrator)) {
            log.info("插入systemAdministrator成功,id为{}",systemAdministrator.getSystemAdministratorId());
            return systemAdministrator.getSystemAdministratorId();
        } else {
            log.error("插入systemAdministrator失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteSystemAdministratorById(int id) {
        log.info("正在删除id为{}的systemAdministrator",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的systemAdministrator成功",id);
            return id;
        } else {
            log.error("删除id为{}的systemAdministrator失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateSystemAdministrator(SystemAdministrator systemAdministrator) {
        log.info("正在更新id为{}的systemAdministrator",systemAdministrator.getSystemAdministratorId());
        if (super.updateById(systemAdministrator)) {
            log.info("更新d为{}的systemAdministrator成功",systemAdministrator.getSystemAdministratorId());
            return systemAdministrator.getSystemAdministratorId();
        } else {
            log.error("更新id为{}的systemAdministrator失败",systemAdministrator.getSystemAdministratorId());
            throw new BizException("更新失败[id=" + systemAdministrator.getSystemAdministratorId() + "]");
        }
    }

}
