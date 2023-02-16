package com.yaros.frasesYaros.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.yaros.frasesYaros.R;
import com.yaros.frasesYaros.adaptadores.AdaptadorFrases;
import com.yaros.frasesYaros.interfaces.IAPIService;
import com.yaros.frasesYaros.interfaces.IClickListener;
import com.yaros.frasesYaros.models.Autor;
import com.yaros.frasesYaros.models.Frase;
import com.yaros.frasesYaros.modificar.NuevoAutor;
import com.yaros.frasesYaros.rest.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutorFraseFragment extends Fragment implements IClickListener {
    private IAPIService apiService;
    IClickListener iClickListener;
    Spinner spinner;
    RecyclerView recyclerView;
    Button btNuevo;
    Button btModificar;
    ArrayList<Autor> autores;
    ArrayList<Frase> frases;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_frases_autor, container, false);
        spinner = inflatedView.findViewById(R.id.spinAutor);
        recyclerView = inflatedView.findViewById(R.id.rvAutor);
        btNuevo = inflatedView.findViewById(R.id.btAutorNuevo);
        btModificar = inflatedView.findViewById(R.id.btAutorModificar);
        apiService = RestClient.getInstance();
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        autores = null;
        frases = null;
        getAutores();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getFrases(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new NuevoAutor();
                //FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, f)
                        .commit();
                getActivity().setTitle("Nuevo Autor");
            }
        });

    }




    public void getAutores(){
        System.out.println("halo");
        apiService.getAutores().enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                System.out.println("hey" +response);
                if(response.isSuccessful()) {
                    autores = (ArrayList<Autor>) response.body();
                    ArrayAdapter<Autor> spinnerAdapter = new ArrayAdapter<Autor>(getActivity(), android.R.layout.simple_spinner_item, autores);
                    spinner.setAdapter(spinnerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
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
