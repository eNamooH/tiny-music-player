package com.martinmimigames.tinymusicplayer.samsung;

import android.os.Build;
import java.util.Locale;

public class SamsungUtils {
    
    public static boolean isSamsungDevice() {
        String brand = Build.BRAND.toLowerCase(Locale.ROOT);
        return brand.contains("samsung");
    }
    
    public static String getSamsungPackageOverride() {
        if (isSamsungDevice()) {
            return "com.samsung.android.liveupdate";
        }
        return "com.martinmimigames.tinymusicplayer";
    }
    
    public static int getAndroidVersionCode() {
        return Build.VERSION.SDK_INT;
    }
    
    public static boolean supportsLiveNotifications() {
        return isSamsungDevice() && getAndroidVersionCode() >= Build.VERSION_CODES.S;
    }
}
