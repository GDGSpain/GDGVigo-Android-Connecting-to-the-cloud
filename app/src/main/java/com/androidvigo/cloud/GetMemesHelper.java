package com.androidvigo.cloud;


public class GetMemesHelper {

    public static final String API_ENDPOINT = "http://alltheragefaces.com/api/all/faces";

    public static GetMemesHelper INSTANCE = null;

    public static GetMemesHelper getInstance () {

        if (INSTANCE == null)
            INSTANCE = new GetMemesHelper();

        return INSTANCE;
    }

    private GetMemesHelper () { /* Unused */ }

}
