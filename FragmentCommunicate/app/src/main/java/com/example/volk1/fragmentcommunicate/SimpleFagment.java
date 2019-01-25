package com.example.volk1.fragmentcommunicate;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleFagment extends Fragment {

    OnFragmentInteractionListener mListener;

    public int mRadioButtonChoice = NONE;

    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NONE = 2;

    private static final String CHOICE = "choice";

    public SimpleFagment() {
        // Required empty public constructor
    }

    public static SimpleFagment newInstance(String choice) {
        SimpleFagment simpleFagment = new SimpleFagment();
        Bundle arguments = new Bundle();
        arguments.putString(CHOICE, choice);
        simpleFagment.setArguments(arguments);
        return simpleFagment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);

        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        if (getArguments() != null && getArguments().containsKey(CHOICE)) {
            // A choice was made, so get the choice
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            // Check the radio button choice.
            if (mRadioButtonChoice != NONE) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = rootView.findViewById(R.id.fragment_header);

                switch (index) {
                    case YES:
                        textView.setText(R.string.yes_message);
                        mRadioButtonChoice = YES;
                        mListener.onRadioButtonChoice("Yes");
                        break;
                    case NO:
                        textView.setText(R.string.no_message);
                        mRadioButtonChoice = NO;
                        mListener.onRadioButtonChoice("No");
                        break;
                    default:
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice("None");
                        break;
                }
            }
        });

        final RatingBar ratingBar = rootView.findViewById(R.id.rating_star);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String rating = "My Rating: "
                        + String.valueOf(ratingBar.getRating());
                Toast.makeText(getContext(), rating, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }

    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(String choice);
    }
}