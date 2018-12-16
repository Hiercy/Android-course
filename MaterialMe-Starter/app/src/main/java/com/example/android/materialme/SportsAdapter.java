/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.materialme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Sport> mSportsData;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param sportsData ArrayList containing the sports data.
     * @param context    Context of the application.
     */
    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;
    }

    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(final SportsAdapter.ViewHolder holder, final int position) {
        // Get current sport.
        final Sport currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//            implements View.OnClickListener

        // Member Variables for the TextViews
        private TextView title;
        private TextView mTitleText;
        private TextView mInfoText;

        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            title = itemView.findViewById(R.id.title);
            mTitleText = itemView.findViewById(R.id.newsTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);

            mSportsImage = itemView.findViewById(R.id.sportsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            // Populate the textviews with data.
            title.setText(currentSport.getTitle());
            mTitleText.setText("News");
            mInfoText.setText(currentSport.getInfo());

            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            Sport currentSport = mSportsData.get(getAdapterPosition());

            Intent intentDetail = new Intent(mContext, DetailActivity.class);

            intentDetail.putExtra("image_resources", currentSport.getImageResource());
            intentDetail.putExtra("title", currentSport.getTitle());
            intentDetail.putExtra("text", currentSport.getText());

            Pair<View, String> pair1 = Pair.create((View) mSportsImage, DetailActivity.VIEW_NAME_HEADER_IMAGE);
            Pair<View, String> pair2 = Pair.create((View) title, DetailActivity.VIEW_NAME_HEADER_TITLE);
            Pair<View, String> pair3 = Pair.create((View) mInfoText, DetailActivity.VIEW_NAME_HEADER_TEXT);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, pair1, pair2, pair3);
            ActivityCompat.startActivity(mContext, intentDetail, optionsCompat.toBundle());
        }
    }
}
