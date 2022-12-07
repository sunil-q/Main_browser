package com.fastspeed.five5gbrowser.browser_bholu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.ItemHistoryBholuBinding;

import java.util.ArrayList;
import java.util.List;

public class BholuHistoryAdapter extends RecyclerView.Adapter<BholuHistoryAdapter.HistryViewHolderBholu> {


    private List<String> listBholu = new ArrayList<>();
    private OnHistoryClickListnearBholu onHistoryClickListnearBholu;

    public BholuHistoryAdapter(List<String> list, OnHistoryClickListnearBholu onHistoryClickListnear) {
        this.listBholu = list;
        this.onHistoryClickListnearBholu = onHistoryClickListnear;
    }

    @NonNull
    @Override
    public HistryViewHolderBholu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewBholu = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_bholu, parent, false);
        return new HistryViewHolderBholu(viewBholu);
    }

    @Override
    public void onBindViewHolder(@NonNull HistryViewHolderBholu holder, int position) {
        String urlBholu = listBholu.get(position);
        holder.binding.tvHistoryBholu.setText(urlBholu);
        String[] trim1Bholu = urlBholu.split("\\.");
        Log.d("TAG", urlBholu);

        if (trim1Bholu.length >= 2) {
            Log.d("TAG", trim1Bholu[0]);
            Log.d("TAG", "onBindViewHolder: " + trim1Bholu[1] + "=============================\n");
            String str = trim1Bholu[1];
            holder.binding.tvNameBholu.setText(str);
        } else {
            holder.binding.tvNameBholu.setText("Blank");
        }


        holder.binding.btnremoveBholu.setOnClickListener(v -> onHistoryClickListnearBholu.onHistoryClick(position, urlBholu, "DELETE"));
        holder.itemView.setOnClickListener(v -> onHistoryClickListnearBholu.onHistoryClick(position, urlBholu, "OPEN"));
    }

    @Override
    public int getItemCount() {
        return listBholu.size();
    }

    public interface OnHistoryClickListnearBholu {
        void onHistoryClick(int position, String url, String work);
    }

    public class HistryViewHolderBholu extends RecyclerView.ViewHolder {
        ItemHistoryBholuBinding binding;

        public HistryViewHolderBholu(@NonNull View itemView) {
            super(itemView);
            binding = ItemHistoryBholuBinding.bind(itemView);
        }
    }
}
