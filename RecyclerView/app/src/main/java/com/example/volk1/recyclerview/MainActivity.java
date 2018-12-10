package com.example.volk1.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<String> mWordList = new LinkedList<>();
    private LinkedList<String> mWordListReset = new LinkedList<>();

    private WordListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        for (int i = 0; i <= 20; i++) {
            mWordList.add("Word " + i);
            mWordList = mWordListReset;
        }

        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                int wordListSize = mWordList.size();
                mWordList.add("+ Word " + wordListSize);
                mRecyclerView.getAdapter().notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
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
        switch (item.getItemId()) {
            case R.id.deleteall:
                deleteAllItems();
                break;
            case R.id.action_settings:
                reset();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reset() {
        mWordList.clear();
        for (int i = 0; i <= 20; i++)
            mWordList.add("Word " + i);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void deleteAllItems() {
        mWordList.clear();
        mWordList.addAll(mWordList);
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
