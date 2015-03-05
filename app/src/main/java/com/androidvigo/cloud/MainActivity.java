package com.androidvigo.cloud;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    public static final String API_ENDPOINT = "http://alltheragefaces.com/api/all/faces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OMG this will be run on the UI THREAD
        askWithHttpURLConnection();
    }

    public String askWithHttpURLConnection () {

        HttpURLConnection urlConnection = null;
        BufferedReader reader           = null;

        String memeString = null;

        try {

            URL memeURL = new URL(API_ENDPOINT);

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
                "IOException: "+e.getMessage());

        } finally {

            if (urlConnection != null) {

                urlConnection.disconnect();
            }

            if (reader != null) {

                try {

                    reader.close();;

                } catch (final IOException e) {

                    Log.e("[ERROR]", "MainActivity, askWithHttpURLConnection (99)- " +
                        "Error closing the reader");
                }
            }
        }

        return memeString;
    }
}
