package com.fm.modules.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.fm.modules.R;
import com.fm.modules.app.login.Logued;
import com.fm.modules.app.pedidos.Principal;
import com.fm.modules.entities.RespuestaPedidosDriver;
import com.fm.modules.models.Departamento;
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

public class RecyclerPedidosActualesAdapter extends RecyclerView.Adapter<RecyclerPedidosActualesAdapter.ViewHolder> {

    List<RespuestaPedidosDriver> pedidosList;
    Context context;

    //SimpleDateFormat ffecha = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat fhora = new SimpleDateFormat("HH:mm:ss");
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // Java8
    // JavaTime - usar atributo

    RespuestaPedidosDriver resObt;
    Restaurante buscarRestaurante = new Restaurante();
    Driver buscarDriver = new Driver();
    Usuario buscarUsuario = new Usuario();

    ObtenerRestaurante2 obtenerRestaurante = new ObtenerRestaurante2();
    ObtenerDriver2 obtenerDriver = new ObtenerDriver2();
    ObtenerUsuario2 obtenerUsuario = new ObtenerUsuario2();
    ActualizarPedido2 actualizarPedido = new ActualizarPedido2();
    ActualizarDriver actualizarDriver = new ActualizarDriver();

    Pedido upPedido2 = new Pedido();
    RespuestaPedidosDriver upRespuestaPedidoDriver = new RespuestaPedidosDriver();

    public RecyclerPedidosActualesAdapter(Context context, List<RespuestaPedidosDriver> pedidosList){
        this.context = context;
        this.pedidosList = pedidosList;
    }

    @NonNull
    @Override
    public RecyclerPedidosActualesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_pedidos2, parent, false);
        return new RecyclerPedidosActualesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPedidosActualesAdapter.ViewHolder holder, int position) {
        if(this.pedidosList.isEmpty()){
            Toast.makeText(this.context, "No tienes pedidos a entregar", Toast.LENGTH_SHORT).show();
        }else{
            holder.asignarDatos(pedidosList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tvNombreRestaurante;
        AppCompatTextView tvDireccionCliente;
        AppCompatTextView tvPrecioPedido;
        AppCompatTextView tvFormadePago;
        AppCompatTextView btnNumeroOrden;
        AppCompatTextView btnTomarPedido;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreRestaurante = itemView.findViewById(R.id.tvNombreRestaurante);
            tvDireccionCliente = itemView.findViewById(R.id.tvDirecionCliente);
            tvPrecioPedido = itemView.findViewById(R.id.tvPrecioPedido);
            tvFormadePago = itemView.findViewById(R.id.tvFormadePago);
            btnNumeroOrden = itemView.findViewById(R.id.btnNumeroOrden);
            btnTomarPedido = itemView.findViewById(R.id.btnTomarPedido);
        }

        public void asignarDatos(final RespuestaPedidosDriver res){
            tvNombreRestaurante.setText(res.getUsuario());
            tvDireccionCliente.setText(res.getDireccion());
            String precioPedido = decimalFormat.format(res.getTotalEnRestautante());
            tvPrecioPedido.setText("$" +precioPedido);
            tvFormadePago.setText("Forma de pago: " +res.getFormaDePago());
            btnNumeroOrden.setText("Orden #" +res.getPedidoId());
            btnTomarPedido.setText("Entregar a cliente");
            btnTomarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog(res);
                }
            });
        }

        public void dialog(final RespuestaPedidosDriver res){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Pedido");
            builder.setMessage("¿Pedido Entregado?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //RestauranteService restauranteService = new RestauranteService();
                    //Restaurante restaurante = restauranteService.obtenerRestaurantePorId(res.getRestauranteId());
                    buscarRestaurante.setRestauranteId(res.getRestauranteId());
                    buscarDriver.setDriverId(res.getDriverId());
                    buscarUsuario.setUsuarioId(res.getUsuarioId());

                    resObt = res;

                    obtenerRestaurante.execute();
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

    public void reiniciarAsynkProcess() {
        obtenerRestaurante.cancel(true);
        obtenerRestaurante = new ObtenerRestaurante2();

        obtenerDriver.cancel(true);
        obtenerDriver = new ObtenerDriver2();

        obtenerUsuario.cancel(true);
        obtenerUsuario = new ObtenerUsuario2();

        actualizarPedido.cancel(true);
        actualizarPedido = new ActualizarPedido2();

        actualizarDriver.cancel(true);
        actualizarDriver = new ActualizarDriver();
    }


    public class ObtenerRestaurante2 extends AsyncTask<String, String, List<Restaurante>> {

        @Override
        protected List<Restaurante> doInBackground(String... strings) {
            List<Restaurante> restaurantes = new ArrayList<>();
            //Driver driver = Logued.driverLogued;
            try {
                RestauranteService restauranteService = new RestauranteService();
                Restaurante restaurante = restauranteService.obtenerRestaurantePorId(buscarRestaurante.getRestauranteId());
                buscarRestaurante = restaurante;

            }catch (Exception e){
                System.out.println("Error en UnderThreash ObtenerRestaurante:" +e.getMessage() +" " +e.getClass());
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
                if (!restaurantes.isEmpty()){

                }else{
                    //Toast.makeText(context, "Pedidos No Cargados" +restaurantes.size(), Toast.LENGTH_SHORT).show();
                }
                //reiniciarAsynkProcess();
                //Toast.makeText(context, "Restaurante: " +buscarRestaurante.getRepresentante(), Toast.LENGTH_SHORT).show();
            }catch (Throwable throwable){
                System.out.println("Error Activity: " +throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ObtenerDriver2 extends AsyncTask<String, String, List<Driver>> {

        @Override
        protected List<Driver> doInBackground(String... strings) {
            List<Driver> drivers = new ArrayList<>();
            //Driver driver = Logued.driverLogued;
            try {
                DriverService driverService = new DriverService();
                Driver driver = driverService.obtenerDriverPorId(buscarDriver.getDriverId());
                buscarDriver = driver;

            }catch (Exception e){
                System.out.println("Error en UnderThreash ObtenerDriver:" +e.getMessage() +" " +e.getClass());
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
                if (!drivers.isEmpty()){

                    //Toast.makeText(context, "Pedidso Cargados" +drivers.size(), Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(context, "Pedidos No Cargados" +drivers.size(), Toast.LENGTH_SHORT).show();
                }
                //reiniciarAsynkProcess();
            }catch (Throwable throwable){
                System.out.println("Error Activity: " +throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ObtenerUsuario2 extends AsyncTask<String, String, List<Usuario>> {

        @Override
        protected List<Usuario> doInBackground(String... strings) {
            List<Usuario> usuarios = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                UsuarioService usuarioService = new UsuarioService();
                Usuario usuario = usuarioService.obtenerUsuarioPorId(buscarUsuario.getUsuarioId());
                buscarUsuario = usuario;

            }catch (Exception e){
                System.out.println("Error en UnderThreash ObtenerUsuario:" +e.getMessage() +" " +e.getClass());
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
                if (!usuarios.isEmpty()){

                    //Toast.makeText(context, "Usuario Cargados" +usuarios.size(), Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(context, "Usuario No Cargados" +usuarios.size(), Toast.LENGTH_SHORT).show();
                    //reiniciarAsynkProcess();
                }
                //reiniciarAsynkProcess();
            }catch (Throwable throwable){
                System.out.println("Error Activity: " +throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ActualizarPedido2 extends AsyncTask<String, String, List<Pedido>> {

        @Override
        protected List<Pedido> doInBackground(String... strings) {
            List<Pedido> usuarios = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                PedidoService pedidoService2 = new PedidoService();

                upPedido2.setPedidoId(resObt.getPedidoId());
                upPedido2.setRestaurante(buscarRestaurante);
                upPedido2.setDrivers(buscarDriver);
                upPedido2.setUsuario(buscarUsuario);
                upPedido2.setStatus(4);
                System.out.println("Statussss" +upPedido2.getStatus());
                upPedido2.setFormaDePago(resObt.getFormaDePago());
                upPedido2.setTotalDePedido(resObt.getTotalDePedido());
                upPedido2.setTotalEnRestautante(resObt.getTotalEnRestautante());
                upPedido2.setTotalDeCargosExtra(resObt.getTotalDeCargosExtra());
                upPedido2.setTotalEnRestautanteSinComision(resObt.getTotalEnRestautanteSinComision());
                upPedido2.setPedidoPagado(resObt.isPedidoPagado());
                System.out.println("Fecha: " +resObt.getFechaOrdenado().toString() +"\n Tiempo: " +resObt.getTiempoPromedioEntrega().toString());
                upPedido2.setFechaOrdenado((new Date()).toString());
                //upPedido2.setFechaOrdenado(resObt.getFechaOrdenado());
                upPedido2.setTiempoPromedioEntrega(resObt.getTiempoPromedioEntrega());
                upPedido2.setPedidoEntregado(true);
                upPedido2.setNotas(resObt.getNotas());
                upPedido2.setTiempoAdicional(resObt.getTiempoAdicional());
                upPedido2.setDireccion(resObt.getDireccion());

                System.out.println(upPedido2.toString());
                System.out.println(upPedido2.getStatus());
                pedidoService2.actualizarPedidoPorId(upPedido2);

            }catch (Exception e){
                System.out.println("Error en UnderThreash ActualizarPedidoce:" +e.getMessage() +" " +e.getClass());
                System.out.println(upPedido2.toString());
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
                actualizarDriver.execute();
                System.out.println("Actualizar Driver");
                //reiniciarAsynkProcess();
            }catch (Throwable throwable){
                System.out.println("Error Activity: " +throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }

    public class ActualizarDriver extends AsyncTask<String, String, Driver> {

        @Override
        protected Driver doInBackground(String... strings) {
            List<Pedido> usuarios = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                PedidoService pedidoService2 = new PedidoService();
                DriverService driverService = new DriverService();
                Logued.driverLogued.setStatusAsignado(false);
                System.out.println("Driver a actualizar: " +Logued.driverLogued.toString());
                driverService.actualizarDriverPorId(Logued.driverLogued);
                driver = Logued.driverLogued;
                reiniciarAsynkProcess();
            }catch (Exception e){
                System.out.println("Error en UnderThreash ActualizarPedidoce:" +e.getMessage() +" " +e.getClass());
                System.out.println(upPedido2.toString());
            }
            return driver;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Driver driver) {
            super.onPostExecute(driver);
            try {
                if (driver != null){

                    //Toast.makeText(context, "Usuario Cargados" +pedidos.size(), Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(context, "Usuario No Cargados" +pedidos.size(), Toast.LENGTH_SHORT).show();
                    //reiniciarAsynkProcess();
                }
                reiniciarAsynkProcess();
            }catch (Throwable throwable){
                System.out.println("Error Activity: " +throwable.getMessage());
                throwable.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}