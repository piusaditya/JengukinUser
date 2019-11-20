package com.example.affereaflaw.uxdatabase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class Pasien extends AppCompatActivity {

    private Button btnaddPasien;
    private EditText etNamaPasien, etTanggal, etKamar, etRs, etKode;
    private ImageButton imgSelect;
    private ProgressDialog progress;
    private DatabaseReference databaseSO;
    private StorageReference storageReference;
    private Uri imageUri;
    private static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien);

        btnaddPasien = (Button) findViewById(R.id.btnAddPasien);
        etNamaPasien = (EditText) findViewById(R.id.etFirstName);
        etTanggal = (EditText) findViewById(R.id.etDate);
        etKamar = (EditText) findViewById(R.id.etKamar);
        etRs = (EditText) findViewById(R.id.etHospital);
        etKode = (EditText) findViewById(R.id.etKode);
        imgSelect = (ImageButton) findViewById(R.id.imgSelect);

        progress = new ProgressDialog(this);
        databaseSO = FirebaseDatabase.getInstance().getReference().child("Data Pasien");
        storageReference = FirebaseStorage.getInstance().getReference().child("Foto Pasien").child(String.valueOf(new Date().getTime()));

        btnaddPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPasien();
            }
        });

        imgSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galery = new Intent(Intent.ACTION_GET_CONTENT);
                galery.setType("image/*");
                startActivityForResult(galery, GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            imageUri = data.getData();
            imgSelect.setImageURI(imageUri);
        }
    }

    private void addPasien() {

        progress.setMessage("Menambahkan data pasien...");
        progress.show();
        final String nama = etNamaPasien.getText().toString();
        final String tanggal = etTanggal.getText().toString();
        final String kamar = etKamar.getText().toString();
        final String rs = etRs.getText().toString();
        final String kode = etKode.getText().toString();

        if (!TextUtils.isEmpty(nama)&&!TextUtils.isEmpty(tanggal)&&!TextUtils.isEmpty(kamar)&&!TextUtils.isEmpty(rs)&&imageUri!=null){

            storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloader = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPostSO = databaseSO.push();
                    newPostSO.child("nama").setValue(nama);
                    newPostSO.child("tanggalMasuk").setValue(tanggal);
                    newPostSO.child("noKamar").setValue(kamar);
                    newPostSO.child("rs").setValue(rs);
                    newPostSO.child("kode").setValue(kode);
                    newPostSO.child("image").setValue(downloader.toString());

                }
            });

            Intent i = new Intent(Pasien.this, MainMenu.class);
            startActivity(i);
            finish();
        }
    }
}
