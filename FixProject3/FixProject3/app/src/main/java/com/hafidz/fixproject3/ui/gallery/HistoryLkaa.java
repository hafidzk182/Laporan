package com.hafidz.fixproject3.ui.gallery;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hafidz.fixproject3.R;

import java.util.ArrayList;

public class HistoryLkaa extends AppCompatActivity {
    FloatingActionButton tambah;
    Adapterlaka adapterlaka;
    DatabaseReference database= FirebaseDatabase.getInstance().getReference();
    ArrayList<com.hafidz.fixproject3.ui.gallery.ModelLaka> listmodellaka;
    RecyclerView tvtampil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_lkaa);
        tvtampil = findViewById(R.id.tvtampil);
        tambah = findViewById(R.id.btntambah);
        tambah.setOnClickListener(v -> startActivity(new Intent(HistoryLkaa.this,TambahLAKA.class)));tvtampil=findViewById(R.id.tvtampil);
        LinearLayoutManager mlayoutmanager=new LinearLayoutManager(this);
        mlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        tvtampil.setHasFixedSize(true);
        tvtampil.setLayoutManager(mlayoutmanager);
        tvtampil.setItemAnimator(new DefaultItemAnimator());
        tvtampil.setAdapter(adapterlaka);
        tampiData();

    }
    private void tampiData() {
        database.child("Laporan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listmodellaka=new ArrayList<>();
                for (DataSnapshot item:snapshot.getChildren()){
                    com.hafidz.fixproject3.ui.gallery.ModelLaka laka=item.getValue(com.hafidz.fixproject3.ui.gallery.ModelLaka.class);
                    assert laka != null;
                    laka.setKey(item.getKey());
                    listmodellaka.add(laka);
                }
                adapterlaka=new com.hafidz.fixproject3.ui.gallery.Adapterlaka(listmodellaka,HistoryLkaa.this);
                tvtampil.setAdapter(adapterlaka);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}