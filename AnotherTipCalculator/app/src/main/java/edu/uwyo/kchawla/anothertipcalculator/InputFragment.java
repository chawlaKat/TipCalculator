package edu.uwyo.kchawla.anothertipcalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InputFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class InputFragment extends Fragment
        implements RadioGroup.OnCheckedChangeListener{

    private static final String TAG = "HEY_LISTEN_input";
    private OnFragmentInteractionListener mListener;

    private EditText mBillEditText;
    private EditText mPercentEditText;
    private EditText mSplitEditText;
    private Button mCalculateButton;
    private RadioGroup mRoundRadioGroup;

    private int rounded = 0;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        // get references
        mBillEditText = v.findViewById(R.id.billEditText);
        mPercentEditText = v.findViewById(R.id.percentEditText);
        mSplitEditText = v.findViewById(R.id.splitEditText);

        mRoundRadioGroup = v.findViewById(R.id.roundRadioGroup);
        mRoundRadioGroup.setOnCheckedChangeListener(this);

        mCalculateButton = v.findViewById(R.id.calculateButton);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get user input
                double bill = Double.parseDouble(mBillEditText.getText().toString());
                int percent = Integer.parseInt(mPercentEditText.getText().toString());
                int split = Integer.parseInt(mSplitEditText.getText().toString());

                // bounds check
                if (split == 0)
                    split = 1;

                // calculate
                double tip = calculate(bill, percent, split, rounded);
                double total = bill / split + tip;

                // send info to activity
                mListener.onFragmentInteraction(split, tip, total);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        // find out which radio button was checked
        if (radioGroup == mRoundRadioGroup) {
            if (i == R.id.roundTotalRbutton)
                rounded = 2;
            else if (i == R.id.roundTipRbutton)
                rounded = 1;
            else
                rounded = 0;
        }
    }

    /**
     * Calculates tip per person
     *
     * @param bill: initial cost
     * @param percent: to tip
     * @param split: number of people splitting bill
     * @param rounded: whether to round (0, 1, 2)
     * @return tip per person
     */
    protected double calculate(double bill, int percent, int split, int rounded)
    {
        bill = bill / split;

        double tip = bill * (percent/100.);
        double total;

        // choose between rounding methods
        switch (rounded)
        {
            case 0:
                break;
            case 1:
                tip = Math.ceil(tip);
                break;
            case 2:
                total = bill + tip;
                total = Math.ceil(total);
                tip = total - bill;
        }

        return tip;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int split, double tip, double total);
    }
}
