package com.yaros.frasesYaros.interfaces;

import com.yaros.frasesYaros.models.Autor;
import com.yaros.frasesYaros.models.Categoria;
import com.yaros.frasesYaros.models.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAPIService {
    @GET("frase/dia/{fecha}")
    Call<Frase> getFraseDia(@Path("fecha") String fecha);

    @GET("autor/{id}")
    Call<Autor> getAutorId(@Path("id") Integer id);

    @GET("autor/all")
    Call<List<Autor>> getAutores();

    @GET("frase/autor/{id}")
    Call<List<Frase>> getFrasesDeAutor(@Path("id") Integer id);

    @GET("categoria/all")
    Call<List<Categoria>> getCategorias();

    @POST("autor/add")
    Call<Boolean> addAutor(@Body Autor autor);

    @POST("categoria/add")
    Call<Boolean> addCategoria(@Body Categoria categoria);
}
