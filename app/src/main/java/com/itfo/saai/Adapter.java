package com.itfo.saai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Contacto> contactos;

    public Adapter(Context ctx, List<Contacto> contactos){
        this.inflater = LayoutInflater.from(ctx);
        this.contactos = contactos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_contactos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.tvName.setText(contactos.get(position).getNombre());
        holder.tvEmail.setText(contactos.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tvName,tvLastN,tvEmail;
        ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvNombre);
            tvLastN = itemView.findViewById(R.id.tvApellido);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            userImage = itemView.findViewById(R.id.imgUser);

            // handle onClick

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

