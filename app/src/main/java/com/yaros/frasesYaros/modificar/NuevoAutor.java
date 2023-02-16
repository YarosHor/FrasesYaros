package com.yaros.frasesYaros.modificar;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.yaros.frasesYaros.R;
import com.yaros.frasesYaros.adaptadores.AdaptadorFrases;
import com.yaros.frasesYaros.interfaces.IAPIService;
import com.yaros.frasesYaros.models.Autor;
import com.yaros.frasesYaros.models.Frase;
import com.yaros.frasesYaros.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoAutor extends Fragment {
    private IAPIService apiService;
    private TextView tvTitulo;
    private EditText etNombre;
    private EditText etProfesion;
    private EditText etMuerte;
    private EditText etNacimiento;
    private Button button;
    private Autor autor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.autor_add, container, false);
        apiService = RestClient.getInstance();
        tvTitulo =  inflatedView.findViewById(R.id.tvNuevoAutorTitulo);
        etNombre =  inflatedView.findViewById(R.id.etvNuevoAutorNombre);
        etProfesion =  inflatedView.findViewById(R.id.etvNuevoAutorProfesion);
        etMuerte =  inflatedView.findViewById(R.id.etvNuevoAutorMuerte);
        etNacimiento =  inflatedView.findViewById(R.id.etvNuevoAutorNacimiento);
        button =  inflatedView.findViewById(R.id.btCrearAutor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autor = new Autor(etNombre.toString(), Integer.parseInt(etNacimiento.toString()), etMuerte.toString(), etProfesion.toString());
                nuevoAutor();
            }
        });
        return inflatedView;
    }



    public void nuevoAutor(){
        apiService.addAutor(autor).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()){
                    System.out.println("Yey");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }



}
