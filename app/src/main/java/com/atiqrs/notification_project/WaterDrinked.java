package com.atiqrs.notification_project;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WaterDrinked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_drinked);
        setTitle("Water Drinked");

        Intent intent = getIntent();
        int notifyID = intent.getIntExtra("achive",0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(notifyID);
    }
}
