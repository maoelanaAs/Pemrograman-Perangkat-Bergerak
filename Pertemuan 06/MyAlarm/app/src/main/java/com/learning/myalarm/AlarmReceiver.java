package com.learning.myalarm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    private static final int NOTIFICATION_ID = 0;
    public static final String NOTIFICATION_CHANNEL_ID = "4655";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = NOTIFICATION_CHANNEL_ID;
            String description = context.getString(R.string.content);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = new NotificationChannel
                    (NOTIFICATION_CHANNEL_ID, name, importance);

            mChannel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService
                    (NotificationManager.class);
            notificationManager.createNotificationChannel(mChannel);
        }

        Toast.makeText(context, "Alarm Aktif", Toast.LENGTH_SHORT).show();
        Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Ringtone r = RingtoneManager.getRingtone(context, notif);
        r.play();

        NotificationManager notifMgr = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, NOTIFICATION_ID,
                i, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder build = new NotificationCompat.Builder
                (context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_lock_idle_alarm)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .setContentTitle(context.getString(R.string.head))
                .setContentText(context.getString(R.string.content))
                .setContentIntent(pi)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        notifMgr.notify(NOTIFICATION_ID, build.build());
        Toast.makeText(context, context.getString(R.string.content),
                Toast.LENGTH_LONG).show();
    }
}

