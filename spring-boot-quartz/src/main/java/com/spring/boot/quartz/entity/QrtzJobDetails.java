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
@TableName("QRTZ_JOB_DETAILS")
public class QrtzJobDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schedName;

    private String jobName;

    private String jobGroup;

    private String description;

    private String jobClassName;

    private String isDurable;

    private String isNonconcurrent;

    private String isUpdateData;

    private String requestsRecovery;

    private Blob jobData;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }
    public String getIsDurable() {
        return isDurable;
    }

    public void setIsDurable(String isDurable) {
        this.isDurable = isDurable;
    }
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }
    public String getIsUpdateData() {
        return isUpdateData;
    }

    public void setIsUpdateData(String isUpdateData) {
        this.isUpdateData = isUpdateData;
    }
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }
    public Blob getJobData() {
        return jobData;
    }

    public void setJobData(Blob jobData) {
        this.jobData = jobData;
    }

    @Override
    public String toString() {
        return "QrtzJobDetails{" +
            "schedName=" + schedName +
            ", jobName=" + jobName +
            ", jobGroup=" + jobGroup +
            ", description=" + description +
            ", jobClassName=" + jobClassName +
            ", isDurable=" + isDurable +
            ", isNonconcurrent=" + isNonconcurrent +
            ", isUpdateData=" + isUpdateData +
            ", requestsRecovery=" + requestsRecovery +
            ", jobData=" + jobData +
        "}";
    }
}
