package com.biggerbytes.serverremote.commandfragments;

import android.content.Intent;

import com.biggerbytes.serverremote.commandUtils.CommandConstants;
import com.biggerbytes.serverremote.R;

/**
 * An extension of HeaderFragment, for the 'Server' header.
 * Created by shach on 2/21/2016.
 */
public class ServerHeaderFragment extends HeaderFragment {

    @Override
    public int getLayoutId() { return R.layout.fragment_server_header; }

    @Override
    public byte getCommand() {
        return CommandConstants.SERVER_HEADER;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
