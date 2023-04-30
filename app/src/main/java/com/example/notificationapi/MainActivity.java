package com.example.notificationapi;

import static com.example.notificationapi.Model.Constant.TOPIC;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notificationapi.Alarm.MyReciever;
import com.example.notificationapi.Api.ApiUtilities;
import com.example.notificationapi.Model.NotificationData;
import com.example.notificationapi.Model.PushNotification;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText title, message;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        title = findViewById(R.id.editTextTextPersonName);
        message = findViewById(R.id.editTextTextPersonName2);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/h2");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int time = Integer.parseInt("10");
//                long triggerTime = System.currentTimeMillis() + (time * 1000);

//                Intent i = new Intent(MainActivity.this, MyReciever.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 100, i, PendingIntent.FLAG_UPDATE_CURRENT);
//                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);

                String titletxt = title.getText().toString();
                String msgTxt = message.getText().toString();

                if(!titletxt.isEmpty() && !msgTxt.isEmpty()){
                    PushNotification notification = new PushNotification(new NotificationData(titletxt, msgTxt), TOPIC);
                    sendNotification(notification);
                }
            }
        });

    }

    private void sendNotification(PushNotification notification) {

        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}