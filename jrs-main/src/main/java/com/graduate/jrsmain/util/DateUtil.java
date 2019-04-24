package com.graduate.jrsmain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static boolean judgeDate(String message){
        boolean convertStatus = true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-DD-mm");
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(message);
        }catch (ParseException e){
            convertStatus = false;
        }
        return convertStatus;
    }
}
