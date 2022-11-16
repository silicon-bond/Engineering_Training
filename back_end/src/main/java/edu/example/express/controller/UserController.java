package edu.example.express.controller;


import edu.example.express.entity.Express;
import edu.example.express.entity.User;
import edu.example.express.entity.VerificationCode;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.ExpressService;
import edu.example.express.service.NetworkService;
import edu.example.express.service.UserService;
import edu.example.express.service.VerificationCodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;


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

    @Resource
    private NetworkService networkService;

    private VerificationCodeService verificationCodeService;

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

    @GetMapping("/getExpress/ByReceiptPhoneNum")
    public ResultBean<?> getExpressBygetExpressListByReceiptPhoneNum(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                     @RequestParam(name = "networkId", defaultValue = "") int NetworkId,
                                                                     @RequestParam(name = "ReceiptPhoneNumberr", defaultValue = "") String ReceiptPhoneNumberr) {
        return new ResultBean<>(expressService.getExpressListByReceiptPhoneNum(ReceiptPhoneNumberr, page, pageSize));
    }

    @GetMapping("/getExpress/ByDeliverPhoneNum")
    public ResultBean<?> getExpressBygetExpressListByReceiptPhoneNum(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                     @RequestParam(name = "DeliverPhoneNum",defaultValue = "") String DeliverPhoneNum) {
        return new ResultBean<>(expressService.getExpressListByReceiptPhoneNum(DeliverPhoneNum, page, pageSize));
    }

    @GetMapping("/getNetWorkByName")
    public ResultBean<?> getNetWorkByFactor(@RequestParam(name = "page", defaultValue = "1") int page,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(name = "factor",defaultValue = "10") String factor){

        return new ResultBean<>(networkService.listNetworksByPage(page,pageSize,factor));
    }

    @PostMapping("/addExpress")
    public ResultBean<?> addExpress(@RequestParam(name = "express")Express express){
        ResultBean<Object> result = new ResultBean<>();
        int flag = expressService.insertExpress(express);
        if(flag > 0)
            result.setMessage("添加成功");

        return result;
    }

    @PostMapping("/deleteExpress")
    public ResultBean<?> deleteExpress(@RequestParam(name = "id")int id){
        ResultBean<Object> result = new ResultBean<>();
        int flag = expressService.deleteExpressById(id);
        if(flag > 0)
            result.setMessage("删除成功");

        return result;
    }

    @PostMapping("/Register")
    public  ResultBean<?> Register(@RequestParam(name = "nikename")String name,
                                   @RequestParam(name = "email")String email,
                                   @RequestParam(name = "password")String password,
                                   @RequestParam(name = "captcha")String captche
                                   ){
        ResultBean<Object> result = new ResultBean<>();
        VerificationCode code = new VerificationCode();
        code.setEmail(email);
        code.setCode(captche);
        Boolean flag = verificationCodeService.IsVerificationCode(code);
        if(!flag){
            result.setMessage("验证码错误");
            return result;
        }
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);


        if(userService.insertUser(user)>0){
            result.setMessage("注册成功");
        }

        return  result;

    }

    @PostMapping("/Forgetpassword")
    public  ResultBean<?> Forgetpassword(@RequestParam(name = "email")String email,
                                   @RequestParam(name = "password")String password,
                                   @RequestParam(name = "captcha")String captche
                                                                                ){
        ResultBean<Object> result = new ResultBean<>();
        VerificationCode code = new VerificationCode();
        code.setCode(captche);
        code.setEmail(email);
        Boolean flag = verificationCodeService.IsVerificationCode(code);
        if(!flag){
            result.setMessage("验证码错误");
            return result;
        }
        User user = userService.getUserByEmail(email);

        user.setPassword(password);

        if(userService.updateUser(user)>0){
            result.setMessage("修改成功");
        }

        return  result;

    }




}
