package com.biggerbytes.serverremote.commandUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.biggerbytes.serverremote.R;

/**
 * Created by shach on 4/22/2016.
 */
public class ClassesInformationDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()) {{
            setTitle("School Classes Information");

            View viewgroup = View.inflate(getActivity(), R.layout.info_schoolclasses, null);
            ListView list = (ListView) viewgroup.findViewById(R.id.classes_listview);

            //  Accessing the names and corresponding ID's (corresponding in index)
            final Resources RES = getResources();
            final String[] names = RES.getStringArray(R.array.classes_name);
            final int[] ids = RES.getIntArray(R.array.classes_id);

            //  Adding views to the list
            for (int i = 0; i < names.length; i++) {
                TextView tv = new TextView(getActivity());
                tv.setText(String.format("%3s \t - \t %3d", names[i], ids[i]));
                list.addFooterView(tv);
            }

        }}.create();
    }
}
