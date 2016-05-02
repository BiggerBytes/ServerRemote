package com.biggerbytes.serverremote.commandUtils;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by shach on 4/24/2016.
 */
public enum TypeTranslatorMap {


    integerTranslator(Integer.class, new TypeTranslator() {

        private static final String TAG = "TypeTranslatorMap";

        @Override
        public byte[] translate(String s) {
            int value = Integer.parseInt(s);
            Log.d(TAG, "value = " + value);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(value);


            return out.toByteArray();
        }
    }
    ),

    stringTranslator(String.class, new TypeTranslator() {
        private static final String TAG = "TypeTranslatorMap";

        @Override
        public byte[] translate(String s) {
            try {
                Log.d(TAG, "for: " + s );
                return s.getBytes("UTF-8");
            } catch (Exception e) {
                return new byte[]{};
            }
        }
    }

    );

    private Class type;
    private TypeTranslator translator;

    TypeTranslatorMap(Class type, TypeTranslator translator) {
        this.type = type;
        this.translator = translator;
    }

    static TypeTranslator getTranslatorByClass(Class klazz) {
        for (TypeTranslatorMap map : TypeTranslatorMap.values())
            if (map.type == klazz) return map.translator;
        return null;
    }

    static byte[] translate(Class klass, String data) {
        TypeTranslator map = getTranslatorByClass(klass);

        return map == null ? new byte[]{} : map.translate(data);
    }

    static abstract class TypeTranslator {
        abstract byte[] translate(String s);
    }


}