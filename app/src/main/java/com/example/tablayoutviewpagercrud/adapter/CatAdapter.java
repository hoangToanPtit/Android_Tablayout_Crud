package com.example.tablayoutviewpagercrud.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutviewpagercrud.MainActivity;
import com.example.tablayoutviewpagercrud.R;
import com.example.tablayoutviewpagercrud.model.Cat;

import java.util.*;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder>{
    private List<Cat> mList;
    private MainActivity mainActivity;
    private CatItemListener itemListener;

    public CatAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mList = new ArrayList<>();
    }

    public List<Cat> getmList() {
        return mList;
    }

    public void setmList(List<Cat> mList) {
        this.mList = mList;
    }

    public CatItemListener getItemListener() {
        return itemListener;
    }

    public void setItemListener(CatItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public Cat getItem(int position){
        return mList.get(position);
    }

    public List<Cat> getListCat(){
        return mList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        holder.img.setImageResource(cat.getImg());
        holder.name.setText(cat.getName());
        holder.price.setText(cat.getPrice()+"");
        holder.infor.setText(cat.getInfor());
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thong bao xoa");
                builder.setMessage("Ban co chac chan muon xoa" + cat.getName() + "khong?");
                builder.setIcon(R.drawable.ic_remove);
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mList.remove(position);
                        notifyDataSetChanged();
                        mainActivity.list = mList;
                    }
                });
                builder.setNegativeButton("khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView name, price, infor;
        Button btRemove;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            infor = itemView.findViewById(R.id.item_desc);
            btRemove = itemView.findViewById(R.id.item_btRemove);
            btRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println("abc");
            Toast.makeText(view.getContext(), "abc", Toast.LENGTH_SHORT).show();
            if(itemListener!=null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface CatItemListener {
        void onItemClick(View view, int position);
    }

    public void add(Cat cat) {
        mList.add(cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }

    public void update(int position, Cat cat) {
        mList.set(position, cat);
        notifyDataSetChanged();
        mainActivity.list = mList;
    }
}
