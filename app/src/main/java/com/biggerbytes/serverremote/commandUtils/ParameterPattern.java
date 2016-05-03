package com.biggerbytes.serverremote.commandUtils;

/**
 * Created by shach on 4/22/2016.
 *
 * This enum contains the value of each flag, as well as a skeleton
 * for it's parameters. Will be used to assemble the parameters correctly.
 */
public enum ParameterPattern {

    SET_INFO_THREAD_STATE(0x60,
            new int[]{1},
            new Class[]{Integer.class}),

    SET_REFRESH_RATE(0x61,
            new int[]{1},
            new Class[]{Integer.class}),

    READ_INFO_NOW(0x62,
            new int[]{0},
            new Class[]{Void.class}),

    ADD_CANCEL_DUMMY(0x63,
            new int[]{1, 2, 10, 32},
            new Class[]{Integer.class, String.class, String.class, String.class}),

    ADD_SUB_TEACHER(0x64,
            new int[]{1, 2, 10, 32},
            new Class[]{Integer.class, String.class, String.class, String.class}),

    REMOVE_ALL_DUMMIES_FROM_ID(0x65,
            new int[]{1},
            new Class[]{Integer.class});


    private byte flag;
    private byte[][] parameters;
    private Class[] parameterTypes;

    ParameterPattern(int flag, int[] sizes, Class[] classes) {
        this.flag = (byte) flag;

        //  Building the parameters pattern:
        parameters = new byte[sizes.length][];
        for (int i = 0 ; i < sizes.length; i++ ) parameters[i] = new byte[sizes[i]];

        this.parameterTypes = classes;

    }

    static ParameterPattern findPatternsWithFlag(byte flag) {
        for (ParameterPattern pp : values()) if (pp.flag == flag) return pp;
        return null;
    }

    int lengthOfAllParameters() {
        int sum = 0;
        for (byte[] t1 : parameters) sum+= t1.length;
        return sum;
    }

    public byte getFlag() {
        return flag;
    }

    public byte[][] getParameters() {
        return parameters;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }
}
