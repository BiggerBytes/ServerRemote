package com.biggerbytes.serverremote.commandUtils;

import android.content.Intent;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.biggerbytes.serverremote.DataMaps.*;

/**
 * Created by shach on 3/29/2016.
 */
public class CommandAssembler {
    private static final String TAG = "CommandAssembler";

    private byte header, flag;
    private byte param[];

    public CommandAssembler(byte header, byte flag, byte param[]) {
        this.header = header;
        this.flag = flag;
        this.param = param;
    }

    public CommandAssembler(byte header, byte flag, Intent data) {
        this.header = header;
        this.flag = flag;
        try {
            this.param = intentDataToByteArray(data);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
        }
    }

    public byte[] assemble() {
        byte arr[] = new byte[2 + param.length];
        arr[0] = header;
        arr[1] = flag;
        for (int i = 2; i < arr.length; i++) arr[i] = param[i - 2];


        return arr;
    }


    protected byte[] intentDataToByteArray(Intent data) throws UnsupportedEncodingException {
        //  Using ParameterPattern to identify the structure of the command
        byte[][] pattern = ParameterPattern.findPatternsWithFlag(data.getByteExtra(KEY_FLAG, (byte) 0));
        //  Getting the parameter themselves
        String[] parameters = data.getStringArrayExtra(KEY_PARAMETERS);
        //  Iterating and assemgling the command via the pattern
        for (int i = 0; i < parameters.length; i++) pattern[i] = parameters[i].getBytes("UTF-8");

        try (ByteArrayOutputStream builder = new ByteArrayOutputStream()) {
            for (byte[] paramData : pattern)
                if ((paramData!=null) &&
                    paramData.length > 0) builder.write(paramData);

            return builder.toByteArray();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
            return new byte[]{};
        }

    }
}
