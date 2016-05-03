package com.biggerbytes.serverremote;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.biggerbytes.serverremote.R;

/**
 * Is an AlertDialog that will present the admin all the classes and corresponding ID's, for future operations.
 *
 * Created by shach on 4/22/2016.
 */
public class ClassesInformationDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setTitle("Class ID\'s")
                .setView(R.layout.info_schoolclasses)
                .setItems(getItems(), null)
                .create();
    }

    public String[] getItems(){
        Resources res = getResources();
        String[] name = res.getStringArray(R.array.classes_name);
        int[] id = res.getIntArray(R.array.classes_id);

        assert name.length == id.length : "Different sized resources";

        String[] items = new String[name.length];

        for (int i = 0; i < name.length; i++)   items[i] = id[i] + " - " + name[i];

        return items;
    }


}
