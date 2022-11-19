package edu.example.express.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.example.express.mapper.ExpressMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.ExpressService;
import edu.example.express.entity.Express;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;


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

    @Autowired
    ExpressMapper expressMapper;

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
     * 查询分页数据
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/deliverPhoneNumber"})
    public ResultBean<?> listByDeliverPhoneNumber(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber) {
        return new ResultBean<>(expressService.getExpressListByDeliverPhoneNumber(phoneNumber, page, pageSize));
    }

    /**
     * 查询分页数据
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/receiptPhoneNumber"})
    public ResultBean<?> listByReceiptPhoneNumber(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                  @RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber) {
        return new ResultBean<>(expressService.getExpressListByReceiptPhoneNumber(phoneNumber, page, pageSize));
    }

    /**
     * 查询分页数据
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/receiptPhoneNumber/id"})
    public ResultBean<?> listByReceiptPhoneNumberAndId(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                  @RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
                                                       @RequestParam(name = "id", defaultValue = "") int id) {
        return new ResultBean<>(expressService.getExpressByIdAndReceiptPhoneNumber(page, pageSize, id, phoneNumber));
    }

    /**
     * 查询分页数据
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/deliverPhoneNumber/id"})
    public ResultBean<?> listByDeliverPhoneNumberAndId(@RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                  @RequestParam(name = "phoneNumber", defaultValue = "") String phoneNumber,
                                                       @RequestParam(name = "id", defaultValue = "") int id) {
        return new ResultBean<>(expressService.getExpressByIdAndDeliverPhoneNumber(page, pageSize, id, phoneNumber));
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
    @RequestMapping(method = RequestMethod.POST, value = "updateById")
    public ResultBean<?> updateById(@RequestBody Express express) {
        return new ResultBean<>(expressService.updateExpress(express));
    }


    /**
     * 根据物流状态&物流ID进行分页查询
     */
    @GetMapping("/listPageByIdAndState")
    public ResultBean<?> listByPage2(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor,
                                     @RequestParam(name = "id",defaultValue = "-1") int id,
                                     @RequestParam(name = "state",defaultValue = "-1") int state) {
        return new ResultBean<>(expressService.listExpresssPageByIdAndState(page, pageSize,factor,id,state));
    }



    @GetMapping("/getOneExpress")
    public ResultBean<?> listByPage3(@RequestParam(name = "page", defaultValue = "1") int page,
                                     @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                     @RequestParam(name = "factor", defaultValue = "") String factor,
                                     @RequestParam(name = "id",defaultValue = "-1") int id){
        System.out.println(id);
        List<Express> expressList = expressMapper.getExpressAndNetworkName(id);
        System.out.println(expressList);
        PageHelper.startPage(page,pageSize);
        PageInfo<Express> pageInfo = new PageInfo<>(expressList);
        return new ResultBean<>(pageInfo);
    }

//    @GetMapping("test")
//    public ResultBean<?> test(@RequestParam(name = "page", defaultValue = "1") int page,
//                              @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
//        Page<Express> page1 = expressMapper.selectPage1(new Page<Express>(page,pageSize));
//        return new ResultBean<>(page1);
//    }

}
