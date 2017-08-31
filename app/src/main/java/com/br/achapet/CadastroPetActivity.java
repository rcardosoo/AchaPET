package com.br.achapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.br.achapet.model.Animal;
import com.br.achapet.model.Usuario;

public class CadastroPetActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etRaca;
    private EditText etTipo;
    private EditText etDescricao;
    private EditText etIdade;
    private EditText etPorte;
    private EditText etFoto;
    private Usuario logado;
    private Button btSalvar;
    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        this.setReferencias();
        this.setListeners();

        try {  //obter usuario pela intent
            this.logado = (Usuario) getIntent().getSerializableExtra("LOGADO");
        }catch (Exception e) {

        }
    }

    private void setReferencias(){
        this.etNome = (EditText) findViewById(R.id.etNome);
        this.etRaca = (EditText) findViewById(R.id.etRaca);
        this.etTipo = (EditText) findViewById(R.id.etTipo);
        this.etDescricao = (EditText) findViewById(R.id.etDescricao);
        this.etIdade = (EditText) findViewById(R.id.etIdade);
        this.etPorte = (EditText) findViewById(R.id.etPorte);
        this.etFoto = (EditText) findViewById(R.id.etFoto);
        this.btSalvar = (Button) findViewById(R.id.btSalvar);
        this.btVoltar = (Button) findViewById(R.id.btVoltar);
    }

    private void setListeners(){
        this.btSalvar.setOnClickListener(new OnClickBotao());
    }

    private class OnClickBotao implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if(view.equals(CadastroPetActivity.this.btSalvar)){
                String nome = etNome.getText().toString();
                String raca = etRaca.getText().toString();
                String tipo = etTipo.getText().toString();
                String descricao = etDescricao.getText().toString();
                int idade = Integer.parseInt(etIdade.getText().toString());
                String foto = etFoto.getText().toString();
                String porte = etPorte.getText().toString();

                Animal a = new Animal(nome, raca, tipo, descricao, idade, porte, foto, CadastroPetActivity.this.logado);
                Log.i("TESTE", "Usuario dono do pet = "+CadastroPetActivity.this.logado.getLogin());
                Log.i("TESTE", "Animal criado? "+a.getNome());
                Log.i("TESTE", "RACAUA "+a.getRaca());
                Log.i("TESTE", "Usuario criado? "+a.getUsuario().getNome());
                Intent it = new Intent();
                it.putExtra("ANIMAL", a);

                setResult(RESULT_OK, it);
                finish();
            }else if (view.equals(CadastroPetActivity.this.btVoltar)){
                Intent it = new Intent();
                setResult(RESULT_CANCELED, it);
                finish();
            }

        }
    }
}
