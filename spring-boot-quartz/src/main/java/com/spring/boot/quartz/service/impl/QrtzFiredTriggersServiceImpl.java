package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzFiredTriggers;
import com.spring.boot.quartz.mapper.QrtzFiredTriggersMapper;
import com.spring.boot.quartz.service.QrtzFiredTriggersService;
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
public class QrtzFiredTriggersServiceImpl extends ServiceImpl<QrtzFiredTriggersMapper, QrtzFiredTriggers> implements QrtzFiredTriggersService {

}
