package com.pondthaitay.fcm_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_subscribe).setOnClickListener(this);
        findViewById(R.id.btn_un_subscribe).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_subscribe) {
            FirebaseMessaging.getInstance().subscribeToTopic("promotion");
            toast("subscribed");
        } else if (id == R.id.btn_un_subscribe) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("promotion");
            toast("un subscribed");
        }
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}