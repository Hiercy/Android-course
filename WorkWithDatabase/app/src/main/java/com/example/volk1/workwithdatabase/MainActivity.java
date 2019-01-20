package com.example.volk1.workwithdatabase;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.volk1.workwithdatabase.activities.NewQuestionActivity;
import com.example.volk1.workwithdatabase.roomDB.entity.Question;
import com.example.volk1.workwithdatabase.view_model.QuestionViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_QUESTION_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_QUESTION_ACTIVITY_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_UPDATE_TITLE = "extra_title_to_be_updated";
    public static final String EXTRA_DATA_UPDATE_QUESTION = "extra_question_to_be_updated";
    public static final String EXTRA_DATA_UPDATE_ANSWER = "extra_answer_to_be_updated";
    public static final String EXTRA_DATA_UPDATE_ID = "extra_id_to_be_updated";

    private QuestionViewModel mQuestionViewModel;

    private QuestionAdapter mAdapter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        mQuestionViewModel.getAllQuestion().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mAdapter.setQuestionList(questions);
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // LinearLayout manager
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, gridColumnCount);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set Adapter
        mAdapter = new QuestionAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // ItemTouchHelper
        touchHelper();

        FloatingActionButton fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewQuestionActivity.class);
                startActivityForResult(intent, NEW_QUESTION_ACTIVITY_REQUEST_CODE);
            }
        });

        FloatingActionButton fab_restore = findViewById(R.id.fab_restore);
        fab_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("Clearing the questions...");

                mQuestionViewModel.deleteAll();
            }
        });

        mAdapter.setOnItemClickListener(new QuestionAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Question question = mAdapter.getQuestionAtPosition(position);
                launchUpdateQuestionActivity(question);
            }
        });
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        mQuestionViewModel.getAllQuestion().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable List<Question> questions) {
                mAdapter.setQuestionList(questions);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_QUESTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Question question = new Question(
                    data.getStringExtra(NewQuestionActivity.REPLY_TITLE),
                    data.getStringExtra(NewQuestionActivity.REPLY_DESC),
                    data.getStringExtra(NewQuestionActivity.REPLY_ANSWER));

            mQuestionViewModel.insert(question);
        } else if (requestCode == UPDATE_QUESTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title_date = data.getStringExtra(NewQuestionActivity.REPLY_TITLE);
            String question_data = data.getStringExtra(NewQuestionActivity.REPLY_DESC);
            String answer_date = data.getStringExtra(NewQuestionActivity.REPLY_ANSWER);
            int id = data.getIntExtra(NewQuestionActivity.REPLY_ID, -1);

            if (id != -1) {
                mQuestionViewModel.update(new Question(
                        id,
                        title_date,
                        question_data,
                        answer_date));
            } else {
                displayToast("unable to update");
            }
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void touchHelper() {
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Question question = mAdapter.getQuestionAtPosition(position);
                        displayToast("Deleting " + question.getTitle());

                        // Delete the word
                        mQuestionViewModel.deleteQuestion(question);
                    }
                });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }

    public void launchUpdateQuestionActivity(Question question) {
        Intent intent = new Intent(this, NewQuestionActivity.class);

        intent.putExtra(EXTRA_DATA_UPDATE_TITLE, question.getTitle());
        intent.putExtra(EXTRA_DATA_UPDATE_QUESTION, question.getQuestion());
        intent.putExtra(EXTRA_DATA_UPDATE_ANSWER, question.getAnswer());
        intent.putExtra(EXTRA_DATA_UPDATE_ID, question.getID());

        startActivityForResult(intent, UPDATE_QUESTION_ACTIVITY_REQUEST_CODE);
    }
}