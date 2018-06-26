package com.example.globaltics.nomygroupsa.Clases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by GlobalTIC's on 2/12/2017.
 */

public class Conexion {
    public static HttpURLConnection httpURLConnection(String urla){
        try{
            URL url = new URL(urla);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            return connection;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
