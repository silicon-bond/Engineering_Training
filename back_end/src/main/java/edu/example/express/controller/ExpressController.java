package edu.example.express.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.ExpressService;
import edu.example.express.entity.Express;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * <p>
 * 快递单 前端控制器
 * </p>
 *
 * @author zxy
 * @since 2022-11-11
 * @version v1.0
 */
@RestController
@RequestMapping("/express/api/express")
@CrossOrigin(origins = "*")
public class ExpressController {

    @Resource
    private ExpressService expressService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(expressService.listExpresssByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(expressService.getExpressById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody Express express) {
        return new ResultBean<>(expressService.insertExpress(express));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(expressService.deleteExpressById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public ResultBean<?> updateById(@RequestBody Express express) {
        return new ResultBean<>(expressService.updateExpress(express));
    }
}
