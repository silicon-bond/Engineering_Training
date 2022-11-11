package edu.example.express.controller;


import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Express;
import edu.example.express.entity.User;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.DeliverymanService;
import edu.example.express.service.ExpressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/express/api/deliveryman")
public class DeliverymanController {

    @Resource
    private DeliverymanService deliverymanService;

    @Resource
    private ExpressService expressService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(deliverymanService.getDeliverymanByPage(page, pageSize, factor));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id")Integer id){
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

    @RequestMapping(method = RequestMethod.GET, value = "/expressList/{networkId}")
    private ResultBean<?> getExpressListByNetworkId(@RequestParam(name = "page", defaultValue = "1") int page,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                    @PathVariable("networkId")Integer networkId){
        return new ResultBean<>(expressService.getExpressListByNetworkId(networkId, page, pageSize));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orderReceiving/{expressId}/{deliverymanId}")
    private ResultBean<?> orderReceiving(@PathVariable("expressId")Integer expressId,
                                         @PathVariable("deliverymanId")Integer deliverymanId){
        Express express = expressService.getExpressById(expressId);
        if (express != null){
            Integer state = express.getState();
            express.setState(state+1);
            if (state == 0){
                express.setCollectId(deliverymanId);
            } else {
                express.setDeliveryId(deliverymanId);
            }
            return new ResultBean<>(expressService.updateExpress(express));
        } else {
            return new ResultBean<>("物流状态修改失败", "500");
        }
    }
}
