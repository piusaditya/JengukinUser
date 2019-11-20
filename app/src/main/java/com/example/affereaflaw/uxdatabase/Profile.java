package com.example.affereaflaw.uxdatabase;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends Fragment {


    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public static class profilPasien extends RecyclerView.ViewHolder {
        View viewProfileP;

        public profilPasien(View itemView) {
            super(itemView);
            viewProfileP  = itemView;
        }

        public void setNama(String nama){
            final TextView txtNama = (TextView) viewProfileP.findViewById(R.id.etvFirstName);
            txtNama.setText(nama);
        }

        public void setKamar(String noKamar){
            final TextView txtKamar = (TextView) viewProfileP.findViewById(R.id.etvKamar);
            txtKamar.setText(noKamar);
        }

        public void setKode(String kode){
            final TextView txtKode = (TextView) viewProfileP.findViewById(R.id.etvKode);
            txtKode.setText(kode);
        }

        public void setRs(String rs){
            final TextView txtKode = (TextView) viewProfileP.findViewById(R.id.etvHospital);
            txtKode.setText(rs);
        }

        public void setTanggal(String tanggalMasuk){
            final TextView txtKode = (TextView) viewProfileP.findViewById(R.id.etvDate);
            txtKode.setText(tanggalMasuk);
        }

        public void setTelp(String telp){
            final TextView txtKode = (TextView) viewProfileP.findViewById(R.id.etvTelp);
            txtKode.setText(telp);
        }

        public void setImage(final String image, final Context ctx) {
            final CircleImageView imgProfil = (de.hdodenhof.circleimageview.CircleImageView) viewProfileP.findViewById(R.id.imgProfilView);

            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(imgProfil, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(imgProfil);
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //String kode = getActivity().getIntent().getExtras().getString("Kode");
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("label", 0);
        String mString = mPrefs.getString("tag", "0");

        final RecyclerView rvPasien = (RecyclerView) view.findViewById(R.id.rvProfil);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Data Pasien");
        Query query = databaseReference.orderByChild("kode").equalTo(mString);
        databaseReference.keepSynced(true);
        //rvPasien.setHasFixedSize(true);
        rvPasien.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerAdapter<profilGetSet, profilPasien> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<profilGetSet, profilPasien>(
                profilGetSet.class,
                R.layout.profile_row,
                profilPasien.class,
                query
        ) {
            @Override
            protected void populateViewHolder(profilPasien viewHolder, profilGetSet model, int position) {
                final String key = getRef(position).getKey();
                viewHolder.setNama(model.getNama());
                viewHolder.setKamar(model.getNoKamar());
                viewHolder.setKode(model.getKode());
                viewHolder.setRs(model.getRs());
                viewHolder.setTanggal(model.getTanggalMasuk());
                viewHolder.setTelp(model.getTelp());
                viewHolder.setImage(model.getImage(),getContext());
            }
        };
        rvPasien.setAdapter(firebaseRecyclerAdapter);
        return view;
    }
}