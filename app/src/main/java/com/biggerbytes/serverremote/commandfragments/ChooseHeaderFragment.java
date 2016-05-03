package com.biggerbytes.serverremote.commandfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biggerbytes.serverremote.R;

/**
 *  An abstract host of the fragment hosting the main layout, letting the user choose the header of the command.
 * Created by shach on 2/21/2016.
 */
public class ChooseHeaderFragment extends Fragment {

    public static final String TAG = "ChooseHeaderFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chooseheader, container, false);

        return v;
    }
}
