package cn.sweetyhut.ezoa.utils;

import java.math.BigDecimal;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/26 23:01
 */
public class TimeUtil {
    public static int minsPerHour = 60;
    public static int secondsPerHour = 3600;
    public static int indexOfHour = 0;
    public static int indexOfmin = 1;
    public static int indexOfSec = 2;

    public static BigDecimal getWorkHours(String firstTime, String lastTime) {
        BigDecimal firstHours = getHours(firstTime);
        BigDecimal lastHours = getHours(lastTime);
        return lastHours.subtract(firstHours);
    }

    private static BigDecimal getHours(String time) {
        BigDecimal result = new BigDecimal(0);
        String[] timeArr = time.split(":");

        BigDecimal hours = BigDecimal.valueOf(Integer.valueOf(timeArr[indexOfHour]));
        if (timeArr.length > indexOfmin) {
            BigDecimal minOnHours = BigDecimal.valueOf(Integer.valueOf(timeArr[indexOfmin]) / minsPerHour);
            result.add(minOnHours);
        }
        if (timeArr.length > indexOfSec) {
            BigDecimal secOnHours = BigDecimal.valueOf(Integer.valueOf(timeArr[indexOfSec]) / secondsPerHour);
            result.add(secOnHours);
        }
        result.add(hours);
        result.setScale(1, BigDecimal.ROUND_HALF_UP);
        return result;
    }
}
