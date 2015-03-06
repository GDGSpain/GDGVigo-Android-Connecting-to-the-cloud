package com.androidvigo.cloud;


import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

public class GetMemesHelper {

    private static final String API_ENDPOINT = "http://alltheragefaces.com/api/all/faces";

    public static GetMemesHelper INSTANCE = null;

    public static GetMemesHelper getInstance () {

        if (INSTANCE == null)
            INSTANCE = new GetMemesHelper();

        return INSTANCE;
    }

    private GetMemesHelper () { /* Unused */ }

    public void loadMemesWithIon (final GetMemesCallback callback, Context context) {

        Ion.with(context)
            .load(API_ENDPOINT)
            .as(new TypeToken<List<MemeEntity>>(){})
            .setCallback(new FutureCallback<List<MemeEntity>>() {
                @Override
                public void onCompleted(Exception e, List<MemeEntity> memeEntityList) {

                    callback.onMemesResult(memeEntityList);
                }
            });
    }
}
