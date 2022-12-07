package com.fastspeed.five5gbrowser.browser_bholu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.ItemBookmarkBholuBinding;

import java.util.List;

public class BholuBookamrksAdapter extends RecyclerView.Adapter<BholuBookamrksAdapter.BookmarkViewHolderBholu> {
    private List<String> listBholu;
    private OnBookmarkClickListnerBholu onBookmarkClickListnerBholu;

    public BholuBookamrksAdapter(List<String> list, OnBookmarkClickListnerBholu onBookmarkClickListner) {

        this.listBholu = list;
        this.onBookmarkClickListnerBholu = onBookmarkClickListner;
    }

    @NonNull
    @Override
    public BookmarkViewHolderBholu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewBholu = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark_bholu, parent, false);
        return new BookmarkViewHolderBholu(viewBholu);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolderBholu holder, int position) {
        String urlBholu = listBholu.get(position);
        holder.binding.tvBookmarkBholu.setText(urlBholu);
        Log.d("TAG", urlBholu);
        String[] trim1Bholu = urlBholu.split("//");
        Log.d("TAG", trim1Bholu[0]);
        Log.d("TAG", trim1Bholu[1]);
        String str = trim1Bholu[1].split("/")[0];
        Log.d("TAG", str);
        String[] c = str.split("\\.");
        Log.d("TAG", c[1]);
        holder.binding.tvNameBholu.setText(str);
        holder.binding.tvLetterBholu.setText(String.valueOf(c[1].charAt(0)).toUpperCase());


        holder.binding.btnremoveBholu.setOnClickListener(v -> onBookmarkClickListnerBholu.onBookmarkClick(position, urlBholu, "DELETE"));
        holder.itemView.setOnClickListener(v -> onBookmarkClickListnerBholu.onBookmarkClick(position, urlBholu, "OPEN"));
    }

    @Override
    public int getItemCount() {
        return listBholu.size();
    }

    public interface OnBookmarkClickListnerBholu {
        void onBookmarkClick(int position, String url, String work);
    }

    public class BookmarkViewHolderBholu extends RecyclerView.ViewHolder {
        ItemBookmarkBholuBinding binding;

        public BookmarkViewHolderBholu(@NonNull View itemView) {
            super(itemView);
            binding = ItemBookmarkBholuBinding.bind(itemView);
        }
    }
}
