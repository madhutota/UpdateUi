package com.example.updateui;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class DateService extends IntentService {
    private static Context context;
    private Timer timer;
    public  String Data;


    public DateService() {
        super(DateService.class.getSimpleName());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        /*Intent intent1 = new Intent();
        intent1.setAction(AppConstants.BROADCAST_DATA);
        intent1.putExtra("DATAPASSED", "Tutorialspoint.com");
        //(intent1);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);*/

        MyThread myThread = new MyThread();
        myThread.start();

    }
    public class MyThread extends Thread{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                int delay = 1000; // delay for 1 sec.
                int period = 1000; // repeat every 1 sec.
               Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Calendar c = Calendar.getInstance();
                        Intent intent = new Intent();
                        intent.setAction(AppConstants.BROADCAST_DATA);
                        intent.putExtra(AppConstants.DATE_DATA, String.valueOf(formatter.format(c.getTime())));
                        LocalBroadcastManager.getInstance(DateService.this).sendBroadcast(intent);
                    }
                }, delay, period);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            stopSelf();
        }

    }

}
