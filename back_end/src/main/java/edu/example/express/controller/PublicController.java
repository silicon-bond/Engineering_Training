package edu.example.express.controller;

import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.SystemAdministrator;
import edu.example.express.entity.User;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.DeliverymanService;
import edu.example.express.service.SystemAdministratorService;
import edu.example.express.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/express/api")
@CrossOrigin(origins = "*")
public class PublicController {

    @Resource
    private UserService userService;

    @Resource
    private DeliverymanService deliverymanService;

    @Resource
    private SystemAdministratorService systemAdministratorService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResultBean login(@RequestBody Map<String, Object> map){
        String email = (String) map.get("email");
        String password = (String) map.get("password"); 
        User user = userService.getUserByEmail(email);
        Deliveryman deliveryman = deliverymanService.getDeliverymanByEmail(email);
        SystemAdministrator systemAdministrator = systemAdministratorService.getSystemAdministratorByAccount(email);
        if (user != null && user.getPassword().equals(password)){
            return new ResultBean<>(user);
        } else if (deliveryman != null && deliveryman.getPassword().equals(password)){
            return new ResultBean<>(deliveryman);
        } else if(systemAdministrator != null && systemAdministrator.getPassword().equals(password)){
            return new ResultBean<>(systemAdministrator);
        } else {
            return new ResultBean<>("邮箱/账号或密码错误", "500");
        }
    }

}
