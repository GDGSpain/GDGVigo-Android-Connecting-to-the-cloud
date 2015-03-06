package com.androidvigo.cloud;

import java.util.List;

public interface GetMemesCallback {

        public void onMemesResult (List<MemeEntity> memesList);

        public void onMemesError ();
    }