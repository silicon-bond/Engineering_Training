package edu.example.express.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.NetworkAdministrator;
import edu.example.express.entity.User;
import edu.example.express.exception.bizException.BizException;
import edu.example.express.mapper.NetworkAdministratorMapper;
import edu.example.express.service.NetworkAdministratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.nio.ch.Net;


@Slf4j
@Service
public class NetworkAdministratorServiceImpl extends ServiceImpl<NetworkAdministratorMapper, NetworkAdministrator> implements NetworkAdministratorService {
    @Override
    public int insertNetworkAdministrator(NetworkAdministrator networkAdministrator) {
        log.info("正在插入networkAdministrator");
        if (super.save(networkAdministrator)) {
            log.info("插入networkAdministrator成功,id为{}",networkAdministrator.getNetworkAdministratorId());
            return networkAdministrator.getNetworkAdministratorId();
        } else {
            log.error("插入networkAdministrator失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteNetworkAdministratorById(int id) {
        log.info("正在删除id为{}的networkAdministrator",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的networkAdministrator成功",id);
            return id;
        } else {
            log.error("删除id为{}的networkAdministrator失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateNetworkAdministrator(NetworkAdministrator networkAdministrator) {
        log.info("正在更新id为{}的networkAdministrator",networkAdministrator.getNetworkAdministratorId());
        if (super.updateById(networkAdministrator)) {
            log.info("更新id为{}的networkAdministrator成功",networkAdministrator.getNetworkAdministratorId());
            return networkAdministrator.getNetworkAdministratorId();
        } else {
            log.error("更新id为{}的deliveryman失败",networkAdministrator.getNetworkAdministratorId());
            throw new BizException("更新失败[id=" + networkAdministrator.getNetworkAdministratorId() + "]");
        }
    }

    @Override
    public NetworkAdministrator getNetworkAdministratorById(int id) {
        log.info("正在查询networkAdministrator中id为{}的数据",id);
        NetworkAdministrator networkAdministrator = super.getById(id);
        log.info("查询id为{}的networkAdministrator{}",id,(null == networkAdministrator?"无结果":"成功"));
        return networkAdministrator;
    }

    @Override
    public Page<NetworkAdministrator> getNetworkAdministratorByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询networkAdministrator: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<NetworkAdministrator> queryWrapper =  new QueryWrapper<NetworkAdministrator>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<NetworkAdministrator> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询networkAdministrator完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }
}