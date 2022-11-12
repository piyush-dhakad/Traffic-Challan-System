package com.nitzzz.traffic.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nitzzz.traffic.R;
import com.nitzzz.traffic.models.ReceiptModel;
import com.nitzzz.traffic.models.RuleModel;
import com.nitzzz.traffic.utility.NumberFormat;

import java.util.List;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder>{

    private Context context;
    private List<ReceiptModel> dataset;
    private Events events;

    public interface Events{

        void click(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_customer, txt_fine_amt , txt_mobile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_customer = itemView.findViewById(R.id.txt_customer);
            txt_fine_amt = itemView.findViewById(R.id.txt_fine_amt);
            txt_mobile = itemView.findViewById(R.id.txt_mobile);

        }

    }

    public ReceiptAdapter(Context context, List<ReceiptModel> dataset, ReceiptAdapter.Events events) {

        this.context = context;
        this.dataset = dataset;
        this.events = events;
    }

    @NonNull
    @Override
    public ReceiptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_receipts, parent, false);
        return new ReceiptAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.ViewHolder holder, int position) {

        holder.txt_customer.setText(dataset.get(position).full_name);
        holder.txt_mobile.setText( NumberFormat.mobile_number_format("+91"+ dataset.get(position).mobile));
        holder.txt_fine_amt.setText("Fine Amt : ₹" + NumberFormat.to_2d(dataset.get(position).total_fine));

        holder.itemView.setOnClickListener(v-> events.click(position));
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