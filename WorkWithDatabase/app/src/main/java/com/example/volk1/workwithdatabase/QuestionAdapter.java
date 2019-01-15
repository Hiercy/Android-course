package com.example.volk1.workwithdatabase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context mContext;
    private ArrayList<Question> mQuestions;

    QuestionAdapter(Context context, ArrayList<Question> mQuestions) {
        this.mContext = context;
        this.mQuestions = mQuestions;
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuestionViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.QuestionViewHolder questionViewHolder, int i) {
        // Get current question
        final Question question = mQuestions.get(i);

        questionViewHolder.bindTo(question);
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    /*
     *                     ===================== VIEW HOLDER ==================
     */
    public class QuestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private TextView mDescription;

        QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.question_subtitle);

            itemView.setOnClickListener(this);
        }

        void bindTo(Question question) {
            mTitle.setText(question.getTitle());
            mDescription.setText(question.getQuestion());
        }

        @Override
        public void onClick(View view) {
            Question question = mQuestions.get(getAdapterPosition());

            Intent intent = new Intent(mContext, DetailActivity.class);

            intent.putExtra("title",question.getTitle());
            intent.putExtra("question", question.getQuestion());

            mContext.startActivity(intent);
        }
    }
}