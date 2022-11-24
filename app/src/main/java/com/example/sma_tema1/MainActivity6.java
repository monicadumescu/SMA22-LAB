package com.example.sma_tema1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sma_tema1.model.Payment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.sma_tema1.ui.PaymentAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity6 extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private int currentMonth;
    private List<Payment> payments = new ArrayList<>();
    private TextView tStatus;
    private Button bNext, nPrevious;
    private FloatingActionButton fabAdd;
    private ListView listView;
    private Payment payment;
    public enum Month{
        January, February, March, April, May, June, July, August, September, October, November, December;
        public static int intToMonthToInt(Month month)
        {
            return month.ordinal();
        }
        public static Month IntToMonthName(int index)
        {
            return  Month.values()[index];
        }
        public static int monthFromTimestamp(String timestamp)
        {
            int month = Integer.parseInt(timestamp.substring(5,7));
            return month - 1;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        tStatus = (TextView) findViewById(R.id.tStatus);
        nPrevious = (Button) findViewById(R.id.bPrevious);
        bNext = (Button) findViewById(R.id.bNext);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        listView = (ListView) findViewById(R.id.listPayments);
        Button previous = (Button) findViewById(R.id.bPrevious);
        Button next = (Button) findViewById(R.id.bNext);
        TextView text = (TextView) findViewById(R.id.tStatus);

        if(!AppClass.isNetworkAvailable(this))
        {
            if(AppClass.hasLocalStorage(this))
            {
                payments = AppClass.loadFromLocalBackup(this,currentMonth);
                tStatus.setText("Found " + payments.size() + "payments for " + Month.IntToMonthName(currentMonth) + ".");
            }
            else {
                Toast.makeText(this, "This app needs an internet connection!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{
            //AppClass.updateLocalBackup(this,payment,true);
        }

        PaymentAdapter adaptor = new PaymentAdapter(this,R.layout.item_payment, payments);
        listView.setAdapter(adaptor);


        SharedPreferences pref = getSharedPreferences("MyPref", 0); // 0 - for private mode
        currentMonth = pref.getInt("month", -1);

        if(currentMonth == -1)
        {
            currentMonth = Month.monthFromTimestamp(getCurrentTime());
        }

        text.setText(String.valueOf(currentMonth));

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMonth = currentMonth - 1;
                pref.edit().putInt("month", currentMonth).apply();
                recreate();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMonth = currentMonth + 1;
                pref.edit().putInt("month", currentMonth).apply();
                recreate();
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("wallet");

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    try {

                        Payment payment = dataSnapshot.getValue(Payment.class);
                        if (currentMonth == Month.monthFromTimestamp(payment.timestamp)) {
                            payments.add(payment);
                            adaptor.notifyDataSetChanged();
                        }
                    } catch (Exception e)
                    {

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity6.this, AddPaymentActivity.class));
            }
        });
    }
    public static String getCurrentTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd:HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }


}