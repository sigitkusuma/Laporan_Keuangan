package com.example.laporankeuangan;

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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText fillUsername, fillPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fillUsername = findViewById(R.id.et_username);
        fillPassword = findViewById(R.id.et_password);

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = fillUsername.getText().toString();
                String password = fillPassword.getText().toString();

                if (username.equals("")){
                    Toast.makeText(RegisterActivity.this, "Username wajib di Isi", Toast.LENGTH_SHORT).show();
                }else if (password.equals("")){
                    Toast.makeText(RegisterActivity.this, "Password wajib di Isi", Toast.LENGTH_SHORT).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(RegisterActivity.this, "Authentication Successfuly", Toast.LENGTH_SHORT).show();
                                        Intent goLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(goLogin);
                                    }else {
                                        Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(goBack);
        finish();
    }
}