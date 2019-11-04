package com.cricker.admin.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cricker.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.cricker.admin.Config.PATH;

public class AddMatchActivity extends AppCompatActivity {

    private EditText text_title;
    private EditText text_desc;
    private EditText text_id;
    private EditText text_image1;
    private EditText text_image2;
    private EditText text_team1;
    private EditText text_team2;

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

    private EditText text_tips1;
    private EditText text_tips2;
    private EditText text_tips3;
    private EditText text_tips4;
    private EditText text_tips5;
    private EditText text_tips6;
    private EditText text_tips7;
    private Button buttonSave;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRefMatch;
    DatabaseReference mRefInfo;
    DatabaseReference mRefTips;

    private String id;
    long childrenCount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);

        text_title = findViewById(R.id.edit_text_title);
        text_desc = findViewById(R.id.edit_text_desc);
        text_id = findViewById(R.id.edit_text_id);
        text_image1 = findViewById(R.id.edit_text_image1);
        text_image2 = findViewById(R.id.edit_text_image2);
        text_team1 = findViewById(R.id.edit_text_team1);
        text_team2 = findViewById(R.id.edit_text_team2);


        text_avg_score_1st_inns = findViewById(R.id.edit_text_avg_score_1st_inns);
        text_avg_score_2nd_inns = findViewById(R.id.edit_text_avg_score_2nd_inns);
        text_avg_score_3rd_inns = findViewById(R.id.edit_text_avg_score_3rd_inns);
        text_avg_score_4th_inns = findViewById(R.id.edit_text_avg_score_4th_inns);
        text_capacity = findViewById(R.id.edit_text_capacity);
        text_city = findViewById(R.id.edit_text_city);
        text_date = findViewById(R.id.edit_text_date);
        text_duration = findViewById(R.id.edit_text_duration);
        text_match = findViewById(R.id.edit_text_match);
        text_series = findViewById(R.id.edit_text_series);
        text_time = findViewById(R.id.edit_text_time);
        text_venue = findViewById(R.id.edit_text_venue);
        text_weather = findViewById(R.id.edit_text_weather);


        text_tips1 = findViewById(R.id.edit_text_tips1);
        text_tips2 = findViewById(R.id.edit_text_tips2);
        text_tips3 = findViewById(R.id.edit_text_tips3);
        text_tips4 = findViewById(R.id.edit_text_tips4);
        text_tips5 = findViewById(R.id.edit_text_tips5);
        text_tips6 = findViewById(R.id.edit_text_tips6);
        text_tips7 = findViewById(R.id.edit_text_tips7);

        buttonSave = findViewById(R.id.save_btn);

        id = text_id.getText().toString();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRefMatch = mFirebaseDatabase.getReference(PATH + "Cricket");
        mRefInfo = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "info");
        mRefTips = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "tips");


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = text_id.getText().toString();
                mRefMatch = mFirebaseDatabase.getReference(PATH + "Cricket");
                mRefInfo = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "info");
                mRefTips = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "tips");


                //Match
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("title").setValue(text_title.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("desc").setValue(text_desc.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("id").setValue(text_id.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("image1").setValue(text_image1.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("image2").setValue(text_image2.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("t1").setValue(text_team1.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("t2").setValue(text_team2.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("team1").setValue(text_team1.getText().toString());
                mRefMatch.child(String.valueOf(childrenCount + 1)).child("team2").setValue(text_team2.getText().toString());


                // Info
                mRefInfo.child("1").child("avg_score_1st_inns").setValue(Integer.valueOf(text_avg_score_1st_inns.getText().toString()));
                mRefInfo.child("1").child("avg_score_2nd_inns").setValue(Integer.valueOf(text_avg_score_2nd_inns.getText().toString()));
                mRefInfo.child("1").child("avg_score_3rd_inns").setValue(Integer.valueOf(text_avg_score_3rd_inns.getText().toString()));
                mRefInfo.child("1").child("avg_score_4th_inns").setValue(Integer.valueOf(text_avg_score_4th_inns.getText().toString()));
                mRefInfo.child("1").child("capacity").setValue(Integer.valueOf(text_capacity.getText().toString()));
                mRefInfo.child("1").child("city").setValue(text_city.getText().toString());
                mRefInfo.child("1").child("date").setValue(text_date.getText().toString());
                mRefInfo.child("1").child("duration").setValue(text_duration.getText().toString());
                mRefInfo.child("1").child("match").setValue(text_match.getText().toString());
                mRefInfo.child("1").child("series").setValue(text_series.getText().toString());
                mRefInfo.child("1").child("time").setValue(text_time.getText().toString());
                mRefInfo.child("1").child("venue").setValue(text_venue.getText().toString());
                mRefInfo.child("1").child("weather").setValue(text_weather.getText().toString());


                // Tips
                if (text_tips1.getText().toString().length() > 2)
                    mRefTips.child("1").child("tips").setValue(text_tips1.getText().toString());
                if (text_tips2.getText().toString().length() > 2)
                    mRefTips.child("2").child("tips").setValue(text_tips2.getText().toString());
                if (text_tips3.getText().toString().length() > 2)
                    mRefTips.child("3").child("tips").setValue(text_tips3.getText().toString());
                if (text_tips4.getText().toString().length() > 2)
                    mRefTips.child("4").child("tips").setValue(text_tips4.getText().toString());
                if (text_tips5.getText().toString().length() > 2)
                    mRefTips.child("5").child("tips").setValue(text_tips5.getText().toString());
                if (text_tips6.getText().toString().length() > 2)
                    mRefTips.child("6").child("tips").setValue(text_tips6.getText().toString());
                if (text_tips7.getText().toString().length() > 2)
                    mRefTips.child("7").child("tips").setValue(text_tips7.getText().toString());

            }
        });


        mRefMatch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                childrenCount = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
