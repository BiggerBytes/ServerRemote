package com.biggerbytes.serverremote.commandUtils;

/**
 * Created by shach on 4/22/2016.
 *
 * This enum contains the value of each flag, as well as a skeleton
 * for it's parameters.
 */
public enum ParameterPattern {

    SET_INFO_THREAD_STATE(0x60, 1),
    SET_REFRESH_RATE(0x61, 1),
    READ_INFO_NOW(0x62, 0),
    ADD_CANCEL_DUMMY(0x63, 1, 2, 24),
    ADD_SUB_TEACHER(0x64, 1, 2, 24),
    REMOVE_ALL_DUMMIES_FROM_ID(0x65, 1);


    private byte flag;
    private byte[][] parameters;

    ParameterPattern(int flag, int... sizes) {
        this.flag = (byte) flag;

        //  Building the parameters pattern:
        parameters = new byte[sizes.length][];
        for (int i = 0 ; i < sizes.length; i++ ) parameters[i] = new byte[sizes[i]];



    }

    static byte[][] findPatternsWithFlag(byte flag) {
        for (ParameterPattern pp : values()) if (pp.flag == flag) return pp.parameters;
        return null;
    }

    int lengthOfAllParameters() {
        int sum = 0;
        for (byte[] t1 : parameters) sum+= t1.length;
        return sum;
    }


}
