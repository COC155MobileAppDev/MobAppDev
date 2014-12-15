package com.example.team05.lecturec.Controllers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.example.team05.lecturec.R;
import com.example.team05.lecturec.ViewControllers.MainActivity;

/**
 * Created by Johnbastian on 15/12/2014.
 */
public class NotificationService extends Service {

    NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();

        notificationManager = (NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent loadMainIntent = new Intent(this.getApplicationContext(), MainActivity.class);

        Notification.Builder notificationBuilder = new Notification.Builder(getApplicationContext())
                .setContentTitle("LectuREC")
                .setContentText("WILL OPEN APPLICATION BY FORCE")
                .setSmallIcon(R.drawable.ic_launcher);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(getApplicationContext(), 0, loadMainIntent, 0);

        notificationBuilder.setContentIntent(pendingNotificationIntent);

        int mNotificationID = 001;


        if (Build.VERSION.SDK_INT >= 16 ) notificationManager.notify(mNotificationID, notificationBuilder.build());
        else notificationManager.notify(mNotificationID, notificationBuilder.getNotification());

        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





}
