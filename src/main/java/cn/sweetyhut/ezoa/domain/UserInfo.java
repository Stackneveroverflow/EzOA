package cn.sweetyhut.ezoa.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:36
 */
@Data
@ToString
public class UserInfo {
    private Integer id;

    private String uid;

    private String name;

    private Integer age;

    private String tel;

    private String email;

    private Integer status;

    private String department;

    private String station;

    private Date entryTime;

    private Date quitTime;

    private String level;

    private Integer workAge;

    private Date createTime;

    private Date updateTime;
}
