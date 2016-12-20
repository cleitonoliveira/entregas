//package com.example.cleiton.final162.activities;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
///**
// * Created by cleit on 17/06/2016.
// */
//public class AdapterCliente<C> extends ArrayAdapter<ClienteDAO> {
//
//    Context context;
//
//    public AdapterCliente(Context context, List<ClienteDAO> listaClientes) {
//        super(context, R.layout.item_cliente, listaClientes);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View view = convertView;
//        ClienteHolder holder = null;
//
//
//        if (view == null){
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            view = inflater.inflate(R.layout.item_cliente, parent, false);
//
//            holder = new ClienteHolder();
//            holder.idcliente = (TextView)view.findViewById(R.id.item_id_cliente);
//            holder.nomeCliente = (TextView) view.findViewById(R.id.item_nome_cliente);
//
//            view.setTag(holder);
//        }
//        else{
//            holder = (ClienteHolder)view.getTag();
//        }
//
//        ClienteDAO cliente = getItem(position);
//        holder.idcliente.setText(cliente.getId()+"");
//        holder.nomeCliente.setText(cliente.getNome());
//
//        return view;
//    }
//
//    public class ClienteHolder{
//        TextView idcliente;
//        TextView nomeCliente;
//    }
//}
