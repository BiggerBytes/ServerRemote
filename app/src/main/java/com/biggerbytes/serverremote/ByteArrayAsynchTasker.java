package com.biggerbytes.serverremote;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.biggerbytes.serverremote.commandUtils.ByteDataSocket;

import java.io.IOException;

/**
 * An Extension of AsynchTask.
 * Is a separate thread, for sending the data to the server.
 *
 * Created by shach on 4/22/2016.
 */
public class ByteArrayAsynchTasker extends AsyncTask<Void, Void, Integer> {

    byte[] data;

    public ByteArrayAsynchTasker(byte[] data) {
        this.data = data;
    }

    private static final String TAG = "ByteArrayAsynchTasker";

    /**
     * @see AsyncTask
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(Void... params) {
        //  Unboxing the params array from Byte[] to byte[]

        try (ByteDataSocket dataSocket = new ByteDataSocket(data)){

            dataSocket.send();

        } catch (IOException e) {
            Log.e(TAG, "An error occured!", e.getCause());
        }

        return null;
    }

}
