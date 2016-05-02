package com.biggerbytes.serverremote;

import static com.biggerbytes.serverremote.commandUtils.CommandConstants.*;

import com.biggerbytes.serverremote.commandfragments.HeaderFragment;
import com.biggerbytes.serverremote.commandfragments.SchedulesFragment;
import com.biggerbytes.serverremote.commandfragments.ServerHeaderFragment;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by shach on 3/29/2016.
 */
public interface DataMaps {

    String ipAddress = "84.108.62.118"; //"ec2-52-28-93-13.eu-central-1.compute.amazonaws.com";
    int port = 25565;
    String KEY_HEADER = "HEADER_KEY", KEY_FLAG = "FLAG_KEY", KEY_PARAMETERS = "PARAMETERS_KEY";

    Map<Integer, Integer> dialogMap = new Hashtable<Integer, Integer>() {{
        put(R.id.setinfothreadstate_btn, R.layout.flag_setinfothreadstate);
        put(R.id.setrefreshrate_btn, R.layout.flag_setrefreshrate);
        put(R.id.readinfonow_btn, R.layout.flag_readinfonow);
        put(R.id.addcanceldummy_btn, R.layout.flag_adddummy);
        put(R.id.addsubdummy_btn, R.layout.flag_adddummy);
        put(R.id.removealldummies_btn, R.layout.flag_removealldummiesfromid);
    }};
    Map<Integer, Byte> btnToByteId = new Hashtable<Integer, Byte>() {{
            put(R.id.setinfothreadstate_btn, SET_INFO_THREAD_STATE);
            put(R.id.setrefreshrate_btn, SET_REFRESH_RATE);
            put(R.id.readinfonow_btn, READ_INFO_NOW);
            put(R.id.addcanceldummy_btn, ADD_CANCEL_DUMMY);
            put(R.id.addsubdummy_btn, ADD_SUBSTITUE_DUMMY);
            put(R.id.removealldummies_btn, REMOVE_ALL_DUMMIES_FROM_ID);
    }};
    Map<Integer, HeaderFragment> btnToHeaderFragment = new Hashtable<Integer, HeaderFragment>() {{
        put(R.id.server_header_btn, new ServerHeaderFragment());
        put(R.id.schedule_header_btn, new SchedulesFragment());
    }};
}
