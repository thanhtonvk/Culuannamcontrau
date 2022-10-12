package tta.edu.culuannamcontrau;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tta.edu.culuannamcontrau.Adapter.BankAdapter;
import tta.edu.culuannamcontrau.Model.Bank;

public class ChooseBankActivity extends AppCompatActivity {
    List<Bank> bankList;
    RecyclerView rcvGridBank, rcvBank;
    BankAdapter bankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_bank);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }
        bankList = new ArrayList<>();
        bankList.add(new Bank("National card", R.drawable.thequocte));
        bankList.add(new Bank("Vietcombank", R.drawable.vietcombank));
        bankList.add(new Bank("Vietinbank", R.drawable.viettinbank));
        bankList.add(new Bank("Agribank", R.drawable.agribank));
        bankList.add(new Bank("Thẻ quốc tế", R.drawable.thequocte));
        bankList.add(new Bank("Vietcombank", R.drawable.vietcombank));
        bankList.add(new Bank("Vietinbank", R.drawable.viettinbank));
        bankList.add(new Bank("Agribank", R.drawable.agribank));

        bankAdapter = new BankAdapter(bankList, this,this, R.layout.item_grid_bank);
        rcvGridBank = findViewById(R.id.rcv_grid_bank);
        rcvGridBank.setLayoutManager(new GridLayoutManager(this, 4));
        rcvGridBank.setAdapter(bankAdapter);

        BankAdapter bankListAdapter = new BankAdapter(bankList, this,this, R.layout.item_list_bank);
        rcvBank = findViewById(R.id.rcv_bank);
        rcvBank.setAdapter(bankListAdapter);
    }
}