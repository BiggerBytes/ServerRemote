package com.biggerbytes.serverremote.commandUtils;

/**
 * A class designed to connect the handheld device to the server, and send the data
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

    /**
     *
     * @param data The data to be sent to the server
     * @throws IOException
     */
    public ByteDataSocket(byte[] data) throws IOException {
        super(DataMaps.ipAddress, DataMaps.port);
        this.data = data;
    }

    /**
     *
     * @param data  The data to be sent to the server
     * @param ip    The IP address of the hosting server
     * @param port  The port of the hosting server
     * @throws IOException
     */
    public ByteDataSocket(byte[] data, String ip, int port) throws IOException {
        super(ip, port);
        this.data = data;
    }

    /**
     * Will attempt to send the data to the designated target
     * @return <b>true</b> if the operation was successful,<br>
     *     <b>false</b> otherwise
     * @throws IOException
     */
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
