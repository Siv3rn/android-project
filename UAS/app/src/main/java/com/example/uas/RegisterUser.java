package com.example.uas;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private EditText reg_username, reg_email, reg_password;
    private Button reg_button;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        reg_button = (Button) findViewById(R.id.Reg_button);
        reg_button.setOnClickListener(this);

        reg_username = (EditText) findViewById(R.id.Reg_username);
        reg_email = (EditText) findViewById(R.id.Reg_email);
        reg_password = (EditText) findViewById(R.id.Reg_password);

        progressBar = (ProgressBar) findViewById(R.id.Reg_prog);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Reg_button:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String username = reg_username.getText().toString().trim();
        String email = reg_email.getText().toString().trim();
        String password = reg_password.getText().toString().trim();

        if(username.isEmpty()){
            reg_username.setError("Username is required");
            reg_username.requestFocus();
            return;
        }
        if(email.isEmpty()){
            reg_email.setError("Email is required");
            reg_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            reg_email.setError("Please provide a valid email!");
            reg_email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            reg_password.setError("Password is required");
            reg_password.requestFocus();
            return;
        }

        if(password.length() < 6){
            reg_password.setError("Min password should be 6 character!");
            reg_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(username, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterUser.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else {

                                                Toast.makeText(RegisterUser.this, "Failed to registered try again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);


                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(RegisterUser.this, "Registration is Failed!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }

}



