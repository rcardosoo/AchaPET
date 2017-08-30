package com.br.achapet.model.DAO;

/**
 * Created by Rafael on 30/08/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.br.achapet.Database.BancoHelper;
import com.br.achapet.model.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private SQLiteDatabase banco;

    public UsuarioDAO(Context context){
        this.banco = new BancoHelper(context).getWritableDatabase();
    }

    public void insert(Usuario novo){
        ContentValues cv = new ContentValues();
        cv.put("nome", novo.getNome());
        cv.put("login", novo.getLogin());
        cv.put("senha", novo.getSenha());
        cv.put("endereco", novo.getEndereco());
        cv.put("email", novo.getEmail());
        cv.put("telefone", novo.getTelefone());
        this.banco.insert(BancoHelper.TABELA_USUARIO, null, cv);
    }

    public Usuario get(int index){
        return this.get().get(index);
    }

    public Usuario getByCodigo(int codigo) {
        for (Usuario u : this.get() ) {
            if (u.getCodigo() == codigo) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> get(){
        List<Usuario> lista = new ArrayList<Usuario>();
        String[] colunas = {"codigo", "nome", "login", "senha", "endereco", "email", "telefone"};
        Cursor c = this.banco.query(BancoHelper.TABELA_USUARIO, colunas, null, null, null, null, null);
        Usuario u;

        if (c.getCount() > 0){
            c.moveToFirst();
            do{
                u = new Usuario();
                u.setCodigo(c.getInt(c.getColumnIndex("codigo")));
                u.setNome(c.getString(c.getColumnIndex("nome")));
                u.setLogin(c.getString(c.getColumnIndex("login")));
                u.setSenha(c.getString(c.getColumnIndex("senha")));
                u.setEndereco(c.getString(c.getColumnIndex("endereco")));
                u.setEmail(c.getString(c.getColumnIndex("email")));
                u.setTelefone(c.getString(c.getColumnIndex("telefone")));
                lista.add(u);
            }while(c.moveToNext());
        }

        return lista;
    }

    public int size(){
        String[] colunas = {"codigo", "nome", "login", "senha", "endereco", "email", "telefone"};
        Cursor c = this.banco.query(BancoHelper.TABELA_USUARIO, colunas, null, null, null, null, null);
        return c.getCount();
    }

    public void update(Usuario antigo, Usuario novo){
        ContentValues cv = new ContentValues();
        cv.put("nome", novo.getNome());
        cv.put("login", novo.getLogin());
        cv.put("senha", novo.getSenha());
        cv.put("endereco", novo.getEndereco());
        cv.put("email", novo.getEmail());
        cv.put("telefone", novo.getTelefone());

        String[] where = {Integer.toString(antigo.getCodigo())};

        this.banco.update(BancoHelper.TABELA_USUARIO, cv, "codigo = ?", where);
    }

    public void delete(Usuario u){
        String[] where = {Integer.toString(u.getCodigo())};
        this.banco.delete(BancoHelper.TABELA_USUARIO, "codigo = ?", where);
    }
}
