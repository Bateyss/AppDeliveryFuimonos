package com.fm.modules.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fm.modules.R;
import com.fm.modules.app.login.Logued;
import com.fm.modules.app.pedidos.Principal;
import com.fm.modules.models.Driver;
import com.fm.modules.models.Pedido;
import com.fm.modules.service.DriverService;
import com.fm.modules.service.PedidoService;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerPedidosAdapter extends RecyclerView.Adapter<RecyclerPedidosAdapter.ViewHolder> {

    List<Pedido> pedidosList;
    Context context;
    Principal principal;

    public RecyclerPedidosAdapter(Context context, List<Pedido> pedidosList, Principal principal) {
        this.context = context;
        this.pedidosList = pedidosList;
        this.principal = principal;
    }

    @NonNull
    @Override
    public RecyclerPedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_food, parent, false);
        return new RecyclerPedidosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPedidosAdapter.ViewHolder holder, int position) {
        holder.asignarDatos(pedidosList.get(position));
    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrdenActual;
        TextView tvRestaurante;
        TextView tvCliente;
        TextView tvDireccion;
        TextView tvTelefono;
        TextView cobrarCantidad;
        TextView cobrarTitle;
        LinearLayout linearLayout1;
        LinearLayout linearLayout2;
        Button btnMapLoad;
        Button btnEntregado;
        Long idPedido = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrdenActual = itemView.findViewById(R.id.tvOrdenActual);
            tvRestaurante = itemView.findViewById(R.id.tvRestaurante);
            tvCliente = itemView.findViewById(R.id.tvCliente);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            btnMapLoad = itemView.findViewById(R.id.btnMapLoad);
            btnEntregado = itemView.findViewById(R.id.btnEntregado);
            cobrarCantidad = itemView.findViewById(R.id.cobrarCantidad);
            cobrarTitle = itemView.findViewById(R.id.cobrarTitle);
            linearLayout1 = itemView.findViewById(R.id.line1);
            linearLayout2 = itemView.findViewById(R.id.line2);
        }

        public void asignarDatos(final Pedido res) {
            DecimalFormat decimalFormat = new DecimalFormat("$ #,##0.00");
            String order = "Orden Actual #" + res.getPedidoId();
            idPedido = res.getPedidoId();
            btnEntregado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog();
                }
            });
            tvOrdenActual.setText(order);
            if (res.getTiempoPromedioEntrega() != null) {
                Logued.timeEstimado = res.getTiempoPromedioEntrega().getTime();
            }else{
                Logued.timeEstimado = null;
            }
            if (res.getStatus() == 3) {
                principal.startTimer();
                principal.switch1.setOnCheckedChangeListener(null);
                principal.switch1.setChecked(true);
                btnEntregado.setVisibility(View.VISIBLE);
            } else {
                btnEntregado.setVisibility(View.INVISIBLE);
            }

            if (!res.isPedidoPagado()) {
                String precioPedido = decimalFormat.format(res.getTotalEnRestautante());
                cobrarCantidad.setText(precioPedido);
            } else {
                String str = "Cobrado";
                cobrarTitle.setText(str);
                cobrarTitle.setTextColor(Color.GREEN);
            }
            tvRestaurante.setText(res.getRestaurante().getNombreRestaurante());
            tvCliente.setText(res.getUsuario().getNombre());
            String str = null;
            if (res.getDireccion() != null) {
                str = res.getDireccion();
            }
            String[] strings = str.split(" ; ", 7);
            if (strings.length > 6) {
                String ss = strings[0] + "," + strings[1] + "," + strings[3] + "," + strings[4] + "," + strings[5] + "," + strings[6] + ",";
                tvDireccion.setText(ss);
                String[] strings1 = strings[2].split("::", 2);
                if (strings1.length > 1) {
                    if (strings1[0] != null && strings1[1] != null) {
                        btnMapLoad.setVisibility(View.VISIBLE);
                        final String latlongStr = "geo:" + strings1[0] + "," + strings1[1] + "?q=" + strings1[0] + "," + strings1[1] + "(ubicacion)";
                        btnMapLoad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri gmmIntentUri = Uri.parse(latlongStr);
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                                    context.startActivity(mapIntent);
                                }
                            }
                        });
                    } else {
                        btnMapLoad.setEnabled(false);
                        btnMapLoad.setVisibility(View.INVISIBLE);
                    }
                } else {
                    btnMapLoad.setEnabled(false);
                    btnMapLoad.setVisibility(View.INVISIBLE);
                }
                final String telpho = strings[4];
                if (telpho != null && !"".equals(telpho)) {
                    try {
                        Integer.parseInt(telpho);
                        tvTelefono.setText(telpho);
                        tvTelefono.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:+" + telpho));
                                context.startActivity(intent);
                            }
                        });
                    } catch (Exception ignore) {
                    }
                } else {
                    linearLayout1.setVisibility(View.INVISIBLE);
                }
            } else {
                tvDireccion.setText(str);
                btnMapLoad.setEnabled(false);
                btnMapLoad.setVisibility(View.INVISIBLE);
            }
        }

        public void dialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Pedido");
            builder.setMessage("¿Ha Entregado el Pedido?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActualizarPedido actualizarPedido = new ActualizarPedido();
                    actualizarPedido.execute();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }

        private class ActualizarPedido extends AsyncTask<String, String, Boolean> {

            @Override
            protected Boolean doInBackground(String... strings) {
                boolean f = false;
                Driver driver = Logued.driverLogued;
                try {
                    if (idPedido != null) {
                        PedidoService pedidoService = new PedidoService();
                        Pedido pedido = pedidoService.obtenerPedidoPorId(idPedido);
                        if (pedido != null) {
                            pedido.setPedidoEntregado(true);
                            pedido.setPedidoPagado(true);
                            pedido.setStatus(4);
                            pedidoService.actualizarPedidoPorId(pedido);
                            if (driver != null) {
                                DriverService driverService = new DriverService();
                                driver.setStatusAsignado(false);
                                driverService.actualizarDriverPorId(driver);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error en ActualizarPedido background:" + e.getMessage() + " " + e.getClass());
                }
                return f;
            }

            @Override
            protected void onPostExecute(Boolean res) {
                super.onPostExecute(res);
                Intent intent = new Intent(context, Principal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        }
    }
}
