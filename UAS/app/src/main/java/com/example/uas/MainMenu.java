package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {
    private Button menu_logout;
    private FloatingActionButton menu_add;
    AdapterLaporan adapterLaporan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelLaporan> listLaporan;
    RecyclerView recyclerView;
    DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        menu_logout = (Button) findViewById(R.id.Menu_logout);
        menu_add = (FloatingActionButton) findViewById(R.id.Menu_add);
        menu_logout.setOnClickListener(this);
        menu_add.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyler);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greeting = (TextView) findViewById(R.id.Menu_user);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullname = userProfile.username;

                    greeting.setText("Welcome " + fullname + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainMenu.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
            }
        });

        showData();
    }

    private void showData() {
        database.child("Laporan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listLaporan = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    ModelLaporan lpr = item.getValue(ModelLaporan.class);
                    lpr.setKey(item.getKey());
                    listLaporan.add(lpr);
                }
                adapterLaporan = new AdapterLaporan(listLaporan, MainMenu.this);
                recyclerView.setAdapter(adapterLaporan);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Menu_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu.this, MainActivity.class));
            case R.id.Menu_add:
                startActivity(new Intent(MainMenu.this, TambahData.class));

        }
    }
}