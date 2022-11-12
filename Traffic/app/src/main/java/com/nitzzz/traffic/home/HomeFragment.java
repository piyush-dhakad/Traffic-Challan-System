package com.nitzzz.traffic.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitzzz.traffic.R;
import com.nitzzz.traffic.models.ReceiptModel;
import com.nitzzz.traffic.sqlite.ReceiptModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private View view;

    @BindView(R.id.card_view_new) CardView add_new;
    @BindView(R.id.txt_no_records_found) TextView txt_no_records_found;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        start();

    }

    private void start(){

        add_new.setOnClickListener(v->{

            startActivity(new Intent(getActivity(), ReceiptAddActivity.class));
        });


        RecyclerView recycler_view = view.findViewById(R.id.recycler_view);

        List<ReceiptModel> dataset = new ArrayList<>();
        ReceiptModule receiptModule = new ReceiptModule(getActivity());
        dataset.addAll(receiptModule.fetch_all_reverse());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager);
        ReceiptAdapter adapter = new ReceiptAdapter(getActivity(), dataset, (position) -> {

            Intent intent = new Intent(getActivity(), ReceiptViewActivity.class);
            intent.putExtra("model", dataset.get(position));
            startActivity(intent);

        });

        recycler_view.setAdapter(adapter);
        recycler_view.setHasFixedSize(true);
        recycler_view.setNestedScrollingEnabled(false);

        adapter.notifyDataSetChanged();


        if(dataset.size() == 0)
            txt_no_records_found.setVisibility(View.VISIBLE);
        else
            txt_no_records_found.setVisibility(View.GONE);

    }


}