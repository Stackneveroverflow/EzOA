package cn.sweetyhut.ezoa.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:40
 */
@Data
@ToString
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
}
