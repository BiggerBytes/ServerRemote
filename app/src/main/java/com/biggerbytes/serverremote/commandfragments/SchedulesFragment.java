package com.biggerbytes.serverremote.commandfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biggerbytes.serverremote.commandUtils.CommandConstants;
import com.biggerbytes.serverremote.R;

/**
 * Created by shach on 3/26/2016.
 */
public class SchedulesFragment extends HeaderFragment {

    private static final String TAG = "SchedulesFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);


        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(int, int, intent) method was called!");
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_schedules_header;
    }
    @Override
    public byte getCommand() {
        return CommandConstants.SCEHDULES_HEADER;
    }
}
