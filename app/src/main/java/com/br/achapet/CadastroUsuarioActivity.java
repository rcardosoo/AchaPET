package com.br.achapet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.br.achapet.model.DAO.UsuarioDAO;
import com.br.achapet.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etLogin;
    private EditText etSenha;
    private EditText etEndereco;
    private EditText etEmail;
    private EditText etTelefone;
    private Button btnCadastrar;
    private Button btnVoltar;
    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        this.dao = new UsuarioDAO(this);
        this.setReferencias();
        this.setListeners();
    }

    private void setReferencias(){
        this.etNome = (EditText) findViewById(R.id.etUsuarioNome);
        this.etLogin = (EditText) findViewById(R.id.etUsuarioLogin);
        this.etSenha = (EditText) findViewById(R.id.etUsuarioSenha);
        this.etEndereco = (EditText) findViewById(R.id.etUsuarioEndereco);
        this.etEmail = (EditText) findViewById(R.id.etUsuarioEmail);
        this.etTelefone = (EditText) findViewById(R.id.etUsuarioTelefone);
        this.btnCadastrar = (Button) findViewById(R.id.btnUsuarioCadastrar);
        this.btnVoltar = (Button) findViewById(R.id.btnUsuarioVoltar);
    }

    private void setListeners(){
        this.btnCadastrar.setOnClickListener(new OnClickBotao());
        this.btnVoltar.setOnClickListener(new OnClickBotao());
    }

    private class OnClickBotao implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.equals(CadastroUsuarioActivity.this.btnCadastrar)){
                String nome = etNome.getText().toString();
                String login = etLogin.getText().toString();
                String senha = etSenha.getText().toString();
                String endereco = etEndereco.getText().toString();
                String email = etEmail.getText().toString();
                String telefone = etTelefone.getText().toString();

                Usuario u = new Usuario(nome, login, senha, endereco, email, telefone);
                dao.insert(u);

                finish();
            }else if(view.equals(CadastroUsuarioActivity.this.btnVoltar)){
                finish();
            }


        }
    }
}
