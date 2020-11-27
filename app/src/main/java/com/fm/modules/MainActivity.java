package com.fm.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.fm.modules.app.login.ComprobarSessionActivity;
import com.fm.modules.app.login.Logon;

public class MainActivity extends AppCompatActivity {

    Splash splash = new Splash();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        splash.cancel(true);
        splash = new Splash();
        splash.execute();
    }

    public class Splash extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(MainActivity.this, ComprobarSessionActivity.class);
            startActivity(intent);
        }
    }
}