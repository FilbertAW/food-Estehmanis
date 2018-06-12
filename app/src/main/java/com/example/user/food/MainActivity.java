package com.example.user.food;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.job.JobServiceEngine;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewDebug;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.food.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    AmbilDataJSON classAmbilData = new AmbilDataJSON();
    int[] dataYangDiambil = classAmbilData.getData();


    Runnable ambilData = new Runnable() {
        @Override
        public void run() {
            dataYangDiambil = classAmbilData.getData();
            int[] previousData;

            while(!Thread.interrupted()){
                previousData = dataYangDiambil;
                dataYangDiambil = classAmbilData.getData();

                Log.d("data", "Previous: " + previousData[1]);
                Log.d("data", "Current: " + dataYangDiambil[1]);

                if(previousData[0] != dataYangDiambil[0] && dataYangDiambil[0]<20){
                    sendNotification(1);
                }
                if(previousData[1] != dataYangDiambil[1] && dataYangDiambil[1]<20){
                    sendNotification(2);
                }
                if(previousData[2] != dataYangDiambil[2] && dataYangDiambil[2]<20){
                    sendNotification(3);
                }
                /*
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                */
            }
        }
    };

    Thread loopAmbilData = new Thread(ambilData);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        loopAmbilData.start();

    }

    @Override
    protected void onStart() {
        super.onStart();

        dataYangDiambil = classAmbilData.getData();

        if(dataYangDiambil[0] >=50){
            ProgressBar bebas=(ProgressBar)findViewById(R.id.simpleProgressBar);
            bebas.setVisibility(View.VISIBLE);
            bebas.setProgress(dataYangDiambil[0]);
            ProgressBar bebas2=(ProgressBar)findViewById(R.id.simpleProgressBar4);
            bebas2.setVisibility(View.INVISIBLE);
            bebas2.setProgress(dataYangDiambil[0]);
            ProgressBar bebas3=(ProgressBar)findViewById(R.id.simpleProgressBar7);
            bebas3.setVisibility(View.INVISIBLE);
            bebas3.setProgress(dataYangDiambil[0]);
            TextView text1=(TextView)findViewById(R.id.textView7);
            text1.setText(String.valueOf(dataYangDiambil[0]+ "%"));
        }
        if(dataYangDiambil[0]<50&&dataYangDiambil[0]>20){
            ProgressBar bebas=(ProgressBar)findViewById(R.id.simpleProgressBar);
            bebas.setVisibility(View.INVISIBLE);
            bebas.setProgress(dataYangDiambil[0]);
            ProgressBar bebas2=(ProgressBar)findViewById(R.id.simpleProgressBar4);
            bebas2.setVisibility(View.VISIBLE);
            bebas2.setProgress(dataYangDiambil[0]);
            ProgressBar bebas3=(ProgressBar)findViewById(R.id.simpleProgressBar7);
            bebas3.setVisibility(View.INVISIBLE);
            bebas3.setProgress(dataYangDiambil[0]);
            TextView text1=(TextView)findViewById(R.id.textView7);
            text1.setText(String.valueOf(dataYangDiambil[0] + "%"));
        }

        if(dataYangDiambil[0]<20){
            ProgressBar bebas=(ProgressBar)findViewById(R.id.simpleProgressBar);
            bebas.setVisibility(View.INVISIBLE);
            bebas.setProgress(dataYangDiambil[0]);
            ProgressBar bebas2=(ProgressBar)findViewById(R.id.simpleProgressBar4);
            bebas2.setVisibility(View.INVISIBLE);
            bebas2.setProgress(dataYangDiambil[0]);
            ProgressBar bebas3=(ProgressBar)findViewById(R.id.simpleProgressBar7);
            bebas3.setVisibility(View.VISIBLE);
            bebas3.setProgress(dataYangDiambil[0]);
            TextView text1=(TextView)findViewById(R.id.textView7);
            text1.setText(String.valueOf(dataYangDiambil[0]+ "%"));
        }

        if(dataYangDiambil[1]>=50){
            ProgressBar bebas4=(ProgressBar)findViewById(R.id.simpleProgressBar2);
            bebas4.setVisibility(View.VISIBLE);
            bebas4.setProgress(dataYangDiambil[1]);
            ProgressBar bebas5=(ProgressBar)findViewById(R.id.simpleProgressBar5);
            bebas5.setVisibility(View.INVISIBLE);
            bebas5.setProgress(dataYangDiambil[1]);
            ProgressBar bebas6=(ProgressBar)findViewById(R.id.simpleProgressBar8);
            bebas6.setVisibility(View.INVISIBLE);
            bebas6.setProgress(dataYangDiambil[1]);
            TextView text2=(TextView)findViewById(R.id.textView8);
            text2.setText(String.valueOf(dataYangDiambil[1]+ "%"));
        }
        if(dataYangDiambil[1]<50&&dataYangDiambil[1]>20){
            ProgressBar bebas4=(ProgressBar)findViewById(R.id.simpleProgressBar2);
            bebas4.setVisibility(View.INVISIBLE);
            bebas4.setProgress(dataYangDiambil[1]);
            ProgressBar bebas5=(ProgressBar)findViewById(R.id.simpleProgressBar5);
            bebas5.setVisibility(View.VISIBLE);
            bebas5.setProgress(dataYangDiambil[1]);
            ProgressBar bebas6=(ProgressBar)findViewById(R.id.simpleProgressBar8);
            bebas6.setVisibility(View.INVISIBLE);
            bebas6.setProgress(dataYangDiambil[1]);
            TextView text2=(TextView)findViewById(R.id.textView8);
            text2.setText(String.valueOf(dataYangDiambil[1]+ "%"));
        }
        if(dataYangDiambil[1]<20){
            ProgressBar bebas4=(ProgressBar)findViewById(R.id.simpleProgressBar2);
            bebas4.setVisibility(View.INVISIBLE);
            bebas4.setProgress(dataYangDiambil[1]);
            ProgressBar bebas5=(ProgressBar)findViewById(R.id.simpleProgressBar5);
            bebas5.setVisibility(View.INVISIBLE);
            bebas5.setProgress(dataYangDiambil[1]);
            ProgressBar bebas6=(ProgressBar)findViewById(R.id.simpleProgressBar8);
            bebas6.setVisibility(View.VISIBLE);
            bebas6.setProgress(dataYangDiambil[1]);
            TextView text2=(TextView)findViewById(R.id.textView8);
            text2.setText(String.valueOf(dataYangDiambil[1]+ "%"));
        }
        if(dataYangDiambil[2]>=50){
            ProgressBar bebas7=(ProgressBar)findViewById(R.id.simpleProgressBar3);
            bebas7.setVisibility(View.VISIBLE);
            bebas7.setProgress(dataYangDiambil[2]);
            ProgressBar bebas8=(ProgressBar)findViewById(R.id.simpleProgressBar6);
            bebas8.setVisibility(View.INVISIBLE);
            bebas8.setProgress(dataYangDiambil[2]);
            ProgressBar bebas9=(ProgressBar)findViewById(R.id.simpleProgressBar9);
            bebas9.setVisibility(View.INVISIBLE);
            bebas9.setProgress(dataYangDiambil[2]);
            TextView text3=(TextView)findViewById(R.id.textView9);
            text3.setText(String.valueOf(dataYangDiambil[2] + "%"));
            TextView text4=(TextView)findViewById(R.id.textView10);
            text4.setText(String.valueOf(dataYangDiambil[3]+" left"));
        }
        if(dataYangDiambil[2]<50&&dataYangDiambil[2]>20){
            ProgressBar bebas7=(ProgressBar)findViewById(R.id.simpleProgressBar3);
            bebas7.setVisibility(View.INVISIBLE);
            bebas7.setProgress(dataYangDiambil[2]);
            ProgressBar bebas8=(ProgressBar)findViewById(R.id.simpleProgressBar6);
            bebas8.setVisibility(View.VISIBLE);
            bebas8.setProgress(dataYangDiambil[2]);
            ProgressBar bebas9=(ProgressBar)findViewById(R.id.simpleProgressBar9);
            bebas9.setVisibility(View.INVISIBLE);
            bebas9.setProgress(dataYangDiambil[2]);
            TextView text3=(TextView)findViewById(R.id.textView9);
            text3.setText(String.valueOf(dataYangDiambil[2]));
            TextView text4=(TextView)findViewById(R.id.textView10);
            text4.setText(String.valueOf(dataYangDiambil[3]+" left"));
        }
        if(dataYangDiambil[2]<20) {
            ProgressBar bebas7 = (ProgressBar) findViewById(R.id.simpleProgressBar3);
            bebas7.setVisibility(View.INVISIBLE);
            bebas7.setProgress(dataYangDiambil[2]);
            ProgressBar bebas8 = (ProgressBar) findViewById(R.id.simpleProgressBar6);
            bebas8.setVisibility(View.INVISIBLE);
            bebas8.setProgress(dataYangDiambil[2]);
            ProgressBar bebas9 = (ProgressBar) findViewById(R.id.simpleProgressBar9);
            bebas9.setVisibility(View.VISIBLE);
            bebas9.setProgress(dataYangDiambil[2]);
            TextView text3 = (TextView) findViewById(R.id.textView9);
            text3.setText(String.valueOf(dataYangDiambil[2]));
            TextView text4 = (TextView) findViewById(R.id.textView10);
            text4.setText(String.valueOf(dataYangDiambil[3] + " left"));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //loopAmbilData.interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //loopAmbilData.interrupt();
    }

    public void sendNotification(int container) {

        /*
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        */

        Notification.Builder notificationBuilder = new Notification.Builder(this);

        Context context = getApplicationContext();
        PackageManager pm = context.getPackageManager();
        Intent LaunchIntent = null;
        String mapAppName = "io.github.nelvson.finalundergradoctoturtlenokotlin";
        String name ="";
        try{
            if (pm!=null){
                ApplicationInfo app = context.getPackageManager().getApplicationInfo(mapAppName, 0);
                name = (String) pm.getApplicationLabel(app);
                LaunchIntent = pm.getLaunchIntentForPackage(mapAppName);
            }
        }catch(PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        Intent intent = LaunchIntent;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.androidauthority.com/"));
 //       Intent intent = new Intent(Intent.ACTION_MAIN);
 //       intent.setComponent(new ComponentName("io.github.nelvson.finalundergradoctoturtlenokotlin", "io.github.nelvson.finalundergradoctoturtlenokotlin.MainActivity"));
 //       PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //mBuilder.setContentIntent(pendingIntent);

        notificationBuilder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.chocolate)
                .setContentTitle("Food Monitoring")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setVibrate(new long[]{1000,1000})
                .setAutoCancel(true);

        //mBuilder.setSmallIcon(R.drawable.chocolate);
        //mBuilder.setContentTitle("Food Monitoring");

        switch(container){
            case 1:
                notificationBuilder.setContentText("Container 1 less than 20% left");
                //mBuilder.setContentText("Container 1 less than 20% left");
                break;
            case 2:
                notificationBuilder.setContentText("Container 2 less than 20% left");
                //mBuilder.setContentText("Container 2 less than 20% left");
                break;
            case 3:
                notificationBuilder.setContentText("Container 3 less than 20% left");
                //mBuilder.setContentText("Container 3 less than 20% left ( 1 slot) ");
                break;
            default:
                notificationBuilder.setContentText("Container 4 less than 20% left");
                //mBuilder.setContentText("Container 0 80% left");
                break;
        }

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        //mBuilder.setVibrate(new long[] { 1000, 1000});
        //mBuilder.setLatestEventInfo(getApplicationContext(), subject, body,pending);

        //mNotificationManager.notify(001, mBuilder.build());

        Notification notif = notificationBuilder.build();
        mNotificationManager.notify(001,notif);
    }

    /*
    public String passClosestPathJSON(String reqUrl) {

        // String response = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
        } catch (Exception e) {
            Log.e("yee", "msg", e);
            // Log.e("yee","failed my dude");
        } finally {
            //ye
        }
        return result.toString();
    }
    */
}
