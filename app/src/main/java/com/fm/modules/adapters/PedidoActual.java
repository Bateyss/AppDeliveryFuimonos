package com.fm.modules.adapters;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fm.modules.R;
import com.fm.modules.app.login.Logued;
import com.fm.modules.entities.RespuestaPedidosDriver;
import com.fm.modules.models.Departamento;
import com.fm.modules.models.Driver;
import com.fm.modules.models.Menu;
import com.fm.modules.service.DepartamentoService;
import com.fm.modules.service.MenuService;
import com.fm.modules.service.PedidoService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PedidoActual#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PedidoActual extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvPedidosActual;
    RecyclerView rvPedidosActual;
    SwipeRefreshLayout swRefreshPedidosActuales;

    PedidosDriverActual pedidosDriver = new PedidosDriverActual();

    public PedidoActual() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PedidoActual.
     */
    // TODO: Rename and change types and number of parameters
    public static PedidoActual newInstance(String param1, String param2) {
        PedidoActual fragment = new PedidoActual();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        pedidosDriver.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedido_actual, container, false);

        tvPedidosActual = view.findViewById(R.id.tvPedidosActual);
        rvPedidosActual = view.findViewById(R.id.rvPedidosActuales);
        swRefreshPedidosActuales = view.findViewById(R.id.swRefreshPedidosActuales);

        tvPedidosActual.setText("Pedidos a entregar");
        swRefreshPedidosActuales.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new PedidosDriverActual().execute();
            }
        });

        return view;
    }

    public void reiniciarAsynkProcess() {
        pedidosDriver.cancel(true);
        pedidosDriver = new PedidosDriverActual();
    }


    public class PedidosDriverActual extends AsyncTask<String, String, List<RespuestaPedidosDriver>>{

        @Override
        protected List<RespuestaPedidosDriver> doInBackground(String... strings) {
            List<RespuestaPedidosDriver> pedidos = new ArrayList<>();
            Driver driver = Logued.driverLogued;
            try {
                PedidoService pedidoService = new PedidoService();
                pedidos = pedidoService.obtenerPedidoAEntregar(String.valueOf(driver.getDriverId()));

            }catch (Exception e){
                System.out.println("Error en UnderThreash:" +e.getMessage() +" " +e.getClass());
            }
            return pedidos;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<RespuestaPedidosDriver> pedidos) {
            super.onPostExecute(pedidos);
            try {
                if (!pedidos.isEmpty()){
                    RecyclerPedidosActualesAdapter adapter = new RecyclerPedidosActualesAdapter(getContext(), pedidos);
                    rvPedidosActual.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvPedidosActual.setAdapter(adapter);
                    swRefreshPedidosActuales.setRefreshing(false);
                    //Toast.makeText(getContext(), "Pedidso Cargados" +pedidos.size(), Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(getContext(), "Pedidos No Cargados" +pedidos.size(), Toast.LENGTH_SHORT).show();
                    //reiniciarAsynkProcess();
                    RecyclerPedidosActualesAdapter adapter = new RecyclerPedidosActualesAdapter(getContext(), pedidos);
                    rvPedidosActual.setLayoutManager(new LinearLayoutManager(getContext()));
                    rvPedidosActual.setAdapter(adapter);
                    swRefreshPedidosActuales.setRefreshing(false);
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