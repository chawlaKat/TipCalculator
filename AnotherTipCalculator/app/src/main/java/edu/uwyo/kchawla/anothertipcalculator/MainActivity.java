package edu.uwyo.kchawla.anothertipcalculator;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
        implements  InputFragment.OnFragmentInteractionListener{

    private static final String TAG = "HEY_LISTEN_activity";
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        Fragment inFrag = fm.findFragmentById(R.id.inputContainer);

        // if no fragment yet, create it
        if (inFrag == null) {
            inFrag = new InputFragment();
            fm.beginTransaction()
                    .add(R.id.inputContainer, inFrag)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(int split, double tip, double total) {

        // package arguments to send to fragments
        Bundle bundle = new Bundle();
        bundle.putInt("split", split);
        bundle.putDouble("tip", tip);
        bundle.putDouble("total", total);

        // clear any previous split-the-bill info
        Fragment splitFrag = fm.findFragmentById(R.id.splitContainer);
        if (splitFrag != null)
            fm.beginTransaction().remove(splitFrag).commit();

        // if splitting, show extra info
        if (split > 1) {
            splitFrag = new SplitFragment();
            splitFrag.setArguments(bundle);
            fm.beginTransaction()
                    .add(R.id.splitContainer, splitFrag)
                    .commit();
        }

        Fragment outFrag = fm.findFragmentById(R.id.outputContainer);

        // clear any previous totals
        if (outFrag != null)
            fm.beginTransaction().remove(outFrag).commit();

        // display new totals
        Log.i(TAG, " new output frag created");
        outFrag = new OutputFragment();
        outFrag.setArguments(bundle);
        fm.beginTransaction()
                .add(R.id.outputContainer, outFrag)
                .commit();

    }
}
