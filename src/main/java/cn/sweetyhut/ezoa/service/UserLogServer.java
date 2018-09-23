package cn.sweetyhut.ezoa.service;

import cn.sweetyhut.ezoa.domain.UserLog;
import org.springframework.stereotype.Service;

public interface UserLogServer {

    UserLog findByUid(String uid);
}
