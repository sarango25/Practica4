package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private TextView ipA;
    private TextView ipB;
    private TextView ipC;
    private TextView ipD;
    private Button ping;
    private Button search;

    private TextView currentIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipA = findViewById(R.id.ipA);
        ipB = findViewById(R.id.ipB);
        ipC = findViewById(R.id.ipC);
        ipD = findViewById(R.id.ipD);
        ping = findViewById(R.id.pingButton);
        search = findViewById(R.id.searchButton);

        ping.setOnClickListener((view) -> {
            int partA = Integer.parseInt(ipA.getText().toString());
            int partB = Integer.parseInt(ipB.getText().toString());
            int partC = Integer.parseInt(ipC.getText().toString());
            int partD = Integer.parseInt(ipD.getText().toString());

            String pingIP = partA + "." + partB + "." + partC + "." + partD;
            ping(pingIP);

        });

        search.setOnClickListener((view) -> {
            new Thread(()->{
                String message = "";
                InetAddress pingAddress;
                try {

                    for (int i = 0; i < 256; i++){
                        pingAddress = InetAddress.getByName("192.168.0."+i);
                        if(pingAddress.isReachable(500)){
                            message+="192.168.0."+i+"\n";

                        }
                    }
                    Intent i = new Intent(this,resultActivity.class);
                    i.putExtra("results",message);
                    startActivity(i);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        });

        currentIP = findViewById(R.id.currentIP);
        new Thread(()->{
        try {
            InetAddress localIP = InetAddress.getLocalHost();
            runOnUiThread(()->{
                currentIP.setText(localIP.getHostAddress());
            });
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        }).start();


    }

    public void ping(String pingIP){
        new Thread(()->{
            String message = "";
            InetAddress pingAddress;
            try {
                pingAddress = InetAddress.getByName(pingIP);
                for (int i = 0; i < 5; i++){
                    if(pingAddress.isReachable(500)){
                        message+="Recibido\n";

                    }else{
                        message+="Perdido\n";
                    }
                }
                Intent i = new Intent(this,resultActivity.class);
                i.putExtra("results",message);
                startActivity(i);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }



}