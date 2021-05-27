package com.example.mylibrarytest

import java.text.SimpleDateFormat
import java.util.*



class Utils {
    companion object {
        fun getDateFromLong(timestamp : Long) : String {
            val dateformat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            return dateformat.format(Date(timestamp));
        }

        fun getDateTimeFromDate(date : Date) : String{
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss yyyy MMM dd", Locale.ENGLISH);
            val currentDateAndTime = simpleDateFormat.format(date);
            return currentDateAndTime;
        }
    }
}