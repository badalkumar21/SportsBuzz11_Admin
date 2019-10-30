package com.cricker.admin.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.cricker.admin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GeneratorActivity extends AppCompatActivity {

    Spinner spinner;
    String value = null;
    String key;
    Button button;
    ImageView imageViewTeam;
    TextView no_team;
    private DatabaseReference currentRef;
    private ProgressDialog loadingBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setElevation(0);
        actionBar.setTitle("Team Maker");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff1ca055")));
        getWindow().setStatusBarColor(0xff1ca055);

        loadingBar = new ProgressDialog(this);

        spinner = findViewById(R.id.spinner);
        spinner.setPrompt("Choose Combinations");


        final ProgressBar progressBar = findViewById(R.id.team_progressbar);

        no_team = findViewById(R.id.no_team);
        imageViewTeam = findViewById(R.id.smartTeamImage);


        List<String> list = new ArrayList<String>();
        list.add("5-2-3");
        list.add("5-1-4");
        list.add("4-3-3");
        list.add("4-2-4");
        list.add("4-1-5");
        list.add("3-3-4");
        list.add("3-2-5");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button = findViewById(R.id.generate_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                no_team.setVisibility(View.INVISIBLE);

                value = spinner.getSelectedItem().toString();

                key = getIntent().getExtras().get("key").toString();
                currentRef = FirebaseDatabase.getInstance().getReference().child("SmartTeam").child(key).child("Teams").child(value);

//
//                loadingBar.setTitle("Generating...");
//                loadingBar.setMessage("Please wait, we are loading your team...");
//                loadingBar.show();
//                loadingBar.setCanceledOnTouchOutside(false);


                currentRef.getRef().addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        String url = dataSnapshot.getValue(String.class);

                        if (url == null) {

                            progressBar.setVisibility(View.GONE);
                            imageViewTeam.setVisibility(View.GONE);
                            no_team.setVisibility(View.VISIBLE);

                        } else {

                            no_team.setVisibility(View.GONE);
                            imageViewTeam.setVisibility(View.VISIBLE);

                            Glide.with(getApplicationContext())
                                    .load(url)
                                    .into(imageViewTeam);
                        }

                        loadingBar.dismiss();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                onBackPressed();
                return true;
        }
        return false;
    }

}





