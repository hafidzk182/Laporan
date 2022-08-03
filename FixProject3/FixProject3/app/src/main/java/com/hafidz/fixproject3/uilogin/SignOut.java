package com.hafidz.fixproject3.uilogin;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.hafidz.fixproject3.R;

public class SignOut extends Fragment {
    Activity context;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.activity_sign_out,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        FloatingActionButton keluar  = context.findViewById(R.id.signo);
        keluar.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        });
    }
}