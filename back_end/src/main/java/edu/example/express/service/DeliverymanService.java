package edu.example.express.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.User;

public interface DeliverymanService {
    /**
     * 插入快递员
     *
     * @param deliveryman 需要插入的Deliveryman对象
     * @return 返回插入成功之后Deliveryman对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int insertDeliveryman(Deliveryman deliveryman);


    /**
     * 删除快递员
     *
     * @param id 要删除的Deliveryman对象的id
     * @return 返回插入成功之后Deliveryman对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int deleteDeliverymanById(int id);


    /**
     * 修改快递员
     *
     * @param deliveryman 要修改的Deliveryman整个对象
     * @return 返回修改成功之后Deliveryman对象的id
     * @author zlh
     * @since 2022-11-08
     */
    int updateDeliveryman(Deliveryman deliveryman);


    /**
     * 根据Id获得快递员
     *
     * @param id 要查询的Deliveryman id
     * @return 返回成功查询后的Deliveryman对象
     * @author zlh
     * @since 2022-11-08
     */
    Deliveryman getDeliverymanById(int id);


    /**
     * 快递员分页查询
     *
     * @param page     当前页数
     * @param pageSize 页的大小
     * @param factor  搜索关键词
     * @return 返回分页查询后的Deliveryman对象
     * @author zlh
     * @since 2022-11-08
     */
    Page<Deliveryman> getDeliverymanByPage(int page, int pageSize, String factor);
}
