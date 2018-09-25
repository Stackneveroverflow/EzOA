package cn.sweetyhut.ezoa.dao;

import cn.sweetyhut.ezoa.domain.UserLog;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/23 00:44
 */
@Mapper
public interface UserLogDao {

    @Insert("INSERT INTO user_log(uid,work_hours,log_date) VALUES(#{uid},#{workHours},#{logDate})")
    void save(UserLog userLog);

    @Select("SELECT log_date, work_hours FROM user_log WHERE uid = #{uid}")
    List<UserLog> findByUid(@Param("uid") String uid);

    @Select("SELECT work_hours FROM user_log WHERE uid = #{uid} AND log_date = #{date}")
    UserLog findByUidAndDate(@Param("uid") String uid, @Param("date") LocalDate date);

    @Update("UPDATE user_log SET work_hours = #{workHours} WHERE uid = #{uid} AND log_date = #{logDate}")
    Boolean updateWorkHours(UserLog userLog);
}
