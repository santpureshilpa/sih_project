package com.example.signup.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {
    private static final String FILE_NAME = "rationcard.shapref";

    private static final String USER_ID = "userId";
    private static final String USER_TYPE = "userType";
    private static final String CLICKED_DISTRIBUTOR = "clickedDis";
    private static final String TOTAL_PAYABLE_AMOUNT_FROM_CUS_TO_DES = "totalPayable";

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

    public static String getTotalPayableAmountFromCusToDes(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(TOTAL_PAYABLE_AMOUNT_FROM_CUS_TO_DES, "");
    }

    public static void setTotalPayableAmountFromCusToDes(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(TOTAL_PAYABLE_AMOUNT_FROM_CUS_TO_DES, value).commit();
    }

    public static String getClickedDistributor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(CLICKED_DISTRIBUTOR, "");
    }

    public static void setClickedDistributor(Context context, String value) {
        SharedPreferences preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(CLICKED_DISTRIBUTOR, value).commit();
    }

}
