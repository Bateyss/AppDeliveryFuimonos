package com.fm.modules.app.commons.conectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Conectividad {
    public static boolean isNetActive(Object getSystemService_ContextConnectivity) {
        boolean c = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService_ContextConnectivity;
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
}