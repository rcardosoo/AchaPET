package com.br.achapet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.br.achapet.model.Animal;
import com.br.achapet.model.Usuario;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

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
    private Button btnCapturar;
    private ImageView ivFoto;
    static final int REQUEST_IMAGE_CAPTURE=1;
    String mCurrentPhotoPath;

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
        //this.etFoto = (EditText) findViewById(R.id.etFoto);
        this.btSalvar = (Button) findViewById(R.id.btSalvar);
        this.btVoltar = (Button) findViewById(R.id.btVoltar);
        this.btnCapturar = (Button) findViewById(R.id.btnCadastrarAnimalCapturar);
        this.ivFoto = (ImageView) findViewById(R.id.ivCadastrarAnimalFoto);
    }

    private void setListeners(){
        this.btSalvar.setOnClickListener(new OnClickBotao());
        this.btnCapturar.setOnClickListener(new OnClickBotao());
        this.btVoltar.setOnClickListener(new OnClickBotao());
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
                String foto = "animal.jpg";
                String porte = etPorte.getText().toString();

                Animal a = new Animal(nome, raca, tipo, descricao, idade, porte, foto, CadastroPetActivity.this.logado);

                Intent it = new Intent();
                it.putExtra("ANIMAL", a);

                setResult(RESULT_OK, it);
                finish();
            }else if (view.equals(CadastroPetActivity.this.btVoltar)){
                Intent it = new Intent();
                setResult(RESULT_CANCELED, it);
                finish();
            }else {
                if (view.equals(CadastroPetActivity.this.btnCapturar)) {
                    //dispatchTakePictureIntent();
//                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivFoto.setImageBitmap(imageBitmap);
            //galleryAddPic();
        }
    }

//    private File createImageFile() throws IOException {
//        File storageDir = Environment.getExternalStorageDirectory();
//        File image = File.createTempFile(
//                "example",  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
//
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            if (photoFile != null) {
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
//        }
//    }
//
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }
}
