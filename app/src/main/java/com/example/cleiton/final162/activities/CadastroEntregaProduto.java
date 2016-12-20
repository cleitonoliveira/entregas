package com.example.cleiton.final162.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleiton.final162.DAO.Entrega;
import com.example.cleiton.final162.DAO.EntregaDAO;
import com.example.cleiton.final162.DAO.PEDAO;
import com.example.cleiton.final162.DAO.Produto;
import com.example.cleiton.final162.DAO.ProdutoDAO;
import com.example.cleiton.final162.R;

public class CadastroEntregaProduto extends AppCompatActivity {

    Entrega entrega;
    TextView peCliente, peEnd;
    Spinner sProduto;
    EditText qtde;
    ArrayAdapter spinner_adapter,list_adapter;
    ListView lista_pedao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_entrega_produto);

        peCliente = (TextView) findViewById(R.id.cliente_pedao);
        peEnd = (TextView) findViewById(R.id.endereco_pedao);
        sProduto = (Spinner)findViewById(R.id.spinner_produtos);
        qtde = (EditText) findViewById(R.id.qtde);
        lista_pedao = (ListView) findViewById(R.id.lista_pedao);
        entrega = new EntregaDAO(this).buscar(getIntent().getIntExtra("id",0));

        spinner_adapter = new ArrayAdapter<>(
                CadastroEntregaProduto.this,
                android.R.layout.simple_spinner_dropdown_item,
                new ProdutoDAO(this).listar());
        sProduto.setAdapter(spinner_adapter);

        list_adapter = new ArrayAdapter<>(
            CadastroEntregaProduto.this,
            android.R.layout.simple_list_item_1,
            new PEDAO(this).listar(entrega.getId()));

        peCliente.setText(entrega.getCliente());
        peEnd.setText(entrega.getEndereco());
    }

    public void salvarEntregaProduto(View view) {
        Toast.makeText(this, ((Produto)spinner_adapter.getItem((int) sProduto.getSelectedItemId())).getNome()+"", Toast.LENGTH_SHORT).show();
    }

    public void adicionarItem(View view) {

    }
}
