package com.example.cleiton.final162.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleiton.final162.R;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

//    Sistema de entrega. Main.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia a toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //controla as abas
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        //carrega as abas
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //o FAB confere o índice da aba para realizar ações separadas em cada uma
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (viewPager.getCurrentItem()){
                    case 0:
                        startActivity(new Intent(Main.this,CadastroEntrega.class));
                        break;
                    case 1:
                        startActivity(new Intent(Main.this,CadastroProduto.class));
                        break;
                    case 2:
//                        startActivity(new Intent(Main.this,Mapa.class));
                        break;
                }
            }
        });
    }

    //controla as abas e carrega os fragments no adapter
    private void setupViewPager(ViewPager viewPager){
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new Lista(),"Entregas");
        fragmentAdapter.addFragment(new Lista(),"Produtos");
        fragmentAdapter.addFragment(new Lista(),"Mapa");
        viewPager.setAdapter(fragmentAdapter);
    }

    /**
     * Cria um adapter personalizado para as abas. Não consegui fazer sem criar essa classe.
     * No android developers não tem um básico, eles tambem criam assim.
     * */
    static class FragmentAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>(); //lista de fragments
        private final List<String> mFragmentTitles = new ArrayList<>(); //os títulos deles

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        } //construtor

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position); //retorna o fragment selecionado pela posição dele
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment); //adiciona o fragment passado por parâmetro
            mFragmentTitles.add(title);//adiciona o título do fragment passado por parâmetro
        }

        @Override
        public int getCount() {
            return mFragments.size(); //conta as abas
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position); // mostra o título da aba. Não sei porque não funciona sem isso sendo que ele nem é chamado.
        }
    }

}
