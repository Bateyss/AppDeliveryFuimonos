package com.fm.modules.app.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.fm.modules.R;
import com.fm.modules.app.pedidos.Principal;
import com.fm.modules.models.Driver;
import com.fm.modules.service.DriverService;

public class ComprobarSessionActivity extends AppCompatActivity {

    private String usuario = "", passw = "";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedListener();
    }

    public boolean isNetActive() {
        boolean c = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                c = true;
            }
        } catch (Exception e) {
            Log.e("error", "" + "error al comprobar conexion");
            Log.e("error", "" + e);
            c = false;
        }
        return c;
    }

    private void sharedListener() {
        sharedPreferences = getSharedPreferences("LogonData", MODE_PRIVATE);
        String usuarioPref = sharedPreferences.getString("email", "neles");
        String passwPref = sharedPreferences.getString("password", "neles");
        if (!"neles".equals(usuarioPref) && !"neles".equals(passwPref)) {
            usuario = usuarioPref;
            passw = passwPref;
            Acceder acceder = new Acceder();
            acceder.execute();
        } else {
            Intent intent = new Intent(ComprobarSessionActivity.this, Logon.class);
            startActivity(intent);
        }
    }

    public class Acceder extends AsyncTask<String, String, Integer> {


        @Override
        protected Integer doInBackground(String... strings) {
            int v = 0;

            try {
                if (isNetActive()) {
                    System.out.println("comienza a leer vistas");
                    Driver d = new Driver();
                    d.setUsername(usuario);
                    d.setPassword(passw);

                    DriverService driverService = new DriverService();
                    v = driverService.signIn(d);
                    if (v > 0) {
                        d = driverService.obtenerDriverPorId((long) v);
                        if (d != null) {
                            editor = sharedPreferences.edit();
                            editor.putString("email", usuario);
                            editor.putString("password", passw);
                            editor.apply();
                        } else {
                            editor = sharedPreferences.edit();
                            editor.putString("email", "neles");
                            editor.putString("password", "neles");
                            editor.commit();
                        }
                    }
                    Logued.driverLogued = d;
                }
            } catch (Exception ex) {
                System.out.println("*** errrr***: " + ex);
                ex.printStackTrace();
            }
            return v;
        }


        @Override
        protected void onPostExecute(Integer res) {
            super.onPostExecute(res);
            try {
                if (res > 0) {
                    Intent intent = new Intent(ComprobarSessionActivity.this, Principal.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ComprobarSessionActivity.this, Logon.class);
                    startActivity(intent);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}