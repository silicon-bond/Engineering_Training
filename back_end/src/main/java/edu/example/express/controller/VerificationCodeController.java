package edu.example.express.controller;


import org.springframework.web.bind.annotation.*;
import edu.example.express.entity.dto.ResultBean;
import edu.example.express.service.VerificationCodeService;
import edu.example.express.entity.VerificationCode;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


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
    public ResultBean<?> getVerificationCode(@PathVariable("email") String email) {
        ResultBean<Object> result = new ResultBean<>();
        VerificationCode verificationCodeByEmail = verificationCodeService.getVerificationCodeByEmail(email);
        if(verificationCodeByEmail != null)
        {
            String code = verificationCodeService.generateVerificationCode();
            String title = "Express APP";
            String content = "您的验证码为：" + code;
            verificationCodeService.sendSimpleMail(email,title,content);
            verificationCodeByEmail.setCode(code);
            verificationCodeService.updateVerificationCode(verificationCodeByEmail);
            result.setCode("200");
            result.setMessage("发送成功");
        }else{
            result.setCode("100");
            result.setMessage("无效邮箱");
        }
        return result;
    }

    @GetMapping("/Verificationresult/{email}/{code}")
    public ResultBean<?> GetVerificationresult(@PathVariable("email")String email,@PathVariable("code")String code){
        VerificationCode vcode = new VerificationCode(email,code);
        boolean result = verificationCodeService.IsVerificationCode(vcode);
        return new ResultBean<>(result);
    }


}
