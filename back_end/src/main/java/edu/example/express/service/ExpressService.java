package edu.example.express.service;

import edu.example.express.entity.Express;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDate;

/**
* <p>
* 快递单 服务类
* </p>
*
* @author zxy
* @since 2022-11-11
*/
public interface ExpressService {

    /**
    * 分页查询Express
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author zxy
    * @since 2022-11-11
    */
    Page<Express> listExpresssByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询Express
    *
    * @param id 需要查询的Express的id
    * @return 返回对应id的Express对象
    * @author zxy
    * @since 2022-11-11
    */
    Express getExpressById(int id);


    /**
    * 插入Express
    *
    * @param express 需要插入的Express对象
    * @return 返回插入成功之后Express对象的id
    * @author zxy
    * @since 2022-11-11
    */
    int insertExpress(Express express);

    /**
    * 根据id删除Express
    *
    * @param id 需要删除的Express对象的id
    * @return 返回被删除的Express对象的id
    * @author zxy
    * @since 2022-11-11
    */
    int deleteExpressById(int id);

    /**
    * 根据id更新Express
    *
    * @param express 需要更新的Express对象
    * @return 返回被更新的Express对象的id
    * @author zxy
    * @since 2022-11-11
    */
    int updateExpress(Express express);

    Page<Express> getExpressListByNetworkId(Integer networkId, int page, int pageSize);

    Page<Express> getExpressListByNetworkIdAndDate(Integer networkId, int page, int pageSize, LocalDate DateStart, LocalDate DateOver);

    Page<Express> getExpressListByDeliverymanId(Integer deliverymanId, int page, int pageSize);

    //发货人手机号查找
    Page<Express> getExpressListByDeliverPhoneNum(String DeliverPhoneNumber,int page, int pageSize);

    //收货人手机号查找
    Page<Express> getExpressListByReceiptPhoneNum(String ReceiptPhoneNumberr,int page, int pageSize);
}
