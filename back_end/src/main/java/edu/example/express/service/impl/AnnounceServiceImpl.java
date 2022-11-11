package edu.example.express.service.impl;

import edu.example.express.entity.Announce;
import edu.example.express.mapper.AnnounceMapper;
import edu.example.express.service.AnnounceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

/**
* <p>
* 公告表 服务实现类
* </p>
*
* @author zlh
* @since 2022-11-11
*/
@Slf4j
@Service
public class AnnounceServiceImpl extends ServiceImpl<AnnounceMapper, Announce> implements AnnounceService {

    @Override
    public Page<Announce> listAnnouncesByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询announce: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<Announce> queryWrapper =  new QueryWrapper<Announce>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<Announce> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询announce完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public Announce getAnnounceById(int id) {
        log.info("正在查询announce中id为{}的数据",id);
        Announce announce = super.getById(id);
        log.info("查询id为{}的announce{}",id,(null == announce?"无结果":"成功"));
        return announce;
    }

    @Override
    public int insertAnnounce(Announce announce) {
        log.info("正在插入announce");
        if (super.save(announce)) {
            log.info("插入announce成功,id为{}",announce.getAnnounceId());
            return announce.getAnnounceId();
        } else {
            log.error("插入announce失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteAnnounceById(int id) {
        log.info("正在删除id为{}的announce",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的announce成功",id);
            return id;
        } else {
            log.error("删除id为{}的announce失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateAnnounce(Announce announce) {
        log.info("正在更新id为{}的announce",announce.getAnnounceId());
        if (super.updateById(announce)) {
            log.info("更新d为{}的announce成功",announce.getAnnounceId());
            return announce.getAnnounceId();
        } else {
            log.error("更新id为{}的announce失败",announce.getAnnounceId());
            throw new BizException("更新失败[id=" + announce.getAnnounceId() + "]");
        }
    }

}
