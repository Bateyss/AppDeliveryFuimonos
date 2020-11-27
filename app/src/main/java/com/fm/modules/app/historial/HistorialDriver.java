package com.fm.modules.app.historial;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
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
import com.fm.modules.adapters.RecyclerHistorialDriverAdapter;
import com.fm.modules.app.login.Logued;
import com.fm.modules.app.pedidos.Principal;
import com.fm.modules.models.Driver;
import com.fm.modules.models.Pedido;
import com.fm.modules.service.Constantes;
import com.fm.modules.service.PedidoService;

import java.util.ArrayList;
import java.util.List;

public class HistorialDriver extends AppCompatActivity {

    ImageView leftArrow;
    RecyclerView rvHistorialDriver;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_driver);
        leftArrow = findViewById(R.id.left_arrow);
        rvHistorialDriver = findViewById(R.id.rvHistorialDriver);
        queue = Volley.newRequestQueue(this);
        HistoDriver histoDriver = new HistoDriver();
        histoDriver.execute();
        onBack();
        onLeftPressef();
    }

    private void onLeftPressef() {
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialDriver.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void onBack() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent intent = new Intent(HistorialDriver.this, Principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        getOnBackPressedDispatcher().addCallback(HistorialDriver.this, callback);
    }

    public class HistoDriver extends AsyncTask<String, String, List<Pedido>> {

        @Override
        protected List<Pedido> doInBackground(String... strings) {
            List<Pedido> pedidos = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                PedidoService pedidoService = new PedidoService();
                pedidos = pedidoService.historialPedidoDriver(String.valueOf(driver.getDriverId()));

            } catch (Exception e) {
                System.out.println("Error en UnderThreash:" + e.getMessage() + " " + e.getClass());
            }
            return pedidos;
        }

        @Override
        protected void onPostExecute(List<Pedido> pedidos) {
            super.onPostExecute(pedidos);
            try {
                if (!pedidos.isEmpty()) {
                    RecyclerHistorialDriverAdapter adapter = new RecyclerHistorialDriverAdapter(getApplicationContext(), pedidos);
                    rvHistorialDriver.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvHistorialDriver.setAdapter(adapter);
                    //Logued.respuestaPedidosDriverLogued = pedidos.get(0);

                    System.out.println("Aqui Obteniendo Respuesta Pedidos" + Logued.respuestaPedidosDriverLogued.toString());
                    //System.out.println("Aqui Obteniendo Respuesta Pedidos" +Logued.respuestaPedidosDriverLogued.toString());
                    //obtenerPedido.execute();

                } else {
                    RecyclerHistorialDriverAdapter adapter = new RecyclerHistorialDriverAdapter(getApplicationContext(), pedidos);
                    rvHistorialDriver.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvHistorialDriver.setAdapter(adapter);
                }
            } catch (Throwable throwable) {
                System.out.println("Error Activity: " + throwable.getMessage());
                throwable.printStackTrace();
            }
            notifications();
        }
    }

    private void notifications() {
        if (Logued.driverLogued != null) {
            CountDownTimer mCountDownTimer = new CountDownTimer(6000, 1000) {
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
                Intent intent = new Intent(HistorialDriver.this, HistorialDriver.class);
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
            NotificationManager notificationManager = (NotificationManager) HistorialDriver.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(HistorialDriver.this, "NOTIFICACION");
        builder.setSmallIcon(R.drawable.ic_app_logo);
        builder.setContentTitle("Fuimonos Delivery");
        builder.setContentText("tienes un pedido pendiente");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(HistorialDriver.this);
        notificationManagerCompat.notify(0, builder.build());
        try {
            Intent intent = new Intent(HistorialDriver.this, Principal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception ignore) {
        }
    }
}