package edu.example.express.service.impl;

import edu.example.express.entity.AbnormalFeedback;
import edu.example.express.mapper.AbnormalFeedbackMapper;
import edu.example.express.service.AbnormalFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

/**
* <p>
* 异常反馈表 服务实现类
* </p>
*
* @author zlh
* @since 2022-11-11
*/
@Slf4j
@Service
public class AbnormalFeedbackServiceImpl extends ServiceImpl<AbnormalFeedbackMapper, AbnormalFeedback> implements AbnormalFeedbackService {

    @Override
    public Page<AbnormalFeedback> getAbnormalFeedbacksListByNetworkIdAndState(int page, int pageSize, int networkId, int state) {
        log.info("正在查询AbnormalFeedback中networkId={}, state={}的数据",networkId, state);
        QueryWrapper<AbnormalFeedback> queryWrapper =  new QueryWrapper<AbnormalFeedback>().eq("network_id", networkId)
                .and(wapper -> wapper.eq("state", state));
        Page<AbnormalFeedback> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("根据网点ID和反馈单State分页查询AbnormalFeedback完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<AbnormalFeedback> getAbnormalFeedbacksListByNetworkId(int page, int pageSize, int networkId) {
        log.info("正在查询AbnormalFeedback中networkId为{}的数据",networkId);
        QueryWrapper<AbnormalFeedback> queryWrapper =  new QueryWrapper<AbnormalFeedback>().eq("network_id", networkId);
        Page<AbnormalFeedback> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("根据网点ID分页查询AbnormalFeedback完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<AbnormalFeedback> listAbnormalFeedbacksByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询abnormalFeedback: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<AbnormalFeedback> queryWrapper =  new QueryWrapper<AbnormalFeedback>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<AbnormalFeedback> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询abnormalFeedback完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public AbnormalFeedback getAbnormalFeedbackById(int id) {
        log.info("正在查询abnormalFeedback中id为{}的数据",id);
        AbnormalFeedback abnormalFeedback = super.getById(id);
        log.info("查询id为{}的abnormalFeedback{}",id,(null == abnormalFeedback?"无结果":"成功"));
        return abnormalFeedback;
    }

    @Override
    public int insertAbnormalFeedback(AbnormalFeedback abnormalFeedback) {
        log.info("正在插入abnormalFeedback");
        if (super.save(abnormalFeedback)) {
            log.info("插入abnormalFeedback成功,id为{}",abnormalFeedback.getAbnormalFeedbackId());
            return abnormalFeedback.getAbnormalFeedbackId();
        } else {
            log.error("插入abnormalFeedback失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteAbnormalFeedbackById(int id) {
        log.info("正在删除id为{}的abnormalFeedback",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的abnormalFeedback成功",id);
            return id;
        } else {
            log.error("删除id为{}的abnormalFeedback失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateAbnormalFeedback(AbnormalFeedback abnormalFeedback) {
        log.info("正在更新id为{}的abnormalFeedback",abnormalFeedback.getAbnormalFeedbackId());
        if (super.updateById(abnormalFeedback)) {
            log.info("更新d为{}的abnormalFeedback成功",abnormalFeedback.getAbnormalFeedbackId());
            return abnormalFeedback.getAbnormalFeedbackId();
        } else {
            log.error("更新id为{}的abnormalFeedback失败",abnormalFeedback.getAbnormalFeedbackId());
            throw new BizException("更新失败[id=" + abnormalFeedback.getAbnormalFeedbackId() + "]");
        }
    }

}
