package com.br.achapet.model.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.achapet.R;
import com.br.achapet.model.Animal;
import com.br.achapet.model.DAO.AnimalDAO;

/**
 * Created by Rafael on 30/08/2017.
 */

public class AnimalAdapter extends BaseAdapter {
    private Context context;
    private AnimalDAO dao;

    public AnimalAdapter(Context context, AnimalDAO dao) {
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
        View view;

        if (convertView == null){
            LayoutInflater li = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.animal_layout, null);
        }else
            view = convertView;

        TextView tvNome = (TextView) view.findViewById(R.id.tvNomeAnimal);
        TextView tvRaca = (TextView) view.findViewById(R.id.tvRacaAnimal);
        TextView tvFoto = (TextView) view.findViewById(R.id.tvFotoAnimal);

        Animal a = this.dao.get(position);

        tvNome.setText(a.getNome());
        tvRaca.setText(a.getRaca());
        tvFoto.setText(a.getFoto());

        view.setMinimumHeight(200);

        if (position % 2 == 0)
            view.setBackgroundColor(Color.rgb(244,244,244));
        else
            view.setBackgroundColor(Color.WHITE);

        return view;
    }
}
