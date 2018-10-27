package com.example.machina.myconnectwithdb;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by machina on 28/10/2018.
 */

public class URLConnector extends Thread{
    private String URLstr;
    private String result;

    public URLConnector(String url){
        URLstr = url;
    }

    @Override
    public void run() {
        final String output = request(URLstr);
        result = output;
    }

    public String getResult(){
        return result;
    }

    private String request(String urlStr){
        StringBuilder output = new StringBuilder();
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                int resCode = conn.getResponseCode();
                if(resCode == HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        output.append(line+'\n');
                    }

                    reader.close();
                    conn.disconnect();
                }
            }
        }catch (Exception ex){
            Log.e("SampleHTTP", "Exception in processing response", ex);
            ex.printStackTrace();
        }

        return output.toString();
    }
}
