package com.example.volk1.workwithdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] questionTitle;
    private String[] questionDescription;
    private ArrayList<Question> mDataset;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Init ArrayList
        mDataset = new ArrayList<>();

        // LinearLayout manager
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mLayoutManager = new GridLayoutManager(this, gridColumnCount);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set Adapter
        mAdapter = new QuestionAdapter(this, mDataset);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        questionDescription = getResources()
                .getStringArray(R.array.array_question);
        questionTitle = getResources()
                .getStringArray(R.array.array_title);

        // Clear the existing data
        mDataset.clear();

        // Create the ArrayList of Question objects
        for (int i = 0; i < questionTitle.length; i++) {
            mDataset.add(new Question(questionTitle[i], questionDescription[i]));
        }

        // Notify the adapter of the changes
        mAdapter.notifyDataSetChanged();
    }
}