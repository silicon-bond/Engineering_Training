package edu.example.express.service.impl;

import edu.example.express.entity.Express;
import edu.example.express.mapper.ExpressMapper;
import edu.example.express.service.ExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

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
            log.info("更新d为{}的express成功",express.getExpressId());
            return express.getExpressId();
        } else {
            log.error("更新id为{}的express失败",express.getExpressId());
            throw new BizException("更新失败[id=" + express.getExpressId() + "]");
        }
    }

}
