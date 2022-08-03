package com.hafidz.fixproject3.uilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hafidz.fixproject3.MainActivity;
import com.hafidz.fixproject3.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editEmail, editPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(true);

        btn_register.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
        btn_login.setOnClickListener(view -> {
            if (editEmail.getText().length() > 0 && editPassword.getText().length() > 0) {
                Login(editEmail.getText().toString(),editPassword.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan Isi Semua Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Login (String Email,String Password) {
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                if (task.getResult().getUser()!=null){
                    reload();
                }else {
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));}
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}