package com.nitzzz.traffic.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nitzzz.traffic.R;
import com.nitzzz.traffic.models.RuleModel;

import java.util.List;

public class RuleListAdapter extends RecyclerView.Adapter<RuleListAdapter.ViewHolder>{

    private Context context;
    private List<RuleModel> dataset;
    private Events events;
    public boolean read_only = false;

    public interface Events{

        void check_change(int position, boolean chk);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_name, txt_fine;
        CheckBox chk_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_fine = itemView.findViewById(R.id.txt_fine);
            chk_name = itemView.findViewById(R.id.chk_name);

            clicks();

        }

        private void clicks(){

            chk_name.setOnCheckedChangeListener((buttonView, isChecked) -> {
                events.check_change(getAdapterPosition(), isChecked);
            });
        }

    }

    public RuleListAdapter(Context context, List<RuleModel> dataset, Events events) {

        this.context = context;
        this.dataset = dataset;
        this.events = events;
    }

    @NonNull
    @Override
    public RuleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_rules_list, parent, false);
        return new RuleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RuleListAdapter.ViewHolder holder, int position) {

        holder.txt_name.setText(dataset.get(position).rule);
        holder.chk_name.setText(dataset.get(position).rule);
        holder.txt_fine.setText("Fine : ₹" + dataset.get(position).fine);


        if(read_only){

            holder.txt_name.setVisibility(View.VISIBLE);
            holder.chk_name.setVisibility(View.GONE);
        }else{

            holder.chk_name.setChecked(dataset.get(position).chk);
            holder.chk_name.setVisibility(View.VISIBLE);
            holder.txt_name.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}