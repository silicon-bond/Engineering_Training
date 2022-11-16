package edu.example.express.service;

import edu.example.express.entity.VerificationCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.text.ParseException;

/**
* <p>
*  服务类
* </p>
*
* @author lx
* @since 2022-11-12
*/
public interface VerificationCodeService {

    /**
    * 分页查询VerificationCode
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author lx
    * @since 2022-11-12
    */
    Page<VerificationCode> listVerificationCodesByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询VerificationCode
    *
    * @param id 需要查询的VerificationCode的id
    * @return 返回对应id的VerificationCode对象
    * @author lx
    * @since 2022-11-12
    */
    VerificationCode getVerificationCodeByEmail(String id);

    /**
    * 插入VerificationCode
    *
    * @param verificationCode 需要插入的VerificationCode对象
    * @return 返回插入成功之后VerificationCode对象的id
    * @author lx
    * @since 2022-11-12
    */
    String insertVerificationCode(VerificationCode verificationCode);

    /**
    * 根据id删除VerificationCode
    *
    * @param id 需要删除的VerificationCode对象的id
    * @return 返回被删除的VerificationCode对象的id
    * @author lx
    * @since 2022-11-12
    */
    String deleteVerificationCodeByEmail(String id);

    /**
    * 根据id更新VerificationCode
    *
    * @param verificationCode 需要更新的VerificationCode对象
    * @return 返回被更新的VerificationCode对象的id
    * @author lx
    * @since 2022-11-12
    */
    String updateVerificationCode(VerificationCode verificationCode);

    public String generateVerificationCode();
    public void sendSimpleMail(String to,String title,String content);

    boolean IsVerificationCode(VerificationCode verificationCode) ;

}
