package com.example.machina.myconnectwithdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String test;
    private URLConnector task;

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
    }
}
