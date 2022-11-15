package edu.example.express.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Express;
import edu.example.express.exception.bizException.BizException;
import edu.example.express.mapper.DeliverymanMapper;
import edu.example.express.service.DeliverymanService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class DeliverymanServiceImpl extends ServiceImpl<DeliverymanMapper,Deliveryman> implements DeliverymanService {
    @Override
    public int insertDeliveryman(Deliveryman deliveryman) {
        log.info("正在插入deliveryman");
        if (super.save(deliveryman)) {
            log.info("插入deliveryman成功,id为{}",deliveryman.getDeliverymanId());
            return deliveryman.getDeliverymanId();
        } else {
            log.error("插入deliveryman失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteDeliverymanById(int id) {
        log.info("正在删除id为{}的deliveryman",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的deliveryman成功",id);
            return id;
        } else {
            log.error("删除id为{}的deliveryman失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateDeliveryman(Deliveryman deliveryman) {
        log.info("正在更新id为{}的deliveryman",deliveryman.getDeliverymanId());
        if (super.updateById(deliveryman)) {
            log.info("更新id为{}的deliveryman成功",deliveryman.getDeliverymanId());
            return deliveryman.getDeliverymanId();
        } else {
            log.error("更新id为{}的deliveryman失败",deliveryman.getDeliverymanId());
            throw new BizException("更新失败[id=" + deliveryman.getDeliverymanId() + "]");
        }
    }

    @Override
    public Deliveryman getDeliverymanById(int id) {
        log.info("正在查询deliveryman中id为{}的数据",id);
        Deliveryman deliveryman = super.getById(id);
        log.info("查询id为{}的deliveryman{}",id,(null == deliveryman?"无结果":"成功"));
        return deliveryman;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询deliveryman: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Deliveryman> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询deliveryman完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByNetworkId(int page, int pageSize, int networkId) {
        log.info("正在执行分页查询deliveryman: page = {} pageSize = {} networkId = {}", page, pageSize, networkId);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().eq("network_id", networkId);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Deliveryman> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询deliveryman完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Deliveryman getDeliverymanByEmail(String email) {
        log.info("正在查询deliveryman中email为{}的数据", email);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().eq("email", email);
        Deliveryman deliveryman = super.getOne(queryWrapper);
        log.info("查询email为{}的deliveryman{}",email,(null == deliveryman?"无结果":"成功"));
        return deliveryman;
    }
}
