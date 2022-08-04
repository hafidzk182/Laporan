package com.hafidz.fixproject3.ui.gallery;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hafidz.fixproject3.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahLAKA extends AppCompatActivity {
    TextView errorText1;
    TextView errorText2;
    EditText edtvtgl;
    Calendar mycalendar;
    Button btnsubmit;
    EditText edlokej;
    EditText eduraiankej;
    ProgressDialog progressDialog;
    DatabaseReference database= FirebaseDatabase.getInstance().getReference();
    DatePickerDialog datePicker;
    Spinner spinerruas;
    Spinner spinnerkejadian;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_laka);
        eduraiankej=findViewById(R.id.uraianpesan);
        edlokej=findViewById(R.id.lokasikej);
        btnsubmit=findViewById(R.id.btn_submit);
        edtvtgl = findViewById(R.id.tanggalkejadian);
        mycalendar = Calendar.getInstance();
        spinerruas = findViewById(R.id.spinnerRuas);
        spinnerkejadian = findViewById(R.id.spinnerkejadian);
        progressDialog = new ProgressDialog(TambahLAKA.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu");
        progressDialog.setCancelable(true);
        errorText2=(TextView)spinnerkejadian.getSelectedView();
        errorText1=(TextView)spinerruas.getSelectedView();
        btnsubmit.setOnClickListener(v -> {
            String getRuas = String.valueOf(spinerruas.getSelectedItemId());
            String getLokasikejadia = edlokej.getText().toString();
            String getKejadian = String.valueOf(spinnerkejadian.getSelectedItemId());
            String getTanggalkejadian = edtvtgl.getText().toString();
            String getUraianpesan = eduraiankej.getText().toString();
            if (getRuas.isEmpty()) {
                errorText1.setError("silahkan pilih ruas");
            }else if(getKejadian.isEmpty()){
                errorText2.setError("Silahkan Pilih Kejadian");
            }else if (getLokasikejadia.isEmpty()){
                edlokej.setError("SilahkanIsi Lokasi Kejadian");
            }else if(getTanggalkejadian.isEmpty()){
                edtvtgl.setError("Silahkan Isi Tanggal Kejadian");
            }else if(getUraianpesan.isEmpty()){
                eduraiankej.setError("SIlahkan isi pesan anda");
            }else{
                database.child("Laporan").push().setValue(new com.hafidz.fixproject3.ui.gallery.ModelLaka(getRuas,getLokasikejadia,getKejadian,getTanggalkejadian,getUraianpesan)).addOnSuccessListener(aVoid -> {
                    Toast.makeText(TambahLAKA.this,"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TambahLAKA.this,HistoryLkaa.class));
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(TambahLAKA.this,"Data Gagal Disimpan",Toast.LENGTH_SHORT).show());
            }
        });

        ArrayAdapter<CharSequence> kejadian = ArrayAdapter.createFromResource(getApplicationContext(),R.array.Kejadian, android.R.layout.simple_spinner_dropdown_item);
        kejadian.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerkejadian.setAdapter(kejadian);
        spinnerkejadian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),kejadian.getItem(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> ruas = ArrayAdapter.createFromResource(getApplicationContext(),R.array.ruas, android.R.layout.simple_spinner_dropdown_item);
        ruas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerruas.setAdapter(ruas);
        spinerruas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),ruas.getItem(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final int day = mycalendar.get(Calendar.DAY_OF_MONTH);
        final int month = mycalendar.get(Calendar.MONTH);
        final int year = mycalendar.get(Calendar.YEAR);

        datePicker = new DatePickerDialog(TambahLAKA.this);

        edtvtgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker = new DatePickerDialog(TambahLAKA.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        edtvtgl.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                        updatelabel();
                    }
                },year,month,day);
                datePicker.getDatePicker().setMaxDate(mycalendar.getTimeInMillis());

                datePicker.show();
            }
        });
    }

        private void updatelabel(){
        String myformat="MM/dd/yy HH:mm";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myformat, Locale.US);
        edtvtgl.setText(dateFormat.format(mycalendar.getTime()));
    }
}
