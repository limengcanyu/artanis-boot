package com.spring.boot.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 * 
 * </p>
 *
 * @author rock
 * @since 2022-08-17
 */
@TableName("QRTZ_CALENDARS")
public class QrtzCalendars implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String calendarName;

    private Blob calendar;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }
    public Blob getCalendar() {
        return calendar;
    }

    public void setCalendar(Blob calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        return "QrtzCalendars{" +
            "schedName=" + schedName +
            ", calendarName=" + calendarName +
            ", calendar=" + calendar +
        "}";
    }
}
