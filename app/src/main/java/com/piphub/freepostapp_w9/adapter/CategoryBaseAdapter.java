package com.piphub.freepostapp_w9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.model.Category;

import java.util.List;

public class CategoryBaseAdapter extends BaseAdapter {
    private List<Category> categories;
    private Context context;

    public CategoryBaseAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Category getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View currentView = view;
        if (currentView == null) {
            currentView = LayoutInflater.from(context).inflate(R.layout.spinner_card_item_layout, null);
            CategoryViewHolder holder = new CategoryViewHolder();
            holder.title = currentView.findViewById(R.id.tvTitle);
            holder.title.setText(categories.get(i).getName());
        }
        return currentView;
    }

    public class CategoryViewHolder {
        TextView title;
    }
}
