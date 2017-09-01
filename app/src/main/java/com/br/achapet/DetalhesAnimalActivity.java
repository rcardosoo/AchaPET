package com.br.achapet;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.achapet.model.Animal;
import com.br.achapet.model.DAO.AnimalDAO;

public class DetalhesAnimalActivity extends AppCompatActivity {
    private TextView tvNome;
    private TextView tvRaca;
    private TextView tvTipo;
    private TextView tvDescricao;
    private TextView tvIdade;
    private TextView tvPorte;
    private Button btnVoltar;
    private Button btnAdotar;
    private ImageView ivFoto;
    private AnimalDAO dao;
    private Animal a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_animal);

        this.dao = new AnimalDAO(this);
        //SETAR REFERENCIAS E LISTENERS
        this.setReferencias();
        this.setListenerBotoes();
        //PEGAR O ANIMAL DA INTENT
        Intent it = this.getIntent();
        this.a = (Animal) it.getSerializableExtra("ANIMAL");
        //SETAR OS DADOS DO ANIMAL NA VIEW
        this.tvNome.setText("Nome: "+a.getNome());
        this.tvRaca.setText("Raça: "+a.getRaca());
        this.tvTipo.setText("Tipo: "+a.getTipo());
        this.tvDescricao.setText("Descrição: "+a.getDescricao());
        this.tvIdade.setText("Idade: "+Integer.toString(a.getIdade()));
        this.tvPorte.setText("Porte: "+a.getPorte());
    }

    private void setReferencias(){
        this.tvNome = (TextView) findViewById(R.id.tvAnimalDetalhesNome);
        this.tvRaca = (TextView) findViewById(R.id.tvAnimalDetalhesRaca);
        this.tvTipo = (TextView) findViewById(R.id.tvAnimalDetalhesTipo);
        this.tvDescricao = (TextView) findViewById(R.id.tvAnimalDetalhesDescricao);
        this.tvIdade = (TextView) findViewById(R.id.tvAnimalDetalhesIdade);
        this.tvPorte = (TextView) findViewById(R.id.tvAnimalDetalhesPorte);
        this.btnVoltar = (Button) findViewById(R.id.btnDetalhesAnimalVoltar);
        this.btnAdotar = (Button) findViewById(R.id.btnDetalhesAnimalAdotar);
        this.ivFoto = (ImageView) findViewById(R.id.ivAnimaDetalhesFoto);
    }

    private void setListenerBotoes(){
        this.btnAdotar.setOnClickListener(new OnClickBotao());
        this.btnVoltar.setOnClickListener(new OnClickBotao());
    }

    private class OnClickBotao implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.equals(DetalhesAnimalActivity.this.btnAdotar)){
                AlertDialog.Builder builder = new AlertDialog.Builder(DetalhesAnimalActivity.this);
                builder.setTitle("Você deseja adotar esse animal?");
                builder.setMessage("Informaçõs do dono:"+"\nNome: "+a.getUsuario().getNome() + "\nTelefone: "+ a.getUsuario().getTelefone()+
                                    "\nE-mail: "+ a.getUsuario().getEmail()+ "\nEndereço: "+a.getUsuario().getEndereco());
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Animal b = a;
                        b.setAdotado(1);
                        dao.update(a, b);
                        String email = "mailto: "+a.getUsuario().getEmail();

                        Uri uri = Uri.parse(email);
                        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                        it.putExtra(Intent.EXTRA_SUBJECT, "INTERESSE EM ADOÇÃO");
                        it.putExtra(Intent.EXTRA_TEXT, "Olá, tenho interesse em adotar o "+a.getNome()+", aguardo um retorno!");
                        startActivity(Intent.createChooser(it, "Enviar e-mail"));
                        finish();
                    }
                });
                builder.setNeutralButton("Ligar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String telefone = a.getUsuario().getTelefone();
                        Uri uri = Uri.parse("tel:"+telefone);
                        Intent it = new Intent(Intent.ACTION_CALL, uri);
                        int permissao = ActivityCompat.checkSelfPermission(DetalhesAnimalActivity.this, Manifest.permission.CALL_PHONE);
                        if (permissao == PackageManager.PERMISSION_GRANTED){
                            startActivity(it);
                        }
                    }
                });
                builder.setNegativeButton("Não", null);
                builder.create().show();

            }else if(v.equals(DetalhesAnimalActivity.this.btnVoltar)){
                finish();
            }
        }
    }


}
