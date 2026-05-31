package com.martinmimigames.tinymusicplayer.samsung;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.media.app.NotificationCompat.MediaStyle;

public class SamsungLiveNotifier {
    
    private static final String CHANNEL_ID = "music_live_channel";
    private static final int NOTIFICATION_ID = 1001;
    
    public static void createLiveNotification(
            Context context,
            String title,
            String artist,
            int progress,
            int smallIconResId) {
        
        ensureChannel(context);
        
        NotificationManager manager = (NotificationManager) 
            context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        if (manager == null) return;
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(artist)
            .setProgress(100, progress, false)
            .setSmallIcon(smallIconResId)
            .setStyle(new MediaStyle())
            .setVibrate(new long[]{})
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH);
        
        manager.notify(NOTIFICATION_ID, builder.build());
    }
    
    public static void updateProgress(
            Context context,
            String title,
            String artist,
            int progress,
            int smallIconResId) {
        
        createLiveNotification(context, title, artist, progress, smallIconResId);
    }
    
    public static void cancelLiveNotification(Context context) {
        NotificationManager manager = (NotificationManager) 
            context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        if (manager != null) {
            manager.cancel(NOTIFICATION_ID);
        }
    }
    
    private static void ensureChannel(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        
        NotificationManager manager = (NotificationManager)
            context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        if (manager == null) return;
        
        if (manager.getNotificationChannel(CHANNEL_ID) != null) {
            return;
        }
        
        NotificationChannel channel = new NotificationChannel(
            CHANNEL_ID,
            "Music Player",
            NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription("Now playing music notifications");
        channel.enableVibration(false);
        channel.setSound(null, null);
        channel.setShowBadge(true);
        
        manager.createNotificationChannel(channel);
    }
}