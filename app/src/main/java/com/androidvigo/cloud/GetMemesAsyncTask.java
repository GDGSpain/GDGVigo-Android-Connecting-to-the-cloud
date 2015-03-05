package com.androidvigo.cloud;


import android.os.AsyncTask;

@SuppressWarnings("UnusedDeclaration")
public class GetMemesAsyncTask extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {

        // OMG Main thread
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        // OMG Background thread
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        // OMG Main thread
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {

        // OMG Main thread
        super.onPostExecute(s);
    }
}
