package sg.edu.rp.c346.taskmanager_p06;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class TaskReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("this is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel");
        builder.setContentTitle("Reminder from Task Manager");
        builder.setContentText(name);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);

        Notification n = builder.build();

        // An integer good to have, for you to programmatically cancel it
        notificationManager.notify(0, n);

    }
}
