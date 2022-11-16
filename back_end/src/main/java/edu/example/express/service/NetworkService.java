package edu.example.express.service;

import edu.example.express.entity.Network;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
* <p>
* 网点 服务类
* </p>
*
* @author csk
* @since 2022-11-16
*/
public interface NetworkService {

    /**
    * 分页查询Network
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author csk
    * @since 2022-11-16
    */
    Page<Network> listNetworksByPage(int page, int pageSize, String factor);


    /**
     * 分页查询Network
     *
     * @param page     当前页数
     * @param pageSize 页的大小
     * @param factor  搜索关键词
     * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
     * @author csk
     * @since 2022-11-16
     */
    Page<Network> getNetworkByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询Network
    *
    * @param id 需要查询的Network的id
    * @return 返回对应id的Network对象
    * @author csk
    * @since 2022-11-16
    */
    Network getNetworkById(int id);

    /**
    * 插入Network
    *
    * @param network 需要插入的Network对象
    * @return 返回插入成功之后Network对象的id
    * @author csk
    * @since 2022-11-16
    */
    int insertNetwork(Network network);

    /**
    * 根据id删除Network
    *
    * @param id 需要删除的Network对象的id
    * @return 返回被删除的Network对象的id
    * @author csk
    * @since 2022-11-16
    */
    int deleteNetworkById(int id);

    /**
    * 根据id更新Network
    *
    * @param network 需要更新的Network对象
    * @return 返回被更新的Network对象的id
    * @author csk
    * @since 2022-11-16
    */
    int updateNetwork(Network network);


    List<Network> getAllNetworks();

}
