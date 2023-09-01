package com.spring.boot.mybatis.plus.service;

import com.spring.boot.mybatis.plus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.mybatis.plus.vo.UserAddressVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rock
 * @since 2022-07-04
 */
public interface UserService extends IService<User> {
    List<User> selectFetchSize();

    List<UserAddressVO> selectJoin();
}
