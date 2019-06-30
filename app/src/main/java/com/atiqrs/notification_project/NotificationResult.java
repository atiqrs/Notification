package com.atiqrs.notification_project;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_result);

        setTitle("Water Not Drinked");
        Intent intent = getIntent();
        int notifyID = intent.getIntExtra("clear",0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(notifyID);

        TextView tv = new TextView(this);
        tv.setText("Notification run to here ! \n\nYou are Welcome");
    }
}
