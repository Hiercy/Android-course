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

import com.example.volk1.workwithdatabase.activities.NewQuestionActivity;
import com.example.volk1.workwithdatabase.helper.ItemTouchHelperCallback;
import com.example.volk1.workwithdatabase.roomDB.entity.Question;
import com.example.volk1.workwithdatabase.view_model.QuestionViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private QuestionViewModel mQuestionViewModel;

    private QuestionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // LinearLayout manager
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, gridColumnCount);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set Adapter
        mAdapter = new QuestionAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewQuestionActivity.class);
                // TODO: change to startActivityForResult(intent, int);
                startActivity(intent);
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
}