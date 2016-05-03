package com.biggerbytes.serverremote.commandUtils;

import android.content.Intent;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

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

    /**
     * Will assemble the command in this structure:<br>
     * 1) 69 - The triggering value, for the server to be notified to process the following command<br>
     * 2) {HEADER} - First category of the command. Will differentiate between operations on the server itself and operations on the data hosted by the server.<br>
     * 3) {FLAG} - Second category of the command. Will determine the specific operation wished to be performed. Will determine the following parameters to be filled. <br>
     * 4) {PARAMETER} - Is the data that needs to accompany the flag in order to initiate the operation. <br><br>
     * @return The data processed for server to read in byte[].
     */
    public byte[] assemble() {
        byte arr[] = new byte[3 + param.length];
        arr[0] = (byte) 69;
        arr[1] = header;
        arr[2] = flag;
        for (int i = 3; i < arr.length; i++) arr[i] = param[i - 3];


        return arr;
    }


    /**
     * A method used to specifically process the parameters
     * @param data An intent containing all the data of the command.
     * @return  A byte[] of the parameters processed and ordered
     * @throws UnsupportedEncodingException
     */
    protected byte[] intentDataToByteArray(Intent data) throws UnsupportedEncodingException {
        //  Using ParameterPattern to identify the structure of the command
        ParameterPattern pattern = ParameterPattern.findPatternsWithFlag(data.getByteExtra(KEY_FLAG, (byte) 0));
        byte[][] skeleton = pattern.getParameters();

        //  Getting the parameter themselves
        String[] parameters = data.getStringArrayExtra(KEY_PARAMETERS);

        //  Iterating and assembling the command via the pattern
        for (int i = 0; i < parameters.length && i < skeleton.length; i++) {
            /*
            Log.d(TAG, "parameters[i] = " + parameters[i]);
            Log.d(TAG, "pattern[i].length = " + skeleton[i].length);
            Log.d(TAG, "parameters[i].getBytes(\"UTF-8\").length = " + parameters[i].getBytes("UTF-8").length);
            */

            //  Via TypeTranslatorMap, we translate the parameter to byte[] in a correct representation of the data
            byte[] byteData = TypeTranslatorMap.translate(pattern.getParameterTypes()[i], parameters[i]);
            Log.d(TAG, "" + Arrays.toString(byteData));


            //  Assemble the byte array, one byte at a time to make sure it fits
            for (int j = 0; j < skeleton[i].length && j < byteData.length; j++)
                skeleton[i][j] = byteData[j];

        }

        try (ByteArrayOutputStream builder = new ByteArrayOutputStream()) {
            for (byte[] paramData : skeleton)
                if ((paramData != null) &&
                        paramData.length > 0) builder.write(paramData);

            byte[] arr = builder.toByteArray();
            builder.close();

            data.removeExtra(KEY_HEADER);
            data.removeExtra(KEY_FLAG);
            data.removeExtra(KEY_PARAMETERS);

            return arr;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e.fillInStackTrace());
            return new byte[]{};
        }

    }


}
