package edu.example.express.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.SystemAdministratorService;
import edu.example.express.entity.SystemAdministrator;
import org.springframework.web.bind.annotation.RestController;
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





    /**
     * 模块二：对网点进行管理
     */

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
