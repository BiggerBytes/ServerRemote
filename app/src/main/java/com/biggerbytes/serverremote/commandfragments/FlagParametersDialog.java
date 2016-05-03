package com.biggerbytes.serverremote.commandfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;

import com.biggerbytes.serverremote.DataMaps;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * An Abstract class for filling in the parameters of the chosen flag of the command.
 * Will be displayed upon choosing a flag.
 *
 * Created by shach on 3/29/2016.
 */
public abstract class FlagParametersDialog extends DialogFragment {
    private static final String TAG = "FlagParametersDialog";
    private static final ArrayList<EditText> views = new ArrayList<EditText>();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) getActivity().getLayoutInflater().inflate(getLayoutId(), null);

        //  Iterating through the views
        for (int i = 0; i < v.getChildCount(); i++) if (v.getChildAt(i) instanceof EditText) views.add((EditText) v.getChildAt(i));

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Fill the parameters:")
                .setPositiveButton("Submit and send",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResults(views);
                            }
                        })
                .create();
    }

    /**
     *  Will be called upon completion in filling the parameters.
     *  The data will be processed and sent to the hosting fragment for future processing.
     * @param viewsOnResults
     */
    public void sendResults(ArrayList<EditText> viewsOnResults) {

        //
        String[] results = new String[views.size()];
        for (int i = 0; i < results.length; i++) results[i] = views.get(i).getText().toString();

        Log.d(TAG, "Parent class name = " + getParentFragment().getClass().getSimpleName());


        // Debugging
        //  for (String s : results) Log.d(TAG, s);


        //  Packing the parameters in an intent
        Intent intent = new Intent();
        intent.putExtra(DataMaps.KEY_PARAMETERS, results);

        views.clear();

        getTargetFragment().onActivityResult(0, 0, intent);
    }

    public abstract int getLayoutId();
}
