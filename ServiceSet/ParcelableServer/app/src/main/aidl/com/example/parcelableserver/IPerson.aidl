// IPerson.aidl
package com.example.parcelableserver;

// Declare any non-default types here with import statements
import com.example.parcelableserver.Person;

interface IPerson {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addPerson(in Person person);
   // 定义一个Person对象作为传入参数
   	List<Person> getPersonList();
}
