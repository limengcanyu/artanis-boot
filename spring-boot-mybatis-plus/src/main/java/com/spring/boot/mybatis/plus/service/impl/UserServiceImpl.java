package com.spring.boot.mybatis.plus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.spring.boot.mybatis.plus.entity.User;
import com.spring.boot.mybatis.plus.mapper.UserMapper;
import com.spring.boot.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.mybatis.plus.vo.UserAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock
 * @since 2022-07-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectFetchSize() {
        return userMapper.selectFetchSize();
    }

    @Override
    public List<UserAddressVO> selectJoin() {
        return userMapper.selectJoin();
    }
}
