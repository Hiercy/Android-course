package com.example.productlist;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ImageAdapter extends BaseAdapter {

    private Context context;
    List<Product> mProducts = new ArrayList<>();

    public ImageAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.mProducts = products;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return mProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(185, 185));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(mProducts.get(i).getImage());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
//                Toast.makeText(view.getContext(), "Test: ", Toast.LENGTH_SHORT).show();
                Dialog dialog = new Dialog(context);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.item);
                dialog.setTitle(mProducts.get(i).getTitle());

                TextView title = dialog.findViewById(R.id.title_item);
                TextView desc = dialog.findViewById(R.id.desc);
                ImageView image = dialog.findViewById(R.id.big_img);

                title.setText(mProducts.get(i).getTitle());
                desc.setText(mProducts.get(i).getDesc());
                image.setImageResource(mProducts.get(i).getLargeImage());

                dialog.show();
            }
        });

        return imageView;
    }
}
