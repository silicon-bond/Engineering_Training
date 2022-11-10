package edu.example.express.service.impl;

import edu.example.express.entity.*;
import edu.example.express.mapper.SystemAdministratorMapper;
import edu.example.express.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Resource
    DeliverymanService deliverymanService;

    @Resource
    NetworkAdministratorService networkAdministratorService;

    @Resource
    NetworkService networkService;


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
        int id = deliverymanService.insertDeliveryman(deliveryman);
        return id;
    }

    @Override
    public int deleteDeliverymanById(int id) {
        int result = deliverymanService.deleteDeliverymanById(id);
        return result;
    }

    @Override
    public int updateDeliveryman(Deliveryman deliveryman) {
        int id = deliverymanService.updateDeliveryman(deliveryman);
        return id;
    }

    @Override
    public Deliveryman getDeliverymanById(int id) {
        Deliveryman deliveryman = deliverymanService.getDeliverymanById(id);
        return deliveryman;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByPage(int page, int pageSize, String factor) {
        Page<Deliveryman> deliverymanList = deliverymanService.getDeliverymanByPage(page, pageSize, factor);
        return deliverymanList;
    }


    @Override
    public int addOneNetworkAdministrator(NetworkAdministrator networkAdministrator) {
        int id = networkAdministratorService.insertNetworkAdministrator(networkAdministrator);
        return id;
    }

    @Override
    public int deleteNetworkAdministratorById(int id) {
        networkAdministratorService.deleteNetworkAdministratorById(id);
        return id;
    }

    @Override
    public int updateNetworkAdministrator(NetworkAdministrator networkAdministrator) {
        int id = networkAdministratorService.updateNetworkAdministrator(networkAdministrator);
        return id;
    }

    @Override
    public NetworkAdministrator getNetworkAdministratorById(int id) {
        NetworkAdministrator networkAdministrator = networkAdministratorService.getNetworkAdministratorById(id);
        return networkAdministrator;
    }

    @Override
    public Page<NetworkAdministrator> getNetworkAdministratorByPage(int page, int pageSize, String factor) {
        Page<NetworkAdministrator> networkAdministratorList = networkAdministratorService.getNetworkAdministratorByPage(page, pageSize, factor);
        return networkAdministratorList;
    }


    /**
     * 模块二：对网点进行管理
     */
    @Override
    public int addOneNetwork(Network network) {
        int id = networkService.insertNetwork(network);
        return id;
    }

    @Override
    public int deleteNetworkById(int id) {
        networkService.deleteNetworkById(id);
        return id;
    }

    @Override
    public int updateNetwork(Network network) {
        int id = networkService.updateNetwork(network);
        return id;
    }

    @Override
    public Network getNetworkById(int id) {
        Network network = networkService.getNetworkById(id);
        return network;
    }

    @Override
    public Page<Network> getNetworkByPage(int page, int pageSize, String factor) {
        Page<Network> networkList = networkService.getNetworkByPage(page, pageSize, factor);
        return networkList;
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
