package com.pondthaitay.fcm_example;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.text.DateFormat;
import java.util.Date;

public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
        Log.d(TAG, currentDateTime);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}