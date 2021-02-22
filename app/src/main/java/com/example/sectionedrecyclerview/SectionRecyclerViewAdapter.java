package com.example.sectionedrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SectionRecyclerViewAdapter extends RecyclerView.Adapter<SectionRecyclerViewAdapter.SectionViewHolder> {

    private final Context context;
    private final RecyclerViewType recyclerViewType;
    private final ArrayList<SectionModel> sectionModelArrayList;

    public SectionRecyclerViewAdapter(Context context, RecyclerViewType recyclerViewType, ArrayList<SectionModel> sectionModelArrayList) {
        this.context = context;
        this.recyclerViewType = recyclerViewType;
        this.sectionModelArrayList = sectionModelArrayList;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_custom_row_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        final SectionModel sectionModel = sectionModelArrayList.get(position);
        holder.sectionLabel.setText(sectionModel.getSectionLabel());

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(false);

        /* set layout manager on basis of recyclerview enum type */
        switch (recyclerViewType) {
            case LINEAR_VERTICAL:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case LINEAR_HORIZONTAL:
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.itemRecyclerView.setLayoutManager(linearLayoutManager1);
                break;
            case GRID:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                holder.itemRecyclerView.setLayoutManager(gridLayoutManager);
                break;
        }

        ItemRecyclerViewAdapter adapter = new ItemRecyclerViewAdapter(context, sectionModel.getItemArrayList().subList(0, 4));
        holder.itemRecyclerView.setAdapter(adapter);

        //show toast on click of show all button
        holder.showAllButton.setOnClickListener(v -> {

            ItemRecyclerViewAdapter itemRecyclerViewAdapter;
            if (sectionModel.isExpanded()) {
                sectionModel.setExpanded(false);
                holder.showAllButton.setText(R.string.show_all);
                itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(context, sectionModel.getItemArrayList().subList(0, 4));
            } else {
                sectionModel.setExpanded(true);
                holder.showAllButton.setText(R.string.show_less);
                itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(context, sectionModel.getItemArrayList());
            }
            holder.itemRecyclerView.setAdapter(itemRecyclerViewAdapter);
        });
    }

    @Override
    public int getItemCount() {
        return sectionModelArrayList.size();
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView sectionLabel;
        private final TextView showAllButton;
        private final RecyclerView itemRecyclerView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            sectionLabel = itemView.findViewById(R.id.section_label);
            showAllButton = itemView.findViewById(R.id.section_show_all_button);
            itemRecyclerView = itemView.findViewById(R.id.item_recycler_view);
        }
    }
}
