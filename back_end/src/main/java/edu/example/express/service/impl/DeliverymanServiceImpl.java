package edu.example.express.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Express;
import edu.example.express.entity.Network;
import edu.example.express.exception.bizException.BizException;
import edu.example.express.mapper.DeliverymanMapper;
import edu.example.express.service.DeliverymanService;

import edu.example.express.service.NetworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DeliverymanServiceImpl extends ServiceImpl<DeliverymanMapper,Deliveryman> implements DeliverymanService {

    @Resource
    private NetworkService networkService;

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
        deliveryman = completeInfo(deliveryman);
        log.info("查询id为{}的deliveryman{}",id,(null == deliveryman?"无结果":"成功"));
        return deliveryman;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询deliveryman: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Deliveryman> result = super.page(new Page<>(page, pageSize));
        result.setRecords(completeListInfo(result.getRecords()));
        log.info("分页查询deliveryman完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByNetworkId(int page, int pageSize, int networkId) {
        log.info("正在执行分页查询deliveryman: page = {} pageSize = {} networkId = {}", page, pageSize, networkId);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().eq("network_id", networkId);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Deliveryman> result = super.page(new Page<>(page, pageSize), queryWrapper);
        result.setRecords(completeListInfo(result.getRecords()));
        log.info("分页查询deliveryman完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Deliveryman getDeliverymanByDeliverymanIdAndNetworkId(int deliverymanId, int networkId) {
        log.info("正在执行查询deliveryman: deliverymanId = {} networkId = {}", deliverymanId, networkId);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().eq("deliveryman_id", deliverymanId).eq("network_id", networkId);
        Deliveryman deliveryman = super.getOne(queryWrapper);
        deliveryman = completeInfo(deliveryman);
        log.info("查询deliverymanId={} networkId={} 的deliveryman{}",deliverymanId, networkId,(null == deliveryman?"无结果":"成功"));
        return deliveryman;
    }

    @Override
    public Deliveryman getDeliverymanByEmail(String email) {
        log.info("正在查询deliveryman中email为{}的数据", email);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().eq("email", email);
        Deliveryman deliveryman = super.getOne(queryWrapper);
        deliveryman = completeInfo(deliveryman);
        log.info("查询email为{}的deliveryman{}",email,(null == deliveryman?"无结果":"成功"));
        return deliveryman;
    }

    @Override
    public Deliveryman getDeliverymanByPhoneNumber(String phoneNumber) {
        log.info("正在查询deliveryman中phoneNumber为{}的数据", phoneNumber);
        QueryWrapper<Deliveryman> queryWrapper =  new QueryWrapper<Deliveryman>().eq("phone_number", phoneNumber);
        Deliveryman deliveryman = super.getOne(queryWrapper);
        deliveryman = completeInfo(deliveryman);
        log.info("查询phoneNumber为{}的deliveryman{}",phoneNumber,(null == deliveryman?"无结果":"成功"));
        return deliveryman;
    }

    private Deliveryman completeInfo(Deliveryman deliveryman){
        if (deliveryman != null){
            Network network = networkService.getNetworkById(deliveryman.getNetworkId());
            deliveryman.setNetwork(network);
        }
        return deliveryman;
    }

    private List<Deliveryman> completeListInfo(List<Deliveryman> deliverymanList){
        List<Deliveryman> newList = new ArrayList<Deliveryman>();
        for (Deliveryman deliveryman : deliverymanList){
            newList.add(completeInfo(deliveryman));
        }
        return newList;
    }
}
