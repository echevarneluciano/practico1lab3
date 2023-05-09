package com.example.practico1lab3.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practico1lab3.login.MainActivity;
import com.example.practico1lab3.model.Usuario;
import com.example.practico1lab3.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private Usuario usuario;
    private MutableLiveData<Usuario> usuarioLiveData;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> cargar() {
        if(this.usuarioLiveData == null){
            this.usuarioLiveData = new MutableLiveData<>();
        }
        return this.usuarioLiveData;
    }

    public void setUsuario(String dni, String apellido, String nombre, String mail, String pass){
         usuario = new Usuario(Long.parseLong(dni), apellido, nombre, mail, pass);
         ApiClient.guardar(context,usuario);
         Intent intent = new Intent(context, MainActivity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(intent);
    }

    public void setPerfil(Boolean bool){
        if(bool) {
            usuario = ApiClient.leer(context);
            this.usuarioLiveData.setValue(usuario);
        }
    }

}
