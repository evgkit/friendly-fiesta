package com.evgkit.simpleandroidapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.evgkit.simpleandroidapp.R;
import com.evgkit.simpleandroidapp.model.Cat;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatHolder> {
    private final List<Cat> cats;

    @NonNull
    private final OnItemClickListener onItemClickListener;

    @NonNull
    private final View.OnClickListener internalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cat cat = (Cat) v.getTag();
            if (null != cat) {
                int position = cats.indexOf(cat);
                onItemClickListener.onItemClick(cat, position);
            }
        }
    };

    public CatsAdapter(List<Cat> cats, @NonNull OnItemClickListener onItemClickListener) {
        this.cats = cats;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CatHolder(inflater.inflate(R.layout.item_cat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatHolder holder, int position) {
        Cat cat = cats.get(position);
        holder.catImage.setOnClickListener(internalClickListener);
        holder.catImage.setTag(cat);
        Picasso.get().load(cat.getUrl()).into(holder.catImage);
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    static class CatHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ImageView catImage;

        public CatHolder(View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.catsImageView);
        }
    }
}
