package com.nzy.viewstudy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationUtils extends ContextWrapper {
    public static final String TAG = NotificationUtils.class.getSimpleName();

    public static final String id = "channel_1";
    public static final String name = "notification";
    private NotificationManager manager;
    private Context mContext;

    public NotificationUtils(Context base) {
        super(base);
        mContext = base;
    }

    @RequiresApi(api = 26)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public void sendNotificationFullScreen( String title, String content, String type) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel();
            Notification notification = getChannelNotificationQ
                    (title, content, type);
            getManager().notify(1, notification);
        }
    }

    public void clearAllNotifiication(){
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    public  Notification getChannelNotificationQ(String title, String content, String type) {
        Intent fullScreenIntent = new Intent(this, MainActivity.class);
        fullScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        fullScreenIntent.putExtra("action", "callfromdevice");
        fullScreenIntent.putExtra("type", type);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, id)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setTicker(content)
                        .setContentText(content)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setCategory(Notification.CATEGORY_CALL)
                        .setFullScreenIntent(fullScreenPendingIntent,true);

        Notification incomingCallNotification = notificationBuilder.build();
        return incomingCallNotification;
    }

}