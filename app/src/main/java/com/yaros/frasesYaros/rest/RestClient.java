package com.yaros.frasesYaros.rest;

import com.yaros.frasesYaros.interfaces.IAPIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static IAPIService instance;
    private static final int PORT = 8080;
    private static final String BASE_URL = "http://192.168.1.55" + ":" + PORT;

    /* Lo hacemos privado para evitar que se puedan crear instancias de esta forma */
    private RestClient() {

    }

    public static synchronized IAPIService getInstance() {
        if(instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            instance = retrofit.create(IAPIService.class);
        }
        return instance;
    }
}
