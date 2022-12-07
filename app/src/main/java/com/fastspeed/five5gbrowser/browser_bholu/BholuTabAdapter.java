package com.fastspeed.five5gbrowser.browser_bholu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.ItemTabsBholuBinding;

import java.util.List;

public class BholuTabAdapter extends RecyclerView.Adapter<BholuTabAdapter.TabViewHolderBholu> {
    private List<BholuBrowserFragment> websitesBholu;
    private OnTabItemClickListnearBholu onTabItemClickListnearBholu;
    private Context contextBholu;

    public BholuTabAdapter(List<BholuBrowserFragment> websites, OnTabItemClickListnearBholu onTabItemClickListnear) {

        this.websitesBholu = websites;
        this.onTabItemClickListnearBholu = onTabItemClickListnear;
    }

    @NonNull
    @Override
    public TabViewHolderBholu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contextBholu = parent.getContext();
        View viewBholu = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tabs_bholu, parent, false);
        return new TabViewHolderBholu(viewBholu);
    }

    @Override
    public void onBindViewHolder(@NonNull TabViewHolderBholu holder, int position) {

        BholuBrowserFragment modelBholu = websitesBholu.get(position);
        Log.d("web tablistadapter", "onBindViewHolder: ");
        Log.d("web tablistadapter", "onBindViewHolder: " + modelBholu.getTitleBholu());
        holder.binding.tvWebsiteBholu.setText(modelBholu.getTitleBholu());
        try {
            Glide.with(contextBholu)
                    .asBitmap()
                    .load(modelBholu.getBitmapBholu())
                    .centerCrop()
                    .into(holder.binding.imageBholu);
        } catch (Exception e) {
            Log.d("web ", "onBindViewHolder: cresh " + e.toString());
            e.printStackTrace();
        }
        holder.binding.imageBholu.setOnClickListener(v -> onTabItemClickListnearBholu.onTabitemClick(modelBholu, position, "OPEN"));
        holder.binding.closeBholu.setOnClickListener(v -> onTabItemClickListnearBholu.onTabitemClick(modelBholu, position, "CLOSE"));
    }

    @Override
    public int getItemCount() {
        return websitesBholu.size();
    }

    public interface OnTabItemClickListnearBholu {
        void onTabitemClick(BholuBrowserFragment websiteModel, int pos, String work);
    }

    public class TabViewHolderBholu extends RecyclerView.ViewHolder {
        ItemTabsBholuBinding binding;

        public TabViewHolderBholu(@NonNull View itemView) {
            super(itemView);
            binding = ItemTabsBholuBinding.bind(itemView);
        }
    }
}
