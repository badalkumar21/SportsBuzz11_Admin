package com.cricker.admin.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cricker.admin.Model.ModelSquad;
import com.cricker.admin.R;
import com.cricker.admin.ViewHolder.ViewHolderFantasyXI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.cricker.admin.Config.PATH;

public class FantasyTeamMakerActivity extends AppCompatActivity {

    public String team1;
    public String team2;
    public String id;

    int points;

    Button backButton;

    RecyclerView mRecyclerView_wk;
    RecyclerView mRecyclerView_bt;
    RecyclerView mRecyclerView_al;
    RecyclerView mRecyclerView_bw;

    FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI> firebaseRecyclerAdapterWk;
    FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI> firebaseRecyclerAdapterBt;
    FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI> firebaseRecyclerAdapterAl;
    FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI> firebaseRecyclerAdapterBw;

    FirebaseDatabase mFirebaseDatabase;

    DatabaseReference mRef;
    DatabaseReference mRefWk;
    DatabaseReference mRefBt;
    DatabaseReference mRefAl;
    DatabaseReference mRefBw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_generator);

        backButton = findViewById(R.id.backButton);

        Toast.makeText(this, "Hey", Toast.LENGTH_SHORT).show();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        id = getIntent().getExtras().getString("id");
        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "Teams");
        mRefWk = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/wk");
        mRefBt = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/bt");
        mRefAl = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/al");
        mRefBw = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/bw");

        points = 0;

        orderPlayers();

        setupRecyclerviewWk();

        setupRecyclerviewBt();

        setupRecyclerviewAl();

        setupRecyclerviewBw();

    }

    private void orderPlayers() {


        mRef.child(team1).orderByChild("role").equalTo("wk")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefWk.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        mRef.child(team1).orderByChild("role").equalTo("bt")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefBt.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        mRef.child(team1).orderByChild("role").equalTo("al")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefAl.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        mRef.child(team1).orderByChild("role").equalTo("bw")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefBw.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        mRef.child(team2).orderByChild("role").equalTo("wk")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefWk.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        mRef.child(team2).orderByChild("role").equalTo("bt")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefBt.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        mRef.child(team2).orderByChild("role").equalTo("al")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefAl.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        mRef.child(team2).orderByChild("role").equalTo("bw")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                            mRefBw.child(Squad.getName()).setValue(Squad);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void setupRecyclerviewWk() {

        mRecyclerView_wk = findViewById(R.id.myRecycleView_wk);
        mRecyclerView_wk.setLayoutManager(new GridLayoutManager(this, 4));

        Query query = mRefWk.orderByChild("role").equalTo("wk");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterWk = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull final ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());
                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            points += model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        } else {

                            points -= model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

            @NonNull
            @Override
            public ViewHolderFantasyXI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fantasy_xi_row, parent, false);
                return new ViewHolderFantasyXI(view);
            }
        };

        mRecyclerView_wk.setAdapter(firebaseRecyclerAdapterWk);
    }

    private void setupRecyclerviewBt() {

        mRecyclerView_bt = findViewById(R.id.myRecycleView_bt);
        mRecyclerView_bt.setLayoutManager(new GridLayoutManager(this, 4));

        Query query = mRefBt.orderByChild("role").equalTo("bt");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterBt = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull final ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            points += model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        } else {

                            points -= model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolderFantasyXI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fantasy_xi_row, parent, false);
                return new ViewHolderFantasyXI(view);
            }
        };

        mRecyclerView_bt.setAdapter(firebaseRecyclerAdapterBt);
    }

    private void setupRecyclerviewAl() {

        mRecyclerView_al = findViewById(R.id.myRecycleView_al);
        mRecyclerView_al.setLayoutManager(new GridLayoutManager(this, 4));

        Query query = mRefAl.orderByChild("role").equalTo("al");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterAl = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull final ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            points += model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        } else {

                            points -= model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

            @NonNull
            @Override
            public ViewHolderFantasyXI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fantasy_xi_row, parent, false);
                return new ViewHolderFantasyXI(view);
            }
        };

        mRecyclerView_al.setAdapter(firebaseRecyclerAdapterAl);
    }

    private void setupRecyclerviewBw() {

        mRecyclerView_bw = findViewById(R.id.myRecycleView_bw);
        mRecyclerView_bw.setLayoutManager(new GridLayoutManager(this, 4));

        Query query = mRefBw.orderByChild("role").equalTo("bw");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterBw = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull final ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());

                viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            points += model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        } else {

                            points -= model.getPoints();
                            Toast.makeText(FantasyTeamMakerActivity.this, "points:" + points, Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

            @NonNull
            @Override
            public ViewHolderFantasyXI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fantasy_xi_row, parent, false);
                return new ViewHolderFantasyXI(view);
            }
        };

        mRecyclerView_bw.setAdapter(firebaseRecyclerAdapterBw);
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapterWk.startListening();
        firebaseRecyclerAdapterBt.startListening();
        firebaseRecyclerAdapterAl.startListening();
        firebaseRecyclerAdapterBw.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapterWk.stopListening();
        firebaseRecyclerAdapterBt.stopListening();
        firebaseRecyclerAdapterAl.stopListening();
        firebaseRecyclerAdapterBw.stopListening();
    }

}
