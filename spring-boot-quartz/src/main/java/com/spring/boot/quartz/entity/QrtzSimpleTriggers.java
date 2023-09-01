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
@TableName("QRTZ_SIMPLE_TRIGGERS")
public class QrtzSimpleTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerName;

    private String triggerGroup;

    private Long repeatCount;

    private Long repeatInterval;

    private Long timesTriggered;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }
    public Long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Long repeatCount) {
        this.repeatCount = repeatCount;
    }
    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
    public Long getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

    @Override
    public String toString() {
        return "QrtzSimpleTriggers{" +
            "schedName=" + schedName +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", repeatCount=" + repeatCount +
            ", repeatInterval=" + repeatInterval +
            ", timesTriggered=" + timesTriggered +
        "}";
    }
}
