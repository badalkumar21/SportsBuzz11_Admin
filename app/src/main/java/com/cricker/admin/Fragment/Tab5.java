package com.cricker.admin.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cricker.admin.Activity.MatchDetailsActivity;
import com.cricker.admin.Activity.WebViewActivity;
import com.cricker.admin.Model.MyModel;
import com.cricker.admin.R;
import com.cricker.admin.ViewHolder.MyViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Tab5 extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;
    FirebaseRecyclerAdapter<MyModel, MyViewHolder> firebaseRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab5, container, false);


        pb = view.findViewById(R.id.pb_tab5);
        mRecyclerView = view.findViewById(R.id.myRecycleView_tab5);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Cricket");
        Query query = mRef.orderByKey();

        pb.setVisibility(View.VISIBLE);

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<MyModel>().setQuery(query, MyModel.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MyModel, MyViewHolder>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i, @NonNull final MyModel model) {

                viewHolder.setAds(getActivity().getBaseContext(), model.getAds());

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage1(Objects.requireNonNull(getActivity()).getBaseContext(), model.getImage1());
                viewHolder.setImage2(getActivity().getBaseContext(), model.getImage2());
                viewHolder.setT1(model.getT1());
                viewHolder.setT2(model.getT2());
                if (model.getUrl() == null) {
                    viewHolder.setStatus_r("upcoming");
                    viewHolder.setStatus_g(null);
                } else {
                    viewHolder.setStatus_g("available");
                    viewHolder.setStatus_r(null);
                }


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String offers = model.getTarget();
                        final String url = model.getUrl();

                        if (url == null) {

                            Intent intent = new Intent(getActivity().getApplicationContext(), MatchDetailsActivity.class);
                            intent.putExtra("team1", model.getTeam1());
                            intent.putExtra("team2", model.getTeam2());
                            startActivity(intent);

                        } else {

                            if (offers == null) {

                                Intent intent = new Intent(getActivity().getApplicationContext(), WebViewActivity.class);
                                intent.putExtra("id", url);
                                startActivity(intent);


                            } else {

                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(intent);

                            }
                        }
                    }
                });

                pb.setVisibility(View.GONE);

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_v7, parent, false);

                return new MyViewHolder(view);
            }
        };


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
}
