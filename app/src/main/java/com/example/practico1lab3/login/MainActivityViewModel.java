package com.example.practico1lab3.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.practico1lab3.model.Usuario;
import com.example.practico1lab3.request.ApiClient;
import com.example.practico1lab3.request.ApiClientConOStream;
import com.example.practico1lab3.ui.RegistroActivity;

import java.io.File;
import java.io.Serializable;

public class MainActivityViewModel extends AndroidViewModel {

private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void getRegistro(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void logear(String mail, String pass){
       //Usuario usuario = ApiClient.leer(context);

        File archivo = new File(context.getFilesDir(),"usuario.dat");
        Usuario usuario = ApiClientConOStream.leer(archivo);

        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if(mail.equals(usuario.getMail()) && pass.equals(usuario.getPass())){
           intent.putExtra("perfil",true);
           context.startActivity(intent);
       }
    }

}
