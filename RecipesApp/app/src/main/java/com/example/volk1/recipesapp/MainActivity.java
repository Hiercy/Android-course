package com.example.volk1.recipesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recipe> mRecipe = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecepieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);

        mAdapter = new RecepieAdapter(this, mImage, mRecipe);
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        setRecipeData();
    }

    private void setRecipeData() {
        Recipe rec = new Recipe("Kale/Lemon Sandwich", "For the Cookie Dough:\n" +
                "2/3 cup superfine sugar\n" +
                "8 ounces butter (room temperature)\n" +
                "1 large \u200Begg yolk (lightly beaten, room temperature)\n" +
                "2 teaspoons lemon juice\n" +
                "2 teaspoons lemon zest\n" +
                "2 1/4 cups all-purpose flour\n" +
                "For the Cookie Filling:\n" +
                "1/3 cup butter (room temperature)\n" +
                "4 cups confectioners\\' sugar\n" +
                "4 tablespoons lemon juice\n" +
                "1/2 teaspoon lemon zest");

        mImage.add("https://i.redd.it/glin0nwndo501.jpg");
        mRecipe.add(rec);

        rec = new Recipe("Mango-Lime Bean Salad", "1/4 cup apple cider vinegar\n" +
                "\n" +
                "2 tablespoons fresh lime juice\n" +
                "\n" +
                "1 tablespoon honey\n" +
                "1 teaspoon ground cumin\n" +
                "1/3 cup canola oil\n" +
                "Kosher salt and freshly cracked black pepper\n" +
                "1/2 small ripe mango, peeled and diced into 1/4-inch pieces\n" +
                "1/2 yellow bell pepper, cut into small dice\n" +
                "1 (14.5-ounce) can black beans, drained and rinsed\n" +
                "1 small jalapeno, seeds and ribs removed, minced\n" +
                "1 small red onion, cut into small dice\n" +
                "1/4 cup fresh cilantro leaves, chopped");

        mImage.add("https://i.redd.it/glin0nwndo501.jpg");
        mRecipe.add(rec);

        rec = new Recipe("Sweet Potato", "3 pounds sweet potatoes, peeled, cut into 1½-inch pieces\n" +
                "¼ cup olive oil\n" +
                "2 teaspoons kosher salt\n" +
                "½ teaspoon freshly ground black pepper");

        mImage.add("https://s23209.pcdn.co/wp-content/uploads/2016/11/Loaded-Sweet-PotatoIMG_4135edit.jpg");
        mRecipe.add(rec);

        rec = new Recipe("Lime Mousse", "1/2 cup sugar.\n" +
                "2 tablespoons cornstarch.\n" +
                "Pinch salt.\n" +
                "3 large egg yolks.\n" +
                "2/3 cup 2% milk.\n" +
                "1/4 cup lemon juice.\n" +
                "1 tablespoon lime juice.\n" +
                "1-1/2 teaspoons grated lemon zest.");

        mImage.add("http://scibosnian.com/wp-content/uploads/2017/12/Lime_Mousse_6.jpg");
        mRecipe.add(rec);

        mAdapter.notifyDataSetChanged();
    }
}
