package com.hafidz.fixproject3.ui.gallery;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hafidz.fixproject3.R;

import java.util.List;

public class Adapterlaka extends RecyclerView.Adapter<Adapterlaka.Myview>{
    private List<ModelLaka>mlist;
    private Activity activity;
    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    public Adapterlaka(List<ModelLaka>mlist,Activity activity){
        this.mlist = mlist;
        this.activity = activity;

    }
    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate1 = LayoutInflater.from(parent.getContext());
        View viewitem = inflate1.inflate(R.layout.layoutitem,parent,false);
        return new Myview(viewitem);
    }

    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position) {
        final ModelLaka data = mlist.get(position);
        holder.tvruas.setText("@string/ruas1"+data.getRuas());
        holder.tvlokasi.setText("@string/LOKASIKEJADIAN"+data.getLokasikejadia());
        holder.tvkejadian.setText("@string/KEJADIAN:"+data.getKejadian());
        holder.tvtanggal.setText("Tanggal Kejadian"+data.getTanggalKejadian());
        holder.tvuraian.setText("@STUraian Pesan"+data.getUraianpesan());
    }

    @Override
    public int getItemCount() {
        try {
            return mlist.size();
        } catch (Exception ex){return 0;}
    }

    public class Myview extends RecyclerView.ViewHolder {
        TextView tvruas,tvlokasi,tvkejadian,tvtanggal,tvuraian;
        CardView cardhasil;
        public Myview(@NonNull View itemView) {
            super(itemView);
            tvruas= itemView.findViewById(R.id.tvruas);
            tvlokasi= itemView.findViewById(R.id.tvlokasi);
            tvkejadian = itemView.findViewById(R.id.tvkejadian);
            tvtanggal = itemView.findViewById(R.id.tvtanggal);
            tvuraian = itemView.findViewById(R.id.tvuraian);

            cardhasil = itemView.findViewById(R.id.cardhasil);
        }
    }
}
