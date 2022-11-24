package com.example.sma_tema1;

import static com.example.sma_tema1.model.User.encodePassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText emailAddress = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.paasword);
        Button registerButton = (Button) findViewById(R.id.register);

        FirebaseAuth authAction=FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailAddress.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    authAction.createUserWithEmailAndPassword(emailAddress.getText().toString(), encodePassword(emailAddress.getText().toString(),password.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                openLogin();
                                Toast.makeText(getApplicationContext(), "You were registered successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),""+ task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
    }

    private void openLogin() {
        new Intent(this, LoginActivity.class);
    }
}