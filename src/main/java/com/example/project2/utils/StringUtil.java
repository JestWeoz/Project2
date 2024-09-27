package com.example.project2.utils;

public class StringUtil {
    public static boolean checkString(String str) {
        if (str != null && !str.isEmpty()) {
            return true;
        }
        return false;
    }
}
