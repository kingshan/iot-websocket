package com.kingshan.iot.push.utils;

import java.util.Date;
import org.joda.time.DateTimeUtils;
import org.joda.time.Interval;
import org.joda.time.Period;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/8 9:29
 */
public class DateUtil {

    public static String diff(Date date1, Date date2) {
        Interval  interval = new Interval(date1.getTime(),date2.getTime());
        Period period = interval.toPeriod();

        return  period.getMinutes()+" 分钟";
//        System.out.printf(
//                "%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n",
//                period.getYears(), period.getMonths(), period.getDays(),
//                period.getHours(), , period.getSeconds());

    }
}
