package edu.example.express.controller;


import edu.example.express.entity.User;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.ExpressService;
import edu.example.express.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author csk
 * @since 2022-11-05
 * @version v1.0
 */
@RestController
@RequestMapping("/express/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private ExpressService expressService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(userService.listUsersByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(userService.getUserById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody User user) {
        return new ResultBean<>(userService.insertUser(user));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(userService.deleteUserById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody User user) {
        return new ResultBean<>(userService.updateUser(user));
    }
}
