
// IRemoteInterface.aidl
package com.example.service;
import com.example.service.Command;

interface IRemoteInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int getPid();
    Command getCommand();
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}