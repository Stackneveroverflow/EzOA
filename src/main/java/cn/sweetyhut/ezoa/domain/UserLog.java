package cn.sweetyhut.ezoa.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:41
 */
@Data
@ToString
public class UserLog {
    private Integer id;

    private String uid;

    private BigDecimal workHours;

    private LocalDate logDate;
}
