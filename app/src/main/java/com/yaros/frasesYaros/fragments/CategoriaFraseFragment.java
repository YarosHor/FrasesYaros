package com.yaros.frasesYaros.fragments;

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
import android.widget.Spinner;

import com.yaros.frasesYaros.R;
import com.yaros.frasesYaros.adaptadores.AdaptadorFrases;
import com.yaros.frasesYaros.interfaces.IAPIService;
import com.yaros.frasesYaros.interfaces.IClickListener;
import com.yaros.frasesYaros.models.Categoria;
import com.yaros.frasesYaros.models.Frase;
import com.yaros.frasesYaros.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaFraseFragment extends Fragment implements IClickListener {
    private IAPIService apiService;
    IClickListener iClickListener;
    Spinner spinner;
    RecyclerView recyclerView;
    ArrayList<Categoria> categorias;
    ArrayList<Frase> frases;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_frases_categoria, container, false);
        spinner = inflatedView.findViewById(R.id.spinCategoria);
        recyclerView = inflatedView.findViewById(R.id.rvCategoria);
        apiService = RestClient.getInstance();
        iClickListener = this;
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categorias = null;
        frases = null;
        getCategorias();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getFrases(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }




    public void getCategorias(){
        System.out.println("halo");
        apiService.getCategorias().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    categorias = (ArrayList<Categoria>) response.body();
                    ArrayAdapter<Categoria> spinnerAdapter = new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_spinner_item, categorias);
                    spinner.setAdapter(spinnerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
    public void getFrases(Integer id){
        apiService.getFrasesDeAutor(id).enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {
                if(response.isSuccessful()){
                    frases = (ArrayList<Frase>) response.body();
                    recyclerView.setAdapter(new AdaptadorFrases(frases.toArray(new Frase[0]), iClickListener));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(Call<List<Frase>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(int position) {

    }
}
