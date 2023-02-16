package com.yaros.frasesYaros.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yaros.frasesYaros.R;
import com.yaros.frasesYaros.interfaces.IClickListener;
import com.yaros.frasesYaros.models.Frase;

public class AdaptadorFrases extends RecyclerView.Adapter<AdaptadorFrases.FrasesAutorViewHolder>{

    private Frase[] frases;
    private IClickListener listener;

    public AdaptadorFrases(Frase[] frases, IClickListener listener) {
        this.frases = frases;
        //todo
        this.listener = listener;
    }

    @NonNull
    @Override
    public FrasesAutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frase, parent, false);
        return new FrasesAutorViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FrasesAutorViewHolder holder, int position) {
        Frase frase = frases[position];
        holder.bindFrase(frase);
    }

    @Override
    public int getItemCount() {
        return frases.length;
    }



    public class FrasesAutorViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFrase;
        private TextView tvItemAutor;
        private TextView tvItemCategoria;
        private IClickListener listener;

        public FrasesAutorViewHolder(@NonNull View itemView, IClickListener listener) {
            super(itemView);
            tvFrase = itemView.findViewById(R.id.tvFrase);
            tvItemAutor = itemView.findViewById(R.id.tvItemAutor);
            tvItemCategoria = itemView.findViewById(R.id.tvItemCategoria);
            this.listener = listener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(getAdapterPosition());
                }
            });

        }

        public void bindFrase(Frase frase) {
            tvFrase.setText(frase.getTexto());
            tvItemAutor.setText(frase.getAutor().getNombre());
            tvItemCategoria.setText(frase.getCategoria().getNombre());
        }




    }

}
