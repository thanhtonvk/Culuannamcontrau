package tta.edu.culuannamcontrau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tta.edu.culuannamcontrau.Model.Bank;
import tta.edu.culuannamcontrau.R;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {
    List<Bank> bankList;
    Context context;
    Activity activity;
    int layout;

    public BankAdapter(List<Bank> bankList, Context context, Activity activity, int layout) {
        this.bankList = bankList;
        this.context = context;
        this.layout = layout;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bank bank = bankList.get(position);
        holder.tvName.setText(bank.getName());
        holder.imgBank.setImageResource(bank.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBank;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBank = itemView.findViewById(R.id.img_name);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
