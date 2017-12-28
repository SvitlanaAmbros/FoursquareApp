package com.example.admin.foresquareapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.foresquareapp.R;
import com.example.admin.foresquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foresquareapp.dto_place_info.Place;
import com.example.admin.foresquareapp.interfaces.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 25.12.2017.
 */

public class RVPlaceAdapter extends RecyclerView.Adapter<RVPlaceAdapter.PlaceViewHolder>{
    private List<Place> listPlace;
    private List<PlaceInfo> additionalInfoPlace;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public RVPlaceAdapter(List<Place> listPlace, List<PlaceInfo> additionalInfoPlace,
                          OnItemClickListener onItemClickListener) {
        this.listPlace = listPlace;
        this.additionalInfoPlace = additionalInfoPlace;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        context = parent.getContext();

        return new PlaceViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        String imgURL = listPlace.get(position).getCategories().get(0).getIcon().getPrefix() +
                "100" +
                listPlace.get(position).getCategories().get(0).getIcon().getSuffix();

        Glide.with(context)
                .load(imgURL)
                .into(holder.imgPlace);

        holder.tvNamePlace.setText(listPlace.get(position).getName());
        StringBuilder stringBuilderPrice = new StringBuilder();
        stringBuilderPrice.append(listPlace.get(position).getCategories().get(0).getPluralName());

//        if(additionalInfoPlace.get(position).getPrice().getCurrency() != null
//                && additionalInfoPlace.get(position).getPrice().getCurrency().length() != 0) {
//                stringBuilderPrice.append(", ").append(additionalInfoPlace.get(position).getPrice().getCurrency());
//        }
        holder.tvPrice.setText(stringBuilderPrice.toString());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(listPlace.get(position).getLocation().getDistance());

        if (listPlace.get(position).getLocation().getAddress() != null
                && listPlace.get(position).getLocation().getAddress().length() != 0) {
            stringBuilder.append(" m,").append(listPlace.get(position).getLocation().getAddress());
        }

        holder.tvAddress.setText(stringBuilder.toString());
        holder.tvRate.setText(additionalInfoPlace.get(position).getRating() + "");
//        if(additionalInfoPlace.get(position).getRatingColor().i) {
        if (additionalInfoPlace.get(position).getRatingColor() != null
                && additionalInfoPlace.get(position).getRatingColor().length() != 0) {
            holder.tvRate.setBackgroundColor(Color.parseColor("#" + additionalInfoPlace.get(position).getRatingColor()));
        }
//            holder.tvRate.setText(additionalInfoPlace.get(position).getRatingColor());
//        }

    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.img_place)
        ImageView imgPlace;
        @BindView(R.id.tv_name_place)
        TextView tvNamePlace;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_rate)
        TextView tvRate;

        private OnItemClickListener onItemClickListener;

        PlaceViewHolder(View view, OnItemClickListener listener) {
            super(view);
            this.onItemClickListener = listener;
            view.setOnClickListener(this);

            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
