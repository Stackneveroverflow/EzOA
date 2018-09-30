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

    public static double minsPerHour = 60;
    public static double secondsPerHour = 3600;
    public static int indexOfHour = 0;
    public static int indexOfMin = 1;
    public static int indexOfSec = 2;

    public static BigDecimal getWorkHours(String firstTime, String lastTime) {
        BigDecimal firstHours = getHours(firstTime);
        BigDecimal lastHours = getHours(lastTime);
        return lastHours.subtract(firstHours);
    }

    private static BigDecimal getHours(String time) {
        BigDecimal result = new BigDecimal(0);
        String[] timeArr = time.split(":");

        BigDecimal hours = BigDecimal.valueOf(Long.valueOf(timeArr[indexOfHour]));
        if (timeArr.length > indexOfMin) {
            BigDecimal minOnHours = BigDecimal.valueOf(Double.valueOf(timeArr[indexOfMin]) / minsPerHour);
            result = result.add(minOnHours);
        }
        if (timeArr.length > indexOfSec) {
            BigDecimal secOnHours = BigDecimal.valueOf(Double.valueOf(timeArr[indexOfSec]) / secondsPerHour);
            result = result.add(secOnHours);
        }
        result = result.add(hours);
        result = result.setScale(1, BigDecimal.ROUND_HALF_UP);
        return result;
    }
}
