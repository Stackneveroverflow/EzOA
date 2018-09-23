package cn.sweetyhut.ezoa.service;

import cn.sweetyhut.ezoa.domain.UserLog;

public interface UserLogServer {

    UserLog findByUid(String uid);
}
