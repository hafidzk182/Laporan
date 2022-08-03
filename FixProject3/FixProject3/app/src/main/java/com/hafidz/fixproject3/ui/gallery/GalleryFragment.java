package com.hafidz.fixproject3.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hafidz.fixproject3.R;

public class GalleryFragment extends Fragment {
    Activity context;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        return inflater.inflate(R.layout.fragment_gallery,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button btnhistory = context.findViewById(R.id.btnhistory);
        btnhistory.setOnClickListener(v -> {
            Intent intent = new Intent(context,HistoryLkaa.class);
            startActivity(intent);
        });
    }
}