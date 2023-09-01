package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzCronTriggers;
import com.spring.boot.quartz.mapper.QrtzCronTriggersMapper;
import com.spring.boot.quartz.service.QrtzCronTriggersService;
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
public class QrtzCronTriggersServiceImpl extends ServiceImpl<QrtzCronTriggersMapper, QrtzCronTriggers> implements QrtzCronTriggersService {

}
