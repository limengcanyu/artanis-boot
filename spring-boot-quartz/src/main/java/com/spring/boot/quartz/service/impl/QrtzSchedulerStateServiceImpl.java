package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzSchedulerState;
import com.spring.boot.quartz.mapper.QrtzSchedulerStateMapper;
import com.spring.boot.quartz.service.QrtzSchedulerStateService;
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
public class QrtzSchedulerStateServiceImpl extends ServiceImpl<QrtzSchedulerStateMapper, QrtzSchedulerState> implements QrtzSchedulerStateService {

}
