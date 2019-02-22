package com.l.marc.tinderpi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragmentTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rc;
    private RecyclerChat adaptadorRecycler;
    private RecyclerView.LayoutManager rvLM;
    private ArrayList<ChatPickerModel> arrayChatPicker;

    private View viewAux;

    private DatabaseReference bbdd;
    private DatabaseReference bbddUser;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;

    private FragmentListenerChat mListener;

    public ChatFragmentTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragmentTab.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragmentTab newInstance(String param1, String param2) {
        ChatFragmentTab fragment = new ChatFragmentTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.chat_fragment_tab, container, false);
        viewAux = inflater.inflate(R.layout.chat_fragment_tab, container, false);

        arrayChatPicker = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        bbdd = FirebaseDatabase.getInstance().getReference("Usuarios").child("Usuario").child("matches");
        bbddUser = FirebaseDatabase.getInstance().getReference("Usuarios");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot match: dataSnapshot.getChildren())
                {
                    ChatPickerModel cpm;
                    if (match.getChildrenCount()>1)
                    {
                        Log.d("MIO","2 hijos");
                        Log.d("MIO",match.child("idDelUser").getValue().toString());
                        cpm = new ChatPickerModel(match.child("idDelUser").getValue().toString());
                        arrayChatPicker.add(cpm);
                        Log.d("MIO",""+arrayChatPicker.size());
                    }
                    else
                    {
                        Log.d("MIO","1 hijo");
                        cpm = new ChatPickerModel(match.child("idDelUser").getValue().toString());
                        arrayChatPicker.add(cpm);
                    }
                }
                iniciarRecycler();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentCharTab();
        }
    }

    public void iniciarRecycler()
    {
        rc = (RecyclerView) viewAux.findViewById(R.id.recycler_chat_picker);
        rvLM = new LinearLayoutManager(viewAux.getContext());
        rc.setLayoutManager(rvLM);

        adaptadorRecycler = new RecyclerChat(arrayChatPicker);
        rc.setAdapter(adaptadorRecycler);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListenerChat) {
            mListener = (FragmentListenerChat) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface FragmentListenerChat {
        // TODO: Update argument type and name
        void onFragmentCharTab();
    }
}
