package tta.edu.culuannamcontrau;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tta.edu.culuannamcontrau.Adapter.MoneyAdapter;

public class LoadMoneyActivity extends AppCompatActivity {

    List<String> moneyList;
    MoneyAdapter adapter;
    RecyclerView rcvMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_money);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }
        rcvMoney = findViewById(R.id.rcv_money);
        moneyList = new ArrayList<>();
        moneyList.add("50.000đ");
        moneyList.add("100.000đ");
        moneyList.add("200.000đ");
        moneyList.add("500.000đ");
        moneyList.add("1.000.000đ");
        adapter = new MoneyAdapter(moneyList, this);
        rcvMoney.setAdapter(adapter);
        findViewById(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LinkBankActivity.class));
            }
        });
    }
}