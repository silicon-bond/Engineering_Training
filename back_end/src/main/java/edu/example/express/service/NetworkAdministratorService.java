package edu.example.express.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.NetworkAdministrator;

public interface NetworkAdministratorService {
    /**
     * 插入快递员
     *
     * @param  networkAdministrator 需要插入的NetworkAdministrator对象
     * @return 返回插入成功之后NetworkAdministrator对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int insertNetworkAdministrator(NetworkAdministrator networkAdministrator);


    /**
     * 删除快递员
     *
     * @param id 要删除的NetworkAdministrator对象的id
     * @return 返回插入成功之后NetworkAdministrator对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int deleteNetworkAdministratorById(int id);


    /**
     * 修改快递员
     *
     * @param deliveryman 要修改的NetworkAdministrator整个对象
     * @return 返回修改成功之后NetworkAdministrator对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int updateNetworkAdministrator(NetworkAdministrator networkAdministrator);


    /**
     * 根据Id获得快递员
     *
     * @param id 要查询的NetworkAdministrator id
     * @return 返回成功查询后的NetworkAdministrator对象
     * @author zlh
     * @since 2022-11-08
     */
    Deliveryman getNetworkAdministratorById(int id);


    /**
     * 快递员分页查询
     *
     * @param page     当前页数
     * @param pageSize 页的大小
     * @param factor  搜索关键词
     * @return 返回分页查询后的NetworkAdministrator对象
     * @author zlh
     * @since 2022-11-08
     */
    Page<Deliveryman> getNetworkAdministratorByPage(int page, int pageSize, String factor);
}
