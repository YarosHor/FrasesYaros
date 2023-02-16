package com.yaros.frasesYaros.modificar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yaros.frasesYaros.R;
import com.yaros.frasesYaros.interfaces.IAPIService;
import com.yaros.frasesYaros.models.Autor;
import com.yaros.frasesYaros.rest.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarAutor extends Fragment {
    private IAPIService apiService;
    private Spinner spinner;
    private TextView tvId;
    private EditText etNombre;
    private EditText etProfesion;
    private EditText etMuerte;
    private EditText etNacimiento;
    private Button button;
    private Autor autor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.autor_modify, container, false);
        apiService = RestClient.getInstance();

        spinner = inflatedView.findViewById(R.id.spModificarAutor);
        tvId = inflatedView.findViewById(R.id.tvModificarAutorId);
        etNombre =  inflatedView.findViewById(R.id.etvModificarAutorNombre);
        etProfesion =  inflatedView.findViewById(R.id.etvModificarAutorProfesion);
        etMuerte =  inflatedView.findViewById(R.id.etvModificarAutorMuerte);
        etNacimiento =  inflatedView.findViewById(R.id.etvModificarAutorNacimiento);
        button =  inflatedView.findViewById(R.id.btModificarAutor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autor = new Autor(Integer.parseInt(tvId.toString()), etNombre.toString(), Integer.parseInt(etNacimiento.toString()), etMuerte.toString(), etProfesion.toString());
                modificarAutor();
            }
        });
        return inflatedView;
    }



    public void modificarAutor(){
        apiService.updateAutor(autor).enqueue(new Callback<Boolean>() {
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
