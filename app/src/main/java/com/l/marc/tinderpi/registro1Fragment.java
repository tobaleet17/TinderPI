package com.l.marc.tinderpi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.

 * create an instance of this fragment.
 */
public class registro1Fragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String PASSWORD_PATTERN = ("^" +
            "(?=.*[0-9])" +         //Por lo menos un número
            "(?=.*[a-z])" +         //Por lo menos una letra Minuscula
            "(?=.*[A-Z])" +         //Por lo menos una letra Majúscula
            "(?=.*[a-zA-Z])" +      //Alguna letra
            //"(?=.*[@#$%^&+=])" +    //Algún caracter especial
            "(?=\\S+$)" +           //Sin espacio en blanco
            ".{4,20}" +               //Tiene que ser entre 4 y 20 caracteres
            "$");

    private EditText nombreUsuario;
    private EditText contrasenya;
    private EditText repetirContrasenya;
    private EditText email;
    private Button siguiente;

    private registro2Fragment registro2;

    public registro1Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registro1, container, false);

        nombreUsuario = v.findViewById(R.id.nombreUsuario);
        contrasenya = v.findViewById(R.id.contrasenya);
        repetirContrasenya = v.findViewById(R.id.repetirContrasenya);
        email = v.findViewById(R.id.email);
        siguiente = v.findViewById(R.id.siguiente);
        siguiente.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.siguiente)
        {
            validarTodo();
            Personas personaAux = new Personas();
        }
    }



    public boolean validarEmail()
    {
        String emailIntroducido = email.getText().toString().trim();
        if (emailIntroducido.isEmpty())
        {
            email.setError("No se puede dejar este espacio en blanco");
            return false;
        }
        else {
            Pattern p = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}@(floridauniversitaria\\.es|florida\\-uni\\.es)");
            Matcher m = p.matcher(emailIntroducido);
            boolean b = m.matches();
            if(b) {
                email.setError(null);
                return true;
            }else {
                email.setError("Dirección de correo no válida");
                return false;
            }
        }
    }

    public boolean validarContrasenya()
    {

        String contrasenya1 = contrasenya.getText().toString();
        String contrasenya2 = repetirContrasenya.getText().toString();
        boolean patternCorrecto = validarElPatternDeLaContrasenya(contrasenya1);

        if (contrasenya1.isEmpty())
        {
            contrasenya.setError("No se puede dejar este espacio en blanco");
            return false;
        }
        else if (!contrasenya1.equals(contrasenya2))
        {
            contrasenya.setError("Las contraseñas no coincide");
            return false;
        }
        else if (patternCorrecto==false){
            contrasenya.setError("Comprueba de que has introducido correctamente la contraseña, tiene que ser entre 8 y 40 carácteres");
            return false;
        }
        else {
            contrasenya.setError(null);
            return true;
        }
    }

    public boolean validarElPatternDeLaContrasenya(String contrasenya)
    {
        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        Matcher m = p.matcher(contrasenya);
        boolean b = m.matches();
        return b;
    }

    public boolean validarNombreUsuario()
    {
        String nombre = nombreUsuario.getText().toString();
        if (nombre.isEmpty())
        {
            nombreUsuario.setError("No se puede dejar este espacio en blanco");
            return false;
        }
        else
        {
            nombreUsuario.setError(null);
            return true;
        }
    }

    public void validarTodo()
    {
        boolean okNombre = validarNombreUsuario();
        boolean okEmail = validarEmail();
        boolean okContrasenya = validarContrasenya();


        if (okNombre && okContrasenya && okEmail)
        {
            Toast.makeText(getContext(), "Todo ok", Toast.LENGTH_LONG).show();

            Personas personasAux = new Personas();
            String nom=nombreUsuario.getText().toString();
            personasAux.setNombreUsuario(nom);
            String txtemail=email.getText().toString();
            personasAux.setEmail(txtemail);
            String pass=contrasenya.getText().toString();
            personasAux.setPass(pass);

            registro2 = registro2Fragment.newInstance(personasAux);
            ((NavigationHost) getActivity()).navigateTo(registro2, true);
        }
        else
        {
            Toast.makeText(getContext(), "Comprueba de que has introducido correctamente los datos", Toast.LENGTH_LONG).show();
        }

    }
}