package com.yaros.frasesYaros;

import android.graphics.ColorSpace;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<ColorSpace.Model>> getsuperHeroes();
}
