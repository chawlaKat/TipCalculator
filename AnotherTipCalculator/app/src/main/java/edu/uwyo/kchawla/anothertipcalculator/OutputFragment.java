package edu.uwyo.kchawla.anothertipcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class OutputFragment extends Fragment {

    public static final String TAG = "HEY_LISTEN_output ";

    private TextView mTipResultText;
    private TextView mTotalResultText;

    private int split;
    private double tip;
    private double total;

    public OutputFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_output, container, false);

        // get view references
        mTipResultText = v.findViewById(R.id.outputTipResultText);
        mTotalResultText = v.findViewById(R.id.outputTotalResultText);

        // if totals have been calculated, display them
        if (getArguments() != null) {

            split = getArguments().getInt("split");
            tip = getArguments().getDouble("tip") * split;
            total = getArguments().getDouble("total") * split;

            mTipResultText.setText(String.format("$%.2f", tip));
            mTotalResultText.setText(String.format("$%.2f", total));
        }

        return v;
    }
}
