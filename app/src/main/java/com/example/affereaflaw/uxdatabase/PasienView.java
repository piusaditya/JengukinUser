package com.example.affereaflaw.uxdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PasienView extends AppCompatActivity {

    private String key = null;
    private DatabaseReference databasePasienView;
    private EditText etNamaPasien, etTanggal, etKamar, etRs, etKode;
    private Button btnRemove, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_view);

        key = getIntent().getExtras().getString("Data Pasien");
        etNamaPasien = (EditText) findViewById(R.id.etvFirstName);
        etTanggal = (EditText) findViewById(R.id.etvDate);
        etKamar = (EditText) findViewById(R.id.etvKamar);
        etRs = (EditText) findViewById(R.id.etvHospital);
        etKode = (EditText) findViewById(R.id.etvKode);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        databasePasienView = FirebaseDatabase.getInstance().getReference().child("Data Pasien");
        databasePasienView.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String nameView = (String) dataSnapshot.child("nama").getValue();
                String tanggalView = (String) dataSnapshot.child("tanggalMasuk").getValue();
                String noKamarView = (String) dataSnapshot.child("noKamar").getValue();
                String rsView = (String) dataSnapshot.child("rs").getValue();
                String kodeView = (String) dataSnapshot.child("kode").getValue();

                etNamaPasien.setText(nameView);
                etTanggal.setText(tanggalView);
                etKamar.setText(noKamarView);
                etRs.setText(rsView);
                etKode.setText(kodeView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasePasienView.child(key).removeValue();
                startActivity(new Intent(PasienView.this, MainMenu.class));
            }
        });
    }
}
