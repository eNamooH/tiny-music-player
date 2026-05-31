package com.martinmimigames.tinymusicplayer.samsung;

import android.content.Context;
import android.content.SharedPreferences;

public class SamsungPrefs {
    private static final String PREFS_NAME = "samsung_live_prefs";
    private static final String KEY_CONVERTER_ENABLED = "converter_enabled";
    private static final String KEY_SAMSUNG_LIVE_ENABLED = "samsung_live_enabled";
    
    private final SharedPreferences prefs;
    
    public SamsungPrefs(Context context) {
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    public boolean getConverterEnabled() {
        return prefs.getBoolean(KEY_CONVERTER_ENABLED, true);
    }
    
    public void setConverterEnabled(boolean value) {
        prefs.edit().putBoolean(KEY_CONVERTER_ENABLED, value).apply();
    }
    
    public boolean getSamsungLiveEnabled() {
        return prefs.getBoolean(KEY_SAMSUNG_LIVE_ENABLED, true);
    }
    
    public void setSamsungLiveEnabled(boolean value) {
        prefs.edit().putBoolean(KEY_SAMSUNG_LIVE_ENABLED, value).apply();
    }
}