package com.example.laporankeuangan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Error";
    private FirebaseAuth mAuth;
    Button btnLogin;
    TextView registerText;
    EditText fillUsername, fillPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        registerText = findViewById(R.id.tv_create_account);
        btnLogin = findViewById(R.id.btn_login);
        fillUsername = findViewById(R.id.et_username);
        fillPassword = findViewById(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String username = fillUsername.getText().toString();
               String password = fillPassword.getText().toString();

               if (username.equals("")){
                   Toast.makeText(LoginActivity.this, "Silahkan masukan username anda", Toast.LENGTH_SHORT).show();
               }else if (password.equals("")){
                   Toast.makeText(LoginActivity.this, "Masukan Password Anda", Toast.LENGTH_SHORT).show();
               }else {
                   mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               FirebaseUser user = mAuth.getCurrentUser();
                               Toast.makeText(LoginActivity.this, "Authentication Successfull", Toast.LENGTH_SHORT).show();
                               Intent goPayment = new Intent(LoginActivity.this, PaymentActivity.class);
                               startActivity(goPayment);
                               finish();
                           }else {
                               Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });


    }
}