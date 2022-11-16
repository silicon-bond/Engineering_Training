package edu.example.express.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.NetworkService;
import edu.example.express.entity.Network;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * <p>
 * 网点 前端控制器
 * </p>
 *
 * @author csk
 * @since 2022-11-16
 * @version v1.0
 */
@RestController
@RequestMapping("/express/api/network")
@CrossOrigin(origins = "*")
public class NetworkController {

    @Resource
    private NetworkService networkService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(networkService.listNetworksByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(networkService.getNetworkById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody Network network) {
        return new ResultBean<>(networkService.insertNetwork(network));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(networkService.deleteNetworkById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody Network network) {
        return new ResultBean<>(networkService.updateNetwork(network));
    }
}
