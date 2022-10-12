package tta.edu.culuannamcontrau;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.rpc.context.AttributeContext;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText Username,Email,Pass,Confirm_pass, Idcard,Rfid;
    Button createAccount;
    TextView cancel_signup;
    FirebaseAuth auth;
    FirebaseFirestore db;
    DatabaseReference reference;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Username=findViewById(R.id.etUsernameSignup);
        Email=findViewById(R.id.etEmailSignup);
        Pass=findViewById(R.id.etPassSignup);
        Confirm_pass=findViewById(R.id.etConfirmSignup);
        createAccount=findViewById(R.id.createaccountbtn);
        cancel_signup=findViewById(R.id.tvcancel_signup);
        Idcard=findViewById(R.id.etIdcard);
        Rfid=findViewById(R.id.etRFId);
        reference=FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Pass.getText().toString().trim();
                String confirm_pass = Confirm_pass.getText().toString().trim();
                String idcard = Idcard.getText().toString().trim();
                String rfid = Rfid.getText().toString().trim();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(rfid) || TextUtils.isEmpty(password) || TextUtils.isEmpty(username)){
                    if(TextUtils.isEmpty(email))
                    Email.setError("Email is Required.");
                    if(TextUtils.isEmpty(rfid)){
                        Rfid.setError("RFID is Required.");
                    }
                    if(TextUtils.isEmpty(password)){
                        Pass.setError("Password is Required.");
                    }
                    if(TextUtils.isEmpty(confirm_pass)){
                        Confirm_pass.setError("Password confirm is Required.");
                    }
                    if(TextUtils.isEmpty(username)){
                        Username.setError("User name is Required.");
                    }

                    return;
                }

//                if(TextUtils.isEmpty(password)){
//                    Pass.setError("Password is Required.");
//                    return;
//                }

                if(password.length() < 6){
                    Pass.setError("Password Must be >= 6 Characters");
                    return;
                }
                if(password.equals(confirm_pass)){
                    Map<String, Object> data = new HashMap<>();
                    data.put("remain", 0);
                    db.collection(rfid).document("money").set(data);
                    //Authenticate with new user account (need fix)
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        String[] arrlist = email.split("@");
                                        ID = arrlist[0];
                                        //add new user to firebase, initialize some parameter
                                        reference.child("Users").child(ID).child("name").setValue(username);
                                        reference.child("Users").child(ID).child("address");
                                        reference.child("Users").child(ID).child("phone");
                                        reference.child("Users").child(ID).child("id").setValue(idcard);
                                        reference.child("Users").child(ID).child("rfid").setValue(rfid);


                                        startActivity(new Intent(Signup.this,Login.class));
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Signup.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else{
                    Toast.makeText(Signup.this, "Password mismatch!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancel_signup.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),Login.class)));
    }
}