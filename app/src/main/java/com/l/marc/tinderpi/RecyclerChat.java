package com.l.marc.tinderpi;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerChat extends RecyclerView.Adapter<RecyclerChat.ChatPickerViewHolder> {

    private ArrayList<ChatPickerModel> arrayChats;

    public static class ChatPickerViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements AdapterView.OnClickListener {

        public TextView nombre;
        public TextView ultimMensaje;
        public ImageView imagen;

        public Context context;
        public View v;

        public ChatPickerViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=(TextView)itemView.findViewById(R.id.cartaChatPicker_nombre);
            ultimMensaje = (TextView) itemView.findViewById(R.id.cartaChatPicker_ultimMensaje);
            imagen = (ImageView) itemView.findViewById(R.id.cartaChatPicker_imagen);

            v = itemView;
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerChat(ArrayList<ChatPickerModel> array)
    {
        this.arrayChats = array;
        Log.d("MIO",arrayChats.size()+"");
    }

    @NonNull
    @Override
    public ChatPickerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_chat,viewGroup, false);
        Log.d("MIO","Hola inflo un layout");

        return new ChatPickerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatPickerViewHolder chatPickerViewHolder, int i) {
        chatPickerViewHolder.nombre.setText(arrayChats.get(i).getNombreUser());
    }

    @Override
    public int getItemCount() {
        return arrayChats.size();
    }
}