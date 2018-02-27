package cc.ibooker.zmalllib.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间管理类
 * Created by 邹峰立 on 2017/7/12.
 */
public class DateUtil {
    /**
     * yyyy-MM-dd
     */
    public static final String Format_Date = "yyyy-MM-dd";
    /**
     * HH:mm:ss
     */
    public static final String Format_Time = "HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyyMMddHHmmss
     */
    public static final String Format_DateTimeMillis = "yyyyMMddHHmmss";

    /**
     * 获取当前时间戳
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }

    /**
     * 获取day天前的日期yyyy-MM-dd
     *
     * @param day 天
     */
    public static String getDayBeforeDate(int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(now.getTime());
    }

    /**
     * 获取day天前的日期时间yyyyMMddHHmmss
     *
     * @param day 天
     */
    public static String getDayBeforeDateTimeMillis(int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(now.getTime());
    }

    /**
     * 获取格式化day天前的日期时间
     *
     * @param day    天
     * @param format 格式化
     */
    public static String getDayBeforeFormatDateTime(int day, String format) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return new SimpleDateFormat(format, Locale.getDefault()).format(now.getTime());
    }

    /**
     * 格式化当前日期
     *
     * @param format 格式化
     */
    public static String getCurrentFormatDate(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format, Locale.getDefault());
        return t.format(new Date());
    }

    /**
     * 获取当前时间HH:mm:ss
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    /**
     * 格式化当前日期时间
     *
     * @param format 格式化
     */
    public static String getFormatCurrentDateTime(String format) {
        SimpleDateFormat t = new SimpleDateFormat(format, Locale.getDefault());
        return t.format(new Date());
    }

    /**
     * 格式化日期
     *
     * @param date   日期对象
     * @param format 格式化
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat t = new SimpleDateFormat(format, Locale.getDefault());
        return t.format(date);
    }

    /**
     * 获取当前日期时间
     */
    public static String getCurrentDateTime() {
        return getFormatCurrentDateTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化字符串为日期格式yyyy-MM-dd HH:mm:ss
     *
     * @param str 待格式化字符串
     */
    public static Date parseDateTime(String str) {
        try {
            if (TextUtils.isEmpty(str))
                return null;
            if (str.length() <= 10)
                return parseDate(str);
            else
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自定义字符串转换日期格式
     *
     * @param str    待格式化字符串
     * @param format 格式化
     */
    public static Date parseFormatDate(String str, String format) {
        try {
            if (TextUtils.isEmpty(str))
                return null;
            SimpleDateFormat t = new SimpleDateFormat(format, Locale.getDefault());
            return t.parse(str.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转化成日期格式yyyy-MM-dd
     *
     * @param str 待格式化字符串
     */
    public static Date parseDate(String str) {
        try {
            if (TextUtils.isEmpty(str))
                return null;
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前中文日期时间
     */
    public static String getCurrentDateTimeCN() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int mouth = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DATE);
        int hour = ca.get(Calendar.HOUR);
        int mimute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        return year + "年" + (mouth + 1) + "月" + day + "日" + hour + "时" + mimute + "分" + second + "秒";
    }

    /**
     * 获取当前中文日期
     */
    public static String getCurrentDateCN() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int mouth = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DATE);
        return year + "年" + (mouth + 1) + "月" + day + "日";
    }

    /**
     * 获取当前中文时间
     */
    public static String getCurrentTimeCN() {
        Calendar ca = Calendar.getInstance();
        int hour = ca.get(Calendar.HOUR);
        int mimute = ca.get(Calendar.MINUTE);
        int second = ca.get(Calendar.SECOND);
        return hour + "时" + mimute + "分" + second + "秒";
    }

    /**
     * 获取当前年份
     */
    public static int getCurrentYear() {
        Calendar ca = Calendar.getInstance();
        return ca.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     */
    public static int getCurrentMonth() {
        Calendar ca = Calendar.getInstance();
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     */
    public static int getCurrentDay() {
        Calendar ca = Calendar.getInstance();
        return ca.get(Calendar.DATE);
    }

    /**
     * 字符串日期格式转换成日期格式
     *
     * @param datetime 字符串日期
     */
    public static String getStrToDateTime(String datetime) {
        String mDateTime = "";
        try {
            if (!TextUtils.isEmpty(datetime)) {
                if (datetime.length() == 8) {
                    //例如：参数为 19740306 时，返回下面代码执行效果
                    mDateTime = (datetime.substring(0, 4) + "-"
                            + datetime.substring(5, 6) + "-"
                            + datetime.substring(7, 8));
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
                    Date date = formatter.parse(datetime);
                    mDateTime = formatter.format(date);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 将字符串转换成中文日期时间
     *
     * @param time 传入数据的格式为19740306020100
     * @return 返回的数据格式为1974年03月06日02时01分00秒
     */
    public static String getFormatDateTimeCN(String time) {
        try {
            if (!TextUtils.isEmpty(time)) {
                if (time.length() == 14) {
                    // 参数为 例如：19740306 时，返回下面代码执行效果
                    return time.subSequence(0, 4) + "年"
                            + time.subSequence(4, 6) + "月"
                            + time.subSequence(6, 8) + "日"
                            + time.subSequence(8, 10) + "时"
                            + time.subSequence(10, 12) + "分"
                            + time.subSequence(12, 14) + "秒";
                } else if (time.length() > 9) {// 1988-11-11 00:00:00
                    String temp = (String) time.subSequence(0, 10);
                    return getFormatDateCN(temp.replaceAll("-", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将字符串转换成日期时间
     *
     * @param time 传入数据的格式为19740306020100
     * @return 返回的数据格式为1974-03-06 02:01:00
     */
    public static String getFormatDateTime(String time) {
        try {
            if (!TextUtils.isEmpty(time)) {
                if (time.length() == 14) {
                    // 参数为 例如：19740306 时，返回下面代码执行效果
                    return time.subSequence(0, 4) + "-"
                            + time.subSequence(4, 6) + "-"
                            + time.subSequence(6, 8) + " "
                            + time.subSequence(8, 10) + ":"
                            + time.subSequence(10, 12) + ":"
                            + time.subSequence(12, 14);
                } else if (time.length() > 9) {// 1988-11-11 00:00:00
                    String temp = (String) time.subSequence(0, 10);
                    return getFormatDateCN(temp.replaceAll("-", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将字符串转换成中文日期
     *
     * @param time 传入数据的格式为19740306
     * @return 返回的数据格式为1974年03月06日
     */
    public static String getFormatDateCN(String time) {
        try {
            if (!TextUtils.isEmpty(time)) {
                if (time.length() == 8) {
                    // 参数为 例如：19740306 时，返回下面代码执行效果
                    return time.subSequence(0, 4) + "年"
                            + time.subSequence(4, 6) + "月"
                            + time.subSequence(6, 8) + "日";
                } else if (time.length() > 9) {// 1988-11-11 00:00:00
                    String temp = time.substring(0, 10);
                    return getFormatDateCN(temp.replaceAll("-", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 将字符串转换成中文月日
     *
     * @param time 传入数据的格式为19740306
     * @return 返回的数据格式为03月06日
     */
    public static String getFormatDate(String time) {
        try {
            if (!TextUtils.isEmpty(time)) {
                if (time.length() == 8) {
                    // 参数为 例如：19740306 时，返回下面代码执行效果
                    return time.subSequence(4, 6) + "月"
                            + time.subSequence(6, 8) + "日";
                } else if (time.length() > 9) {// 1988-11-11 00:00:00
                    String temp = (String) time.subSequence(0, 10);
                    return getFormatDate(temp.replaceAll("-", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param str1 时间参数 1：1974-03-06 02:01:00
     * @param str2 时间参数 2：2017-07-02
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceDateTimeLong(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(Format_DateTime, Locale.getDefault());
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new long[]{day, hour, min, sec};
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceDateTimeCN(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(Format_DateTime, Locale.getDefault());
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 将字符串格式化成日历
     *
     * @param str    格式化字符串
     * @param format 格式化格式
     */
    public static Calendar strToCalendar(String str, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format, Locale.getDefault());
            Date date = sf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期字符串，yyyy-MM-dd格式为MM-dd
     *
     * @param str 格式化字符串：yyyy-MM-dd
     */
    public static String formatDateStrBySplit(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            String[] dates = str.split("-");
            if (dates.length >= 3)
                return dates[1] + "-" + dates[2];
            else
                return null;
        }
    }

    /**
     * 格式化时间字符串，HH:mm:ss格式为HH:mm
     *
     * @param str 格式化字符串：HH:mm:ss
     */
    public static String formatTimeStrBySplit(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            String[] times = str.split(":");
            if (times.length >= 2)
                return times[0] + ":" + times[1];
            else
                return null;
        }
    }

    /**
     * 整数(秒数)转换为时分秒格式(xx时xx分xx秒)
     *
     * @param time 秒数
     * @return 时分秒
     */
    public static String secToTime(int time) {
        String timeStr;
        int hour;
        int minute;
        int second;
        if (time <= 0)
            return "00" + "时" + "00" + "分" + "00" + "秒";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99" + "时" + "59" + "分" + "59" + "秒";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    private static String unitFormat(int i) {
        String retStr;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

}
