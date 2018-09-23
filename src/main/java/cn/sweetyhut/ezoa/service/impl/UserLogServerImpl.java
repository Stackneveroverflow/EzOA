package cn.sweetyhut.ezoa.service.impl;

import cn.sweetyhut.ezoa.dao.UserLogDao;
import cn.sweetyhut.ezoa.domain.UserLog;
import cn.sweetyhut.ezoa.service.UserLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 01:15
 */
@Service
public class UserLogServerImpl implements UserLogServer {
    @Autowired
    private UserLogDao userLogDao;

    @Override
    public UserLog findByUid(String uid) {
        return userLogDao.seletByUid(uid);
    }
}
