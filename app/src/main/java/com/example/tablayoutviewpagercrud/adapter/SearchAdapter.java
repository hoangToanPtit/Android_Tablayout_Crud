package com.example.tablayoutviewpagercrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutviewpagercrud.R;
import com.example.tablayoutviewpagercrud.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<Cat> mSearch;

    public SearchAdapter() {
        mSearch = new ArrayList<>();
    }

    public List<Cat> getList() {
        return mSearch;
    }

    public void setmSearch(List<Cat> mSearch) {
        this.mSearch = mSearch;
    }

    public void setListCat(List<Cat> mSearch){
        this.mSearch = mSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Cat cat = mSearch.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice()+"");
        holder.infor.setText(cat.getInfor());
    }

    @Override
    public int getItemCount() {
        return mSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, price, infor;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            infor = itemView.findViewById(R.id.item_desc);
        }
    }
}
