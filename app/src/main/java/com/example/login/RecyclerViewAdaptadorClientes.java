package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdaptadorClientes extends RecyclerView.Adapter<RecyclerViewAdaptadorClientes.ViewHolder> {

    private List<Clientes> listaClients;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_clientes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdaptadorClientes.ViewHolder holder, int position) {
        Clientes clientes = listaClients.get(position);

        holder.infoNom.setText(clientes.getNombre_cliente());
        holder.infoApe.setText(clientes.getApellido());
        holder.infoDire.setText(clientes.getDireccion());
        holder.infotel.setText(clientes.getTelefono());
        holder.infogenero.setText(clientes.getGenero());
        holder.infopais.setText(clientes.getPais());
        holder.infoDate.setText(clientes.getFecha());
        holder.infoRegsitro.setText(clientes.getFechaRegistro());

    }

    public RecyclerViewAdaptadorClientes(List<Clientes> listaClients) {
        this.listaClients = listaClients;
    }

    @Override
    public int getItemCount() {
        return listaClients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView infoNom,infoApe,infoDire,infogenero,infopais,infoDate,infoRegsitro,infotel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoNom = (TextView) itemView.findViewById(R.id.InfoNom);
            infoApe = (TextView) itemView.findViewById(R.id.infoApe);
            infoDire = (TextView) itemView.findViewById(R.id.infoDire);
            infotel = (TextView) itemView.findViewById(R.id.infoTel);
            infogenero = (TextView) itemView.findViewById(R.id.infoOpcion);
            infopais = (TextView) itemView.findViewById(R.id.infoPais);
            infoDate = (TextView) itemView.findViewById(R.id.infoDate);
            infoRegsitro = (TextView) itemView.findViewById(R.id.infoRegistro);

        }
    }
}
