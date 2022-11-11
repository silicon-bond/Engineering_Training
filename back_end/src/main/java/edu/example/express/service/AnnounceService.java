package edu.example.express.service;

import edu.example.express.entity.Announce;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
* 公告表 服务类
* </p>
*
* @author zlh
* @since 2022-11-11
*/
public interface AnnounceService {

    /**
    * 分页查询Announce
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author zlh
    * @since 2022-11-11
    */
    Page<Announce> listAnnouncesByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询Announce
    *
    * @param id 需要查询的Announce的id
    * @return 返回对应id的Announce对象
    * @author zlh
    * @since 2022-11-11
    */
    Announce getAnnounceById(int id);

    /**
    * 插入Announce
    *
    * @param announce 需要插入的Announce对象
    * @return 返回插入成功之后Announce对象的id
    * @author zlh
    * @since 2022-11-11
    */
    int insertAnnounce(Announce announce);

    /**
    * 根据id删除Announce
    *
    * @param id 需要删除的Announce对象的id
    * @return 返回被删除的Announce对象的id
    * @author zlh
    * @since 2022-11-11
    */
    int deleteAnnounceById(int id);

    /**
    * 根据id更新Announce
    *
    * @param announce 需要更新的Announce对象
    * @return 返回被更新的Announce对象的id
    * @author zlh
    * @since 2022-11-11
    */
    int updateAnnounce(Announce announce);

}
