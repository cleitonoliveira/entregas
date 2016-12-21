package com.example.cleiton.final162.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cleiton.final162.DAO.Entrega;
import com.example.cleiton.final162.DAO.EntregaDAO;
import com.example.cleiton.final162.R;

public class CadastroEntrega extends AppCompatActivity {

    Entrega entrega;
    EntregaDAO entregaDAO;
    TextView tEndereco, tCliente, tObs;
    Switch sEntregue;
    ListView lista_pe;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lista_pe){
            ListView listView = (ListView)v;
            AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) menuInfo;

            menu.add(0,1,0,"Editar");
            menu.add(0,2,0,"Apagar");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
//        switch (item.getItemId()){
//            case 1:
//
//                break;
//            case 2:
//                break;
//
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_entrega);

        tEndereco = (TextView) findViewById(R.id.form_endereco);
        tCliente = (TextView) findViewById(R.id.form_cliente);
        tObs = (TextView) findViewById(R.id.form_obs);
        sEntregue = (Switch) findViewById(R.id.entregue);
        lista_pe = (ListView)findViewById(R.id.lista_pe);

        registerForContextMenu(lista_pe);


        //se a activity veio com um valor no intent, significa que é uma edição.
        entrega = new EntregaDAO(this).buscar(getIntent().getIntExtra("id",0));
        if (entrega != null && entrega.getId() != 0){
            tEndereco.setText(entrega.getEndereco());
            tCliente.setText(entrega.getCliente());
            tObs.setText(entrega.getObs());
        }
    }

    public void salvarEntrega(View view) {
        String endereco,cliente,obs;
        endereco = tEndereco.getText().toString();
        cliente = tCliente.getText().toString();
        obs = tObs.getText().toString();

        entrega = new Entrega();
        entrega.setEndereco(endereco);
        entrega.setCliente(cliente);
        entrega.setObs(obs);
        entrega.setEntregue(sEntregue.isChecked());

        try {
            //salva os valores.
            entrega.setId(new EntregaDAO(CadastroEntrega.this).salvar(entrega));

            //cria um snackbar de confirmação que só aparece se o try funcionar.
            Snackbar
                    .make(view,"Salvo. Insira os produtos.",Snackbar.LENGTH_INDEFINITE) //dá o título e a duração do snackbar
                    .setAction("Inserir produto", new View.OnClickListener() { //cria um action com um listener
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(CadastroEntrega.this,CadastroEntregaProduto.class).putExtra("id",entrega.getId())); //chama o cadastro de produtos
                        }
                    })
                    .show(); //mostra o snackbar de fato
        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(view,"Erro ao salvar.",Snackbar.LENGTH_SHORT).show();
        }
    }

}
