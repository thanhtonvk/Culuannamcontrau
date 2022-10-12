package tta.edu.culuannamcontrau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class UserInfo extends AppCompatActivity {
    TextView username, idcard,address,phone,rfid;
    Toolbar toolbar;
    Button editinfo;
    itemList item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Init();
        item=new itemList();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(item.getMailname());

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserInfo.this,class1.class));
                finish();
            }
        });
        // Name of user
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // user name show
                    username.setText(snapshot.child("name").getValue().toString());
                    // id card show
                    idcard.setText(snapshot.child("id").getValue().toString());
                    // address
//                    String Address = ;
                    if(!snapshot.child("address").exists()){
                        address.setText(""); //blank box when no data
                    } else{
                        address.setText(snapshot.child("address").getValue().toString());
                    }

                    // phone number
                    if(!snapshot.child("phone").exists()){
                        phone.setText(""); //blank box when no data
                    } else{
                        phone.setText(snapshot.child("phone").getValue().toString());
                    }
                    // rfid
                    rfid.setText(snapshot.child("rfid").getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //edit info press
        editinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInfo.this,InfoEditor.class));
            }
        });


    }

    public void Init() {
        username=findViewById(R.id.nameinfo);
        idcard=findViewById(R.id.idinfo);
        address=findViewById(R.id.addressinfo);
        phone=findViewById(R.id.phoneinfo);
        rfid=findViewById(R.id.tvRfid);
        editinfo=findViewById(R.id.editbtn);

    }
}