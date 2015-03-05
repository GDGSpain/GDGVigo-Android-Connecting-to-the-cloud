package com.androidvigo.cloud;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.List;


public class MainActivity extends ActionBarActivity
    implements GetMemesAsyncTask.GetMemesCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGetMemesRequest(View view) {

        new GetMemesAsyncTask(this).execute();
    }

    @Override
    public void onMemesResult(List<MemeEntity> memesList) {

    }

    @Override
    public void onMemesError() {

    }
}
