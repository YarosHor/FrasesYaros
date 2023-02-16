package com.yaros.frasesYaros.other;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED")) {
            // on device boot complete, reset the alarm
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, FLAG_IMMUTABLE);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(context));

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, sharedPref.getInt("dailyNotificationHour", 7));
            calendar.set(Calendar.MINUTE, sharedPref.getInt("dailyNotificationMin", 10));
            calendar.set(Calendar.SECOND, 1);

            Calendar newC = new GregorianCalendar();
            newC.setTimeInMillis(sharedPref.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis()));

            if (calendar.after(newC)) {
                calendar.add(Calendar.HOUR, 1);
            }

            if (manager != null) {
                manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        }
    }
}