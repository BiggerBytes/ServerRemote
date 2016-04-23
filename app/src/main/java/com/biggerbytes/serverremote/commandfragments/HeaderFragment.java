package com.biggerbytes.serverremote.commandfragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.biggerbytes.serverremote.ByteArrayAsynchTasker;
import com.biggerbytes.serverremote.DataMaps;
import com.biggerbytes.serverremote.commandUtils.ByteDataSocket;
import com.biggerbytes.serverremote.commandUtils.CommandAssembler;

import java.io.IOException;

import static com.biggerbytes.serverremote.DataMaps.*;

/**
 * Created by shach on 2/21/2016.
 */
public abstract class HeaderFragment extends Fragment implements Button.OnClickListener {

    private static final String TAG = "HeaderFragment";
    private byte flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
        wireViews(v);


        return v;
    }

    private void wireViews(ViewGroup v) {

        //  Iterating through all the inflated to the layout views.
        for (int i = 0; i < v.getChildCount(); i++) {

            //  A View is found
            View v1 = v.getChildAt(i);

            //  If it's a button - wire it to the listener
            if (v1 instanceof Button) {
                Button b = (Button) v1;
                b.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(final View v) {
        //  Setting the chosen flag
        flag = DataMaps.btnToByteId.get(v.getId());


        FlagParametersDialog dialog = new FlagParametersDialog() {
            @Override
            public int getLayoutId() {
                return DataMaps.dialogMap.get(v.getId());
            }
        };
        dialog.setTargetFragment(HeaderFragment.this, 0);
        dialog.show(getChildFragmentManager(), "parameter_input_fragment");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final byte DEF = 0;

        data.putExtra(KEY_HEADER, getCommand());
        data.putExtra(KEY_FLAG, flag);

        CommandAssembler assembler = new CommandAssembler(
                data.getByteExtra(KEY_HEADER, DEF),
                data.getByteExtra(KEY_FLAG, DEF),
                data);

        try {
            ByteArrayAsynchTasker tasker = new ByteArrayAsynchTasker(assembler.assemble());
            tasker.execute();
            Toast.makeText(HeaderFragment.this.getActivity(), "The Command was send!", Toast.LENGTH_SHORT).show();

        } catch (Exception c) {
            Log.e(TAG, "An Error Occured:\t", c.getCause());
            Toast.makeText(getActivity(), "An error occured. Please check that the server and your phone is online.", Toast.LENGTH_LONG).show();
        }
    }

    public abstract int getLayoutId();
    public abstract byte getCommand();

}
