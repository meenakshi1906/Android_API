package com.example.meenu_androidapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Hero> {
    List<Hero> heroList;
    Context context;

    public ListViewAdapter(List<Hero> heroList, Context context1) {
        super(context1, R.layout.custom_layout_listview,heroList);
        this.heroList = heroList;
        this.context = context1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listviewitem = inflater.inflate(R.layout.custom_layout_listview,null,true);
        TextView name = listviewitem.findViewById(R.id.name);
        TextView imageurl = listviewitem.findViewById(R.id.imageurl);
        Hero hero = heroList.get(position);
        name.setText(hero.getName());
        imageurl.setText(hero.getImageURL());
        return listviewitem;
    }
}
