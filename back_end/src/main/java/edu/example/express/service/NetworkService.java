package edu.example.express.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Network;

public interface NetworkService {
    /**
     * 插入网点
     *
     * @param network 需要插入的Network对象
     * @return 返回插入成功之后Network对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int insertNetwork(Network network);


    /**
     * 删除网点
     *
     * @param id 要删除的Network对象的id
     * @return 返回插入成功之后Network对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int deleteNetworkById(int id);


    /**
     * 修改网点
     *
     * @param network 要修改的Network整个对象
     * @return 返回修改成功之后Network对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int updateNetwork(Network network);


    /**
     * 根据Id获得网点
     *
     * @param id 要查询的Network id
     * @return 返回成功查询后的Network对象
     * @author zlh
     * @since 2022-11-08
     */
    Network getNetworkById(int id);


    /**
     * 网点分页查询
     *
     * @param page     当前页数
     * @param pageSize 页的大小
     * @param factor  搜索关键词
     * @return 返回分页查询后的Network对象
     * @author zlh
     * @since 2022-11-08
     */
    Page<Network> getNetworkByPage(int page, int pageSize, String factor);

}
