package com.university.soa.bus;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by krrishnaaaa on Oct 02, 2015
 */
public final class PrefUtil {
    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveString(Context context, String count, String key) {
        getPref(context).edit().putString(key, count).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        return getPref(context).getString(key, defValue);
    }

    public static void removeString(Context context, String key) {
        getPref(context).edit().remove(key).apply();
    }

    public static List<BookedInfo> getAllValues(Context context) {
        Map<String, ?> values = getPref(context).getAll();
        List<BookedInfo> bookDataList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : values.entrySet()) {
            BookedInfo bookedInfo = new BookedInfo();
            bookedInfo.empcode=entry.getKey();
            bookedInfo.name = entry.getValue().toString();
          /*  bookedInfo.name2 = entry.getValue().toString();
            bookedInfo.name3 = entry.getValue().toString();
            bookedInfo.name4 = entry.getValue().toString();*/
            bookDataList.add(bookedInfo);
        }
        return bookDataList;
    }
}