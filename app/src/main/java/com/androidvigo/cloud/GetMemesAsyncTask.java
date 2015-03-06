package com.androidvigo.cloud;


import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Task used to make a request to the meme api, the result of
 * the request is retrieved as a List of MemeEntities
 */
@SuppressWarnings("UnusedDeclaration")
public class GetMemesAsyncTask extends AsyncTask<Void, Void, List<MemeEntity>> {

    private final GetMemesCallback mCallback;

    /**
     * Main constructor
     *
     * @param callback that will be fired when the AsyncTask ends
     */
    public GetMemesAsyncTask(GetMemesCallback callback) {

        if (callback == null) {
            throw new IllegalArgumentException("The callback argument cannot be null");
        }

        mCallback = callback;
    }

    @Override
    protected List<MemeEntity> doInBackground(Void... params) {

        String result = askWithHttpURLConnection();
        List <MemeEntity> memeEntityList = parseMemesWithGSON(result);

        return memeEntityList;
    }

    @Override
    protected void onPostExecute(List<MemeEntity> memeEntities) {

        super.onPostExecute(memeEntities);

        if (memeEntities != null)
            mCallback.onMemesResult(memeEntities);

        else
            mCallback.onMemesError();
    }

    /**
     * Uses an HttpUrlConnection object to start a GET
     * request to the meme API
     *
     * @return an String with the response in a json or
     * a null value if something fails
     */
    public String askWithHttpURLConnection() {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;


        String memeString = null;
        try {

            URL memeURL = new URL(GetMemesHelper.API_ENDPOINT);

            urlConnection = (HttpURLConnection) memeURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into the memeString
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {

                Log.e("[ERROR]", "MainActivity, askWithHttpURLConnection (47)- " +
                    "The stream was null");

                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {

                // Beautify JSON
                buffer.append(line + "\n");
            }

            if (buffer.length() != 0) {

                memeString = buffer.toString();

            } else {

                Log.e("[ERROR]", "MainActivity, askWithHttpURLConnection (70)- " +
                    "Empty buffer");
            }

        } catch (MalformedURLException e) {

            Log.e("[ERROR]", "MainActivity, askWithHttpURLConnection (36)- " +
                "memeURL is malformed: " + e.getMessage());

        } catch (IOException e) {

            Log.e("[ERROR]", "MainActivity, askWithHttpURLConnection (45)- " +
                "IOException: " + e.getMessage());

        } finally {

            if (urlConnection != null) {

                urlConnection.disconnect();
            }

            if (reader != null) {

                try {

                    reader.close();

                } catch (final IOException e) {

                    Log.e("[ERROR]", "MainActivity, askWithHttpURLConnection (99)- " +
                        "Error closing the reader");
                }
            }
        }

        return memeString;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public List<MemeEntity> parseMemesWithGSON (String json) {

        Gson gson = new Gson();

        List<MemeEntity> memesList = gson.fromJson(
            json, new TypeToken<List<MemeEntity>>(){}.getType());

        return memesList;
    }
}
