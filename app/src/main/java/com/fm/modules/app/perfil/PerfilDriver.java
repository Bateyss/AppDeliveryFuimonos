package com.fm.modules.app.perfil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.fm.modules.R;
import com.fm.modules.app.login.Logon;
import com.fm.modules.app.login.Logued;
import com.fm.modules.app.pedidos.Principal;
import com.fm.modules.models.Driver;
import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilDriver extends AppCompatActivity {

    AppCompatTextView tvHelloUser;
    AppCompatTextView tvHoras;
    MaterialButton btnLogout;

    Driver d = Logued.driverLogued;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_profile);
        sharedPreferences = getApplicationContext().getSharedPreferences("LogonData", Context.MODE_PRIVATE);

        tvHelloUser = findViewById(R.id.tvHelloUser);
        tvHoras = findViewById(R.id.tvHoras);
        btnLogout = findViewById(R.id.btnLogout);

        System.out.println("Hora Entrada: " + d.getHoraDeEntrada());
        System.out.println("Hora Salida: " + d.getHoraDeSalida());

        double diferencia = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        try {
            Date d1 = simpleDateFormat.parse(d.getHoraDeSalida());
            Date d2 = simpleDateFormat.parse(d.getHoraDeEntrada());
            if (d1 != null && d2 != null) {
                diferencia = d1.getHours() - d2.getHours();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String hello = "Hola " + d.getNombreDriver();
        String hours = "Horario: 00:00-00:00 Difencia: " + diferencia + "horas";
        if (d != null && d.getHoraDeEntrada() != null && d.getHoraDeSalida() != null) {
            hours = "Horario: " + d.getHoraDeEntrada() + "-" + d.getHoraDeSalida() + " Difencia: " + diferencia + "horas";
        }
        tvHelloUser.setText(hello);
        tvHoras.setText(hours);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    editor = sharedPreferences.edit();
                    editor.putString("email", "neles");
                    editor.putString("password", "neles");
                    editor.apply();

                    Logued.driverLogued = null;
                    Intent intent = new Intent(PerfilDriver.this, Logon.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception ignore) {
                }
            }
        });
        onBack();
    }

    public void onBack() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent intent = new Intent(PerfilDriver.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        getOnBackPressedDispatcher().addCallback(PerfilDriver.this, callback);
    }
}
