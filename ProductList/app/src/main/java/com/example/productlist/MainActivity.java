package com.example.productlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProducts = new ArrayList<>();

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this, mProducts));

        initData();
    }

    private void initData() {
        for (int i = 0; i < 3; i++) {
            mProducts.add(new Product("Banana","Banana is yellow",
                    R.drawable.banana_small, R.drawable.banana_big));
        }

        for (int i = 0; i < 3; i++) {
            mProducts.add(new Product("Orange","Orange is Orange",
                    R.drawable.orange_small, R.drawable.orange_big));
        }

        for (int i = 0; i < 3; i++) {
            mProducts.add(new Product("Meat","Meat is red",
                    R.drawable.meat_small, R.drawable.meat_big));
        }
    }
}
