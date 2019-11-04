package com.cricker.admin.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cricker.admin.Adapter.TabAdapter;
import com.cricker.admin.Fragment.InfoFragment;
import com.cricker.admin.Fragment.SquadFragment;
import com.cricker.admin.Fragment.TeamGeneratorFragment;
import com.cricker.admin.Fragment.TipsFragment;
import com.cricker.admin.R;
import com.google.android.material.tabs.TabLayout;

public class MatchDetailsActivity extends AppCompatActivity {

    public String id;
    public String team1;
    public String team2;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        id = getIntent().getExtras().getString("id");
        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");

        adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new TeamGeneratorFragment(), "Teams");
        adapter.addFragment(new InfoFragment(), "Info");
        adapter.addFragment(new SquadFragment(), "Squad");
        adapter.addFragment(new TipsFragment(), "Tips");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
