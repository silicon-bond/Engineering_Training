package edu.example.express.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.Network;
import edu.example.express.exception.bizException.BizException;
import edu.example.express.mapper.NetworkMapper;
import edu.example.express.service.NetworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NetworkServiceImpl extends ServiceImpl<NetworkMapper, Network> implements NetworkService{

    @Override
    public int insertNetwork(Network network) {
        log.info("正在插入network");
        if (super.save(network)) {
            log.info("插入network成功,id为{}",network.getNetworkId());
            return network.getNetworkId();
        } else {
            log.error("插入network失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteNetworkById(int id) {
        log.info("正在删除id为{}的network",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的network成功",id);
            return id;
        } else {
            log.error("删除id为{}的network失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateNetwork(Network network) {
        log.info("正在更新id为{}的network",network.getNetworkId());
        if (super.updateById(network)) {
            log.info("更新id为{}的network成功",network.getNetworkId());
            return network.getNetworkId();
        } else {
            log.error("更新id为{}的network失败",network.getNetworkId());
            throw new BizException("更新失败[id=" + network.getNetworkName() + "]");
        }
    }

    @Override
    public Network getNetworkById(int id) {
        log.info("正在查询network中id为{}的数据",id);
        Network network = super.getById(id);
        log.info("查询id为{}的network{}",id,(null == network?"无结果":"成功"));
        return network;
    }

    @Override
    public Page<Network> getNetworkByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询network: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Network> queryWrapper =  new QueryWrapper<Network>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Network> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询network完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }
}
