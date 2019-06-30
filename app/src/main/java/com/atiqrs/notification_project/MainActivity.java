package com.atiqrs.notification_project;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFY_ID = 1001;
    Button notificationSelectionBtn;


    @Override
    protected void onRestart() {
        notificationSelectionBtn = findViewById(R.id.notificationSelectorBtn);
        notificationSelectionBtn.setText("Select any notification");
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void NotificationBuilderLarge() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Intent intent = new Intent(this,NotificationResult.class);
        intent.putExtra("clear",NOTIFY_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,NOTIFY_ID,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        Intent i = new Intent(this,WaterDrinked.class);
        i.putExtra("achive",NOTIFY_ID);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this,1,i,PendingIntent.FLAG_CANCEL_CURRENT);

        //Notification ITEM
        builder.setSmallIcon(R.drawable.ic_alarm_clock);
        builder.setContentTitle("This is Drinking Time");

        String time = (String) DateFormat.format("EEE hh:mm a",new Date());
        builder.setContentText(time);

        //for cancle
        builder.setAutoCancel(true);

        //big icon set
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        //subText
        builder.setSubText("Dont miss that!");

        //set listener after clicking
        builder.setContentIntent(pendingIntent1);

        //Expended Notification Layout
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("This is Drinking Time, you must dring min 250g water");
        bigTextStyle.bigText(getResources().getString(R.string.notificationDesc));
        builder.setStyle(bigTextStyle);

        //Set Button on Expanded Notification Layout
        builder.addAction(R.drawable.ic_alarm_clock,"Achive",pendingIntent1);
        builder.addAction(R.drawable.ic_alarm_clock,"Note Now",pendingIntent);


        //notification build finished and user display
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID,notification);

        //show locked screen notification
        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
    }
    private void NotificationBuilderSmall() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Intent intent = new Intent(this,WaterDrinked.class);
        intent.putExtra("clear",NOTIFY_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,NOTIFY_ID,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        //Notification ITEM
        builder.setSmallIcon(R.drawable.ic_alarm_clock);
        builder.setContentTitle("This is Drinking Time");

        String time = (String) DateFormat.format("EEE hh:mm a",new Date());
        builder.setContentText(time);

        //for cancle
        builder.setAutoCancel(true);

        //big icon set
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        //subText
        builder.setSubText("Dont miss that!");

        //set listener after clicking
        builder.setContentIntent(pendingIntent);

        //notification build finished and user display
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID,notification);

        //show locked screen notification
        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
    }

    public void pop(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.notification_menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.Small){
                    notificationSelectionBtn = findViewById(R.id.notificationSelectorBtn);
                    notificationSelectionBtn.setText("Small");
                    smallNotification();
                }
                if (menuItem.getItemId() == R.id.Extended){
                    notificationSelectionBtn = findViewById(R.id.notificationSelectorBtn);
                    notificationSelectionBtn.setText("Extended");
                    extendedNotification();
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void extendedNotification() {
        NotificationBuilderLarge();
    }
    private void smallNotification() {
        NotificationBuilderSmall();
    }



}
