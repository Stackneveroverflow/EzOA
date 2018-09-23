package cn.sweetyhut.ezoa.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:41
 */
public class UserLog {
    private Integer id;

    private String uid;

    private BigDecimal workHours;

    private Date logTime;

    public Integer getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public BigDecimal getWorkHours() {
        return workHours;
    }

    public void setWorkHours(BigDecimal workHours) {
        this.workHours = workHours;
    }

    public Date getLogTime() {
        return logTime;
    }
}
