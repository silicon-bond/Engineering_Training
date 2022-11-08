package edu.example.express.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.example.express.entity.Deliveryman;
import edu.example.express.mapper.DeliverymanMapper;
import edu.example.express.service.DeliverymanService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliverymanServiceImpl extends ServiceImpl<DeliverymanMapper, DeliverymanMapper> implements DeliverymanService {
    @Override
    public int insertDeliveryman(Deliveryman deliveryman) {
        return 0;
    }

    @Override
    public int deleteDeliverymanById(int id) {
        return 0;
    }

    @Override
    public int updateDeliveryman(Deliveryman deliveryman) {
        return 0;
    }

    @Override
    public Deliveryman getDeliverymanById(int id) {
        return null;
    }

    @Override
    public Page<Deliveryman> getDeliverymanByPage(int page, int pageSize, String factor) {
        return null;
    }
}
