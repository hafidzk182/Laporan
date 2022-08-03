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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.hafidz.fixproject3.MainActivity;
import com.hafidz.fixproject3.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private EditText editNama;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editPasswordconf;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editNama = findViewById(R.id.name);
        editPasswordconf = findViewById(R.id.passwordconf);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(true);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);
        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(view -> finish());
        btn_register.setOnClickListener(view -> {
            if (editNama.getText().length() > 0 && editEmail.getText().length() > 0 && editPassword.getText().length() > 0) {
                if (editPassword.getText().toString().equals(editPasswordconf.getText().toString())) {
                    register(editNama.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Password tidak sama", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan Isi Semua Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(String Nama, String Email, String Password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(Email, Password).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        FirebaseUser firebaseUser = task.getResult().getUser();
                        if (firebaseUser != null) {
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(Nama)
                                    .build();
                            firebaseUser.updateProfile(request).addOnCompleteListener(task1 -> reload());
                        } else {
                            Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}