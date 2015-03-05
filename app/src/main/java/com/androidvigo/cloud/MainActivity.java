package com.androidvigo.cloud;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;


public class MainActivity extends ActionBarActivity
    implements GetMemesAsyncTask.GetMemesCallback {

    private ListView mMemesListView;
    private ProgressBar mLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMemesListView      = (ListView) findViewById(R.id.activity_main_memes_listview);
        mLoadingProgressBar = (ProgressBar) findViewById(R.id.activity_main_loading_indicator);
    }

    public void startGetMemesRequest(View requestButton) {

        requestButton.setVisibility(View.GONE);
        mLoadingProgressBar.setVisibility(View.VISIBLE);

        new GetMemesAsyncTask(this).execute();
    }

    @Override
    public void onMemesResult(List<MemeEntity> memesList) {

        mLoadingProgressBar.setVisibility(View.GONE);

        String [] memesNames = new String[memesList.size()];

        for (int i = 0; i < memesList.size(); i++)
            memesNames[i] = memesList.get(i).getTitle();

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, memesNames);

        mMemesListView.setAdapter(listAdapter);
    }

    @Override
    public void onMemesError() {

    }
}
