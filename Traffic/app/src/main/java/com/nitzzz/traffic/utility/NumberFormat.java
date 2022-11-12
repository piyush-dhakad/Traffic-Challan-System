package com.nitzzz.traffic.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberFormat {

    public static String order_id(int val){
        return new DecimalFormat("000000").format(val);
    }

    public static String to_2d(int val){
        return new DecimalFormat("00.00").format(val);
    }

    public static String to_2d(double val){
        return new DecimalFormat("00.00").format(val);
    }

    public static String to_int_signed(int val){
        return new DecimalFormat("00").format(val);
    }

    public static String to_int_signed(double val){
        return new DecimalFormat("00").format(val);
    }

    public static int to_int(String val){

        try{
            return Integer.parseInt(val);
        }catch (Exception ex){
            return 0;
        }
    }

    public static double to_double(String val){

        try{
            return Double.parseDouble(val);
        }catch (Exception ex){
            return 0;
        }
    }

    public static Date date_source(String date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    public static String date_source(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String date_source(long milli){
        return new SimpleDateFormat("yyyy-MM-dd").format(milli);
    }

    public static String date_format1(long milli){
        return new SimpleDateFormat("dd-MM-yyyy").format(milli);
    }

    public static String date_format2(long milli){
        return new SimpleDateFormat("dd-MM-yyyy, EEEE").format(milli);
    }

    public static String date_format3(long milli){
        return new SimpleDateFormat("dd MMMM yyyy").format(milli);
    }

    public static String date_time_format1(long milli){
        return new SimpleDateFormat("dd MMMM yyyy HH:mm:ss a").format(milli);
    }

    public static String time_format1(long milli){
        return new SimpleDateFormat("HH:mm:ss a").format(milli);
    }



    public static int get_days(long milli1, long milli2){

        long diff = Math.abs(milli1 - milli2);
        return (int)(diff / (24 * 60 * 60 * 1000));
    }


    public static String hide_single_quote(String str){

        if (isNullOrEmpty(str)) return "";
        return str.replaceAll("'", "''")
                .replaceAll("\n", "")
                .replaceAll("\r", "");
    }


    public static boolean isNullOrEmpty(String str){

        if(str == null || str.isEmpty())
            return true;
        else
            return false;
    }


    public static String mobile_number_format(String mobile){

        if(mobile == null || mobile.isEmpty() || mobile.length() < 10)
            return mobile;

        return mobile.substring(0, 3) + " " + mobile.substring(3, 8) + " " + mobile.substring(8);
    }


}
