package cn.sweetyhut.ezoa.service;

import cn.sweetyhut.ezoa.domain.UserLog;

import java.time.LocalDate;
import java.util.List;

public interface UserLogService {
    void save(UserLog userLog);

    List<UserLog> findByUid(String uid);

    UserLog findByUidAndDate(String uid, LocalDate date);

    Boolean updateWorkHours(UserLog userLog);
}
