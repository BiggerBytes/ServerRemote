package com.biggerbytes.serverremote.commandUtils;

import android.content.Intent;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.biggerbytes.serverremote.DataMaps.*;

/**
 * Created by shach on 3/29/2016.
 * <p/>
 * A class designed to assemble the the byte array, to be sent
 * over to the server
 */
public class CommandAssembler {
    private static final String TAG = "CommandAssembler";

    private byte header, flag;
    private byte param[];

    /**
     * A public constructor of the command assembler
     *
     * @param header The chosen header. Currently only 'Schedules' and 'Server' available
     * @param flag   The chosen flag. Will determine the following parameters pack
     * @param param  The parameters pack. Already assembled into a byte array.
     */
    public CommandAssembler(byte header, byte flag, byte param[]) {
        this.header = header;
        this.flag = flag;
        this.param = param;
    }

    /**
     * @param header The chosen header
     *               <br>
     * @param flag   The chosen flag
     *               <br>
     * @param data   An Intent object containing the parameters.
     */
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
        ParameterPattern pattern = ParameterPattern.findPatternsWithFlag(data.getByteExtra(KEY_FLAG, (byte) 0));
        byte[][] skeleton = pattern.getParameters();

        //  Getting the parameter themselves
        String[] parameters = data.getStringArrayExtra(KEY_PARAMETERS);

        //  Iterating and assembling the command via the pattern
        for (int i = 0; i < parameters.length && i < skeleton.length; i++) {
            Log.d(TAG, "parameters[i] = " + parameters[i]);
            Log.d(TAG, "pattern[i].length = " + skeleton[i].length);
            Log.d(TAG, "parameters[i].getBytes(\"UTF-8\").length = " + parameters[i].getBytes("UTF-8").length);


            //  Via TypeTranslatorMap, we translate the parameter to byte[] in a correct representation of the data
            byte[] byteData = TypeTranslatorMap.translate(pattern.getParameterTypes()[i], parameters[i]);

            //  Assemble the byte array, one byte at a time to make sure it fits
            for (int j = 0; j < skeleton[i].length && j < byteData.length; j++)
                skeleton[i][j] = byteData[j];

        }

        try (ByteArrayOutputStream builder = new ByteArrayOutputStream()) {
            for (byte[] paramData : skeleton)
                if ((paramData != null) &&
                        paramData.length > 0) builder.write(paramData);

            return builder.toByteArray();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
            return new byte[]{};
        }

    }


}
