package com.cricker.admin.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cricker.admin.Activity.MatchDetailsActivity;
import com.cricker.admin.Model.ModelTips;
import com.cricker.admin.R;
import com.cricker.admin.ViewHolder.ViewHolderTips;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.cricker.admin.Config.PATH;

public class TipsFragment extends Fragment {

    public MatchDetailsActivity matchDetailsActivity;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;
    FirebaseRecyclerAdapter<ModelTips, ViewHolderTips> firebaseRecyclerAdapter;
    FloatingActionButton buttonAddTips;
    FloatingActionButton buttonDeleteTips;
    int childrenCount = 0;
    private String id;
    TextView textViewNoTips;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tips, container, false);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();

        textViewNoTips = view.findViewById(R.id.no_tips_text);
        id = matchDetailsActivity.id;

        buttonAddTips = view.findViewById(R.id.add_tips);
        buttonDeleteTips = view.findViewById(R.id.delete_tips);
        pb = view.findViewById(R.id.pb_tips);
        mRecyclerView = view.findViewById(R.id.myRecycleView_tips);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH +"FantasySquad/Team" + "/" + id + "/" + "tips");
        Query query = mRef.orderByKey();

        pb.setVisibility(View.VISIBLE);

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelTips>().setQuery(query, ModelTips.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelTips, ViewHolderTips>(fbOptions) {
            @Override
            protected void onBindViewHolder(final ViewHolderTips viewHolder, final int position, final ModelTips model) {

                viewHolder.setTips(model.getTips());

                viewHolder.buttonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRef.child("" + (position + 1)).child("tips").setValue(viewHolder.editTextTips.getText().toString());
                    }
                });

                pb.setVisibility(View.GONE);

            }

            @Override
            public ViewHolderTips onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_row, parent, false);

                return new ViewHolderTips(view);
            }
        };

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() == 0) {
                    textViewNoTips.setVisibility(View.VISIBLE);
                } else {
                    textViewNoTips.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

        buttonAddTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.child("" + (childrenCount + 1)).child("tips").setValue("   ");
            }
        });

        buttonDeleteTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.child("" + (childrenCount)).child("tips").setValue(null);
            }
        });


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                childrenCount = (int) dataSnapshot.getChildrenCount();
                Log.d("childrenCount", "onDataChange: childrenCount: " + childrenCount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
