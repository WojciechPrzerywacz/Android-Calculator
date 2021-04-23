package com.example.calculator_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.simpleCalcBTN);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity.this, SimpleCalcActivity.class));
            }
        });

        Button button2 = findViewById(R.id.scienceCalcBTN);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity.this, ScienceCalcActivity.class));
            }
        });

        Button button3 = findViewById(R.id.aboutBTN);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

        Button button4 = findViewById(R.id.exitBTN);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

}