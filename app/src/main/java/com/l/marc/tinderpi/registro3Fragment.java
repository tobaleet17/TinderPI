package com.l.marc.tinderpi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;


public class registro3Fragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private loginFragment login;
    EditText resultadoBuscar;
    private Personas personas;
    TextView resultado;
    Button siguiente;

    private Context mainContext;

//subida
    private FirebaseAuth mAuth;


    public registro3Fragment() {
    }

    public static registro3Fragment newInstance(Personas param1, Bitmap imagen) {
        registro3Fragment fragment = new registro3Fragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);

        ByteArrayOutputStream streamArray = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG,100,streamArray);
        byte[] bytesImagen = streamArray.toByteArray();

        args.putByteArray(ARG_PARAM2,bytesImagen);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            personas = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_registro3, container, false);
        resultadoBuscar=v.findViewById(R.id.resultadoBuscar);
        resultadoBuscar.setOnClickListener(this);
        resultado = v.findViewById(R.id.resultadooo);
        resultado.setText(personas.getNombre()+", "+personas.getLocalidad()+", "+personas.getNombreUsuario()+", "+personas.getPreferenciasSexuales());
        siguiente=v.findViewById(R.id.aceptar);
        siguiente.setOnClickListener(this);

        mainContext = v.getContext();

        mAuth = FirebaseAuth.getInstance();
        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.resultadoBuscar){
            final CharSequence[] items = { "Hombres", "Mujeres", "Genero Binario", "Ambos", "Todos"};

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Seleccione lo que esta buscando:");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {

                    resultadoBuscar.setText(items[item]);
                    dialog.dismiss();

                }
            });
            builder.show();
        }

        if (v.getId()==R.id.aceptar){
            if (comprobar()==true){
                conexion();
                login=new loginFragment();
                ((NavigationHost) getActivity()).navigateTo(login,false);

            }
            else
                Toast.makeText(getContext(),"no va",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean comprobar(){
        boolean comproba;
        comproba=true;
        String Buscar= resultadoBuscar.getText().toString().trim();
        if(Buscar.isEmpty()){

            resultadoBuscar.setError("Debe seleccionar una fecha.");
            comproba=false;
        }
        else
        {
            resultadoBuscar.setError(null);
        }
        return comproba;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void  conexion(){
        mAuth.createUserWithEmailAndPassword(personas.getEmail(), personas.getPass())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
}