package edu.example.express.service;

import edu.example.express.entity.AbnormalFeedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
* 异常反馈表 服务类
* </p>
*
* @author zlh
* @since 2022-11-11
*/
public interface AbnormalFeedbackService {

    /**
     * 根据网点ID分页查询给定状态的AbnormalFeedback
     *
     * @param page     当前页数
     * @param pageSize 页的大小
     * @param networkId  网点ID
     * @param state    反馈单的状态
     * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
     * @author wh
     * @since 2022-11-18
     */
    Page<AbnormalFeedback> getAbnormalFeedbacksListByNetworkIdAndState(int page, int pageSize, int networkId, int state);

    /**
     * 根据网点ID分页查询AbnormalFeedback
     *
     * @param page     当前页数
     * @param pageSize 页的大小
     * @param networkId  网点ID
     * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
     * @author wh
     * @since 2022-11-18
     */
    Page<AbnormalFeedback> getAbnormalFeedbacksListByNetworkId(int page, int pageSize, int networkId);

    /**
    * 分页查询AbnormalFeedback
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author zlh
    * @since 2022-11-11
    */
    Page<AbnormalFeedback> listAbnormalFeedbacksByPage(int page, int pageSize, String factor);

    /**
    * 根据id查询AbnormalFeedback
    *
    * @param id 需要查询的AbnormalFeedback的id
    * @return 返回对应id的AbnormalFeedback对象
    * @author zlh
    * @since 2022-11-11
    */
    AbnormalFeedback getAbnormalFeedbackById(int id);

    /**
    * 插入AbnormalFeedback
    *
    * @param abnormalFeedback 需要插入的AbnormalFeedback对象
    * @return 返回插入成功之后AbnormalFeedback对象的id
    * @author zlh
    * @since 2022-11-11
    */
    int insertAbnormalFeedback(AbnormalFeedback abnormalFeedback);

    /**
    * 根据id删除AbnormalFeedback
    *
    * @param id 需要删除的AbnormalFeedback对象的id
    * @return 返回被删除的AbnormalFeedback对象的id
    * @author zlh
    * @since 2022-11-11
    */
    int deleteAbnormalFeedbackById(int id);

    /**
    * 根据id更新AbnormalFeedback
    *
    * @param abnormalFeedback 需要更新的AbnormalFeedback对象
    * @return 返回被更新的AbnormalFeedback对象的id
    * @author zlh
    * @since 2022-11-11
    */
    int updateAbnormalFeedback(AbnormalFeedback abnormalFeedback);

}
