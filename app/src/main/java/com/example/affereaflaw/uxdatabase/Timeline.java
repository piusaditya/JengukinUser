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
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class Timeline extends Fragment {

    public Timeline() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static class timeline extends RecyclerView.ViewHolder {
        View viewTimeline;

        public timeline (View itemView) {
            super(itemView);
            viewTimeline  = itemView;
        }

        public void setTimeline(String Timeline){
            final TextView txtTimeline = (TextView) viewTimeline.findViewById(R.id.timeline);
            txtTimeline.setText(Timeline);
        }

        public void setKategori(String Kategori){
            final TextView txtKategori = (TextView) viewTimeline.findViewById(R.id.kategori);
            txtKategori.setText(Kategori);
        }

        public void setTime(String Time){
            final TextView txtTime = (TextView) viewTimeline.findViewById(R.id.time);
            txtTime.setText(Time);
        }
        public void setImage(final String Image, final Context ctx) {
            final CircleImageView imgTimeline = (de.hdodenhof.circleimageview.CircleImageView) viewTimeline.findViewById(R.id.imgTimeline);

            Picasso.with(ctx).load(Image).networkPolicy(NetworkPolicy.OFFLINE).into(imgTimeline, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(Image).into(imgTimeline);
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("label", 0);
        String mString = mPrefs.getString("tag", "0");

        final View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        final RecyclerView rvTimeline = (RecyclerView) view.findViewById(R.id.rvTimeline);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Timeline").child(mString);
        databaseReference.keepSynced(true);
        //rvPasien.setHasFixedSize(true);

        rvTimeline.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerAdapter<TimelineGetSet, timeline> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<TimelineGetSet, timeline>(
                TimelineGetSet.class,
                R.layout.timeline_row,
                timeline.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(timeline viewHolder, TimelineGetSet model, int position) {
                viewHolder.setTimeline(model.getTimeline());
                viewHolder.setTime(model.getTime());
                viewHolder.setKategori(model.getKategori());
                viewHolder.setImage(model.getImage(), getContext());
            }
        };
        rvTimeline.setAdapter(firebaseRecyclerAdapter);

        return view;
    }
}
