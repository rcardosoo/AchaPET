package com.br.achapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.achapet.model.Animal;
import com.br.achapet.model.DAO.UsuarioDAO;
import com.br.achapet.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText etLogin;
    private EditText etSenha;
    private Button btnLogar;
    private Button btnCadastrar;
    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.dao = new UsuarioDAO(this);
        this.setReferencias();
        this.setListeners();
    }

    private void setReferencias(){
        this.etLogin = (EditText) findViewById(R.id.etLoginLogin);
        this.etSenha = (EditText) findViewById(R.id.etLoginSenha);
        this.btnCadastrar = (Button) findViewById(R.id.btnLoginCadastrar);
        this.btnLogar = (Button) findViewById(R.id.btnLoginLogar);
    }

    private void setListeners(){
        this.btnCadastrar.setOnClickListener(new OnClickBotao());
        this.btnLogar.setOnClickListener(new OnClickBotao());
    }

    private class OnClickBotao implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.equals(LoginActivity.this.btnCadastrar)){
                Intent it = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(it);
            }else if(view.equals(LoginActivity.this.btnLogar)){
                Usuario logado = dao.login(etLogin.getText().toString(), etSenha.getText().toString());
                if(logado!=null){
                    Log.i("TESTE", "LOGOU "+logado.getNome());
                    Intent it = new Intent();
                    it.putExtra("LOGADO", logado);
                    setResult(RESULT_OK, it);

                }else{
                    CharSequence text = "Login ou senha incorretos!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }
            finish();
        }
    }
}