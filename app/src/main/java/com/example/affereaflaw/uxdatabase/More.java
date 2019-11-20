package com.example.affereaflaw.uxdatabase;

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


public class More extends Fragment {

    public More() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static class suggestObat extends RecyclerView.ViewHolder {
        View viewSuggestObat;

        public suggestObat(View itemView) {
            super(itemView);
            viewSuggestObat  = itemView;
        }

        public void setSuggest(String Suggestion){
            final TextView txtSuggest = (TextView) viewSuggestObat.findViewById(R.id.txtSuggest);
            txtSuggest.setText(Suggestion);
        }

        public void setObat(String Obat){
            final TextView txtObat = (TextView) viewSuggestObat.findViewById(R.id.txtObat);
            txtObat.setText(Obat);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        RecyclerView rvSO = (RecyclerView) view.findViewById(R.id.rvSuggestionObat);
        rvSO.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("label", 0);
        String mString = mPrefs.getString("tag", "0");

        DatabaseReference databaseSO = FirebaseDatabase.getInstance().getReference().child("SO").child(mString);

        databaseSO.keepSynced(true);

        FirebaseRecyclerAdapter<SuggestionObatGetSet, suggestObat> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SuggestionObatGetSet, suggestObat>(
                SuggestionObatGetSet.class,
                R.layout.suggestion_obat_row,
                suggestObat.class,
                databaseSO
        ) {
            @Override
            protected void populateViewHolder(suggestObat viewHolder, SuggestionObatGetSet model, int position) {
                viewHolder.setSuggest(model.getSuggestion());
                viewHolder.setObat(model.getObat());
            }
        };
        rvSO.setAdapter(firebaseRecyclerAdapter);
        return view;
    }

}
