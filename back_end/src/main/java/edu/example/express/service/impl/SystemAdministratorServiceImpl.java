package edu.example.express.service.impl;

import edu.example.express.entity.*;
import edu.example.express.mapper.SystemAdministratorMapper;
import edu.example.express.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

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
    UserServiceImpl userService;

    @Resource
    DeliverymanServiceImpl deliverymanService;

    @Resource
    NetworkAdministratorServiceImpl networkAdministratorService;

    @Resource
    NetworkService networkService;

    @Resource
    SystemAdministratorMapper systemAdministratorMapper;


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
        deliverymanList.setRecords(completeListInfo1(deliverymanList.getRecords()));
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
        networkAdministratorList.setRecords(completeListInfo2(networkAdministratorList.getRecords()));
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
        Page<Network> networkList = networkService.listNetworksByPage(page, pageSize, factor);
        return networkList;
    }

    @Override
    public SystemAdministrator getSystemAdministratorByAccount(String account) {
        log.info("正在查询systemAdministrator中account为{}的数据", account);
        QueryWrapper<SystemAdministrator> queryWrapper =  new QueryWrapper<SystemAdministrator>().eq("account", account);
        SystemAdministrator systemAdministrator = super.getOne(queryWrapper);
        log.info("查询account为{}的systemAdministrator{}",account,(null == systemAdministrator?"无结果":"成功"));
        return systemAdministrator;
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


    @Override
    public int deleteByIdAndRole(int id, int role) {
        log.info("要删除的角色{}，id：{}",role,id);
        if (role == 1){
            userService.deleteUserById(id);
            return id;
        }
        if (role == 2){
            deliverymanService.deleteDeliverymanById(id);
            return id;
        }
        if (role == 3){
            networkAdministratorService.deleteNetworkAdministratorById(id);
            return id;
        }
        return -1;
    }

    @Override
    public <T> Page<T> getPeopleByIdAndRole(int id, int role,Class<T> tClass, int page, int pageSize, String factor) {
        log.info("要获取的角色{}，id：{}",role,id);
        if (role == 1){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if (id != -1){
                queryWrapper.eq("user_id",id);
            }
            Page<User> userPage = userService.getUserListByQuerymapper(page,pageSize,factor,queryWrapper);
            return (Page<T>) userPage;
        }
        if (role == 2){
            QueryWrapper<Deliveryman> queryWrapper = new QueryWrapper<>();
            if (id != -1){
                queryWrapper.eq("deliveryman_id",id);
            }
            Page<Deliveryman> deliverymanPage = deliverymanService.page(new Page<>(page,pageSize),queryWrapper);
            deliverymanPage.setRecords(completeListInfo1(deliverymanPage.getRecords()));
            return (Page<T>) deliverymanPage;
        }
        if (role == 3){
            QueryWrapper<NetworkAdministrator> queryWrapper = new QueryWrapper<>();
            if (id != -1){
                queryWrapper.eq("network_administrator_id",id);
            }
            Page<NetworkAdministrator> networkAdministratorPage = networkAdministratorService.page(new Page<>(page,pageSize),queryWrapper);
            networkAdministratorPage.setRecords(completeListInfo2(networkAdministratorPage.getRecords()));
            return (Page<T>) networkAdministratorPage;
        }
        return null;
    }



    private Deliveryman completeInfo1(Deliveryman entity){
        Network network = networkService.getNetworkById(entity.getNetworkId());
        entity.setNetwork(network);
        return entity;
    }

    private List<Deliveryman> completeListInfo1(List<Deliveryman> entityList){
        List<Deliveryman> newList = new ArrayList<>();
        for (Deliveryman entity : entityList){
            newList.add(completeInfo1(entity));
        }
        return newList;
    }

    private NetworkAdministrator completeInfo2(NetworkAdministrator entity){
        Network network = networkService.getNetworkById(entity.getNetworkId());
        entity.setNetwork(network);
        return entity;
    }

    private List<NetworkAdministrator> completeListInfo2(List<NetworkAdministrator> entityList){
        List<NetworkAdministrator> newList = new ArrayList<>();
        for (NetworkAdministrator entity : entityList){
            newList.add(completeInfo2(entity));
        }
        return newList;
    }



//    private <T> T completeInfo(T entity){
//        Network network = networkService.getNetworkById(entity.getNetworkId());
//        entity.setNetworkName(network.getNetworkName());
//        return entity;
//    }
//
//    private <T> List<T> completeListInfo(List<T> entityList){
//        List<T> newList = new ArrayList<T>();
//        for (T entity : entityList){
//            newList.add(completeInfo(entity));
//        }
//        return newList;
//    }


    @Override
    public Boolean ifUsedPhoneNumber(String phoneNumber) {
        List<String> phone1 = systemAdministratorMapper.getUserPhoneNumber();
        List<String> phone2 = systemAdministratorMapper.getDeliverymanPhoneNumber();
        List<String> phone3 = systemAdministratorMapper.getNetworkAdministratorPhoneNumber();
        if (phone1.contains(phoneNumber)){
            return true;
        }
        if (phone2.contains(phoneNumber)){
            return true;
        }
        if (phone3.contains(phoneNumber)){
            return true;
        }
        return false;
    }
}
