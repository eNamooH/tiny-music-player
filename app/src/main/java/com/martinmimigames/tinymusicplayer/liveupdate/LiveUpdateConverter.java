package com.martinmimigames.tinymusicplayer.liveupdate;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import com.martinmimigames.tinymusicplayer.samsung.SamsungLiveNotifier;
import com.martinmimigames.tinymusicplayer.samsung.SamsungPrefs;

public class LiveUpdateConverter {
    
    private final Context context;
    private final SamsungPrefs prefs;
    
    public LiveUpdateConverter(Context context) {
        this.context = context;
        this.prefs = new SamsungPrefs(context);
    }
    
    public void convertNotification(Notification notification, String title, String text) {
        if (!prefs.getConverterEnabled()) {
            return;
        }
        
        Bundle extras = notification.extras;
        String subtitle = extras.getCharSequence(Notification.EXTRA_SUB_TEXT, "").toString();
        
        int progress = extras.getInt(Notification.EXTRA_PROGRESS_MAX, 0);
        int progressCurrent = extras.getInt(Notification.EXTRA_PROGRESS, 0);
        
        if (progress > 0) {
            int percentage = (progressCurrent * 100) / progress;
            SamsungLiveNotifier.createLiveNotification(
                context,
                title,
                subtitle.isEmpty() ? text : subtitle,
                percentage,
                android.R.drawable.ic_media_play
            );
        } else {
            SamsungLiveNotifier.createLiveNotification(
                context,
                title,
                text,
                0,
                android.R.drawable.ic_media_play
            );
        }
    }
    
    public void disableConverter() {
        prefs.setConverterEnabled(false);
        SamsungLiveNotifier.cancelLiveNotification(context);
    }
    
    public void enableConverter() {
        prefs.setConverterEnabled(true);
    }
}
