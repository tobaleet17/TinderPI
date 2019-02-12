package com.l.marc.tinderpi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.

 * create an instance of this fragment.
 */
public class loginFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth mAuth;

    private Button entrar;
    private EditText email;
    private EditText pass;


    private fragmentCardView cardView_fragment;
    public loginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        entrar=v.findViewById(R.id.entrarapp);
        email=v.findViewById(R.id.emailentrar);
        pass=v.findViewById(R.id.passentrar);

        entrar.setOnClickListener(this);


        return v;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void entrar(){

        mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            cardView_fragment=new fragmentCardView();
                            ((NavigationHost) getActivity()).navigateTo(cardView_fragment,false);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Vuelve a comprobar el email y" +
                                    " la contraseña introducidos.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.entrarapp){
            entrar();
        }
    }
}
