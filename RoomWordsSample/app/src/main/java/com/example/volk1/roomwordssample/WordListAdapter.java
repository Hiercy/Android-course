package com.example.volk1.roomwordssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.volk1.roomwordssample.entity.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<Word> mWordList;

    public WordListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mLayoutInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder wordViewHolder, int i) {
        if (mWordList != null) {
            Word current = mWordList.get(i);
            wordViewHolder.wordItemList.setText(current.getWord());
        } else {
            wordViewHolder.wordItemList.setText("No Words");
        }
    }

    void setWordList(List<Word> mWordList) {
        this.mWordList = mWordList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWordList != null) {
            return mWordList.size();
        } else {
            return 0;
        }
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        private final TextView wordItemList;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemList = itemView.findViewById(R.id.textView);
        }
    }
}
