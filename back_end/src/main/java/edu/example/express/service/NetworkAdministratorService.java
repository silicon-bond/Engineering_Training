package edu.example.express.service;

import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Express;
import edu.example.express.entity.NetworkAdministrator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
* 网点管理员 服务类
* </p>
*
* @author csk
* @since 2022-11-14
*/
public interface NetworkAdministratorService {

    /**
     * 将网点物流列表里的某个快递单派单给一个指定的快递员
     *
     * @param express     当前快递列表中被选中的快递单
     * @param deliveryId     快递员Id
     * @author wh
     * @since 2022-11-15
     */
    int updateDeliverymanInExpress(Express express, Integer deliveryId);

    /**
    * 分页查询NetworkAdministrator
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author csk
    * @since 2022-11-14
    */
    Page<NetworkAdministrator> getNetworkAdministratorByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询NetworkAdministrator
    *
    * @param id 需要查询的NetworkAdministrator的id
    * @return 返回对应id的NetworkAdministrator对象
    * @author csk
    * @since 2022-11-14
    */
    NetworkAdministrator getNetworkAdministratorById(int id);

    /**
    * 插入NetworkAdministrator
    *
    * @param networkAdministrator 需要插入的NetworkAdministrator对象
    * @return 返回插入成功之后NetworkAdministrator对象的id
    * @author csk
    * @since 2022-11-14
    */
    int insertNetworkAdministrator(NetworkAdministrator networkAdministrator);

    /**
    * 根据id删除NetworkAdministrator
    *
    * @param id 需要删除的NetworkAdministrator对象的id
    * @return 返回被删除的NetworkAdministrator对象的id
    * @author csk
    * @since 2022-11-14
    */
    int deleteNetworkAdministratorById(int id);

    /**
    * 根据id更新NetworkAdministrator
    *
    * @param networkAdministrator 需要更新的NetworkAdministrator对象
    * @return 返回被更新的NetworkAdministrator对象的id
    * @author csk
    * @since 2022-11-14
    */
    int updateNetworkAdministrator(NetworkAdministrator networkAdministrator);

}
