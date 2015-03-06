package com.androidvigo.cloud;


public class GetMemesHelper {

    public static GetMemesHelper INSTANCE = null;

    public static GetMemesHelper getInstance () {

        if (INSTANCE == null)
            INSTANCE = new GetMemesHelper();

        return INSTANCE;
    }

    private GetMemesHelper () { /* Unused */ }




}
