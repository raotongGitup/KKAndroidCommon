package com.keke.roomlib.utils;



import com.keke.roomlib.api.KKRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /*
     * 将时间转换为时间戳
     */

    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


    /*
     * 将时间戳转换为时间（年-月-日 时：分：秒）
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s=s.replace(" ","");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为时间（月-日 时：分）
     */
    public static String stampToDateWithNoYearAndSecond(long s){
        String result1 = new SimpleDateFormat("MM-dd HH:mm").format(new Date(s));
        return result1;
    } /*
     * 将时间戳转换为时间（月-日 时：分）
     */
    public static String stampToDateWithNoYea(long s){
        String result1 = new SimpleDateFormat("MM月dd日 ").format(new Date(s));
        return result1;
    }

    /*
     * 将时间戳转换为时间（月-日 时：分）
     */
    public static String stampToTimeWishMinAndSecond(long s){
        String result1 = new SimpleDateFormat("mm:ss").format(new Date(s));
        return result1;
    }

    /*
     * 将时间戳转换为日期（年.月.日）
     */
    public static String stampToDay(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        s=s.replace(" ","");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    } public static String stampToDayRQ(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        s=s.replace(" ","");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
     * 将时间戳转换为时间(年：月：日)
     */
    public static String stampToDateRq(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd ");
        s=s.replace(" ","");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    /**
     *    流水房日期
     * @param timeStamp
     * @return
     */

    public static String liuShuiFformat(long timeStamp) {
        long curTimeMillis = System.currentTimeMillis();
        Date curDate = new Date(curTimeMillis);
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        if(timeStamp >= todayStartMillis) {
            return "今天";
        }
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;
        if(timeStamp >= yesterdayStartMilis) {
            return "昨天";
        }
//        long yesterdayBeforeStartMilis = yesterdayStartMilis - oneDayMillis;
//        if(timeStamp >= yesterdayBeforeStartMilis) {
//            return "前天";
//        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return  sdf.format(new Date(timeStamp));
    }



    public static String formatSeconds(String seconda) {

        long seconds=-new Long(seconda)+ KKRoom.getInstance().getNowTime();
        long sec=new Long(seconda);
        String timeStr = seconds + "秒";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分钟" ;
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时" ;
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天" ;
                    if (day>60){
                       timeStr= stampToDay(sec*1000+"");
                    }
                }
            }
        }
        return timeStr;
    }

    public static String formatSecondsRq(boolean b, String seconda) {

        long seconds=-new Long(seconda)+ KKRoom.getInstance().getNowTime();
        long sec=new Long(seconda);
        String timeStr = seconds + "秒前";
        if (seconds > 60) {
            long second = seconds % 60;
            long min = seconds / 60;
            timeStr = min + "分钟前" ;
            if (min > 60) {
                min = (seconds / 60) % 60;
                long hour = (seconds / 60) / 60;
                timeStr = hour + "小时前" ;
                if (hour > 24) {
                    hour = ((seconds / 60) / 60) % 24;
                    long day = (((seconds / 60) / 60) / 24);
                    timeStr = day + "天前" ;
                    if (day>60){
                       timeStr= b?stampToDate(sec*1000+""):stampToDayRQ(sec*1000+"");
                    }
                }
            }
        }
        return timeStr;
    }

    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
//        if (day==0) {
//            if (hour==0){
//                return min + "分钟";
//            }
//            return  hour + "小时" ;
//        }
        return day+1 + "天" ;

    }




    public static  String getDJSTime(long time){
        long timeMillis = KKRoom.getInstance().getNowTime()*1000;

        if (timeMillis>time) {
            return "-1";
        }
        if (timeMillis==time) {
            return "00:00";
        }


        long t = (time - timeMillis)/1000;

        long f = t / 60;
        long m = t % 60;
        String str = String.format("%02d:%02d", f,m);
        return str;



    }

}
