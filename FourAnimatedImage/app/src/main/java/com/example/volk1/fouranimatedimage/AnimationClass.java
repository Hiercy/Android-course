package com.example.volk1.fouranimatedimage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class AnimationClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the transition type from the Intent and set it.
        if (getIntent().hasExtra("Transition")) {
            switch (getIntent().getStringExtra("Transition")) {
                case "Explode":
                    getWindow().setEnterTransition(new Explode());
                    break;
                case "Slide":
                    getWindow().setEnterTransition(new Slide());
                    break;
                case "Fade":
                    getWindow().setEnterTransition(new Fade());
                    break;
                default:
                    break;
            }
        }
    }

    public void switchAnimation(final ImageView mAndroid, final ImageView mBlueLine, final Intent intent, final Context context) {
        mAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptions options = ActivityOptions
                        // Create an ActivityOptions to transition between Activities using cross-Activity scene animations.
                        .makeSceneTransitionAnimation((Activity) context,
                                Pair.create((View) mAndroid, "switchAndroid"),
                                Pair.create((View) mBlueLine, "switchBlue"));

                startActivity(intent, options.toBundle());
            }
        });
    }

    public void rotateTransaction(ImageView imageView) {
        final ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, View.ROTATION, 50f, 360f);
        // speed
        animator.setDuration(1000);
        // rotate left and reverse rotate
        animator.setRepeatMode(ValueAnimator.REVERSE);
        // repeat
        animator.setRepeatCount(1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start animation
                animator.start();
            }
        });
    }

    public void explodeTransaction(final Context context, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, context.getClass());
                intent.putExtra("Transition", "Explode");
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
            }
        });
    }

    public void fadeTransaction(final Context context, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, context.getClass());
                intent.putExtra("Transition", "Fade");
                getWindow().setEnterTransition(new Fade());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
            }
        });
    }
}
