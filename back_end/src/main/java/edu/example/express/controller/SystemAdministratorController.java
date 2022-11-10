package edu.example.express.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.SystemAdministratorService;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.Net;

import javax.annotation.Resource;


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

    @PutMapping("/person-management/updateUser")
    public ResultBean UpdateUser(@RequestBody User user){
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

    @PutMapping("/person-management/updateDeliveryman")
    public ResultBean UpdateDeliveryman(@RequestBody Deliveryman deliveryman){
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

    @PutMapping("/person-management/updateNetworkAdministrator")
    public ResultBean UpdateNetworkAdministrator(@RequestBody NetworkAdministrator networkAdministrator){
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

    @PutMapping("/network-management/updateNetwork")
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

    /**
     * 模块三：对物流进行管理
     */



    /**
     * 模块四：异常反馈处理
     */



    /**
     * 模块五：公司资讯管理
     */


    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(systemAdministratorService.listSystemAdministratorsByPage(page, pageSize,factor));
    }

    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(systemAdministratorService.getSystemAdministratorById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody SystemAdministrator systemAdministrator) {
        return new ResultBean<>(systemAdministratorService.insertSystemAdministrator(systemAdministrator));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(systemAdministratorService.deleteSystemAdministratorById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody SystemAdministrator systemAdministrator) {
        return new ResultBean<>(systemAdministratorService.updateSystemAdministrator(systemAdministrator));
    }
}
