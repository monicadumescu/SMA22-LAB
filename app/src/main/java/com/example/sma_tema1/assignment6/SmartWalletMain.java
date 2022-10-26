package com.example.sma_tema1.assignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sma_tema1.R;
import com.example.sma_tema1.assignment6.model.MonthlyExpenses;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SmartWalletMain extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private String currentMonth;
    private ValueEventListener databaseListener;
    private TextView entries;
    private EditText income, expenses, searchRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_wallet_main);

        entries = (TextView) findViewById(R.id.entryesFound);
        income = (EditText) findViewById(R.id.income_box);
        expenses = (EditText) findViewById(R.id.expenses_box);
        searchRes = (EditText) findViewById(R.id.month);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
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

}