package com.cricker.admin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cricker.admin.Activity.MatchDetailsActivity;
import com.cricker.admin.Adapter.TabAdapter;
import com.cricker.admin.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.cricker.admin.Config.PATH;

public class SquadFragment extends Fragment {

    private String team1;
    private String team2;
    private MatchDetailsActivity matchDetailsActivity;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button saveBtn;
    EditText editTextSquadType;
    DatabaseReference mRef;
    FirebaseDatabase mFirebaseDatabase;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_squad, container, false);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        team1 = matchDetailsActivity.team1;
        team2 = matchDetailsActivity.team2;
        id = matchDetailsActivity.id;

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        saveBtn = view.findViewById(R.id.save_btn);
        editTextSquadType = view.findViewById(R.id.squad_type);

        adapter = new TabAdapter(getChildFragmentManager());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "squadType");

        adapter.addFragment(new Tab1Fragment(), team1);
        adapter.addFragment(new Tab2Fragment(), team2);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.child("type").setValue(editTextSquadType.getText().toString());
            }
        });

        return view;

    }
}
