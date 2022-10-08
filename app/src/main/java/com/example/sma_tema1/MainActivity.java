package com.example.sma_tema1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialogBuilder;

    public void clicked(View view) {
                switch (view.getId()) {
                    case R.id.bClick:
                        TextInputEditText tName = (TextInputEditText) findViewById(R.id.tName);
                        TextView eName = (TextView) findViewById(R.id.eName);
                        String greetings = "Hello, " + tName.getText().toString();
                        eName.setText(greetings);
                        break;
                }
            }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextInputEditText tName = (TextInputEditText) findViewById(R.id.tName);
        Button bClick = (Button) findViewById(R.id.bClick);
        TextView eName = (TextView) findViewById(R.id.eName);

        alertDialogBuilder = new AlertDialog.Builder(this);

        bClick.setOnClickListener(new View.OnClickListener() {
            @Override
            //Not both of them will run
            public void onClick(View view) {
                String name = tName.getText().toString();
                String greetings = "Hello, " + name;
//                eName.setText(greetings);

                alertDialogBuilder.setMessage(greetings);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Hello again!", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Be more positive!", Toast.LENGTH_SHORT).show();
                    }
                });
                onBackPressed();
            }

        });

        alertDialogBuilder.setCancelable(false);
    }

   @Override
   public void onBackPressed()
   {
       AlertDialog alertDialog = alertDialogBuilder.create();
       alertDialog.setTitle("Alert Dialog");
       alertDialog.show();
   }

}