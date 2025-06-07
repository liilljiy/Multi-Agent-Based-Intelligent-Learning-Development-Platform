package com.ruanko.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 */
public class TimeUtils {

    private static final String TAG="TimeUtils";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static final Calendar mCalendar = Calendar.getInstance();
    private static final String[] monthList = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};

    /**
     * 时间补0
     * @param time
     * @return
     */
    public static String timeAddZero(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return ""+time;
        }
    }
    /**
     * 毫秒转秒
     *
     * @param fMil
     * @return
     */
    public static String int2String(int fMil) {
        int sec = fMil / 1000;    //毫秒转秒
        int min = sec / 60;    //分钟
        sec = sec % 60;        //秒
        if (min < 10) {    //分钟补0
            if (sec < 10) {    //秒补0
                return "0" + min + ":0" + sec;
            } else {
                return "0" + min + ":" + sec;
            }
        } else {
            if (sec < 10) {    //秒补0
                return min + ":0" + sec;
            } else {
                return min + ":" + sec;
            }
        }

    }

    /**
     * 60秒倒计时
     *
     * @param fMil
     * @return
     */
    public static String countDown(int fMil) {
        int sec = fMil / 1000;    //毫秒转秒
        int countTime = 60 - sec;

        return countTime + "''";
    }

    /**
     * 验证码倒计时
     *
     * @param sec
     * @return
     */
    public static String countDownIdentity(int sec) {
        int countTime = 20 - sec;
        return countTime + "'s";
    }

    /**
     * 获取当前的日期
     *
     * @return yyyy年MM月dd日
     */
    public static String getCurrentDate() {
        String currentDateStr = dateFormat.format(mCalendar.getTime());
        return currentDateStr;
    }
    /**
     * 返回当前时间的格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date());
    }
    public static String getCurrentTime1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date());
    }

    public static String getCurrentDate1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(new Date());
    }
    public static String getFormatMonthDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.CHINA);
        return sdf.format(new Date());
    }

    //返回近一小时、一天、一周、一月、三月的日期
    public static String getLatestTime(String tag) {
        long starttime=60*60*1000*24L;
        switch (tag){
            case "halfahour":
                starttime=starttime/48;
                break;
            case "ahour":
                starttime=starttime/24;
                break;
            case "aday":
                starttime=starttime;
                break;
            case "aweek":
                starttime=starttime*7;
                break;
            case "threeweek":
                starttime=starttime*7*3;
                break;
            case "amonth":
                starttime=starttime*30;
                break;
            case "threemonth":
                starttime=starttime*30*3;
                break;
        }
        Long time=System.currentTimeMillis()-starttime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(time);
    }
    //返回近一小时、一天、一周、一月、三月的日期毫秒数
    public static String getLatestTimeMillis(String tag) {
        long starttime=60*60*1000*24L;
        switch (tag){
            case "ahour":
                starttime=starttime/24;
                break;
            case "aday":
                starttime=starttime;
                break;
            case "aweek":
                starttime=starttime*7;
                break;
            case "threeweek":
                starttime=starttime*7*3;
                break;
            case "amonth":
                starttime=starttime*30;
                break;
            case "threemonth":
                starttime=starttime*30*3;
                break;
        }
        Long time=System.currentTimeMillis()-starttime;
        return String.valueOf(time);
    }

    /**
     * 返回选定时间的格式为 yyyy-MM-dd
     *
     * @return
     */
    public static String getFormatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return date!=null?sdf.format(date):null;
    }
    public static String getFormatMonthDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.CHINA);
        return sdf.format(date);
    }
    public static String getFormatMonthDayStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日", Locale.CHINA);
        return sdf.format(date);
    }
    /**
     * 返回选定时间的格式为 yyyy-MM-dd HH:mm
     *
     * @return
     */
    public static String getFormatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        return sdf.format(date);
    }
    /**
     * 返回选定时间的格式为 yyyy-MM-dd HH:mm
     *
     * @return
     */
    public static String getFormatDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return date!=null?sdf.format(date):null;
    }
    public static String getFormatDateTime1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);
        return date!=null?sdf.format(date):null;
    }
    /**
     * 根据选定时间的格式 yyyy-MM-dd HH:mm:ss 字符串，返回date
     *
     * @return
     */
    public static Date getParseTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(String pTime) throws Exception {
        String date = pTime +"1日";
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(date));
        int dayForWeek = 0;
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            dayForWeek = 7;
        }else{
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取是几号
     *
     * @return dd
     */
    public static int getCurrentDay() {
        return mCalendar.get(Calendar.DATE);
    }

    /**
     * 获取当前的月份
     *
     * @return
     */
    public static int getCurrentMonth() {
        return mCalendar.get(Calendar.MONTH);
    }

    /**
     * 根据月份获取当前的中文月份
     *
     * @return
     */
    public static String getCurrentStringMonth(int month) {

        return monthList[month];
    }

    /**
     * 获取当前的年份
     *
     * @return
     */
    public static int getCurrentYear() {
        return mCalendar.get(Calendar.YEAR);
    }

    /**
     * 根据指定日期获取月份
     * @param dateStr
     * @return
     */
    public static String getMonthByDate(String dateStr){
        String monthStr = null;
        // 用parse方法，可能会异常，所以要try-catch
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH);
            monthStr = getCurrentStringMonth(month);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return monthStr;
    }

    /**
     * 根据指定日期获取月份和年份  用在预约选时间界面中
     * @param dateStr
     * @return
     */
    public static String getMonthYearByDate(String dateStr){
        String smallCalendarStr = null;
        // 用parse方法，可能会异常，所以要try-catch
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            int year = calendar.get(Calendar.YEAR);
            // 这里要注意，月份是从0开始。
            int month = calendar.get(Calendar.MONTH);
//            // 获取天
//            int day = calendar.get(Calendar.DAY_OF_MONTH);

            smallCalendarStr = year + "年" + month + "月";

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return smallCalendarStr;
    }

    /**
     * 以周日为一周的第一天   获取一周的时间
     *
     * @return 一周日期的list
     */
    public static List<String> getDateListBySunday() {
        List<String> weekList = new ArrayList<>();
        Calendar mCalendar = Calendar.getInstance();

        for (int i = 0; i < 7; i++) {
            //以周日为一周的第一天
            mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            mCalendar.add(Calendar.DATE, i);
            String date = dateFormat.format(mCalendar.getTime());
            weekList.add(date);
        }
        return weekList;
    }


    /**
     * 以周一为一周的第一天   获取一周的时间
     *
     * @return 一周日期的list
     */
    public static List<String> getDateListByMonday() {
        List<String> weekList = new ArrayList<>();
        Calendar mCalendar = Calendar.getInstance();

        for (int i = 0; i < 7; i++) {
            //以周日为一周的第一天
            mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            mCalendar.add(Calendar.DATE, i);
            String date = dateFormat.format(mCalendar.getTime());
            weekList.add(date);
        }
        return weekList;
    }

    /**
     * 根据操作次数去 计算当前一周内的日期
     * @param operateNums 操作次数
     * @return
     */
    public static List<String> getOperateDates(int operateNums){
        List<String> dateList = new ArrayList<>();
        Calendar mCalendar = Calendar.getInstance();

        mCalendar.add(Calendar.WEEK_OF_YEAR, operateNums);
        for (int i = 0; i < 7; i++) {
            //以周日为一周的第一天
            mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            mCalendar.add(Calendar.DATE, i);
            String date = dateFormat.format(mCalendar.getTime());
            dateList.add(date);
        }

        return dateList;
    }

    /**
     * 根据date列表获取day列表
     *
     * @param dateList
     * @return
     */
    public static List<Integer> dateListToDayList(List<String> dateList) {
        Calendar calendar = Calendar.getInstance();
        List<Integer> dayList = new ArrayList<>();
        for (String date : dateList) {
            try {
                calendar.setTime(dateFormat.parse(date));
                int day = calendar.get(Calendar.DATE);
                dayList.add(day);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dayList;
    }

    /**
     * 根据输入的日期判断距离当前日期多久
     */
    public static double getDaysFromTodayToDate(String dateStr){
        if(dateStr==null||"".equals(dateStr))
            return -1;
        double days = 0;
        long date1;
        try {
            date1 = dateFormat.parse(dateStr).getTime();

            long date2 = System.currentTimeMillis();
            //计算两个日期之间相差的毫秒数的绝对值
            long ms= Math.abs(date2 - date1);
            //毫秒数除以一天的毫秒数,就得到了天数
            days = Math.floor(ms / (24 * 3600 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
    public static String getDaysFromDateTimeToToday(Date date){
        double days = 0;
        long date1;
        if(date==null)
            return null;
        date1 = date.getTime();
        long date2 = System.currentTimeMillis();
        //计算两个日期之间相差的毫秒数的绝对值
        long ms= Math.abs(date2 - date1);

        // 计算相差的天数
        days = Math.floor(ms / (24 * 3600*1000 ));
        // 计算天数后剩余的毫秒数
        double leave1 = ms % (24 * 3600000 );
        // 计算出小时数
        double hours = Math.floor(leave1 / 3600000);
        // 计算小时数后剩余的毫秒数
        double leave2 = leave1 % 3600000;
        // 计算相差分钟数
        double minutes = Math.floor(leave2 / 60000);

        return (int)days + "天"+ (int)hours + "时" + (int)minutes + "分";
    }
    public static String getDaysFromDateToToday(Date date){
        double days = 0;
        long date1;
        if(date==null)
            return null;
        date1 = date.getTime();
        long date2 = System.currentTimeMillis();
        //计算两个日期之间相差的毫秒数的绝对值
        long ms= Math.abs(date2 - date1);
        //毫秒数除以一天的毫秒数,就得到了天数
        days = Math.floor(ms / (24 * 3600 * 1000));
        return days +"天";
    }
    //日期判定
    private boolean dateiscorrect(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
        if(startYear<endYear)
            return true;

        if(startYear==endYear || (endYear==0 && startYear<=TimeUtils.getCurrentYear()) ) {
            if (startMonth < endMonth )
                return true;
            if (startMonth == endMonth || (endMonth==0  && startMonth-1<=TimeUtils.getCurrentYear()))
                return startDay <= endDay || (endDay == 0 && startDay <= TimeUtils.getCurrentYear());
        }

        return false;
    }

    //判断选择的时间是否在当前时间之后
    public static boolean dateaftertoday(int startYear, int startMonth, int startDay) {
        if(startYear>TimeUtils.getCurrentYear())
            return true;
        if(startYear==TimeUtils.getCurrentYear() && startMonth-1>TimeUtils.getCurrentMonth())
            return true;
        return startYear == TimeUtils.getCurrentYear() && startMonth - 1 == TimeUtils.getCurrentMonth() && startDay > TimeUtils.getCurrentDay();
    }
}
