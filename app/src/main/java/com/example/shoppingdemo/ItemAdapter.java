package com.example.shoppingdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<DbTable> shoppingItems;

    public ItemAdapter(List<DbTable> items)
    {
        this.shoppingItems = items;
    }

    public void setData(List<DbTable> items)
    {
        this.shoppingItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DbTable dbTable = shoppingItems.get(position);
        holder.txtItemName.setText(dbTable.item);
        holder.txtItemQuantity.setText("Quantity : " + dbTable.quantity);

    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtItemName;
        TextView txtItemQuantity;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_item_name);
            txtItemQuantity = itemView.findViewById(R.id.txt_item_quantity);
            imageView = itemView.findViewById(R.id.img_delete);
            imageView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DbTable dbTable = shoppingItems.get(position);
            ItemRepository itemRepository = new ItemRepository(v.getContext());
            itemRepository.deleteItem(dbTable);
            Toast.makeText(v.getContext(), "deleted !", Toast.LENGTH_SHORT).show();
        }
    }
}
