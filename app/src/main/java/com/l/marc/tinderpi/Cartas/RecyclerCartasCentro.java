package com.l.marc.tinderpi.Cartas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.l.marc.tinderpi.R;
import com.l.marc.tinderpi.RecyclerChat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerCartasCentro extends BaseAdapter {

    private List<String> data;
    private CartasFragmentTab context;

    public RecyclerCartasCentro(List<String> data, CartasFragmentTab context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            v = inflater.inflate(R.layout.fragment_fragment_card_view, parent, false);


        }

        ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);

        Picasso.with(context.getContext()).load(R.drawable.camara).fit().centerCrop().into(imageView);
        TextView textView = (TextView) v.findViewById(R.id.sample_text);
        String item = (String)getItem(position);
        textView.setText(item);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
            }
        });
        return v;
    }
}
