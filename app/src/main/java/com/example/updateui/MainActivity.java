package com.example.updateui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_date;


    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras() != null)
                tv_date.setText(intent.getStringExtra(AppConstants.DATE_DATA));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv_date = findViewById(R.id.tv_date);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, DateService.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceivers();


    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver();
    }


    private void registerReceivers() {
        unregisterReceiver();
        try {
            LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(AppConstants.BROADCAST_DATA));
        } catch (Exception ignored) {
        }
    }

    private void unregisterReceiver() {
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        } catch (Exception ignored) {
        }
    }
}
