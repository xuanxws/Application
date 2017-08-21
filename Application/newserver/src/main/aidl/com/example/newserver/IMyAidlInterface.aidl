// IMyAidlInterface.aidl
package com.example.newserver;
import com.example.newserver.Person;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addPerson(in Person person);
     List<Person> getPersonList();
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
