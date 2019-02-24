package com.example.signup.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static final String FILE_NAME = "rationcard.shapref";

    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";

    public static void setUserId(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(USER_ID, value).commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(USER_ID, "");
    }

    public static void setUserType(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(USER_TYPE, value).commit();
    }

    public static String getUserType(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(USER_TYPE, "");
    }

}
