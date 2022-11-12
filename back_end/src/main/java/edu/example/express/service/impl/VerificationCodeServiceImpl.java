package edu.example.express.service.impl;

import edu.example.express.entity.VerificationCode;
import edu.example.express.mapper.VerificationCodeMapper;
import edu.example.express.service.VerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

import java.util.Random;

/**
* <p>
*  服务实现类
* </p>
*
* @author lx
* @since 2022-11-12
*/
@Slf4j
@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode> implements VerificationCodeService {

    private final int generateVerificationCodeLength=4;
    private final String[] metaCode={"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<VerificationCode> listVerificationCodesByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询verificationCode: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<VerificationCode> queryWrapper =  new QueryWrapper<VerificationCode>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<VerificationCode> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询verificationCode完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public VerificationCode getVerificationCodeByEmail(String id) {
        log.info("正在查询verificationCode中id为{}的数据",id);
        VerificationCode verificationCode = super.getById(id);
        log.info("查询id为{}的verificationCode{}",id,(null == verificationCode?"无结果":"成功"));
        return verificationCode;
    }

    @Override
    public String insertVerificationCode(VerificationCode verificationCode) {
        log.info("正在插入verificationCode");
        if (super.save(verificationCode)) {
            log.info("插入verificationCode成功,id为{}",verificationCode.getEmail());
            return verificationCode.getEmail();
        } else {
            log.error("插入verificationCode失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public String deleteVerificationCodeByEmail(String id) {
        log.info("正在删除id为{}的verificationCode",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的verificationCode成功",id);
            return id;
        } else {
            log.error("删除id为{}的verificationCode失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public String updateVerificationCode(VerificationCode verificationCode) {
        log.info("正在更新id为{}的verificationCode",verificationCode.getEmail());
        if (super.updateById(verificationCode)) {
            log.info("更新d为{}的verificationCode成功",verificationCode.getEmail());
            return verificationCode.getEmail();
        } else {
            log.error("更新id为{}的verificationCode失败",verificationCode.getEmail());
            throw new BizException("更新失败[id=" + verificationCode.getEmail() + "]");
        }
    }

    @Override
    public String generateVerificationCode() {
        Random random = new Random();
        StringBuilder verificationCode = new StringBuilder();
        while (verificationCode.length()<generateVerificationCodeLength){
            int i = random.nextInt(metaCode.length);
            verificationCode.append(metaCode[i]);
        }
        return verificationCode.toString();
    }

    @Override
    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public boolean IsVerificationCode(VerificationCode verificationCode) {
        VerificationCode verificationCodeByEmail = getVerificationCodeByEmail(verificationCode.getEmail());
        if(verificationCode.getCode().equals(verificationCodeByEmail.getCode()))
            return true;
        return false;
    }


}
