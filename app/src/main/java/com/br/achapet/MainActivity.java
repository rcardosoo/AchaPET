package com.br.achapet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.br.achapet.model.Adapter.AnimalAdapter;
import com.br.achapet.model.Animal;
import com.br.achapet.model.DAO.AnimalDAO;
import com.br.achapet.model.Usuario;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Usuario logado;
    private ListView animalLv;
    private AnimalDAO dao;
    private AnimalAdapter adapter;
    private static final int CADASTRO_PET = 1, LOGIN = 2;
    private TextView tvNomeLogado;
    private TextView tvEmailLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TESTE", "START MAINACTIVTY CREATE");
        if (this.logado == null) {
            Log.i("TESTE", "ENTRA NO IF LOGIN == NULL MAINACTIVTY");
            Intent it = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(it, LOGIN);
        }
        //==================inicio configurações de menu

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //=================== fim configurações de menu
        mostraLista();
    }


    public void mostraLista() {
        //===================inicio configurações listview
        Log.i("TESTE", "ENTRA CONFIGURAÇÕES DO LISTVIEW MAINACTIVITY");
        this.dao = new AnimalDAO(this);
        this.animalLv = (ListView) findViewById(R.id.LvAnimal);
        this.adapter= new AnimalAdapter(this, this.dao);
        this.animalLv.setAdapter(adapter);
        this.animalLv.setOnItemClickListener(new ListaClickListener());
        Log.i("TESTE", "PASSOU DAS CONFS DE LISTVIERW MAINACTIVTY");
        //===================fim configurações listview
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == CADASTRO_PET){
                Log.i("TESTE", "CHEGOU NO RESULT DO MAINACTIVITY");
                Animal a = (Animal) data.getSerializableExtra("ANIMAL");
                this.dao.insert(a);
                this.atualizaLista();
            } else if (requestCode == LOGIN) {
                Log.i("TESTE", "RESULT LOGIN CHEGOU");
                Usuario u = (Usuario) data.getSerializableExtra("LOGADO");
                this.logado = u;
                Log.i("TESTE", "CHEGOU USUARIO LOGADO? "+this.logado.getLogin());
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void atualizaLista(){
        ((BaseAdapter)this.animalLv.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addPET) {
            Intent it = new Intent(MainActivity.this, CadastroPetActivity.class);

            it.putExtra("LOGADO", this.logado);
            startActivityForResult(it, CADASTRO_PET);
        }

        if (id == R.id.nav_sair){
            logoff();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ListaClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent it = new Intent(MainActivity.this, DetalhesAnimalActivity.class);
            Animal a = MainActivity.this.dao.get(i);
            it.putExtra("ANIMAL", a);
            startActivity(it);
        }
    }

    public void logoff(){
        this.logado = null;
        finish();
    }
 }
