package edu.example.express.service.impl;

import edu.example.express.entity.Network;
import edu.example.express.mapper.NetworkMapper;
import edu.example.express.service.NetworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

/**
* <p>
* 网点 服务实现类
* </p>
*
* @author csk
* @since 2022-11-16
*/
@Slf4j
@Service
public class NetworkServiceImpl extends ServiceImpl<NetworkMapper, Network> implements NetworkService {

    @Override
    public Page<Network> listNetworksByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询network: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Network> queryWrapper =  new QueryWrapper<Network>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Network> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询network完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Network getNetworkById(int id) {
        log.info("正在查询network中id为{}的数据",id);
        Network network = super.getById(id);
        log.info("查询id为{}的network{}",id,(null == network?"无结果":"成功"));
        return network;
    }

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
            log.info("更新d为{}的network成功",network.getNetworkId());
            return network.getNetworkId();
        } else {
            log.error("更新id为{}的network失败",network.getNetworkId());
            throw new BizException("更新失败[id=" + network.getNetworkId() + "]");
        }
    }

    @Override
    public Page<Network> getNetworkByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询network: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Network> queryWrapper =  new QueryWrapper<Network>().like("network_name", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Network> result = super.page(new Page<>(page, pageSize),queryWrapper);
        log.info("分页查询network完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

}
