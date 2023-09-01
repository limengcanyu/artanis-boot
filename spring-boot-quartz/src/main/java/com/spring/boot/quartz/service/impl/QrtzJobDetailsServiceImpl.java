package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzJobDetails;
import com.spring.boot.quartz.mapper.QrtzJobDetailsMapper;
import com.spring.boot.quartz.service.QrtzJobDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rock
 * @since 2022-08-17
 */
@Service
public class QrtzJobDetailsServiceImpl extends ServiceImpl<QrtzJobDetailsMapper, QrtzJobDetails> implements QrtzJobDetailsService {

}
