package com.cricker.admin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.cricker.admin.R;

public class TeamConbinationActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    NumberPicker numberPickerWk;
    NumberPicker numberPickerBt;
    NumberPicker numberPickerAl;
    NumberPicker numberPickerBw;

    Button generateButton;
    String team1, team2, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_conbination);

        generateButton = findViewById(R.id.generate_btn);

        id = getIntent().getExtras().getString("id");
        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");

        numberPickerWk = findViewById(R.id.numberPickerWk);
        numberPickerBt = findViewById(R.id.numberPickerBt);
        numberPickerAl = findViewById(R.id.numberPickerAl);
        numberPickerBw = findViewById(R.id.numberPickerBw);

        numberPickerWk.setMinValue(1);
        numberPickerWk.setMaxValue(4);

        numberPickerBt.setMinValue(3);
        numberPickerBt.setMaxValue(6);

        numberPickerAl.setMinValue(1);
        numberPickerAl.setMaxValue(4);

        numberPickerBw.setMinValue(3);
        numberPickerBw.setMaxValue(6);

        numberPickerWk.setValue(1);
        numberPickerBt.setValue(5);
        numberPickerAl.setValue(2);
        numberPickerBw.setValue(3);

        numberPickerWk.setOnValueChangedListener(this);
        numberPickerBt.setOnValueChangedListener(this);
        numberPickerAl.setOnValueChangedListener(this);
        numberPickerBw.setOnValueChangedListener(this);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamConbinationActivity.this, FantasyTeamMakerActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("team1", team1);
                intent.putExtra("team2", team2);
                intent.putExtra("wk", numberPickerWk.getValue());
                intent.putExtra("bt", numberPickerBt.getValue());
                intent.putExtra("al", numberPickerAl.getValue());
                intent.putExtra("bw", numberPickerBw.getValue());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        int xi = numberPickerWk.getValue() + numberPickerBt.getValue() + numberPickerAl.getValue() + numberPickerBw.getValue();
        if (xi == 11) {
            generateButton.setVisibility(View.VISIBLE);
        } else {
            generateButton.setVisibility(View.GONE);
        }
    }
}
