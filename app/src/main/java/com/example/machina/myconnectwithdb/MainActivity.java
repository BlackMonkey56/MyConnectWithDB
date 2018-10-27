package com.example.machina.myconnectwithdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String test;
    private URLConnector task;
    private JSONObject jo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = "https://localhost/";
        task = new URLConnector(test);

        task.start();

        try{
            task.join();
            System.out.println("Waiting for result...");
        }catch (InterruptedException e){

        }

        String result = task.getResult();

        System.out.println(result);

        parseJSON(result);
    }

    public String parseJSON(String target){
        try{
            JSONObject jo = new JSONObject(target);

            //Get data by 'name'
            JSONArray jsonArr = jo.getJSONArray("id");

            for(int i = 0; i < jsonArr.length(); i++){
                JSONObject object = jsonArr.getJSONObject(i);

                System.out.println(object.getString("id"));
            }

            return "";
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
