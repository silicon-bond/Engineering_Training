package edu.example.express.controller;


import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Express;
import edu.example.express.service.AbnormalFeedbackService;
import edu.example.express.service.DeliverymanService;
import edu.example.express.service.ExpressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.NetworkAdministratorService;
import edu.example.express.entity.NetworkAdministrator;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.time.LocalDate;


/**
 * <p>
 * 网点管理员 前端控制器
 * </p>
 *
 * @author csk
 * @since 2022-11-14
 * @version v1.0
 */
@Slf4j
@RestController
@RequestMapping("/express/api/network-administrator")
@CrossOrigin(origins = "*")
public class NetworkAdministratorController {

    @Resource
    private NetworkAdministratorService networkAdministratorService;

    @Resource
    private ExpressService expressService;

    @Resource
    private DeliverymanService deliverymanService;

    @Resource
    private AbnormalFeedbackService abnormalFeedbackService;

    /**
     * 查询给定Id网点的反馈信息列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/network_id/state")
    public ResultBean<?> listByNetworkIdAndState(@RequestParam(name = "page", defaultValue = "1") int page,
                                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(name = "network_id", defaultValue = "") int networkId,
                                            @RequestParam(name = "state", defaultValue = "") int state) {
        return new ResultBean<>(abnormalFeedbackService.getAbnormalFeedbacksListByNetworkIdAndState(page, pageSize, networkId, state));
    }

    /**
     * 查询给定Id网点的反馈信息列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/network_id")
    public ResultBean<?> listByNetworkId(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "network_id", defaultValue = "") int networkId) {
        return new ResultBean<>(abnormalFeedbackService.getAbnormalFeedbacksListByNetworkId(page, pageSize, networkId));
    }


    /**
     * 给快递员派单
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{expressId}/{deliveryId}")
    public ResultBean<?> updateDeliveryIdInExpress(@PathVariable("expressId") Integer expressId,
                                                   @PathVariable("deliveryId") Integer deliveryId) {
        return new ResultBean<>(networkAdministratorService.updateDeliverymanInExpress(expressId, deliveryId));
    }

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(networkAdministratorService.getNetworkAdministratorByPage(page, pageSize,factor));
    }

    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(networkAdministratorService.getNetworkAdministratorById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody NetworkAdministrator networkAdministrator) {
        return new ResultBean<>(networkAdministratorService.insertNetworkAdministrator(networkAdministrator));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(networkAdministratorService.deleteNetworkAdministratorById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody NetworkAdministrator networkAdministrator) {
        return new ResultBean<>(networkAdministratorService.updateNetworkAdministrator(networkAdministrator));
    }

    /**
     * 根据网点id查询快递
     */
    @RequestMapping(method = RequestMethod.GET, path = "/getExpress")
    public ResultBean<?> getExpressByNetworkId(@RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                               @RequestParam(name = "networkId", defaultValue = "") int NetworkId,
                                               @RequestParam(name = "dateStart", defaultValue = "2000-01-01") LocalDate dateStart,
                                               @RequestParam(name = "dateOver", defaultValue = "2099-12-31") LocalDate dateOver) {
        return new ResultBean<>(expressService.getExpressListByNetworkIdAndDate(NetworkId, page, pageSize, dateStart, dateOver));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getExpressByStateAndTime")
    public ResultBean<?> getExpressByNetworkId(@RequestParam(name = "page", defaultValue = "1") int page,
                                               @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                               @RequestParam(name = "state", defaultValue = "110") int state,
                                               @RequestParam(name = "networkId") int networkId,
                                               @RequestParam(name = "dateStart", defaultValue = "2000-01-01") LocalDate dateStart,
                                               @RequestParam(name = "dateOver", defaultValue = "2099-12-31") LocalDate dateOver) {
        log.info("正在查询express中state为{}的数据", state);
//        dateStart = dateStart.plusDays(1);
//        dateOver = dateOver.plusDays(2);
        if(state == 110){
            return new ResultBean<>(expressService.getExpressListByNetworkIdAndDate(networkId, page, pageSize, dateStart, dateOver));
        }else{
            return new ResultBean<>(expressService.getExpressListByNetworkIdAndDateAndState(state, networkId, page, pageSize, dateStart, dateOver));
        }

    }

    /**
     * 根据网点id查询快递员
     */
    @RequestMapping(method = RequestMethod.GET, path = "/getDeliveryman")
    public ResultBean<?> getDeliverymanByNetworkId(@RequestParam(name = "page", defaultValue = "1") int page,
                                                   @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                   @RequestParam(name = "networkId", defaultValue = "") int NetworkId) {
        return new ResultBean<>(deliverymanService.getDeliverymanByNetworkId(page, pageSize, NetworkId));
    }
}
