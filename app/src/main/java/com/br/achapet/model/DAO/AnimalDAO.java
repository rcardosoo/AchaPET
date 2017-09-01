package com.br.achapet.model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.br.achapet.Database.BancoHelper;
import com.br.achapet.MainActivity;
import com.br.achapet.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalDAO {
    private SQLiteDatabase banco;
    private UsuarioDAO usudao;

    public AnimalDAO(Context context){
        Log.i("TESTE", "ENTROU NO CONSTRUTOR DE ANIMALDAO");
        this.banco = new BancoHelper(context).getWritableDatabase();
        this.usudao = new UsuarioDAO(context);
        Log.i("TESTE", "INSTANCIOU USUARIO DAO");
    }

    public void insert(Animal novo){
        ContentValues cv = new ContentValues();
        cv.put("nome", novo.getNome());
        cv.put("raca", novo.getRaca());
        cv.put("tipo", novo.getTipo());
        cv.put("descricao", novo.getDescricao());
        cv.put("idade", novo.getIdade());
        cv.put("porte", novo.getPorte());
        cv.put("foto", novo.getFoto());
        cv.put("adotado", novo.isAdotado());
        cv.put("codigo_usuario", novo.getUsuario().getCodigo());
        Log.i("TESTE", "VAI INSERIR ANIMAL NO BANCO");
        Log.i("TESTE", "RACA DELE = "+novo.getRaca());
        this.banco.insert(BancoHelper.TABELA_ANIMAL, null, cv);
        Log.i("TESTE", "INSERIU ANIMAL NO BANCO");
    }

    public Animal get(int index){
        return this.get().get(index);
    }

    public List<Animal> get(){
        Log.i("TESTE", "ENTROU NO GET ANIMAIS");
        List<Animal> lista = new ArrayList<Animal>();
        String[] colunas = {"codigo", "nome", "raca", "tipo", "descricao", "idade", "porte", "foto", "adotado", "codigo_usuario"};
        Cursor c = this.banco.query(BancoHelper.TABELA_ANIMAL, colunas, null, null, null, null, null);
        Log.i("TESTE", "COLOCOU TODOS OS ANIMAIS NO CURSOR");
        Animal a;

        if (c.getCount() > 0){
            c.moveToFirst();
            do{
                a = new Animal();
                a.setCodigo(c.getInt(c.getColumnIndex("codigo")));
                a.setNome(c.getString(c.getColumnIndex("nome")));
                a.setRaca(c.getString(c.getColumnIndex("raca")));
                a.setTipo(c.getString(c.getColumnIndex("tipo")));
                a.setDescricao(c.getString(c.getColumnIndex("descricao")));
                a.setIdade(c.getInt(c.getColumnIndex("idade")));
                a.setPorte(c.getString(c.getColumnIndex("porte")));
                a.setFoto(c.getString(c.getColumnIndex("foto")));
                a.setAdotado(c.getInt(c.getColumnIndex("adotado")));
                Log.i("FUDEU", "NOME DO DITO CUJO/: "+a.getNome()+" ADOTADO? "+a.isAdotado());
                Log.i("TESTE", "PEGOU OS CAMPOS - MENOS USUARIO");

                int codUsu = c.getInt(c.getColumnIndex("codigo_usuario"));
                Log.i("TESTE", "VAI GETAR O USUARIO PELO CODIGO = "+codUsu);
                Usuario u = usudao.getByCodigo(codUsu);
                Log.i("TESTE", "GETOU O USUARIO");
                if (u != null) {
                    Log.i("TESTE", "CONSEGUIU OBTER DONO DO ANIMAL");
                    a.setDono(u);
                } else {
                    Log.i("TESTE", "ERRO EM OBTER DONO DO ANIMAL");
                    a.setDono(null);
                }
                Log.i("TESTE", "PEGOU UM ANIMAL");

                if(a.isAdotado()==0){
                    //QUANDO CONSTROI O ANIMAL É COLOCADO O "0"(ZERO), QUE QUER DIZER QUE ELE NÃO É ADOTADO
                    //ANIMAIS QUE TEM 1 NO ADOTADO É QUE FORAM ADOTADOS
                    //Log.i("TESTE", "ADICIONOU NO ARRAY");
                    //Log.i("ANIMAL", "Nome: "+a.getNome());
                    Log.i("FUDEU", "ANTES DO ADD");

                    Log.i("FUDEU", "DEPOIS DO ADD");
                }
                lista.add(a);
            }while(c.moveToNext());
        }
        return lista;
    }

    public int size(){
        String[] colunas = {"codigo", "nome", "raca", "tipo", "descricao", "idade", "porte", "foto", "adotado", "codigo_usuario"};
        Cursor c = this.banco.query(BancoHelper.TABELA_ANIMAL, colunas, null, null, null, null, null);
        return c.getCount();
    }

    public void update(Animal antigo, Animal novo){
        ContentValues cv = new ContentValues();
        cv.put("nome", novo.getNome());
        cv.put("raca", novo.getRaca());
        cv.put("tipo", novo.getTipo());
        cv.put("descricao", novo.getDescricao());
        cv.put("idade", novo.getIdade());
        cv.put("porte", novo.getPorte());
        cv.put("foto", novo.getFoto());
        cv.put("adotado", novo.isAdotado());
        cv.put("codigo_usuario", novo.getUsuario().getCodigo());

        String[] where = {Integer.toString(antigo.getCodigo())};

        this.banco.update(BancoHelper.TABELA_ANIMAL, cv, "codigo = ?", where);
    }

    public void delete(Animal a){
        String[] where = {Integer.toString(a.getCodigo())};
        this.banco.delete(BancoHelper.TABELA_ANIMAL, "codigo = ?", where);
    }
}