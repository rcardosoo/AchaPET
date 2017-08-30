package com.br.achapet.model.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.br.achapet.model.DAO.UsuarioDAO;

/**
 * Created by Rafael on 30/08/2017.
 */

public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    private UsuarioDAO dao;

    public UsuarioAdapter(Context context, UsuarioDAO dao) {
        this.context = context;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return this.dao.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
