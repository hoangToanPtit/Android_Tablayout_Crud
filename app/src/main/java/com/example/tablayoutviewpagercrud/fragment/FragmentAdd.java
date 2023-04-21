package com.example.tablayoutviewpagercrud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tablayoutviewpagercrud.MainActivity;
import com.example.tablayoutviewpagercrud.R;
import com.example.tablayoutviewpagercrud.adapter.CatAdapter;
import com.example.tablayoutviewpagercrud.adapter.SpinnerAdapter;
import com.example.tablayoutviewpagercrud.model.Cat;

public class FragmentAdd extends Fragment implements CatAdapter.CatItemListener{
    private CatAdapter adapter;
    private Spinner spinner;
    private EditText editName, editPrice, editInfor;
    private Button btAdd, btUpdate;
    private RecyclerView recyclerView;
    private int pCurrent;
    private int[] imgs = {R.drawable.img, R.drawable.img_1, R.drawable.img_2};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new CatAdapter((MainActivity) getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img=imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), price, editInfor.getText().toString());
                    adapter.add(cat);

                } catch(NumberFormatException e){

                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img=imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), price, editInfor.getText().toString());
                    adapter.update(pCurrent, cat);
                    btUpdate.setVisibility(View.INVISIBLE);
                } catch(NumberFormatException e){

                }
            }
        });
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), imgs);
        spinner.setAdapter(adapter);

        editName = view.findViewById(R.id.editName);
        editPrice = view.findViewById(R.id.editPrice);
        editInfor = view.findViewById(R.id.editDesc);
        btAdd = view.findViewById(R.id.btAdd);
        btUpdate = view.findViewById(R.id.btUpdate);
        recyclerView = view.findViewById(R.id.reView);
        btUpdate.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setVisibility( View.INVISIBLE);
        btUpdate.setVisibility( View.VISIBLE);
        pCurrent = position;
        Cat cat = adapter.getItem(position);
        Toast.makeText(getContext(), cat.toString(), Toast.LENGTH_SHORT).show();
        int img = cat.getImg();
        int p = 0;
        for(int i=0; i<imgs.length; i++) {
            if(img==imgs[i]){
                if (img==imgs[i]){
                    p=i; break;
                }
            }
        }
        spinner.setSelection(p);
        editName.setText(cat.getName());
        editPrice.setText(cat.getPrice()+"");
        editInfor.setText(cat.getInfor());
    }
}
