package cn.sweetyhut.ezoa.domain;

import java.util.Date;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:40
 */
public class UserAccount {
    private Integer id;

    private String account;

    private String password;

    private String uid;

    private Integer status;

    private String group;

    private Integer limits;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
}
