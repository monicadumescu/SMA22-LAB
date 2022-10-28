package com.example.sma_tema1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sma_tema1.R;
import com.example.sma_tema1.model.MonthlyExpenses;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartWalletMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DatabaseReference databaseReference;
    private String currentMonth;
    private ValueEventListener databaseListener;
    private TextView entries;
    private EditText income, expenses;
    Spinner spinner;
    private String saved_month;


//    public void clicked(View view)
//    {
//        switch (view.getId())
//        {
//            case R.id.search_b:
//                if(!searchRes.getText().toString().isEmpty())
//                {
//                    currentMonth = searchRes.getText().toString().toLowerCase();
//                    saveData();
//                    entries.setText("Searching...");
//                    createNewDBListener();
//                }
//                else
//                {
//                    Toast.makeText(this, "Search fields may not be empty!", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case R.id.update_b:
//                if(income.getText().toString().isEmpty() || expenses.getText().toString().isEmpty() || searchRes.getText().toString().isEmpty())
//                {
//                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
//                }
//                try {
//                    float inc = Float.valueOf(income.getText().toString());
//                }
//                catch (NumberFormatException e)
//                {
//                    Toast.makeText(this, "Income should be a number", Toast.LENGTH_SHORT).show();
//                }
//                try {
//                    float inc = Float.valueOf(expenses.getText().toString());
//                }
//                catch (NumberFormatException e)
//                {
//                    Toast.makeText(this, "Expenses should be a number", Toast.LENGTH_SHORT).show();
//                }
//
//                currentMonth = searchRes.getText().toString().toLowerCase();
//                updateDB();
//                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_wallet_main);

        entries = (TextView) findViewById(R.id.entryesFound);
        income = (EditText) findViewById(R.id.income_box);
        expenses = (EditText) findViewById(R.id.expenses_box);
        spinner = (Spinner) findViewById(R.id.spinner);


        FirebaseApp.initializeApp(getApplicationContext());
        databaseReference = FirebaseDatabase.getInstance().getReference();
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adaptor = new ArrayAdapter(getApplicationContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, list);
        adaptor.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adaptor);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
//                MonthlyExpenses monthlyExpense = new MonthlyExpenses();
                for (DataSnapshot addressSnapshot: snapshot.child("calendar").getChildren()) {
                    String monthlyExpense = addressSnapshot.getKey();
                        list.add(monthlyExpense);
                }
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinner.setOnItemSelectedListener(this);

//        loadData();
//        updateData();
    }


    private  void createNewDBListener() {
        if (databaseReference != null && currentMonth != null && databaseListener != null)
            databaseReference.child("calendar").child(currentMonth).removeEventListener(databaseListener);
        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                MonthlyExpenses monthlyExpense = dataSnapshot.getValue(MonthlyExpenses.class);
                // explicit mapping of month name from entry key
                monthlyExpense.month = dataSnapshot.getKey();

                income.setText(String.valueOf(monthlyExpense.getIncome()));
                expenses.setText(String.valueOf(monthlyExpense.getExpenses()));
                entries.setText("Found entry for " + currentMonth);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };

        databaseReference.child("calendar").child(currentMonth).addValueEventListener((ValueEventListener) databaseListener);
    }



//    private void updateDB()
//    {
//        if (databaseReference != null)
//        {
//            MonthlyExpenses monthlyExpenses = new MonthlyExpenses(searchRes.getText().toString(), Float.valueOf(income.getText().toString()), Float.valueOf(expenses.getText().toString()));
//           databaseReference.child("calendar").child(currentMonth).setValue(monthlyExpenses);
//        }
//    }

    private void saveData()
    {
        SharedPreferences pref = getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("month", currentMonth);
        editor.apply();
    }

    private void loadData()
    {
        SharedPreferences pref = getSharedPreferences("MyPref", 0); // 0 - for private mode
        saved_month = pref.getString("month", "");
    }

//    private void updateData()
//    {
//        searchRes.setText(saved_month);
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        currentMonth = adapterView.getItemAtPosition(i).toString();
        //saveData();
        entries.setText("Searching...");
        createNewDBListener();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}