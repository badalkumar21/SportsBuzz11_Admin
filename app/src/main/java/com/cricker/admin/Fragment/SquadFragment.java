package com.cricker.admin.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cricker.admin.Activity.MatchDetailsActivity;
import com.cricker.admin.Adapter.TabAdapter;
import com.cricker.admin.R;
import com.google.android.material.tabs.TabLayout;

public class SquadFragment extends Fragment {

    public String team1;
    public String team2;
    public MatchDetailsActivity matchDetailsActivity;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_squad, container, false);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        team1 = matchDetailsActivity.team1;
        team2 = matchDetailsActivity.team2;

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getChildFragmentManager());

        adapter.addFragment(new Tab1Fragment(), team1);
        adapter.addFragment(new Tab2Fragment(), team2);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;

    }
}
