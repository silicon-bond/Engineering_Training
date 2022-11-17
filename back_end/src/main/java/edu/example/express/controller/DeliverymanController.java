package edu.example.express.controller;


import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Express;
import edu.example.express.entity.Network;
import edu.example.express.entity.User;
import edu.example.express.entity.dto.ResultBean;

import edu.example.express.service.DeliverymanService;
import edu.example.express.service.ExpressService;
import edu.example.express.service.NetworkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;

@RestController
@RequestMapping("/express/api/deliveryman")
@CrossOrigin(origins = "*")
public class DeliverymanController {

    @Resource
    private DeliverymanService deliverymanService;

    @Resource
    private ExpressService expressService;

    @Resource
    private NetworkService networkService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(deliverymanService.getDeliverymanByPage(page, pageSize, factor));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResultBean<?> login(@RequestParam("email")String email,
                               @RequestParam("password")String password){
        Deliveryman deliveryman = deliverymanService.getDeliverymanByEmail(email);
        if (deliveryman != null && deliveryman.getPassword().equals(password)){
            return new ResultBean<>("登录成功", "1");
        } else {
            return new ResultBean<>("邮箱或密码错误", "500");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResultBean<?> register(@RequestBody(required=false) Deliveryman deliveryman){
        Deliveryman dm1 = deliverymanService.getDeliverymanByEmail(deliveryman.getEmail());
        Deliveryman dm2 = deliverymanService.getDeliverymanByPhoneNumber(deliveryman.getPhoneNumber());
        if (dm1 != null)
            return new ResultBean<>("该邮箱已被注册", "500");
        else if (dm2 != null)
            return new ResultBean<>("该手机号已被绑定", "500");

        LocalDate localDate = LocalDate.now();
        deliveryman.setRegisterDate(localDate);
        deliveryman.setDeliverymanId(deliverymanService.insertDeliveryman(deliveryman));
        return new ResultBean<>(deliveryman);
    }

    /**
     * 根据id查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(deliverymanService.getDeliverymanById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody Deliveryman deliveryman) {
        return new ResultBean<>(deliverymanService.insertDeliveryman(deliveryman));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(deliverymanService.deleteDeliverymanById(id));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody Deliveryman deliveryman) {
        return new ResultBean<>(deliverymanService.updateDeliveryman(deliveryman));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/expressList")
    private ResultBean<?> getExpressListByNetworkId(@RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                    @RequestParam("networkId")Integer networkId){
        return new ResultBean<>(expressService.getExpressListByNetworkId(networkId, page, pageSize));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orderReceiving")
    private ResultBean<?> orderReceiving(@RequestParam("expressId")Integer expressId,
                                         @RequestParam("deliverymanId")Integer deliverymanId){
        Express express = expressService.getExpressById(expressId);
        if (express != null){
            Integer state = express.getState();
            express.setState(state+1);
            if (state == 0){
                express.setCollectId(deliverymanId);
            } else if (state == 3) {
                express.setDeliveryId(deliverymanId);
            }
            return new ResultBean<>(expressService.updateExpress(express));
        } else {
            return new ResultBean<>("物流状态修改失败", "500");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myExpressList")
    private ResultBean<?> getMyExpressList(@RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                           @RequestParam("deliverymanId")Integer deliverymanId){
        return new ResultBean<>(expressService.getExpressListByDeliverymanId(deliverymanId, page, pageSize));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/expressById")
    private ResultBean<?> getExpressById(@RequestParam(name = "expressId")Integer expressId){
        return new ResultBean<>(expressService.getExpressById(expressId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/completeDelivery")
    private ResultBean<?> completeDelivery(@RequestParam(name = "expressId")Integer expressId){
        Express express = expressService.getExpressById(expressId);
        if (express != null && (express.getState() == 4 || express.getState() == 1)){
            express.setState(express.getState() + 1);
            return new ResultBean<>(expressService.updateExpress(express));
        } else {
            return new ResultBean<>("物流状态修改失败", "500");
        }
    }
}
