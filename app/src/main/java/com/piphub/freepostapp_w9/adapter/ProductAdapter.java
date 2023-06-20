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
import com.piphub.freepostapp_w9.model.Product;
import com.piphub.freepostapp_w9.ui.category.FormCategoryActivity;
import com.piphub.freepostapp_w9.ui.product.FormProductActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private List<Product> products;
    private Context context;
    private OnClickListener onClickListener;

    public ProductAdapter(List<Product> products, Context context, OnClickListener onClickListener) {
        this.products = products;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_card_item_layout,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = products.get(position);
        if(item!=null){
            holder.name.setText(item.getName());
            if (item.getStatus().equals("DEL")){
                holder.status.setText("Delete");
                holder.status.setTextColor(context.getResources().getColor(R.color.black));
            }else {
                holder.status.setText("Active");
                holder.status.setTextColor(context.getResources().getColor(R.color.black));
            }
            if(item.getCategory().getId()!=0){
                holder.categoryName.setText("Category : "+item.getCategory().getName().toString());
            }
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FormProductActivity.class);
                    intent.putExtra("ID",item.getId());
                    context.startActivity(intent);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v, item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView image,edit,delete;
        TextView name,status,categoryName;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.ivImageCategory);
            edit = itemView.findViewById(R.id.ivCategoryEdit);
            delete = itemView.findViewById(R.id.ivCategoryDelete);
            name = itemView.findViewById(R.id.tvCategoryName);
            status = itemView.findViewById(R.id.statusCategory);
            categoryName = itemView.findViewById(R.id.statusCategoryMark);
        }
    }
    public interface OnClickListener{
        void onClick(View view, Product item);
    }
}
