package com.segueme.seguemepsul;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by FernandoDias on 27/01/17.
 */

public class SeguemePnsmdpApplication extends Application {

    public class YourAppClass extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            OneSignal.startInit(this).init();

            // Call syncHashedEmail anywhere in your app if you have the user's email.
            // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
            // OneSignal.syncHashedEmail(userEmail);
        }
    }

}
