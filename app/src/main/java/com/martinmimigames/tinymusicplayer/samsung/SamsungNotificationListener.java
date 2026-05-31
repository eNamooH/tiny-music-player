package com.martinmimigames.tinymusicplayer.samsung;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class SamsungNotificationListener extends NotificationListenerService {
    
    private static final String TAG = "SamsungNotificationListener";
    
    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
    }
    
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null) return;
        
        // Filter for own package notifications
        if (sbn.getPackageName().equals(getPackageName())) {
            return;
        }
        
        if (!SamsungUtils.supportsLiveNotifications()) {
            return;
        }
        
        processMusicNotification(sbn);
    }
    
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if (sbn == null) return;
        
        SamsungLiveNotifier.cancelLiveNotification(getApplicationContext());
    }
    
    private void processMusicNotification(StatusBarNotification sbn) {
        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        
        String title = extras.getCharSequence(Notification.EXTRA_TITLE, "").toString();
        String artist = extras.getCharSequence(Notification.EXTRA_SUB_TEXT, "").toString();
        String text = extras.getCharSequence(Notification.EXTRA_TEXT, "").toString();
        
        int progress = extras.getInt(Notification.EXTRA_PROGRESS_MAX, 0);
        int progressCurrent = extras.getInt(Notification.EXTRA_PROGRESS, 0);
        
        if (progress > 0) {
            int percentage = (progressCurrent * 100) / progress;
            SamsungLiveNotifier.createLiveNotification(
                getApplicationContext(),
                title.isEmpty() ? text : title,
                artist,
                percentage,
                android.R.drawable.ic_media_play
            );
        }
    }
}