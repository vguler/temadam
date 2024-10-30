package com.example.myapplication.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    private  final static SimpleDateFormat FORMATTER = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.UK);

    public static Date toDate(String value) {

        try {
            return FORMATTER.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String fromDate(Date date){

        if (date==null){
            return null;
        }
        return  FORMATTER.format(date);
    }
}
