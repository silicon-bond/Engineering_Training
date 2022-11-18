package edu.example.express.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.*;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


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
@RequestMapping("/express/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private ExpressService expressService;

    @Resource
    private NetworkService networkService;

    @Resource
    private VerificationCodeService verificationCodeService;


    @Resource
    private AbnormalFeedbackService abnormalFeedbackService;

    @Resource
    private DeliverymanService deliverymanService;



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
                                                                     @RequestParam(name = "ReceiptPhoneNumberr", defaultValue = "") String ReceiptPhoneNumberr) {
        return new ResultBean<>(expressService.getExpressListByReceiptPhoneNum(ReceiptPhoneNumberr, page, pageSize));
    }

    @GetMapping("/getExpress/ByDeliverPhoneNum")
    public ResultBean<?> getExpressBygetExpressListByDeliverPhoneNum(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                     @RequestParam(name = "DeliverPhoneNum",defaultValue = "") String DeliverPhoneNum) {
        return new ResultBean<>(expressService.getExpressListByReceiptPhoneNum(DeliverPhoneNum, page, pageSize));
    }

    @GetMapping("/getNetWorkByName")
    public ResultBean<?> getNetWorkByFactor(@RequestParam(name = "page", defaultValue = "1") int page,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(name = "factor", defaultValue = "") String factor){

        return new ResultBean<>(networkService.getNetworkByPage(page,pageSize,factor));
    }

    @PostMapping("/addExpress")
    public ResultBean<?> addExpress(@RequestBody Express express){
        ResultBean<Object> result = new ResultBean<>();
        LocalDateTime orderTime = LocalDateTime.now().plus(60*60*8, ChronoUnit.SECONDS);;
        express.setOrderDate(orderTime);
        express.setState(0);//状态置为未揽件
        int flag = expressService.insertExpress(express);
        if(flag > 0)
            result.setMessage("添加成功");

        return result;
    }

    @GetMapping("/deleteExpress")
    public ResultBean<?> deleteExpress(@RequestParam(name = "id")int id){
        ResultBean<Object> result = new ResultBean<>();
        int flag = expressService.deleteExpressById(id);
        if(flag > 0)
            result.setMessage("删除成功");

        return result;
    }

    @PostMapping("/Register")
    public  ResultBean<?> Register(@RequestBody RegisterUserBean userBean
                                   ){
        ResultBean<Object> result = new ResultBean<>();
        VerificationCode code = new VerificationCode();
        code.setEmail(userBean.getEmail());
        code.setCode(userBean.getCaptcha());
        User user = new User();
        User userTemp = userService.getUserByEmail(userBean.getEmail());
        if(userTemp != null){
            result.setMessage("用户已注册");
            return result;
        }
        Boolean flag = verificationCodeService.IsVerificationCode(code);
        if(!flag){
            result.setMessage("验证码错误");
            return result;
        }
        user.setPhoneNumber(userBean.getPhoneNum());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());


        if(userService.insertUser(user)>0){
            result.setMessage("注册成功");
        }

        return  result;

    }

    @PostMapping("/Forgetpassword")
    public  ResultBean<?> Forgetpassword(@RequestBody RegisterUserBean userBean
    ){
        ResultBean<Object> result = new ResultBean<>();
        VerificationCode code = new VerificationCode();
        code.setCode(userBean.getCaptcha());
        code.setEmail(userBean.getEmail());
        Boolean flag = verificationCodeService.IsVerificationCode(code);
        if(!flag){
            result.setMessage("验证码错误");
            return result;
        }
        User user = userService.getUserByEmail(userBean.getEmail());
        Deliveryman deliveryman = deliverymanService.getDeliverymanByEmail(userBean.getEmail());

        if(user != null)
            user.setPassword(userBean.getPassword());

        if(deliveryman != null)
            deliveryman.setPassword(userBean.getPassword());


        if(userService.updateUser(user)>0){
            result.setMessage("修改成功");
        }

        if(deliverymanService.updateDeliveryman(deliveryman)>0){
            result.setMessage("修改成功");
        }

        return  result;

    }

    @GetMapping("/getExpressByStateAndID")
    public ResultBean<?> getExpressByStateAndID(@RequestParam(name = "page", defaultValue = "1") int page,
                                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                @RequestParam( name = "state",defaultValue = "-1") int state,
                                                @RequestParam( name = "id",defaultValue = "-1") int id,
                                                @RequestParam(name="phoneNum",defaultValue = "-1")String phoneNum
    ){
        Page<Express> expressListByStateAndId = expressService.getExpressListByStateAndId(page, pageSize, state, id, phoneNum);
        ResultBean<Object> result = new ResultBean<>(expressListByStateAndId);
        return result;

    }


    @GetMapping("/FeedBack")
    public ResultBean<?> deliverFeedback(@RequestParam(name = "title") String title,
                                         @RequestParam(name = "description") String description,
                                         @RequestParam( name = "phoneNum") String phoneNum ,
                                        @RequestParam( name = "networkId") int networkId){
        AbnormalFeedback feedback = new AbnormalFeedback();
        LocalDate localDate = LocalDate.now();
        feedback.setFeedbackDate(localDate);
        feedback.setTitle(title);
        feedback.setDescription(description);
        feedback.setProviderPhoneNumber(phoneNum);
        feedback.setNetworkId(networkId);

        int i = abnormalFeedbackService.insertAbnormalFeedback(feedback);

        ResultBean<Object> result = new ResultBean<>();

        if(i > 0){
            result.setMessage("反馈成功");
            return result;
        }
        result.setMessage("反馈失败");
        return result;
    }




}
