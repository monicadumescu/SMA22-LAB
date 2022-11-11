package com.example.sma_tema1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sma_tema1.model.Payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String payment_type;
    private DatabaseReference databaseReference;
    private boolean ok =false;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        EditText name = (EditText) findViewById(R.id.name);
        EditText cost = (EditText) findViewById(R.id.cost);
        Button save = (Button) findViewById(R.id.buttonSave);
        Button delete = (Button) findViewById(R.id.buttonDelete);

        Spinner spinnerpayments = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.payment_types, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerpayments.setAdapter(adapter);

        spinnerpayments.setOnItemSelectedListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || cost.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the the fields!", Toast.LENGTH_SHORT).show();
                }
                //Payment payment = new Payment(getCurrentTime(),Double.parseDouble(cost.getText().toString()),name.getText().toString(),payment_type);
                databaseReference = FirebaseDatabase.getInstance().getReference().child("wallet");
                Payment payment = new Payment(getCurrentTime(),Double.parseDouble(cost.getText().toString()), name.getText().toString(), payment_type);
                databaseReference.push().setValue(payment).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Payment was added successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),""+ task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") || cost.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please fill all the the fields!", Toast.LENGTH_SHORT).show();
                }
                //Payment payment = new Payment(getCurrentTime(),Double.parseDouble(cost.getText().toString()),name.getText().toString(),payment_type);
                databaseReference = FirebaseDatabase.getInstance().getReference().child("wallet");
                Payment payment1 = new Payment(getCurrentTime(),Double.parseDouble(cost.getText().toString()), name.getText().toString(), payment_type);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            Payment payment = dataSnapshot.getValue(Payment.class);
                            if (payment.equals(payment1))
                            {
                                ok = true;
                                key = dataSnapshot.getKey();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"No such payment!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if(ok)
                        {
                            databaseReference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Payment was deleted successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),""+ task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        payment_type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static String getCurrentTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd:HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}