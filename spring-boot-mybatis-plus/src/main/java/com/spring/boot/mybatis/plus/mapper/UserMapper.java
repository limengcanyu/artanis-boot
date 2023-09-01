package com.spring.boot.mybatis.plus.mapper;

import com.spring.boot.mybatis.plus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.mybatis.plus.vo.UserAddressVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rock
 * @since 2022-07-04
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> selectFetchSize();

    List<UserAddressVO> selectJoin();
}
