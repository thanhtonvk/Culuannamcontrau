package tta.edu.culuannamcontrau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PassChangeSuccess extends AppCompatActivity {
    Button returnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_change_success);
        returnHome=findViewById(R.id.return_home_btn);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassChangeSuccess.this,class1.class));
            }
        });
    }
}