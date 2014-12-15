package com.example.team05.lecturec.Controllers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Johnbastian on 15/12/2014.
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notifyService = new Intent(context, NotificationService.class);
        context.startService(notifyService);

    }

}
