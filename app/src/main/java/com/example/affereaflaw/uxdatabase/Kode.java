package com.example.affereaflaw.uxdatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Kode extends AppCompatActivity {

    private Button btnKode;
    private EditText txtKode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode);
        btnKode = (Button) findViewById(R.id.btnKode);
        txtKode = (EditText) findViewById(R.id.etKodein);

        btnKode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kode = txtKode.getText().toString().trim();
                SharedPreferences mPrefs = getSharedPreferences("label", 0);
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putString("tag", kode).apply();
                Intent intent = new Intent(Kode.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
