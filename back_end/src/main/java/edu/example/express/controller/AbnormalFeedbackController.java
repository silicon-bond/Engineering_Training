package edu.example.express.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.AbnormalFeedbackService;
import edu.example.express.entity.AbnormalFeedback;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


/**
 * <p>
 * 异常反馈表 前端控制器
 * </p>
 *
 * @author zlh
 * @since 2022-11-11
 * @version v1.0
 */
@RestController
@RequestMapping("/express/api/abnormal-feedback")
@CrossOrigin(origins = "*")
public class AbnormalFeedbackController {

    @Resource
    private AbnormalFeedbackService abnormalFeedbackService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(abnormalFeedbackService.listAbnormalFeedbacksByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResultBean<?> getById(@PathVariable("id") Integer id) {
        return new ResultBean<>(abnormalFeedbackService.getAbnormalFeedbackById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public ResultBean<?> insert(@RequestBody AbnormalFeedback abnormalFeedback) {
        return new ResultBean<>(abnormalFeedbackService.insertAbnormalFeedback(abnormalFeedback));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
        return new ResultBean<>(abnormalFeedbackService.deleteAbnormalFeedbackById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.POST, value = "updateById")
    public ResultBean<?> updateById(@RequestBody AbnormalFeedback abnormalFeedback) {
        return new ResultBean<>(abnormalFeedbackService.updateAbnormalFeedback(abnormalFeedback));
    }
}
