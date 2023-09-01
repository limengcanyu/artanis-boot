package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzTriggers;
import com.spring.boot.quartz.mapper.QrtzTriggersMapper;
import com.spring.boot.quartz.service.QrtzTriggersService;
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
public class QrtzTriggersServiceImpl extends ServiceImpl<QrtzTriggersMapper, QrtzTriggers> implements QrtzTriggersService {

}
