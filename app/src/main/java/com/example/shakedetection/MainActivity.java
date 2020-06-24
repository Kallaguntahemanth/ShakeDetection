package com.example.shakedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AccelerometerListener {
    private TextView text;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text1);
        click=findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("Tilt your phone to get Accelerometer motion alerts");
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(AccelerometerManager.isSupported(this)){
            AccelerometerManager.startListening(this);
        }

    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        if(force>40) {
            Toast.makeText(this, "FOOD", Toast.LENGTH_SHORT).show();
        }
        else if(force>30){
            Toast.makeText(this, "WATER", Toast.LENGTH_SHORT).show();

        }
        else if(force>20){
            Toast.makeText(this, "MEDICATION", Toast.LENGTH_SHORT).show();
        }
    }
    public void onStop(){
        super.onStop();
        if(AccelerometerManager.isListening()){
            AccelerometerManager.stopListening();
            Toast.makeText(this, "onStop Acclerometer stopped", Toast.LENGTH_SHORT).show();
        }
    }
    public void onDestroy(){
        super.onDestroy();
        if(AccelerometerManager.isListening()){
            AccelerometerManager.stopListening();
            Toast.makeText(this, "onDestroy Accelerometer stopped", Toast.LENGTH_SHORT).show();
        }
    }
}
