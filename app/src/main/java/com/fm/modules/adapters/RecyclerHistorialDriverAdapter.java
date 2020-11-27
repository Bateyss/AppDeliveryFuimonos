package com.fm.modules.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fm.modules.R;
import com.fm.modules.app.login.Logued;
import com.fm.modules.app.pedidos.Principal;
import com.fm.modules.entities.RespuestaPedidosDriver;
import com.fm.modules.models.Driver;
import com.fm.modules.models.Pedido;
import com.fm.modules.models.Restaurante;
import com.fm.modules.models.Usuario;
import com.fm.modules.service.DriverService;
import com.fm.modules.service.PedidoService;
import com.fm.modules.service.RestauranteService;
import com.fm.modules.service.UsuarioService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerHistorialDriverAdapter extends RecyclerView.Adapter<RecyclerHistorialDriverAdapter.ViewHolder> {

    List<Pedido> pedidosList;
    Context context;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");

    RespuestaPedidosDriver resObt;
    Restaurante buscarRestaurante = new Restaurante();
    Driver buscarDriver = new Driver();
    Usuario buscarUsuario = new Usuario();


    ObtenerUsuario obtenerUsuario = new ObtenerUsuario();
    ActualizarPedido actualizarPedido = new ActualizarPedido();

    Pedido upPedido = new Pedido();
    RespuestaPedidosDriver upRespuestaPedidoDriver = new RespuestaPedidosDriver();

    public RecyclerHistorialDriverAdapter(Context context, List<Pedido> pedidosList) {
        this.context = context;
        this.pedidosList = pedidosList;
    }

    @NonNull
    @Override
    public RecyclerHistorialDriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_historial, parent, false);
        return new RecyclerHistorialDriverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHistorialDriverAdapter.ViewHolder holder, int position) {
        holder.asignarDatos(pedidosList.get(position));
    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView tvNumeroOrdenH;
        TextView tvFechaH;
        TextView tvTotalH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox);
            tvNumeroOrdenH = itemView.findViewById(R.id.tvNumeroOrdenH);
            tvFechaH = itemView.findViewById(R.id.tvFechaH);
            tvTotalH = itemView.findViewById(R.id.tvTotalH);
        }

        public void asignarDatos(final Pedido pedido) {
            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String precioPedido = decimalFormat.format(pedido.getTotalEnRestautante());
            checkBox.isChecked();
            tvNumeroOrdenH.setText("Numero de Orden #" + pedido.getPedidoId());
            tvFechaH.setText(pedido.getFechaOrdenado());
            //tvFechaH.setText(simpleDateFormat.format(pedido.getFechaOrdenado()));
            tvTotalH.setText("$" + precioPedido);
        }

        public void dialog(final RespuestaPedidosDriver res) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Pedido");
            builder.setMessage("¿Desea tomar este pedido?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    buscarRestaurante.setRestauranteId(res.getRestauranteId());
                    buscarDriver.setDriverId(res.getDriverId());
                    buscarUsuario.setUsuarioId(res.getUsuarioId());
                    resObt = res;
                    ObtenerRestaurante obtenerRestaurante = new ObtenerRestaurante();
                    obtenerRestaurante.execute();
                    ObtenerDriver obtenerDriver = new ObtenerDriver();
                    obtenerDriver.execute();
                    obtenerUsuario.execute();
                    actualizarPedido.execute();
                    Intent intent = new Intent(context, Principal.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                    //reiniciarAsynkProcess();
                    Toast.makeText(context, "Ha decidido tomar este pedido..", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "No tomocesa este pedido..", Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    }


    public class ObtenerRestaurante extends AsyncTask<String, String, List<Restaurante>> {

        @Override
        protected List<Restaurante> doInBackground(String... strings) {
            List<Restaurante> restaurantes = new ArrayList<>();
            //Driver driver = Logued.driverLogued;
            try {
                RestauranteService restauranteService = new RestauranteService();
                Restaurante restaurante = restauranteService.obtenerRestaurantePorId(buscarRestaurante.getRestauranteId());
                buscarRestaurante = restaurante;

            } catch (Exception e) {
                System.out.println("Error en UnderThreash ObtenerRestaurante:" + e.getMessage() + " " + e.getClass());
            }
            return restaurantes;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Restaurante> restaurantes) {
            super.onPostExecute(restaurantes);
            try {
                if (!restaurantes.isEmpty()) {

                } else {
                    //Toast.makeText(context, "Pedidos No Cargados" +restaurantes.size(), Toast.LENGTH_SHORT).show();
                }
                //reiniciarAsynkProcess();
                //Toast.makeText(context, "Restaurante: " +buscarRestaurante.getRepresentante(), Toast.LENGTH_SHORT).show();
            } catch (Throwable throwable) {
                System.out.println("Error Activity: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ObtenerDriver extends AsyncTask<String, String, List<Driver>> {

        @Override
        protected List<Driver> doInBackground(String... strings) {
            List<Driver> drivers = new ArrayList<>();
            //Driver driver = Logued.driverLogued;
            try {
                DriverService driverService = new DriverService();
                Driver driver = driverService.obtenerDriverPorId(buscarDriver.getDriverId());
                buscarDriver = driver;

            } catch (Exception e) {
                System.out.println("Error en UnderThreash ObtenerDriver:" + e.getMessage() + " " + e.getClass());
            }
            return drivers;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Driver> drivers) {
            super.onPostExecute(drivers);
            try {
                if (!drivers.isEmpty()) {

                    Toast.makeText(context, "Pedidso Cargados" + drivers.size(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Pedidos No Cargados" + drivers.size(), Toast.LENGTH_SHORT).show();
                }
                //reiniciarAsynkProcess();
            } catch (Throwable throwable) {
                System.out.println("Error Activity: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ObtenerUsuario extends AsyncTask<String, String, List<Usuario>> {

        @Override
        protected List<Usuario> doInBackground(String... strings) {
            List<Usuario> usuarios = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                UsuarioService usuarioService = new UsuarioService();
                Usuario usuario = usuarioService.obtenerUsuarioPorId(buscarUsuario.getUsuarioId());
                buscarUsuario = usuario;

            } catch (Exception e) {
                System.out.println("Error en UnderThreash ObtenerUsuario:" + e.getMessage() + " " + e.getClass());
            }
            return usuarios;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);
            try {
                if (!usuarios.isEmpty()) {

                    Toast.makeText(context, "Usuario Cargados" + usuarios.size(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Usuario No Cargados" + usuarios.size(), Toast.LENGTH_SHORT).show();
                    //reiniciarAsynkProcess();
                }
                //reiniciarAsynkProcess();
            } catch (Throwable throwable) {
                System.out.println("Error Activity: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ActualizarPedido extends AsyncTask<String, String, List<Pedido>> {

        @Override
        protected List<Pedido> doInBackground(String... strings) {
            List<Pedido> usuarios = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                PedidoService pedidoService = new PedidoService();

                upPedido.setPedidoId(resObt.getPedidoId());
                upPedido.setRestaurante(buscarRestaurante);
                upPedido.setDrivers(buscarDriver);
                upPedido.setUsuario(buscarUsuario);
                upPedido.setStatus(3);
                System.out.println("Statussss" + upPedido.getStatus());
                upPedido.setFormaDePago(resObt.getFormaDePago());
                upPedido.setTotalDePedido(resObt.getTotalDePedido());
                upPedido.setTotalEnRestautante(resObt.getTotalEnRestautante());
                upPedido.setTotalDeCargosExtra(resObt.getTotalDeCargosExtra());
                upPedido.setTotalEnRestautanteSinComision(resObt.getTotalEnRestautanteSinComision());
                upPedido.setPedidoPagado(resObt.isPedidoPagado());
                System.out.println("Fecha: " + resObt.getFechaOrdenado().toString() + "\n Tiempo: " + resObt.getTiempoPromedioEntrega().toString());
                upPedido.setFechaOrdenado((new Date()).toString());
                upPedido.setTiempoPromedioEntrega(resObt.getTiempoPromedioEntrega());
                upPedido.setPedidoEntregado(false);
                upPedido.setNotas(resObt.getNotas());
                upPedido.setTiempoAdicional(resObt.getTiempoAdicional());
                upPedido.setDireccion(resObt.getDireccion());

                System.out.println(upPedido.toString());
                System.out.println(upPedido.getStatus());
                pedidoService.actualizarPedidoPorId(upPedido);

            } catch (Exception e) {
                System.out.println("Error en UnderThreash ActualizarPedidoce:" + e.getMessage() + " " + e.getClass());
                System.out.println(upPedido.toString());
            }
            return usuarios;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Pedido> pedidos) {
            super.onPostExecute(pedidos);
            try {
            } catch (Throwable throwable) {
                System.out.println("Error Activity: " + throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
