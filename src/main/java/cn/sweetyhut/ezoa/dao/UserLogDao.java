package cn.sweetyhut.ezoa.dao;

import cn.sweetyhut.ezoa.domain.UserLog;
import org.apache.ibatis.annotations.*;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:44
 */
@Mapper
public interface UserLogDao {
    @Select("SELECT * from user_log where uid = #{uid}")
    UserLog seletByUid(@Param("uid") String uid);
}
