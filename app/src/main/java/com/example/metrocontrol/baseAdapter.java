package com.example.metrocontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.controlmetro.R;

public class baseAdapter extends BaseAdapter {

    Context context;
    String[] vehicleLijst;
    int[] listImages;
    LayoutInflater inflater;

    public baseAdapter(Context ctx, String[] lijst, int[] images){
        this.context = ctx;
        this.vehicleLijst = lijst;
        this.listImages = images;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {

        return vehicleLijst.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_train_item,  null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.listIcon);
        txtView.setText(vehicleLijst[position]);
        imgView.setImageResource(listImages[0]);
        return convertView;
    }
}
