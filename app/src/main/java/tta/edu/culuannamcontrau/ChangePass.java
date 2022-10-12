package tta.edu.culuannamcontrau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePass extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    EditText et_old_pass, et_new_pass,et_confirmPass;
    Button resetPass_btn;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Init();

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        resetPass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            changePassword();

            }
        });
    }
    public void Init(){
        et_old_pass=findViewById(R.id.oldpass_input);
        et_new_pass=findViewById(R.id.newpass_input);
        resetPass_btn = findViewById(R.id.resetpassbtn);
        et_confirmPass =findViewById(R.id.newpassconfirm_input);
    }
    private void changePassword()   {
        String oldpass_str=et_old_pass.getText().toString().trim();
        String newpass_str=et_new_pass.getText().toString().trim();
        String confirmPass =et_confirmPass.getText().toString().trim();

        if(oldpass_str.length() != 0 && newpass_str.length() != 0 && confirmPass.length() != 0){
            // check if pass is >=6 characters
            if(newpass_str.length() < 6){
                Toast.makeText(ChangePass.this, "Password Must be >= 6 Characters", Toast.LENGTH_SHORT).show();
                return;
            }
            //confirm  entered pass
            if(newpass_str.equals(confirmPass)){
                if(user != null && user.getEmail() != null){
                    AuthCredential credential = EmailAuthProvider
                            .getCredential(user.getEmail(), oldpass_str);

                    // Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        user.updatePassword(newpass_str)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            startActivity(new Intent(ChangePass.this,PassChangeSuccess.class));
                                                        }
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(ChangePass.this,"Wrong current password ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } else {
                    startActivity(new Intent(ChangePass.this,Login.class) );
                    finish();
                }
            }else {
                Toast.makeText(this,"Password mismatching!",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Please enter all the fields!",Toast.LENGTH_SHORT).show();
        }
    }
}