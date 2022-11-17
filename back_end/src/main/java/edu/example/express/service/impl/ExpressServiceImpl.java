package edu.example.express.service.impl;

import edu.example.express.entity.Express;
import edu.example.express.mapper.ExpressMapper;
import edu.example.express.service.ExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
* <p>
* 快递单 服务实现类
* </p>
*
* @author zxy
* @since 2022-11-11
*/
@Slf4j
@Service
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements ExpressService {
    @Autowired
    ExpressMapper expressMapper;

    @Override
    public Page<Express> listExpresssByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询express: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Express> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Express> getExpressByIdAndDeliverPhoneNumber(int page, int pageSize, int id, String deliver_phone_number){
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("deliver_phone_number", deliver_phone_number)
                .eq("express_id", id);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }


    @Override
    public Page<Express> getExpressByIdAndReceiptPhoneNumber(int page, int pageSize, int id, String receipt_phone_number){
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("receipt_phone_number", receipt_phone_number)
                .eq("express_id", id);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Express getExpressById(int id) {
        log.info("正在查询express中id为{}的数据",id);
        Express express = super.getById(id);
        log.info("查询id为{}的express{}",id,(null == express?"无结果":"成功"));
        return express;
    }

    @Override
    public int insertExpress(Express express) {
        log.info("正在插入express");
        if (super.save(express)) {
            log.info("插入express成功,id为{}",express.getExpressId());
            return express.getExpressId();
        } else {
            log.error("插入express失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteExpressById(int id) {
        log.info("正在删除id为{}的express",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的express成功",id);
            return id;
        } else {
            log.error("删除id为{}的express失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateExpress(Express express) {
        log.info("正在更新id为{}的express",express.getExpressId());
        if (super.updateById(express)) {
            log.info("更新id为{}的express成功",express.getExpressId());
            return express.getExpressId();
        } else {
            log.error("更新id为{}的express失败",express.getExpressId());
            throw new BizException("更新失败[id=" + express.getExpressId() + "]");
        }
    }

    @Override
    public Page<Express> getExpressListByNetworkId(Integer networkId, int page, int pageSize) {
        log.info("正在查询express中networkId为{}的数据",networkId);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("network_id", networkId);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Express> getExpressListByDeliverymanId(Integer deliverymanId, int page, int pageSize) {
        log.info("正在查询express中deliverymanId为{}的数据", deliverymanId);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("delivery_id", deliverymanId)
                                                                        .or()
                                                                        .eq("collect_id", deliverymanId);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Express> getExpressListByDeliverPhoneNum(String DeliverPhoneNumber, int page, int pageSize) {
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("Deliver_Phone_Number", DeliverPhoneNumber);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        return result;
    }

    @Override
    public Page<Express> getExpressListByReceiptPhoneNum(String ReceiptPhoneNumberr, int page, int pageSize) {
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("Receipt_Phone_Number", ReceiptPhoneNumberr);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        return result;
    }

    @Override
    public Page<Express> getExpressListByNetworkIdAndDate(Integer networkId, int page, int pageSize, LocalDate DateStart, LocalDate DateOver){
        log.info("正在查询express中NetworkId为{}且时间范围在{}和{}之间的数据", networkId, DateStart, DateOver);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("network_id", networkId)
                .between("order_date", DateStart, DateOver);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Express> getExpressListByReceiptPhoneNumber(String receipt_phone_number, int page, int pageSize){
        log.info("正在查询express中receipt_phone_number为{}数据", receipt_phone_number);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("receipt_phone_number", receipt_phone_number);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }
    @Override
    public Page<Express> getExpressListByDeliverPhoneNumber(String deliver_phone_number, int page, int pageSize){
        log.info("正在查询express中deliver_phone_number为{}数据", deliver_phone_number);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("deliver_phone_number", deliver_phone_number);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Express> listExpresssPageByIdAndState(int page, int pageSize, String factor, int id, int state) {
        String state_name = expressMapper.getExpressStateNameById(id);
        log.info("正在查询express中id为{},或者订单状态id为{},订单状态为{}的数据", id, state,state_name);

        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>();
        if (id != -1){
            queryWrapper.eq("express_id",id);
        }
        if (state != -1){
            queryWrapper.eq("state", state);
        }

        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Page<Express> getExpressListByState(int page, int pageSize, Integer state) {
        log.info("正在查询express中state为{}的数据",state);
        QueryWrapper<Express> queryWrapper =  new QueryWrapper<Express>().eq("state", state);
        Page<Express> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询express完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }
}
