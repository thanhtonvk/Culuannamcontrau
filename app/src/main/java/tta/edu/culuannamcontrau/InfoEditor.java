package tta.edu.culuannamcontrau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoEditor extends AppCompatActivity {
    TextView cancel,save;
    EditText Name,Idcard,Phone,Address;
    DatabaseReference reference;
    itemList item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_editor);
        Name=findViewById(R.id.nameEdit);
        Idcard=findViewById(R.id.idcardEdit);
        Phone=findViewById(R.id.phoneEdit);
        Address=findViewById(R.id.addressEdit);
        cancel=findViewById(R.id.tvcancel);
        save=findViewById(R.id.tvsave);
        item=new itemList();

        reference = FirebaseDatabase.getInstance().getReference("Users").child(item.getMailname());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    // user name show
                    Name.setText(snapshot.child("name").getValue().toString(), TextView.BufferType.EDITABLE);
                    // id card show
                    Idcard.setText(snapshot.child("id").getValue().toString(), TextView.BufferType.EDITABLE);
                    // address
                    if(!snapshot.child("address").exists()){
//                        Address.setText("", TextView.BufferType.EDITABLE); //blank box when no data
                        Address.setHint("Enter your address.");
                    } else{
                        Address.setText(snapshot.child("address").getValue().toString(), TextView.BufferType.EDITABLE);
                    }

                    // phone number
                    if(!snapshot.child("phone").exists()){
                        Phone.setHint("Enter your phone number.");
                    } else{
                        Phone.setText(snapshot.child("phone").getValue().toString(), TextView.BufferType.EDITABLE);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //cancel edit data
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoEditor.this,UserInfo.class));
            }
        });
        //save edit data
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("name").setValue(Name.getText().toString());
                reference.child("id").setValue(Idcard.getText().toString());
                reference.child("phone").setValue(Phone.getText().toString());
                reference.child("address").setValue(Address.getText().toString());
                startActivity(new Intent(InfoEditor.this,UserInfo.class));
            }
        });

    }
}