package com.cricker.admin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cricker.admin.Model.ModelInfo;
import com.cricker.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InfoFragment extends Fragment {


    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ArrayList<ModelInfo> infoArrayList = new ArrayList<>();

    private EditText text_avg_score_1st_inns;
    private EditText text_avg_score_2nd_inns;
    private EditText text_avg_score_3rd_inns;
    private EditText text_avg_score_4th_inns;
    private EditText text_capacity;
    private EditText text_city;
    private EditText text_date;
    private EditText text_duration;
    private EditText text_match;
    private EditText text_series;
    private EditText text_time;
    private EditText text_venue;
    private EditText text_weather;

    private Button buttonSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_info, container, false);

        text_avg_score_1st_inns = view.findViewById(R.id.avg_score_1st_inns);
        text_avg_score_2nd_inns = view.findViewById(R.id.avg_score_2nd_inns);
        text_avg_score_3rd_inns = view.findViewById(R.id.avg_score_3rd_inns);
        text_avg_score_4th_inns = view.findViewById(R.id.avg_score_4th_inns);
        text_capacity = view.findViewById(R.id.capacity);
        text_city = view.findViewById(R.id.city);
        text_date = view.findViewById(R.id.date);
        text_duration = view.findViewById(R.id.duration);
        text_match = view.findViewById(R.id.match);
        text_series = view.findViewById(R.id.series);
        text_time = view.findViewById(R.id.time);
        text_venue = view.findViewById(R.id.venue);
        text_weather = view.findViewById(R.id.weather);

        buttonSave = view.findViewById(R.id.save_btn);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("FantasySquad/Team/IndSa21/info");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    infoArrayList.add(snapshot.getValue(ModelInfo.class));

                }

                ModelInfo modelInfo = infoArrayList.get(0);

                text_avg_score_1st_inns.setText(String.valueOf(modelInfo.getAvg_score_1st_inns()));
                text_avg_score_2nd_inns.setText(String.valueOf(modelInfo.getAvg_score_2nd_inns()));
                text_avg_score_3rd_inns.setText(String.valueOf(modelInfo.getAvg_score_3rd_inns()));
                text_avg_score_4th_inns.setText(String.valueOf(modelInfo.getAvg_score_4th_inns()));

                text_capacity.setText(String.valueOf(modelInfo.getCapacity()));
                text_city.setText(modelInfo.getCity());
                text_date.setText(modelInfo.getDate());
                text_duration.setText(modelInfo.getDuration());
                text_match.setText(modelInfo.getMatch());
                text_series.setText(modelInfo.getSeries());
                text_time.setText(modelInfo.getTime());
                text_venue.setText(modelInfo.getVenue());
                text_weather.setText(modelInfo.getWeather());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRef.child("1").child("avg_score_1st_inns").setValue(Integer.valueOf(text_avg_score_1st_inns.getText().toString()));
                mRef.child("1").child("avg_score_2nd_inns").setValue(Integer.valueOf(text_avg_score_2nd_inns.getText().toString()));
                mRef.child("1").child("avg_score_3rd_inns").setValue(Integer.valueOf(text_avg_score_3rd_inns.getText().toString()));
                mRef.child("1").child("avg_score_4th_inns").setValue(Integer.valueOf(text_avg_score_4th_inns.getText().toString()));
                mRef.child("1").child("capacity").setValue(Integer.valueOf(text_capacity.getText().toString()));
                mRef.child("1").child("city").setValue(text_city.getText().toString());
                mRef.child("1").child("date").setValue(text_date.getText().toString());
                mRef.child("1").child("duration").setValue(text_duration.getText().toString());
                mRef.child("1").child("match").setValue(text_match.getText().toString());
                mRef.child("1").child("series").setValue(text_series.getText().toString());
                mRef.child("1").child("time").setValue(text_time.getText().toString());
                mRef.child("1").child("venue").setValue(text_venue.getText().toString());
                mRef.child("1").child("weather").setValue(text_weather.getText().toString());

            }
        });

        return view;

    }
}
