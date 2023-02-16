package com.yaros.frasesYaros.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yaros.frasesYaros.R;
import com.yaros.frasesYaros.interfaces.IAPIService;
import com.yaros.frasesYaros.models.Autor;
import com.yaros.frasesYaros.models.Frase;
import com.yaros.frasesYaros.rest.RestClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FraseDiaFragment extends Fragment {
    private IAPIService apiService;
    private Frase frase;
    private Autor autor;

    TextView tvFrase;
    TextView tvNombre;
    TextView tvNacimiento;
    TextView tvMuerte;
    TextView tvCategoria;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_frase_dia, container, false);
        tvFrase = inflatedView.findViewById(R.id.tvFraseDia);
        tvNombre = inflatedView.findViewById(R.id.tvAutor);
        tvNacimiento = inflatedView.findViewById(R.id.tvNacimiento);
        tvMuerte = inflatedView.findViewById(R.id.tvMuerto);
        tvCategoria = inflatedView.findViewById(R.id.tvCategoria);
        apiService = RestClient.getInstance();
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFraseActual();
    }
    public void getFraseActual(){
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        apiService.getFraseDia(currentDate).enqueue(new Callback<Frase>() {
            @Override
            public void onResponse(Call<Frase> call, Response<Frase> response) {
                if(response.isSuccessful()) {
                    frase = response.body();
                    tvFrase.setText(frase.getTexto());
                    tvNombre.setText(frase.getAutor().getNombre());
                    tvMuerte.setText(frase.getAutor().getMuerte());
                    tvNacimiento.setText(frase.getAutor().getNacimiento() + "");
                    tvCategoria.setText(frase.getCategoria().getNombre());
                }
            }

            @Override
            public void onFailure(Call<Frase> call, Throwable t) {
                tvFrase.setText(t.toString());
            }
        });
    }
    /*public void getAutorActual(Integer id){
        apiService.getAutorId(id).enqueue(new Callback<Autor>() {
            @Override
            public void onResponse(Call<Autor> call, Response<Autor> response) {
                if(response.isSuccessful()) {
                    autor = response.body();
                    tvNombre.setText(autor.getNombre());
                    tvMuerte.setText(autor.getMuerte());
                    tvNacimiento.setText(autor.getNacimiento());
                }
            }

            @Override
            public void onFailure(Call<Autor> call, Throwable t) {
                tvFrase.setText(t.toString());
            }
        });
    }*/
}
