package edu.example.express.service.impl;

import edu.example.express.entity.Deliveryman;
import edu.example.express.entity.User;
import edu.example.express.mapper.UserMapper;
import edu.example.express.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import edu.example.express.exception.bizException.BizException;

/**
* <p>
* 用户 服务实现类
* </p>
*
* @author csk
* @since 2022-11-05
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> listUsersByPage(int page, int pageSize, String factor) {
        log.info("正在执行分页查询user: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        QueryWrapper<User> queryWrapper =  new QueryWrapper<User>().like("", factor);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<User> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询user完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public User getUserById(int id) {
        log.info("正在查询user中id为{}的数据",id);
        User user = super.getById(id);
        log.info("查询id为{}的user{}",id,(null == user?"无结果":"成功"));
        return user;
    }

    @Override
    public int insertUser(User user) {
        log.info("正在插入user");
        System.out.println(user.getEmail());
        if (super.save(user)) {
            log.info("插入user成功,id为{}",user.getUserId());
            return user.getUserId();
        } else {
            log.error("插入user失败");
            throw new BizException("添加失败");
        }
    }

    @Override
    public int deleteUserById(int id) {
        log.info("正在删除id为{}的user",id);
        if (super.removeById(id)) {
            log.info("删除id为{}的user成功",id);
            return id;
        } else {
            log.error("删除id为{}的user失败",id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public int updateUser(User user) {
        log.info("正在更新id为{}的user",user.getUserId());
        if (super.updateById(user)) {
            log.info("更新d为{}的user成功",user.getUserId());
            return user.getUserId();
        } else {
            log.error("更新id为{}的user失败",user.getUserId());
            throw new BizException("更新失败[id=" + user.getUserId() + "]");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("正在查询user中email为{}的数据", email);
        QueryWrapper<User> queryWrapper =  new QueryWrapper<User>().eq("email", email);
        User user = super.getOne(queryWrapper);
        log.info("查询email为{}的user{}",email,(null == user?"无结果":"成功"));
        return user;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        log.info("正在查询user中phoneNumber为{}的数据", phoneNumber);
        QueryWrapper<User> queryWrapper =  new QueryWrapper<User>().eq("phone_number", phoneNumber);
        User user = super.getOne(queryWrapper);
        log.info("查询phoneNumber为{}的user{}",phoneNumber,(null == user?"无结果":"成功"));
        return user;
    }

    @Override
    public Page<User> getUserListByQuerymapper(int page, int pageSize, String factor, QueryWrapper<User> queryWrapper) {
        Page<User> userPage = super.page(new Page<>(page,pageSize),queryWrapper);
        return userPage;
    }
}
