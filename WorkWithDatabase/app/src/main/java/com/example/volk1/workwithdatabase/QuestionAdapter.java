package com.example.volk1.workwithdatabase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.volk1.workwithdatabase.activities.DetailActivity;
import com.example.volk1.workwithdatabase.helper.ItemTouchHelperAdapter;
import com.example.volk1.workwithdatabase.roomDB.entity.Question;

import java.util.Collections;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>
        implements ItemTouchHelperAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Question> mQuestions;

    QuestionAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.QuestionViewHolder questionViewHolder, int i) {
        if (mQuestions != null) {
            Question current = mQuestions.get(i);
            questionViewHolder.mTitle.setText(current.getTitle());
            questionViewHolder.mDescription.setText(current.getQuestion());
        } else {
            questionViewHolder.mTitle.setText(R.string.no_title);
            questionViewHolder.mDescription.setText(R.string.no_question);
        }
    }

    void setQuestionList(List<Question> questionList) {
        this.mQuestions = questionList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mQuestions != null) {
            return mQuestions.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean onItemMoved(int from, int to) {
        Collections.swap(mQuestions, from, to);
        notifyItemMoved(from, to);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mQuestions.remove(position);
        notifyItemRemoved(position);
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

        @Override
        public void onClick(View view) {
            Question question = mQuestions.get(getAdapterPosition());

            Intent intent = new Intent(mContext, DetailActivity.class);

            intent.putExtra("title", question.getTitle());
            intent.putExtra("question", question.getQuestion());
            intent.putExtra("answer", question.getAnswer());

            mContext.startActivity(intent);
        }
    }
}