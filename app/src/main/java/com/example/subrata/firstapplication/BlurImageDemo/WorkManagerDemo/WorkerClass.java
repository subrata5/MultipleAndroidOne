package com.example.subrata.firstapplication.BlurImageDemo.WorkManagerDemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.subrata.firstapplication.R;

public class WorkerClass extends Worker {

    public static final String KEY_TASK_OUTPUT = "key_task_output";


    public WorkerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //get the data which had been sent from the WorkerManagerExample class
        Data data = getInputData();
        String desc = data.getString(WorkManagerExample.KEY_TASK_DEC);

        Data data1 = new Data.Builder()
                .putString(KEY_TASK_OUTPUT, "Task finished successfully")
                .build();


        displayNotificstion("This is the work task", desc);

        return Result.success(data1);
    }

    private void displayNotificstion(String task, String desc) {

        //Create the manager
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        //Create the notification channel for devices running Oreo or more.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("okayandroid", "okandroid", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        //create the notification
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), "okayandroid")
                        .setContentTitle(task)
                        .setContentText(desc)
                        .setSmallIcon(R.drawable.emoji_icon);
        //user manager object to display the notification
        notificationManager.notify(1, builder.build());



        //making change in the existing code here.

    }
}
