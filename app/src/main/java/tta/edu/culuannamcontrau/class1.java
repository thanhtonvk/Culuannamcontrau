package tta.edu.culuannamcontrau;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class class1 extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Intent intent,intent1;
    TextView Username, remainder;
    itemList item;
    FirebaseFirestore db;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class1);
        InitWidget();
        Init();
        HanlerEvents();
        item=new itemList();
        progressBar=findViewById(R.id.progressBar);
        db = FirebaseFirestore.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        //user money remain
        reference.child(item.getMailname()).child("rfid").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String rfid_info = snapshot.getValue(String.class);
                db.collection(rfid_info).document("money").addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot snapshot,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null){
                            Log.e("FireStore error",e.getMessage());
                            return;}

                        if (snapshot != null && snapshot.exists()) {
                            String remain="Money remain: "+snapshot.getLong("remain");
                            if(remain.equals(null)){
                                progressBar.setVisibility(View.VISIBLE);
                            }else {
                                progressBar.setVisibility(View.INVISIBLE);
                                remainder.setText(remain);
                                item.setRfid(rfid_info);
                            }

                        } else {
                            Toast.makeText(class1.this, "Current data: null", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //User name
        reference.child(item.getMailname()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String user_name = "Welcome, "+snapshot.getValue(String.class);
                    Username.setText(user_name);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        //remainder.setText(item.getRfid());


    }

    private void HanlerEvents() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Init() {
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.Open,R.string.Close);
        toggle.syncState();;
    }

    private void InitWidget() {
        remainder=findViewById(R.id.txtmoney);
        Username=findViewById(R.id.username);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawablelayout);
        navigationView=findViewById(R.id.navigationview);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                intent1=new Intent(this,class1.class);
//                intent1.putExtra("User",user);
                startActivity(intent1);
                break;
            case R.id.changepass:
                intent1=new Intent(this,ChangePass.class);
//                intent1.putExtra("User",user);
                startActivity(intent1);
                break;
            case R.id.info:
                intent1=new Intent(this,UserInfo.class);
//                intent1.putExtra("User",user);
                startActivity(intent1);
                break;
            case R.id.history:
                intent1=new Intent(this,History.class);
//                intent1.putExtra("User",user);
                startActivity(intent1);
                break;
            case R.id.logout:
                startActivity(new Intent(this,Login.class));break;

        }
        return true;
    }
}
