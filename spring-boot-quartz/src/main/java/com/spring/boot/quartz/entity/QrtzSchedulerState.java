package com.spring.boot.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rock
 * @since 2022-08-17
 */
@TableName("QRTZ_SCHEDULER_STATE")
public class QrtzSchedulerState implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String instanceName;

    private Long lastCheckinTime;

    private Long checkinInterval;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    public Long getLastCheckinTime() {
        return lastCheckinTime;
    }

    public void setLastCheckinTime(Long lastCheckinTime) {
        this.lastCheckinTime = lastCheckinTime;
    }
    public Long getCheckinInterval() {
        return checkinInterval;
    }

    public void setCheckinInterval(Long checkinInterval) {
        this.checkinInterval = checkinInterval;
    }

    @Override
    public String toString() {
        return "QrtzSchedulerState{" +
            "schedName=" + schedName +
            ", instanceName=" + instanceName +
            ", lastCheckinTime=" + lastCheckinTime +
            ", checkinInterval=" + checkinInterval +
        "}";
    }
}
