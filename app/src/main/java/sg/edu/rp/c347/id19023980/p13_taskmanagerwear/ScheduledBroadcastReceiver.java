package sg.edu.rp.c347.id19023980.p13_taskmanagerwear;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

public class ScheduledBroadcastReceiver extends BroadcastReceiver {
    int reqCode = 12345;


    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getExtras().getString("name");
        String description = intent.getExtras().getString("description");

        DBHelper dbh = new DBHelper(context);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender();
        Intent i = new Intent(context,AddActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity
                ( context, reqCode, i,
                        PendingIntent.FLAG_CANCEL_CURRENT);

        Intent intentOpen = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intentOpen,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_foreground,"Launch Task Manager",pendingIntent).build();


        Intent intentreply = new Intent(context,
                ReplyActivity.class);
        PendingIntent pendingIntentReply = PendingIntent.getActivity
                (context, 0, intentreply,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput ri = new RemoteInput.Builder("status")
                .setLabel("Status report")
                .setChoices(new String [] {"Completed", "Not yet"})
                .build();

        NotificationCompat.Action action2 = new
                NotificationCompat.Action.Builder(
                R.drawable.ic_launcher_foreground,
                "Reply",
                pendingIntentReply)
                .addRemoteInput(ri)
                .build();
        Intent intentreplyAdd = new Intent(context,
                AddActivity.class);
        PendingIntent pendingIntentReplyAdd = PendingIntent.getActivity
                (context, 0, intentreplyAdd,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteInput riAddDesc = new RemoteInput.Builder("status")
                .setLabel("Description")
                .build();
        NotificationCompat.Action actionAdd = new
                NotificationCompat.Action.Builder(
                R.drawable.ic_launcher_foreground,
                "Add",
                pendingIntentReplyAdd)
                .addRemoteInput(riAddDesc)
                .build();

        extender.addAction(action);
        extender.addAction(action2);
        extender.addAction(actionAdd);
        // Build notification
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "default");

        int num = dbh.getTasks().size();

        Log.d("TEST","TEST: "+dbh.getTasks().get(num-1).getTitle());
        String title = dbh.getTasks().get(num-1).getTitle();
        String desc = dbh.getTasks().get(num-1).getDescription();

        builder.setContentTitle("Task");
        builder.setContentText(title + "\n" + desc);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        builder.extend(extender);

        Notification n = builder.build();
        notificationManager.notify(123, n);
    }
}