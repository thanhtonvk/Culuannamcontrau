package tta.edu.culuannamcontrau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   Context context;
   ArrayList<itemList> list;

    public MyAdapter(Context context, ArrayList<itemList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        itemList itemlist= list.get(position);

        holder.busstop.setText(itemlist.getBusstop());
        holder.time.setText(itemlist.getTime());
        holder.cost.setText(String.valueOf(itemlist.getCost()));
//        holder.busstop.setText(String.valueOf(itemlist.getBusstop()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView busstop,cost,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            busstop = itemView.findViewById(R.id.tvbustopnumber);
            time = itemView.findViewById(R.id.tvtime);
            cost = itemView.findViewById(R.id.tvcost);
        }
    }
}
