package edu.example.express.controller;


import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.VerificationCodeService;
import edu.example.express.entity.VerificationCode;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lx
 * @since 2022-11-12
 * @version v1.0
 */
@RestController
@RequestMapping("/express/api/user/Captcha")
@CrossOrigin(origins = "*")
public class VerificationCodeController {

    @Resource
    private VerificationCodeService verificationCodeService;

    /**
    * 查询分页数据
    */
    @RequestMapping(method = RequestMethod.GET)
    public ResultBean<?> listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return new ResultBean<>(verificationCodeService.listVerificationCodesByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
//

    /**
    * 新增
    */
//    @RequestMapping(method = RequestMethod.POST)
//    public ResultBean<?> insert(@RequestBody VerificationCode verificationCode) {
//        return new ResultBean<>(verificationCodeService.insertVerificationCode(verificationCode));
//    }
//
//    /**
//    * 删除
//    */
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//    public ResultBean<?> deleteById(@PathVariable("id") Integer id) {
//        return new ResultBean<>(verificationCodeService.deleteVerificationCodeByEmail(id));
//    }
//
//    /**
//    * 修改
//    */
//    @RequestMapping(method = RequestMethod.PUT)
//    public ResultBean<?> updateById(@RequestBody VerificationCode verificationCode) {
//        return new ResultBean<>(verificationCodeService.updateVerificationCode(verificationCode));
//    }

    @GetMapping("/{email}")
    @Async
    public ResultBean<?> getVerificationCode(@PathVariable("email") String email) throws ParseException {
        ResultBean<Object> result = new ResultBean<>();
        VerificationCode verificationCode = new VerificationCode();

        verificationCode.setEmail(email);

        String code = verificationCodeService.generateVerificationCode();
        String title = "Express APP";
        String content = "您的验证码为：" + code;
        verificationCodeService.sendSimpleMail(email,title,content);
        verificationCode.setCode(code);
        result.setCode("200");
        result.setMessage("发送成功");
        if(verificationCodeService.getVerificationCodeByEmail(email) != null){
            verificationCodeService.updateVerificationCode(verificationCode);
            return result;
        }
        verificationCodeService.insertVerificationCode(verificationCode);
        return result;
    }

    @GetMapping("/Verificationresult/{email}/{code}")
    public ResultBean<?> GetVerificationresult(@PathVariable("email")String email,@PathVariable("code")String code) throws ParseException {
        VerificationCode vcode = new VerificationCode();
        vcode.setEmail(email);
        vcode.setCode(code);
        boolean result = verificationCodeService.IsVerificationCode(vcode);
        return new ResultBean<>(result);
    }


}
