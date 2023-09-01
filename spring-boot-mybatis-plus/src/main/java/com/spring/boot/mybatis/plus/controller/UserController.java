package com.spring.boot.mybatis.plus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.mybatis.plus.entity.User;
import com.spring.boot.mybatis.plus.service.UserService;
import com.spring.boot.mybatis.plus.vo.UserAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rock
 * @since 2022-07-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * http://localhost:8081/user/selectFetchSize
     *
     * @return
     */
    @GetMapping("selectFetchSize")
    public List<User> selectFetchSize() {
        return userService.selectFetchSize();
    }

    /**
     * http://localhost:8081/user/selectJoin
     *
     * @return
     */
    @GetMapping("selectJoin")
    public List<UserAddressVO> selectJoin() {
        return userService.selectJoin();
    }

    /**
     * http://localhost:8081/user/selectForeach
     *
     * @return
     */
    @GetMapping("selectForeach")
    public List<User> selectForeach() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        return userService.listByIds(ids);
    }

}
