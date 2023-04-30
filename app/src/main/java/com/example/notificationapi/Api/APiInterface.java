package com.example.notificationapi.Api;

import static com.example.notificationapi.Model.Constant.CONTENT_TYPE;
import static com.example.notificationapi.Model.Constant.SERVER_KEY;

import com.example.notificationapi.Model.PushNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APiInterface {

    @Headers({"Authorization: key="+SERVER_KEY, "Content-Type:"+CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotification> sendNotification(@Body PushNotification notification);



}
