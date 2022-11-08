package edu.example.express.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.NetworkAdministrator;
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
        return 0;
    }

    @Override
    public int deleteNetworkAdministratorById(int id) {
        return 0;
    }

    @Override
    public int updateNetworkAdministrator(NetworkAdministrator networkAdministrator) {
        return 0;
    }

    @Override
    public Deliveryman getNetworkAdministratorById(int id) {
        return null;
    }

    @Override
    public Page<Deliveryman> getNetworkAdministratorByPage(int page, int pageSize, String factor) {
        return null;
    }
}
