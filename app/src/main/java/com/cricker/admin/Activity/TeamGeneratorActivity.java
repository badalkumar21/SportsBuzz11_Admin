package com.cricker.admin.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.Random;

public class TeamGeneratorActivity extends AppCompatActivity {

    public String team1;
    public String team2;

    int points;
    int MAX_POINTS = 100;

    int NUM_WK = 2;
    int NUM_BT = 11;
    int NUM_AL = 4;
    int NUM_BW = 5;

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
    DatabaseReference mRefXi;

    ArrayList<Integer> arrayListWk = new ArrayList<>();
    ArrayList<Integer> arrayListBt = new ArrayList<>();
    ArrayList<Integer> arrayListAl = new ArrayList<>();
    ArrayList<Integer> arrayListBw = new ArrayList<>();
    ArrayList<ModelSquad> arrayListXi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_generator);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Teams");
        mRefWk = mFirebaseDatabase.getReference("FantasySquad/Team/IndSa21/wk");
        mRefBt = mFirebaseDatabase.getReference("FantasySquad/Team/IndSa21/bt");
        mRefAl = mFirebaseDatabase.getReference("FantasySquad/Team/IndSa21/al");
        mRefBw = mFirebaseDatabase.getReference("FantasySquad/Team/IndSa21/bw");
        mRefXi = mFirebaseDatabase.getReference("FantasySquad/Team/FantasyXi");

        points = 0;
        mRefXi.setValue(null);

        Query queryWk = mRef.child(team1).orderByChild("role").equalTo("wk");
        queryWk.addValueEventListener(new ValueEventListener() {
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

        Query queryBt = mRef.child(team1).orderByChild("role").equalTo("bt");
        queryBt.addValueEventListener(new ValueEventListener() {
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

        Query queryAl = mRef.child(team1).orderByChild("role").equalTo("al");
        queryAl.addValueEventListener(new ValueEventListener() {
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

        Query queryBw = mRef.child(team1).orderByChild("role").equalTo("bw");
        queryBw.addValueEventListener(new ValueEventListener() {
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


        setupRecyclerviewWk();

        setupRecyclerviewBt();

        setupRecyclerviewAl();

        setupRecyclerviewBw();


        generateFantasyXi(getIntent().getExtras().getInt("wk", 1),
                getIntent().getExtras().getInt("bt", 5),
                getIntent().getExtras().getInt("al", 2),
                getIntent().getExtras().getInt("bw", 3));

    }

    private void setupRecyclerviewWk() {

        mRecyclerView_wk = findViewById(R.id.myRecycleView_wk);
        mRecyclerView_wk.setLayoutManager(new GridLayoutManager(this, 4));

        Query query = mRefXi.orderByChild("role").equalTo("wk");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterWk = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());
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

        Query query = mRefXi.orderByChild("role").equalTo("bt");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterBt = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());
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

        Query query = mRefXi.orderByChild("role").equalTo("al");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterAl = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());
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

        Query query = mRefXi.orderByChild("role").equalTo("bw");

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapterBw = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderFantasyXI>(fbOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderFantasyXI viewHolder, int i, @NonNull ModelSquad model) {
                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(), model.getImage());
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


    private void generateFantasyXi(int wk, int bt, int al, int bw) {

        for (int i = 0; i < wk; i++) {
            getWk();
        }

        for (int i = 0; i < bt; i++) {
            getBt();
        }

        for (int i = 0; i < al; i++) {
            getAl();
        }

        for (int i = 0; i < bw; i++) {
            getBw();
        }

    }

    private void getWk() {

        int val = getRandomNumber(NUM_WK);

        while (arrayListWk.contains(val)) {
            val = getRandomNumber(NUM_WK);
        }
//        if (arrayListWk.contains(val)) {
//            getWk();
//        } else {
        arrayListWk.add(val);

        final int finalVal = val;
        mRefWk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (i == finalVal) {
                        ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                        if (points + Squad.getPoints() > MAX_POINTS) {
                            removeHighCreditPlayer();
                            getWk();
                            break;
                        }
                        points += Squad.getPoints();
                        mRefXi.child(Squad.getName()).setValue(Squad);
                        break;
                    } else {
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        }
    }

    private void getBt() {

        int val = getRandomNumber(NUM_BT);

        while (arrayListBt.contains(val)) {
            val = getRandomNumber(NUM_BT);
        }

//        if (arrayListBt.contains(val)) {
//            getBt();
//        } else {
        arrayListBt.add(val);

        final int finalVal = val;
        mRefBt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (i == finalVal) {
                        ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                        if (points + Squad.getPoints() > MAX_POINTS) {
                            removeHighCreditPlayer();
                            getBt();
                            break;
                        }
                        points += Squad.getPoints();
                        mRefXi.child(Squad.getName()).setValue(Squad);
                        break;
                    } else {
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        }
    }

    private void getAl() {

        int val = getRandomNumber(NUM_AL);

        while (arrayListAl.contains(val)) {
            val = getRandomNumber(NUM_AL);
        }

//        if (arrayListAl.contains(val)) {
//            getAl();
//        } else {
        arrayListAl.add(val);

        final int finalVal = val;
        mRefAl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (i == finalVal) {
                        ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                        if (points + Squad.getPoints() > MAX_POINTS) {
                            removeHighCreditPlayer();
                            getAl();
                            break;
                        }
                        points += Squad.getPoints();
                        mRefXi.child(Squad.getName()).setValue(Squad);
                        break;
                    } else {
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        }
    }

    private void getBw() {

        int val = getRandomNumber(NUM_BW);

        while (arrayListBw.contains(val)) {
            val = getRandomNumber(NUM_BW);
        }

//        if (arrayListBw.contains(val)) {
//            getBw();
//        } else {

        arrayListBw.add(val);

        final int finalVal = val;
        mRefBw.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (i == finalVal) {
                        ModelSquad Squad = snapshot.getValue(ModelSquad.class);
                        if (points + Squad.getPoints() > MAX_POINTS) {
                            removeHighCreditPlayer();
                            getBw();
                            break;
                        }
                        points += Squad.getPoints();
                        mRefXi.child(Squad.getName()).setValue(Squad);
                        break;
                    } else {
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        }
    }


    public int getRandomNumber(int bound) {
        return new Random().nextInt(bound);
    }

    public void removeHighCreditPlayer() {
        int size = arrayListXi.size();
        String role = "";
        if (size > 0) {
            role = arrayListXi.get(size - 1).getRole();
            String key = arrayListXi.get(size - 1).getName();
            mRefXi.child(key).removeValue();
            pickPlayer(role);
        }
    }

    private void pickPlayer(String role) {
        switch (role) {
            case "wk":
                getWk();
                break;

            case "bt":
                getBt();
                break;

            case "al":
                getAl();
                break;

            case "bw":
                getBw();
                break;

            default:
                break;
        }
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
