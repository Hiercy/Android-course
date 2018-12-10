package com.example.volk1.recipesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecepieAdapter extends RecyclerView.Adapter<RecepieAdapter.RecipeHolder> {

    private ArrayList<Recipe> mRecipes = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public RecepieAdapter(Context context, ArrayList<Recipe> mRecipes) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mRecipes = mRecipes;
    }

    public class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView title;
        public final TextView body;
        public LinearLayout linearLayout;

        public RecipeHolder(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
            linearLayout = itemView.findViewById(R.id.linLayout);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @NonNull
    @Override
    // The onCreateViewHolder() method is similar to the onCreate() method.
    // It inflates the item layout, and returns a ViewHolder with the layout and the adapter.
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.item_layout, viewGroup, false);
        return new RecipeHolder(mItemView);
    }

    @Override
    //The onBindViewHolder() method connects data to the view holder.
    public void onBindViewHolder(@NonNull RecipeHolder holder, final int position) {
        Recipe mRec = mRecipes.get(position);
        holder.title.setText(mRec.getTitle());
        holder.body.setText(mRec.getBody());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecActivity.class);
                intent.putExtra("recep_full", String.valueOf(mRecipes.get(position).getBody()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
