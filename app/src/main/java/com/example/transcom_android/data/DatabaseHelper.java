package com.example.transcom_android.data;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.example.transcom_android.data.model.Parametro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


import javax.net.ssl.HttpsURLConnection;

public class DatabaseHelper {
    public static final String HOST = "https://localhost:7149";
/*
    Gson gson = new Gson();

    String jsonResponse = DatabaseHelper.httpPostRequest("/loginEmpresa",
            new Parametro("usuario", "usuario"),
            new Parametro("password", "password"));
    response = gson.fromJson(jsonResponse, LoginResponse.class);

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)*/
public static String httpPostRequest(String requestUrl, Parametro... parametros) {
        HashMap<String, String> map = new HashMap<>();
        for (Parametro parametro : parametros) {
            map.put(parametro.getTag(), parametro.getValue());
        }
        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL(requestUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(map));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            } else {
                response = new StringBuilder();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            if(entry.getValue() == null){
                result.append("null");
            } else {
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
        }

        return result.toString();
    }

    public static boolean isConnectedToInternet(Context c) {
        try {
            ConnectivityManager cm = (ConnectivityManager) c
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
