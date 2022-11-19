package edu.example.express.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.*;
import edu.example.express.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.SystemAdministratorService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 系统远离员 前端控制器
 * </p>
 *
 * @author csk
 * @since 2022-11-05
 * @version v1.0
 */
@RestController
@RequestMapping("/express/api/system-administrator")
@CrossOrigin(origins = "*")
public class SystemAdministratorController {

    @Resource
    private SystemAdministratorService systemAdministratorService;

    /**
     * 模块一：对用户，快递员，网点管理员的增删改查
     */

    /**
     *  管理用户模块
     */
    @PostMapping("/person-management/addOneUser")
    public ResultBean addOneUser(@RequestBody User user){
        if (user.getPhoneNumber() != null){
            if (systemAdministratorService.ifUsedPhoneNumber(user.getPhoneNumber())){
                return new ResultBean("电话已经被使用","403");
            }
        }
        int result =  systemAdministratorService.addOneUser(user);
        ResultBean resultBean = new ResultBean("用户增加成功","",result);
        return resultBean;
    }

    @DeleteMapping("/person-management/deleteUserById/{id}")
    public ResultBean DeleteUserById(@PathVariable("id") int id){
        int result =  systemAdministratorService.deleteUserById(id);
        ResultBean resultBean = new ResultBean("用户删除成功","",result);
        return resultBean;
    }

    @PutMapping("/person-management/update/1")
    public ResultBean UpdateUser(@RequestBody User user){
        String system_phone = systemAdministratorService.getPhoneNumberByRoleAndId(1,user.getUserId());
        if (user.getPhoneNumber() != null && !system_phone.equals(user.getPhoneNumber())){
            if (systemAdministratorService.ifUsedPhoneNumber(user.getPhoneNumber())){
                return new ResultBean("电话已经被使用","403");
            }
        }
        int result = systemAdministratorService.updateUser(user);
        ResultBean resultBean = new ResultBean("用户修改成功","",result);
        return resultBean;
    }

    @GetMapping("/person-management/getUserById/{id}")
    public ResultBean GetUserById(@PathVariable("id") Integer id){
        User user = systemAdministratorService.getUserById(id);
        ResultBean resultBean = new ResultBean("返回单个用户成功","",user);
        return resultBean;
    }

    @GetMapping("/person-management/getUserByPage")
    public ResultBean getUserByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor){
        Page<User> userList = systemAdministratorService.getUserByPage(page, pageSize, factor);
        ResultBean resultBean = new ResultBean("分页查询用户成功","",userList);
        return resultBean;
    }

    /**
     * 管理快递员模块
     */
    @PostMapping("/person-management/addOneDeliveryman")
    public ResultBean addOneDeliveryman(@RequestBody Deliveryman deliveryman){
        if (deliveryman.getPhoneNumber() != null){
            if (systemAdministratorService.ifUsedPhoneNumber(deliveryman.getPhoneNumber())){
                return new ResultBean("电话已经被使用","403");
            }
        }
        int result =  systemAdministratorService.addOneDeliveryman(deliveryman);
        ResultBean resultBean = new ResultBean("快递员增加成功","",result);
        return resultBean;
    }

    @DeleteMapping("/person-management/deleteDeliverymanById/{id}")
    public ResultBean DeleteDeliverymanById(@PathVariable("id") int id){
        int result =  systemAdministratorService.deleteDeliverymanById(id);
        ResultBean resultBean = new ResultBean("快递员删除成功","",result);
        return resultBean;
    }

    @PutMapping("/person-management/update/2")
    public ResultBean UpdateDeliveryman(@RequestBody Deliveryman deliveryman){
        String system_phone = systemAdministratorService.getPhoneNumberByRoleAndId(2,deliveryman.getDeliverymanId());
        System.out.println(system_phone);
        System.out.println(deliveryman.getPhoneNumber());
        if (deliveryman.getPhoneNumber() != null && !system_phone.equals(deliveryman.getPhoneNumber())){
            if (systemAdministratorService.ifUsedPhoneNumber(deliveryman.getPhoneNumber())){
                return new ResultBean("电话已经被使用","403");
            }
        }
        int result = systemAdministratorService.updateDeliveryman(deliveryman);
        ResultBean resultBean = new ResultBean("快递员修改成功","",result);
        return resultBean;
    }

    @GetMapping("/person-management/getDeliverymanById/{id}")
    public ResultBean GetDeliverymanById(@PathVariable("id") Integer id){
        Deliveryman deliveryman = systemAdministratorService.getDeliverymanById(id);
        ResultBean resultBean = new ResultBean("返回单个快递员成功","",deliveryman);
        return resultBean;
    }

    @GetMapping("/person-management/getDeliverymanByPage")
    public ResultBean getDeliverymanByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor){
        Page<Deliveryman> deliverymanList = systemAdministratorService.getDeliverymanByPage(page, pageSize, factor);
        ResultBean resultBean = new ResultBean("分页查询快递员成功","",deliverymanList);
        return resultBean;
    }

    /**
     * 管理网点管理员模块
     */
    @PostMapping("/person-management/addOneNetworkAdministrator")
    public ResultBean addOneNetworkAdministrator(@RequestBody NetworkAdministrator networkAdministrator){
        if (networkAdministrator.getPhoneNumber() != null){
            if (systemAdministratorService.ifUsedPhoneNumber(networkAdministrator.getPhoneNumber())){
                return new ResultBean("电话已经被使用","403");
            }
        }
        int result =  systemAdministratorService.addOneNetworkAdministrator(networkAdministrator);
        ResultBean resultBean = new ResultBean("网点管理员增加成功","",result);
        return resultBean;
    }

    @DeleteMapping("/person-management/deleteNetworkAdministratorById/{id}")
    public ResultBean DeleteNetworkAdministratorById(@PathVariable("id") int id){
        int result =  systemAdministratorService.deleteNetworkAdministratorById(id);
        ResultBean resultBean = new ResultBean("网点管理员删除成功","",result);
        return resultBean;
    }

    @PutMapping("/person-management/update/3")
    public ResultBean UpdateNetworkAdministrator(@RequestBody NetworkAdministrator networkAdministrator){
        String system_phone = systemAdministratorService.getPhoneNumberByRoleAndId(3,networkAdministrator.getNetworkAdministratorId());
        System.out.println(system_phone);
        if (networkAdministrator.getPhoneNumber() != null && !system_phone.equals(networkAdministrator.getPhoneNumber())){
            if (systemAdministratorService.ifUsedPhoneNumber(networkAdministrator.getPhoneNumber())){
                return new ResultBean("电话已经被使用","403");
            }
        }
        int result = systemAdministratorService.updateNetworkAdministrator(networkAdministrator);
        ResultBean resultBean = new ResultBean("网点管理员修改成功","",result);
        return resultBean;
    }

    @GetMapping("/person-management/getNetworkAdministratorById/{id}")
    public ResultBean GetNetworkAdministratorById(@PathVariable("id") Integer id){
        NetworkAdministrator networkAdministrator = systemAdministratorService.getNetworkAdministratorById(id);
        ResultBean resultBean = new ResultBean("返回单个网点管理员成功","",networkAdministrator);
        return resultBean;
    }

    @GetMapping("/person-management/getNetworkAdministratorByPage")
    public ResultBean getNetworkAdministratorByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                           @RequestParam(name = "factor", defaultValue = "") String factor){
        Page<NetworkAdministrator> networkAdministratorList = systemAdministratorService.getNetworkAdministratorByPage(page, pageSize, factor);
        ResultBean resultBean = new ResultBean("分页查询网点管理员成功","",networkAdministratorList);
        return resultBean;
    }


    /**
     * 模块二：对网点进行管理
     */
    @PostMapping("/network-management/addOneNetwork")
    public ResultBean<Network> addOneNetwork(@RequestBody Network network){
        LocalDate localDate = LocalDate.now();
        network.setRegisterDate(localDate);
        int result = systemAdministratorService.addOneNetwork(network);
        ResultBean resultBean = new ResultBean("网点增加成功","",result);
        return resultBean;
    }

    @DeleteMapping("/network-management/deleteNetworkById/{id}")
    public ResultBean DeleteNetworkById(@PathVariable("id") int id){
        int result =  systemAdministratorService.deleteNetworkById(id);
        ResultBean resultBean = new ResultBean("网点删除成功","",result);
        return resultBean;
    }

    @PutMapping("/network-management/updateNetworkById")
    public ResultBean UpdateNetwork(@RequestBody Network network){
        int result = systemAdministratorService.updateNetwork(network);
        ResultBean resultBean = new ResultBean("网点修改成功","",result);
        return resultBean;
    }

    @GetMapping("/network-management/getNetworkById/{id}")
    public ResultBean GetNetworkById(@PathVariable("id") Integer id){
        Network network = systemAdministratorService.getNetworkById(id);
        ResultBean resultBean = new ResultBean("返回单个网点成功","",network);
        return resultBean;
    }

    @GetMapping("/network-management/getNetworkByPage")
    public ResultBean getNetworkByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                    @RequestParam(name = "factor", defaultValue = "") String factor){
        Page<Network> networkList = systemAdministratorService.getNetworkByPage(page, pageSize, factor);
        ResultBean resultBean = new ResultBean("分页查询网点成功","",networkList);
        return resultBean;
    }





    @DeleteMapping("/deleteByIdAndRole")
    public ResultBean deteleByIdAndRole(@RequestParam(name = "id", defaultValue = "-1") int id,
                                        @RequestParam(name = "role", defaultValue = "-1") int role){
        ResultBean resultBean = new ResultBean(systemAdministratorService.deleteByIdAndRole(id,role));
        return resultBean;
    }

    @GetMapping("/getPeopleByIdAndRole")
    public ResultBean getPeopleByIdAndRole(@RequestParam(name = "id", defaultValue = "-1") int id,
                                        @RequestParam(name = "role", defaultValue = "-1") int role,
                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                           @RequestParam(name = "factor", defaultValue = "") String factor){
        if (role == 1){
            Class<User> userClass = null;
            Page<User> userList = systemAdministratorService.getPeopleByIdAndRole(id,role,userClass,page,pageSize,factor);
            return new ResultBean(userList);
        }
        if (role == 2){
            Class<Deliveryman> deliverymanClass = null;
            Page<Deliveryman> deliverymanList = systemAdministratorService.getPeopleByIdAndRole(id,role,deliverymanClass,page,pageSize,factor);
            return new ResultBean(deliverymanList);
        }
        if (role == 3){
            Class<NetworkAdministrator> networkAdministratorClass = null;
            Page<NetworkAdministrator> networkAdministratorList = systemAdministratorService.getPeopleByIdAndRole(id,role,networkAdministratorClass,page,pageSize,factor);
            return new ResultBean(networkAdministratorList);
        }
        return null;
    }

    @GetMapping("/getAllPeople")
    public ResultBean getAllPeople(@RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                   @RequestParam(name = "factor", defaultValue = "") String factor){
        List list = new ArrayList();
        list.add(systemAdministratorService.getUserByPage(page,pageSize,factor));
        list.add(systemAdministratorService.getDeliverymanByPage(page,pageSize,factor));
        list.add(systemAdministratorService.getNetworkAdministratorByPage(page,pageSize,factor));

        return new ResultBean(list);
    }


//    public static void main(String[] args) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);
//
//    }
}



