package com.fm.modules.app.pedidos;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fm.modules.R;
import com.fm.modules.adapters.RecyclerPedidosAdapter;
import com.fm.modules.app.historial.HistorialDriver;
import com.fm.modules.app.login.Logued;
import com.fm.modules.app.perfil.PerfilDriver;
import com.fm.modules.entities.RespuestaPedidosDriver;
import com.fm.modules.models.Driver;
import com.fm.modules.models.Pedido;
import com.fm.modules.service.Constantes;
import com.fm.modules.service.DriverService;
import com.fm.modules.service.PedidoService;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Principal extends AppCompatActivity {

    public static long START_TIME_IN_MILLIS = 1800000;

    CountDownTimer mCountDownTimer;
    private long mTimeLeftMillis = START_TIME_IN_MILLIS;

    TextView tvTiempo;
    RecyclerView rvPedidoAEntregar;
    Button btnActivarStatus;
    Button btnVerHistorial;
    Button btnVerPerfil;
    public SwitchMaterial switch1;
    RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);
        tvTiempo = findViewById(R.id.tvTiempo);
        rvPedidoAEntregar = findViewById(R.id.rvPedidoAEntregar);
        btnActivarStatus = findViewById(R.id.btnActivarStatus);
        btnVerHistorial = findViewById(R.id.btnVerHistorial);
        btnVerPerfil = findViewById(R.id.btnVerPerfil);
        switch1 = findViewById(R.id.switch1);
        tvTiempo.setText("Tiempo Restante");
        if (Logued.pedidoss == null) {
            Logued.pedidoss = 0;
        }
        queue = Volley.newRequestQueue(this);
        notifications();
        switchListener();
        btnListener();
        onBack();
        PedidosDriver pedidosDriver = new PedidosDriver();
        pedidosDriver.execute();
    }

    private void btnListener() {
        btnVerPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, PerfilDriver.class);
                startActivity(intent);
            }
        });
        btnActivarStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarDriver actualizarDriver = new ActualizarDriver();
                actualizarDriver.execute();
            }
        });
        btnVerHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Principal.this, HistorialDriver.class);
                startActivity(i);
            }
        });
        System.out.println("Ejecutando estadp driver!!!!!!!!!!!");
        EstadoDriver estadoDriver = new EstadoDriver();
        estadoDriver.execute();
    }

    private void switchListener() {
        switch1.setVisibility(View.INVISIBLE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ActualizarPedidoParaLlevar actualizarPedidoParaLlevar = new ActualizarPedidoParaLlevar();
                    actualizarPedidoParaLlevar.execute();
                }
            }
        });
    }

    public void startTimer() {
        if (Logued.timeEstimado != null && Logued.timeEstimado > 0) {
            mTimeLeftMillis = Logued.timeEstimado;
        }
        if (Logued.transcurrido != null) {
            if (Logued.transcurrido > 0) {
                mTimeLeftMillis = mTimeLeftMillis - Logued.transcurrido;
            }
        } else {
            Logued.transcurrido = 0;
        }
        mCountDownTimer = new CountDownTimer(mTimeLeftMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftMillis = l;
                Logued.transcurrido = Logued.transcurrido + 1000;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Logued.transcurrido = 0;
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTiempo.setText("Tiempo restante: " + timeLeftFormatted);
    }

    private class PedidosDriver extends AsyncTask<String, String, List<RespuestaPedidosDriver>> {

        @Override
        protected List<RespuestaPedidosDriver> doInBackground(String... strings) {
            List<RespuestaPedidosDriver> pedidos = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                PedidoService pedidoService = new PedidoService();
                pedidos = pedidoService.obtenerPedidoDriver(driver.getDriverId().toString());
                if (pedidos != null && !pedidos.isEmpty()) {
                    if (pedidos.get(0).getPedidoId() > 0) {
                        PedidoService pedidoService2 = new PedidoService();
                        Logued.pedidoLogued = pedidoService2.obtenerPedidoPorId(pedidos.get(0).getPedidoId());
                    } else {
                        Logued.pedidoLogued = null;
                    }
                } else {
                    Logued.pedidoLogued = null;
                }
            } catch (Exception e) {
                System.out.println("Error en PedidosDriver doInBackground:" + e.getMessage() + " " + e.getClass());
            }
            return pedidos;
        }

        @Override
        protected void onPostExecute(List<RespuestaPedidosDriver> pedidos) {
            super.onPostExecute(pedidos);
            try {
                List<Pedido> list = new ArrayList<>();
                if (Logued.pedidoLogued != null) {
                    list.add(Logued.pedidoLogued);
                    switch1.setVisibility(View.VISIBLE);
                } else {
                    switch1.setVisibility(View.INVISIBLE);
                }
                RecyclerPedidosAdapter adapter = new RecyclerPedidosAdapter(Principal.this, list, Principal.this);
                rvPedidoAEntregar.setLayoutManager(new LinearLayoutManager(Principal.this));
                rvPedidoAEntregar.setAdapter(adapter);
                Logued.respuestaPedidosDriverLogued = pedidos.get(0);
            } catch (Throwable throwable) {
                System.out.println("Error Activity: " + throwable.getMessage());
                throwable.printStackTrace();
            }
            notifications();
        }
    }

    private class ActualizarPedidoParaLlevar extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean f = false;
            try {
                if (Logued.pedidoLogued != null) {
                    PedidoService pedidoService2 = new PedidoService();
                    Logued.pedidoLogued.setStatus(3);
                    Logued.pedidoLogued.setPedidoPagado(false);
                    Pedido p = pedidoService2.actualizarPedidoPorId(Logued.pedidoLogued);
                    if (p != null) {
                        f = true;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en ActualizarPedidoParaLlevar doInBackground:" + e.getMessage() + " " + e.getClass());
            }
            return f;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                Intent intent = new Intent(Principal.this, Principal.class);
                startActivity(intent);
            }
        }
    }

    private class ActualizarDriver extends AsyncTask<String, String, Driver> {

        @Override
        protected Driver doInBackground(String... strings) {
            Driver driver = null;
            try {
                if (Logued.driverLogued != null) {
                    DriverService driverService = new DriverService();
                    driver = driverService.obtenerDriverPorId(Logued.driverLogued.getDriverId());
                    if (driver != null) {
                        if (driver.getStatusAsignado() != null) {
                            driver.setStatusAsignado(!driver.getStatusAsignado());
                            driver = driverService.actualizarDriverPorId(driver);
                        } else {
                            driver.setStatusAsignado(true);
                            driver = driverService.actualizarDriverPorId(driver);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en ActualizarDriver doInBackground:" + e.getMessage() + " " + e.getClass());
                driver = null;
            }
            return driver;
        }

        @Override
        protected void onPostExecute(Driver driver) {
            super.onPostExecute(driver);
            if (driver != null) {
                Logued.driverLogued = driver;
                if (Logued.driverLogued.getStatusAsignado() != null && Logued.driverLogued.getStatusAsignado()) {
                    String txt = "Activar Status";
                    btnActivarStatus.setText(txt);
                } else {
                    Logued.driverLogued.setStatusAsignado(false);
                    String txt = "Desactivar Status";
                    btnActivarStatus.setText(txt);
                }
            }
        }
    }

    private class EstadoDriver extends AsyncTask<String, String, Driver> {

        @Override
        protected Driver doInBackground(String... strings) {
            Driver driver = null;
            try {
                if (Logued.driverLogued != null) {
                    DriverService driverService = new DriverService();
                    driver = driverService.obtenerDriverPorId(Logued.driverLogued.getDriverId());
                }
            } catch (Exception e) {
                System.out.println("Error en EstadoDriver doInBackground:" + e.getMessage() + " " + e.getClass());
                driver = null;
            }
            return driver;
        }

        @Override
        protected void onPostExecute(Driver driver) {
            super.onPostExecute(driver);
            if (driver != null) {
                Logued.driverLogued = driver;
                if (Logued.driverLogued.getStatusAsignado() != null && Logued.driverLogued.getStatusAsignado()) {
                    String txt = "Activar Status";
                    btnActivarStatus.setText(txt);
                } else {
                    Logued.driverLogued.setStatusAsignado(false);
                    String txt = "Desactivar Status";
                    btnActivarStatus.setText(txt);
                }
                System.out.println("estado driver :" + Logued.driverLogued.getStatusAsignado());
            }
        }
    }

    private void notifications() {
        if (Logued.driverLogued != null) {
            CountDownTimer mCountDownTimer2 = new CountDownTimer(6000, 1000) {
                @Override
                public void onTick(long l) {
                }

                @Override
                public void onFinish() {
                    volleyMethod(Logued.driverLogued.getDriverId());
                }
            }.start();
        }
    }

    private void volleyMethod(Long g) {
        // RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constantes.DOMINIO.concat("/push/getpedidosdcnd/").concat(g.toString());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("!!!!!!!!! dess response: " + response);
                        if (response != null) {
                            try {
                                int respuesta = Integer.parseInt(response);
                                if (Logued.pedidoss == 0 && respuesta > 0) {
                                    Logued.pedidoss = respuesta;
                                    makeNotificacion();
                                }
                                if (Logued.pedidoss > 0 && respuesta > Logued.pedidoss) {
                                    Logued.pedidoss = respuesta;
                                    makeNotificacion();
                                }
                            } catch (Exception ignore) {
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //none
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
        getAvailableMemory();
        notifications();
    }

    private void getAvailableMemory() {
        try {
            ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            if (memoryInfo.lowMemory) {
                Toast.makeText(this, "Memoria Baja", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "limpiando..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Principal.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (Exception ignore) {
        }
    }

    private void makeNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification";
            NotificationChannel channel = new NotificationChannel("NOTIFICACION", name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) Principal.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Principal.this, "NOTIFICACION");
        builder.setSmallIcon(R.drawable.ic_app_logo);
        builder.setContentTitle("Fuimonos Delivery");
        builder.setContentText("tienes un pedido pendiente");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Principal.this);
        notificationManagerCompat.notify(0, builder.build());
        try {
            Intent intent = new Intent(Principal.this, Principal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception ignore) {
        }
    }

    public void onBack() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Principal.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        getOnBackPressedDispatcher().addCallback(Principal.this, callback);
    }
}
