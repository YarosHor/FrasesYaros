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
import com.yaros.frasesYaros.models.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FraseDiaFragment extends Fragment {
    private IAPIService apiService;

    TextView tvFrase;
    TextView tvNombre;
    TextView tvNacimiento;
    TextView tvMuerte;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_frase_dia, container, false);
        tvFrase = inflatedView.findViewById(R.id.tvFraseDia);
        tvNombre = inflatedView.findViewById(R.id.tvAutor);
        tvNacimiento = inflatedView.findViewById(R.id.tvNacimiento);
        tvMuerte = inflatedView.findViewById(R.id.tvMuerto);
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFrases();
        tvFrase.setText("Hallo this is text");
        tvNombre.setText("Benito Camelas");
        tvNacimiento.setText("12-12-2012");
        tvMuerte.setText("01-01-2070");
    }
    public void getFrases() {
        apiService.getFrases().enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(@NonNull Call<List<Frase>> call, @NonNull Response<List<Frase>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    for(Frase frase: response.body()) {
                        Log.i(String.valueOf(getActivity()), frase.toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Frase>> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
