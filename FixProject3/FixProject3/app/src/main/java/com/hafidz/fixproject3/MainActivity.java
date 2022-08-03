package com.hafidz.fixproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    Button btn_menutama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textname = findViewById(R.id.name);
        btn_menutama=findViewById(R.id.btn_menutama);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser !=null){
            textname.setText(firebaseUser.getDisplayName());
        }else{
            textname.setText(R.string.login_failed);
        }btn_menutama.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),MenutamaActivity.class)));
}
}