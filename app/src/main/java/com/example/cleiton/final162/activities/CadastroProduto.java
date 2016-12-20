package com.example.cleiton.final162.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cleiton.final162.DAO.Produto;
import com.example.cleiton.final162.DAO.ProdutoDAO;
import com.example.cleiton.final162.R;

public class CadastroProduto extends AppCompatActivity {

    EditText pNome,pTipo,pPreco;
    Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        pNome = (EditText) findViewById(R.id.nome_produto);
        pTipo = (EditText) findViewById(R.id.tipo_produto);
        pPreco = (EditText) findViewById(R.id.preco_produto);

        produto = new Produto();
        produto.setId(getIntent().getIntExtra("id",0));
    }

    public void salvarProduto(View view) {
        produto.setNome(pNome.getText().toString());
        produto.setTipo(pTipo.getText().toString());
        produto.setPreco(Double.parseDouble(pPreco.getText().toString()));

        new ProdutoDAO(CadastroProduto.this).salvar(produto);
        Toast.makeText(this, "salvo.", Toast.LENGTH_SHORT).show();
    }
}
