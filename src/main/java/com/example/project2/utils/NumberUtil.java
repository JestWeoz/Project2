package com.example.project2.utils;

public class NumberUtil
{
    public static boolean isNumber (String str) {
        try{
            Long number = Long.parseLong(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
