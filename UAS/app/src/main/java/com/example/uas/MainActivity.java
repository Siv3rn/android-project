package com.example.uas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private TextView Log_register;
   private EditText log_email, log_password;
   private Button log_button;
   private FirebaseAuth mAuth;
   SharedPreferences sharedPreferences;
   private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log_register = (TextView) findViewById(R.id.Log_reg);

        Log_register.setOnClickListener(this);

        log_button = (Button) findViewById(R.id.Log_button);
        log_button.setOnClickListener(this);

        log_email = (EditText) findViewById(R.id.Log_username);
        log_password = (EditText) findViewById(R.id.Log_password);

        progressBar = (ProgressBar) findViewById(R.id.Log_prog);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.Log_reg:
                 startActivity(new Intent(this, RegisterUser.class));
                 break;
             case R.id.Log_button:
                 userLogin();

         }

    }

    private void userLogin() {
        String email = log_email.getText().toString().trim();
        String password = log_password.getText().toString().trim();
        if(email.isEmpty()){
            log_email.setError("Email is required");
            log_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            log_email.setError("Please enter valid email");
            log_email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            log_email.setError("Password is required");
            log_password.requestFocus();
            return;
        }
        if(password.length() < 6){
            log_password.setError("Min password should be 6 character!");
            log_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, MainMenu.class));
                }
                else {
                    Toast.makeText(MainActivity.this, "Email or Password is incorrect, please try again!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null){
            startActivity(new Intent(MainActivity.this, MainMenu.class));
            finish();
        }
        else{

        }
    }
}