package com.biggerbytes.serverremote.commandUtils;

/**
 * A dictionary of all headers/flags, and structure of parameters.
 * @author Avishay
 */
public interface CommandConstants {

    // HEADERS 
    byte SERVER_HEADER = 0x5F;
    byte SCEHDULES_HEADER = 0x6F;

    // SERVER FLAGS    
    byte SET_SERVER_STATE = 0x50;

    // SCHEDULES FLAGS    
    byte SET_INFO_THREAD_STATE = 0x60; // Syntax: {HEADER} {FLAG} {STATE} ;; STATE: ON is 1, OFF is everything else
    byte SET_REFRESH_RATE = 0x61; // Syntax: {HEADER} {FLAG} {MINUTES} ;; where MINUTES is a byte that valued between 0 - 255
    byte READ_INFO_NOW = 0x62; // Syntax: {HEADER} {FLAG}
    byte ADD_CANCEL_DUMMY = 0x63; // Syntax: {HEADER} {FLAG} {CLASS_ID} {HOUR} {DATE} {TEACHER_NAME} ;; HOUR is max 2 bytes (3 - 5), DATE is always 10 bytes (5 - 15), TEACHER_NAME has unknown amount of bytes (15 - ?)
    byte ADD_SUBSTITUE_DUMMY = 0x64; // Substitue system is not implemented yet, so no use.
    byte REMOVE_ALL_DUMMIES_FROM_ID = 0x65; // Syntax: {HEADER} {FLAG} {CLASS_ID}

    // TODO add tests flags

}
