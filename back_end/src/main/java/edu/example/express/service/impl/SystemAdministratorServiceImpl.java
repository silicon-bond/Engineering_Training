package edu.example.express.service.impl;

import edu.example.express.entity.SystemAdministrator;
import edu.example.express.mapper.SystemAdministratorMapper;
import edu.example.express.service.SystemAdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

/**
* <p>
* 系统远离员 服务实现类
* </p>
*
* @author csk
* @since 2022-11-05
*/
@Slf4j
@Service
public class SystemAdministratorServiceImpl extends ServiceImpl<SystemAdministratorMapper, SystemAdministrator> implements SystemAdministratorService {

    @Override
    public Page<SystemAdministrator> listSystemAdministratorsByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询systemAdministrator: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<SystemAdministrator> queryWrapper =  new QueryWrapper<SystemAdministrator>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<SystemAdministrator> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询systemAdministrator完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public SystemAdministrator getSystemAdministratorById(int id) {
        log.info("正在查询systemAdministrator中id为{}的数据",id);
        SystemAdministrator systemAdministrator = super.getById(id);
        log.info("查询id为{}的systemAdministrator{}",id,(null == systemAdministrator?"无结果":"成功"));
        return systemAdministrator;
    }

    @Override
    public int insertSystemAdministrator(SystemAdministrator systemAdministrator) {
        log.info("正在插入systemAdministrator");
        if (super.save(systemAdministrator)) {
            log.info("插入systemAdministrator成功,id为{}",systemAdministrator.getSystemAdministratorId());
            return systemAdministrator.getSystemAdministratorId();
        } else {
            log.error("插入systemAdministrator失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteSystemAdministratorById(int id) {
        log.info("正在删除id为{}的systemAdministrator",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的systemAdministrator成功",id);
            return id;
        } else {
            log.error("删除id为{}的systemAdministrator失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateSystemAdministrator(SystemAdministrator systemAdministrator) {
        log.info("正在更新id为{}的systemAdministrator",systemAdministrator.getSystemAdministratorId());
        if (super.updateById(systemAdministrator)) {
            log.info("更新d为{}的systemAdministrator成功",systemAdministrator.getSystemAdministratorId());
            return systemAdministrator.getSystemAdministratorId();
        } else {
            log.error("更新id为{}的systemAdministrator失败",systemAdministrator.getSystemAdministratorId());
            throw new BizException("更新失败[id=" + systemAdministrator.getSystemAdministratorId() + "]");
        }
    }

}
