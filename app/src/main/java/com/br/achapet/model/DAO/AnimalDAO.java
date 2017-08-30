package com.br.achapet.model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.br.achapet.Database.BancoHelper;
import com.br.achapet.model.*;

import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    private SQLiteDatabase banco;

    public AnimalDAO(Context context){
        Log.i("TESTE", "ENTROU NO CONSTRUTOR DE ANIMALDAO");
        this.banco = new BancoHelper(context).getWritableDatabase();
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

        this.banco.insert(BancoHelper.TABELA_ANIMAL, null, cv);
    }

    public Animal get(int index){
        return this.get().get(index);
    }

    public List<Animal> get(){
        List<Animal> lista = new ArrayList<Animal>();
        String[] colunas = {"codigo", "nome", "raca", "tipo", "descricao", "idade", "porte", "foto", "adotado", "codigo_usuario"};
        Cursor c = this.banco.query(BancoHelper.TABELA_ANIMAL, colunas, null, null, null, null, null);
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
                UsuarioDAO usuarioDAO = new UsuarioDAO(null);
                Usuario u = usuarioDAO.getByCodigo(c.getInt(c.getColumnIndex("codigo_usuario")));
                if (u != null) {
                    a.setDono(u);
                } else {
                    a.setDono(null);
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

