package com.example.cleiton.final162.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.cleiton.final162.DAO.Entrega;
import com.example.cleiton.final162.DAO.EntregaDAO;
import com.example.cleiton.final162.R;

import java.util.List;

public class Lista extends ListFragment {

    List<Entrega> entregas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //lista
        entregas = new EntregaDAO(getContext()).listar();

        //adapter
        ListAdapter adapter = new ArrayAdapter<>(this.getContext(),android.R.layout.simple_list_item_1, entregas);

        //vinculando o adapter para  ListFragment
        setListAdapter(adapter);

        //pra o caso da tela virar
        setRetainInstance(true);

        //inflater
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment, container, false);

//        setHasOptionsMenu(true);

        return rootView;

    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        Snackbar.make(v, "End: "+ l.getItemAtPosition(position).toString(),Snackbar.LENGTH_INDEFINITE).show();
        Snackbar
                .make(v, l.getItemAtPosition(position).toString(),Snackbar.LENGTH_INDEFINITE) //dá o título e a duração do snackbar
                .setAction("Inserir produtos", new View.OnClickListener() { //cria um action com um listener
                    @Override
                    public void onClick(View v) {

                        //vou precisar de meia hora pra explicar essa quantidade de métodos aninhados
                        startActivity(new Intent(getContext(),CadastroEntregaProduto.class).putExtra("id",((Entrega)getListAdapter().getItem(position)).getId()));
                    }
                })
                .show();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"Ver no mapa");
        menu.add(0,2,0,"Marcar como entregue");
        menu.add(0,3,0,"Ver produtos");
        menu.add(0,4,0,"Editar entrega");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        registerForContextMenu(getListView());

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.menu_adicionar:
//                Toast.makeText(this, "deu certo", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, FormPessoa.class));
//                return false;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        if (v.getId() == R.id.lista_pessoas){
//            ListView listView = (ListView) v;
//            AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) menuInfo;
//            pessoa_selecionada = (Pessoa)listView.getItemAtPosition(((AdapterView.AdapterContextMenuInfo)menuInfo).position);
//
//            menu.add(0,1,menu.NONE,"Ver no mapa");
//            menu.add(0,2,menu.NONE,"Editar");
//            menu.add(0,3,menu.NONE,"Apagar");
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case 1:
//                startActivity(new Intent(this,Mapa.class).putExtra("id_pessoa",pessoa_selecionada.getId()));
//                break;
//            case 2:
//                editaPessoa(pessoa_selecionada);
//                break;
//            case 3:
//                apagaPessoa(pessoa_selecionada);
//                break;
//            default:
//        }
//        recreate();
//        return super.onContextItemSelected(item);
//    }
//
//    private void apagaPessoa(Pessoa pessoa_selecionada) {
//        try {
//            dao.deletar(pessoa_selecionada.getId());
//            Toast.makeText(this, pessoa_selecionada.getNome()+" apagada.", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void editaPessoa(Pessoa pessoa_selecionada) {
//        startActivity(new Intent(this,FormPessoa.class).putExtra("pessoa",pessoa_selecionada.getId()));
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lista);
//        lista_pessoas = dao.listar();
//        if (lista_pessoas == null){
//            Toast.makeText(this, "lista null", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            lista = (ListView) findViewById(R.id.lista_pessoas);
//
//            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista_pessoas);
//            lista.setAdapter(listAdapter);
//
//            registerForContextMenu(findViewById(R.id.lista_pessoas));
//        }
//    }
}