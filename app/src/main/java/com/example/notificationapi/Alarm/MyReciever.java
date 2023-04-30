package com.example.notificationapi.Alarm;

import static com.example.notificationapi.Model.Constant.TOPIC;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.widget.Toast;

import com.example.notificationapi.Api.ApiUtilities;
import com.example.notificationapi.MainActivity;
import com.example.notificationapi.Model.NotificationData;
import com.example.notificationapi.Model.PushNotification;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReciever extends BroadcastReceiver {

    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {

//        PushNotification notification = new PushNotification(new NotificationData("hello", "whatsapp"), TOPIC);
//        sendNotification(notification);

        mp = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mp.setLooping(true);
        mp.start();

    }

//    public void sendNotification(PushNotification notification) {
//
//        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
//            @Override
//            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
//                if(response.isSuccessful()){
//                    //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                }else{
//                    //Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PushNotification> call, Throwable t) {
//                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
