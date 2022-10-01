package com.example.sma_tema1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        TextInputEditText tName = (TextInputEditText) findViewById(R.id.tName);
        Button bClick = (Button) findViewById(R.id.bClick);
        TextView eName = (TextView) findViewById(R.id.eName);
        FloatingActionButton bShare = (FloatingActionButton) findViewById(R.id.bShare);
        FloatingActionButton bSearch = (FloatingActionButton) findViewById(R.id.bSearch);

        alertDialogBuilder = new AlertDialog.Builder(this);

        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String name = tName.getText().toString();
                shareIntent.putExtra(Intent.EXTRA_TEXT, name);
                startActivity(Intent.createChooser(shareIntent, "Share using: "));
            }
        });

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(Intent.ACTION_VIEW);
                String text = tName.getText().toString();
                String url = "https://www.google.ro/search?q=" + text;
                searchIntent.setDataAndType(Uri.parse(url), "text/plain");
                startActivity(searchIntent);
            }
        });

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
//            public void clicked(View view) {
//                switch (view.getId()) {
//                    case R.id.bClick:
//                        String name = tName.getText().toString();
//                        String greetings = "Hello, " + name;
//                        eName.setText(greetings);
//                        break;
//                }
//            }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Button bClick = (Button) findViewById(R.id.bClick);
        bClick.setTextColor(Color.parseColor(text));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}