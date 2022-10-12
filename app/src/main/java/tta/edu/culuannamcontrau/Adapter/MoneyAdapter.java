package tta.edu.culuannamcontrau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tta.edu.culuannamcontrau.R;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {
    List<String>listMoney;
    Context context;

    public MoneyAdapter(List<String> listMoney, Context context) {
        this.listMoney = listMoney;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_money,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String money = listMoney.get(position);
        holder.tvMoney.setText(money);
    }

    @Override
    public int getItemCount() {
        return listMoney.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvMoney;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMoney= itemView.findViewById(R.id.tv_money);
        }
    }
}
