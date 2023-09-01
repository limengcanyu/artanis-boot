package com.spring.boot.quartz.service.impl;

import com.spring.boot.quartz.entity.QrtzLocks;
import com.spring.boot.quartz.mapper.QrtzLocksMapper;
import com.spring.boot.quartz.service.QrtzLocksService;
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
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksMapper, QrtzLocks> implements QrtzLocksService {

}
