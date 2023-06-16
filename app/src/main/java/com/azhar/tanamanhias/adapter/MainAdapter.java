package com.azhar.tanamanhias.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.tanamanhias.R;
import com.azhar.tanamanhias.activities.DetailActivity;
import com.azhar.tanamanhias.model.ModelMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azhar Rivaldi on 31-05-2023
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements Filterable {

    private Context context;
    private List<ModelMain> modelMainList;
    private List<ModelMain> modelMainFilterList;

    @Override
    public Filter getFilter() {
        return modelFilter;
    }

    private Filter modelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ModelMain> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(modelMainFilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ModelMain modelMainFilter : modelMainFilterList) {
                    if (modelMainFilter.getNama().toLowerCase().contains(filterPattern)) {
                        filteredList.add(modelMainFilter);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelMainList.clear();
            modelMainList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public MainAdapter(Context context, List<ModelMain> modelMain) {
        this.context = context;
        this.modelMainList = modelMain;
        this.modelMainFilterList = new ArrayList<>(modelMain);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        ModelMain item = modelMainList.get(position);

        holder.tvNamaTanaman.setText(item.getNama());

        //send data to detail activity
        holder.cvListTanaman.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.DETAIL_TANAMAN, modelMainList.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return modelMainList.size();
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        CardView cvListTanaman;
        TextView tvNamaTanaman;

        public MainViewHolder(View itemView) {
            super(itemView);
            cvListTanaman = itemView.findViewById(R.id.cvListTanaman);
            tvNamaTanaman = itemView.findViewById(R.id.tvNamaTanaman);
        }
    }

}
