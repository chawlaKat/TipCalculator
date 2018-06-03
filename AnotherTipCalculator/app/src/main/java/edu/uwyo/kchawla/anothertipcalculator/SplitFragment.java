package edu.uwyo.kchawla.anothertipcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SplitFragment extends Fragment {

    private TextView mTipResultText;
    private TextView mTotalResultText;

    private double tip;
    private double total;

    public SplitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_split, container, false);

        // get text references
        mTipResultText = v.findViewById(R.id.splitTipResultText);
        mTotalResultText = v.findViewById(R.id.splitTotalResultText);

        // if totals have been set, display them
        if (getArguments() != null)
        {
            tip = getArguments().getDouble("tip");
            total = getArguments().getDouble("total");

            mTipResultText.setText(String.format("$%.2f", tip));
            mTotalResultText.setText(String.format("$%.2f", total));
        }

        return v;
    }
}
