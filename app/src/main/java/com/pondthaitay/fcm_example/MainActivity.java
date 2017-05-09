package com.pondthaitay.fcm_example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                // ตั้งค่า unique id ให้กับ job
                .setTag(MyJobService.class.getName())
                // ให้ทำซ้ำเรื่อยๆ
                .setRecurring(true)
                // ให้เริ่มทำงานตั้งแต่ 0 ถึง 60 วินาที (คู่กับ setRecurring)
                .setTrigger(Trigger.executionWindow(5, 30))
                // จะให้ job นี้อยู่จนกว่าจะ boot เครื่องใหม่
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                // ไม่ overwrite job ที่ชื่อ tag เดียวกัน
                .setReplaceCurrent(false)
                // จะทำงานต่อเมื่อเชื่อมต่อ network ได้
                .setConstraints(Constraint.ON_ANY_NETWORK)
                // retry เมื่อ fail
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build();
        dispatcher.mustSchedule(myJob);
    }
}