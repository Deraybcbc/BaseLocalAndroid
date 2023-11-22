package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptadorAll extends RecyclerView.Adapter<RecyclerViewAdaptadorAll.ViewHolder> {

    private List<Clientes> TotalClientes;

    @Override
    public RecyclerViewAdaptadorAll.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_all,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptadorAll.ViewHolder holder, int position) {
        Clientes clientes = TotalClientes.get(position);

        holder.infoID.setText("ID USUARIO "+String.valueOf(clientes.getId_cliente()));
        holder.infoNom.setText(clientes.getNombre_cliente());
        holder.infoApe.setText(clientes.getApellido());
        holder.infoDire.setText(clientes.getDireccion());
        holder.infotel.setText(clientes.getTelefono());
        holder.infogenero.setText(clientes.getGenero());
        holder.infopais.setText(clientes.getPais());
        holder.infoDate.setText(clientes.getFecha());
        holder.infoRegsitro.setText(clientes.getFechaRegistro());
    }

    public RecyclerViewAdaptadorAll (List<Clientes> listaClientes){
        this.TotalClientes = listaClientes;
    }

    @Override
    public int getItemCount() {
        return TotalClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView infoID,infoNom,infoApe,infoDire,infogenero,infopais,infoDate,infoRegsitro,infotel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            infoID = (TextView) itemView.findViewById(R.id.Id_cli);
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
