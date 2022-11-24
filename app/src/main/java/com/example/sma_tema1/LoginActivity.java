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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailAddress = (EditText) findViewById(R.id.emailText);
        EditText password = (EditText) findViewById(R.id.passwordField);
        Button loginButton = (Button) findViewById(R.id.login);
        Button registerButton = (Button) findViewById(R.id.register_b);

        FirebaseAuth authAction=FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAddress.getText().toString();
                String pass = password.getText().toString();
                if(emailAddress.getText().toString().equals("") || password.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please complete all fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    authAction.signInWithEmailAndPassword(email, encodePassword(email, pass)).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                openMainPage();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),""+ task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterPage();
            }
        });
    }

    private void openMainPage() {
        startActivity(new Intent(this, MainActivity6.class));
    }

    private void openRegisterPage()
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }

}