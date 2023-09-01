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
@TableName("QRTZ_PAUSED_TRIGGER_GRPS")
public class QrtzPausedTriggerGrps implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String triggerGroup;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    @Override
    public String toString() {
        return "QrtzPausedTriggerGrps{" +
            "schedName=" + schedName +
            ", triggerGroup=" + triggerGroup +
        "}";
    }
}
