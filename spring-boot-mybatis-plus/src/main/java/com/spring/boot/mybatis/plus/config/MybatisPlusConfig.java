package com.spring.boot.mybatis.plus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.spring.boot.mybatis.plus.mapper")
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 自动分页
//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor()); // 多租户
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // 乐观锁
//        interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor()); // sql 性能规范
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor()); // 防止全表更新与删除
        return interceptor;
    }

}
