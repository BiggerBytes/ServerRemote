package com.biggerbytes.serverremote.commandUtils;

/**
 * Created by shach on 4/2/2016.
 */
import android.util.Log;

import com.biggerbytes.serverremote.DataMaps;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
public class ByteDataSocket extends Socket{

    private static final String TAG = "ByteDataSocket";
    private byte[] data;

    public ByteDataSocket(byte[] data) throws IOException {
        super(DataMaps.ipAddress, DataMaps.port);
        this.data = data;
    }

    public ByteDataSocket(byte[] data, String ip, int port) throws IOException {
        super(ip, port);
        this.data = data;
    }

    public boolean send() throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(getOutputStream());
        try {
            out.writeObject(69);
            out.writeObject(data);
            return true;

        } catch (Exception e) {
            Log.e(TAG, "AN ERROR OCCURRED", e.getCause());
            return false;

        }
        finally {
            out.flush();
            out.close();
        }

    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
