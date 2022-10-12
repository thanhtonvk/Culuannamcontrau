package tta.edu.culuannamcontrau;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    EditText Email, Password;
    Button btnlogin;
    FirebaseAuth fAuth;
    String mailname;
    DatabaseReference mDatabase;
    CheckBox rememberMe;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    TextView signup;
    itemList item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup = findViewById(R.id.tvsignup);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.Password);
        btnlogin = findViewById(R.id.Login);
        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        rememberMe = findViewById(R.id.rememberme);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        item = new itemList();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            Email.setText(loginPreferences.getString("username", ""));
            Password.setText(loginPreferences.getString("password", ""));
            rememberMe.setChecked(true);

        }
        CheckInternet();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (rememberMe.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", email);
                    loginPrefsEditor.putString("password", password);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                if(){
//                    Password.setError("Password is Required.");
//                    return;
//                }

                if (password.length() < 6) {
                    Toast.makeText(Login.this, "Password Must be >= 6 Characters", Toast.LENGTH_SHORT).show();
//                    Password.setError("Password Must be >= 6 Characters");
                    return;
                }

                // authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String[] arrlist = email.split("@");
                            mailname = arrlist[0];
                            item.setMailname(mailname);
                            //retrieve initial rfid


                            startActivity(new Intent(getApplicationContext(), class1.class));
                            finish();

                        } else {
                            Toast.makeText(Login.this, "Email is invalid or Password is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Sign up new account
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
    }

    private void CheckInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()
//                == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
//                == NetworkInfo.State.CONNECTED) {
//        }

        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()
                != NetworkInfo.State.CONNECTED && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
                != NetworkInfo.State.CONNECTED) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }
}