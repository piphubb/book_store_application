package com.piphub.freepostapp_w9.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.piphub.freepostapp_w9.R;
import com.piphub.freepostapp_w9.model.Category;
import com.piphub.freepostapp_w9.ui.category.FormCategoryActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{
    private List<Category> categoryResponseItems;
    private Context context;
    private OnClickListener onClickListener;

    public CategoryAdapter(List<Category> categoryResponseItems, Context context, OnClickListener onClickListener) {
        this.categoryResponseItems = categoryResponseItems;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_card_item_layout,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category item = categoryResponseItems.get(position);
        if(item!=null){
            holder.name.setText(item.getName());
            if (item.getStatus().equals("DEL")){
                holder.status.setText("Delete");
                holder.status.setTextColor(context.getResources().getColor(R.color.black));
            }else {
                holder.status.setText("Active");
                holder.status.setTextColor(context.getResources().getColor(R.color.black));
            }
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FormCategoryActivity.class);
                    intent.putExtra("ID",item.getId());
                    context.startActivity(intent);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v,item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categoryResponseItems.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView image,edit,delete;
        TextView name,status;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivImageCategory);
            edit = itemView.findViewById(R.id.ivCategoryEdit);
            delete = itemView.findViewById(R.id.ivCategoryDelete);
            name = itemView.findViewById(R.id.tvCategoryName);
            status = itemView.findViewById(R.id.statusCategory);
        }
    }
    public interface OnClickListener{
        void onClick(View view, Category item);
    }
}
