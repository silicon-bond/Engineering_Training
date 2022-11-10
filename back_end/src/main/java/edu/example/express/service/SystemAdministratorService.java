package edu.example.express.service;

import edu.example.express.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import sun.nio.ch.Net;

/**
* <p>
* 系统远离员 服务类
* </p>
*
* @author csk
* @since 2022-11-05
*/
public interface SystemAdministratorService {

    /**
     * 模块一：对用户，快递员，网点管理员的增删改查
     */

    int addOneUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    User getUserById(int id);

    Page<User> getUserByPage(int page, int pageSize, String factor);


    int addOneDeliveryman(Deliveryman deliveryman);

    int deleteDeliverymanById(int id);

    int updateDeliveryman(Deliveryman deliveryman);

    Deliveryman getDeliverymanById(int id);

    Page<Deliveryman> getDeliverymanByPage(int page, int pageSize, String factor);


    int addOneNetworkAdministrator(NetworkAdministrator networkAdministrator);

    int deleteNetworkAdministratorById(int id);

    int updateNetworkAdministrator(NetworkAdministrator networkAdministrator);

    NetworkAdministrator getNetworkAdministratorById(int id);

    Page<NetworkAdministrator> getNetworkAdministratorByPage(int page, int pageSize, String factor);

    /**
     * 模块二：对网点进行管理
     */

    int addOneNetwork(Network network);

    int deleteNetworkById(int id);

    int updateNetwork(Network network);

    Network getNetworkById(int id);

    Page<Network> getNetworkByPage(int page, int pageSize, String factor);

    /**
     * 模块三：对物流进行管理
     */

    /**
     * 模块四：异常反馈处理
     */

    /**
     * 模块五：公司资讯管理
     */






    /**
    * 分页查询SystemAdministrator
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author csk
    * @since 2022-11-05
    */
    Page<SystemAdministrator> listSystemAdministratorsByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询SystemAdministrator
    *
    * @param id 需要查询的SystemAdministrator的id
    * @return 返回对应id的SystemAdministrator对象
    * @author csk
    * @since 2022-11-05
    */
    SystemAdministrator getSystemAdministratorById(int id);

    /**
    * 插入SystemAdministrator
    *
    * @param systemAdministrator 需要插入的SystemAdministrator对象
    * @return 返回插入成功之后SystemAdministrator对象的id
    * @author csk
    * @since 2022-11-05
    */
    int insertSystemAdministrator(SystemAdministrator systemAdministrator);

    /**
    * 根据id删除SystemAdministrator
    *
    * @param id 需要删除的SystemAdministrator对象的id
    * @return 返回被删除的SystemAdministrator对象的id
    * @author csk
    * @since 2022-11-05
    */
    int deleteSystemAdministratorById(int id);

    /**
    * 根据id更新SystemAdministrator
    *
    * @param systemAdministrator 需要更新的SystemAdministrator对象
    * @return 返回被更新的SystemAdministrator对象的id
    * @author csk
    * @since 2022-11-05
    */
    int updateSystemAdministrator(SystemAdministrator systemAdministrator);

}
