package com.cricker.admin.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.cricker.admin.Activity.FantasyTeamMakerActivity;
import com.cricker.admin.Activity.MatchDetailsActivity;
import com.cricker.admin.Activity.TeamGeneratorActivity;
import com.cricker.admin.R;

public class TeamGeneratorFragment extends Fragment implements NumberPicker.OnValueChangeListener {

    public MatchDetailsActivity matchDetailsActivity;
    NumberPicker numberPickerWk;
    NumberPicker numberPickerBt;
    NumberPicker numberPickerAl;
    NumberPicker numberPickerBw;
    Button generateButton;
    String team1, team2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_team_generator, container, false);

        generateButton = view.findViewById(R.id.generate_btn);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        team1 = matchDetailsActivity.team1;
        team2 = matchDetailsActivity.team2;

        numberPickerWk = view.findViewById(R.id.numberPickerWk);
        numberPickerBt = view.findViewById(R.id.numberPickerBt);
        numberPickerAl = view.findViewById(R.id.numberPickerAl);
        numberPickerBw = view.findViewById(R.id.numberPickerBw);

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
                if (generateButton.isActivated()) {
                    Intent intent = new Intent(getActivity(), FantasyTeamMakerActivity.class);
                    intent.putExtra("team1", team1);
                    intent.putExtra("team2", team2);
                    intent.putExtra("wk", numberPickerWk.getValue());
                    intent.putExtra("bt", numberPickerBt.getValue());
                    intent.putExtra("al", numberPickerAl.getValue());
                    intent.putExtra("bw", numberPickerBw.getValue());
                    startActivity(intent);
                }
            }
        });

        return view;

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        int xi = numberPickerWk.getValue() + numberPickerBt.getValue() + numberPickerAl.getValue() + numberPickerBw.getValue();
        if (xi == 11) {
            generateButton.setActivated(true);
            generateButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        } else {
            generateButton.setActivated(false);
            generateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
        }
    }
}
