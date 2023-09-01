package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzSimpleTriggers;
import com.spring.boot.quartz.mapper.QrtzSimpleTriggersMapper;
import com.spring.boot.quartz.service.QrtzSimpleTriggersService;
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
public class QrtzSimpleTriggersServiceImpl extends ServiceImpl<QrtzSimpleTriggersMapper, QrtzSimpleTriggers> implements QrtzSimpleTriggersService {

}
