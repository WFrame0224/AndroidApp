// IPet.aidl
package com.example.parcelableserver;

// Declare any non-default types here with import statements
import com.example.parcelableserver.Person;
import com.example.parcelableserver.Pet;

interface IPet {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   // 定义一个Person对象作为传入参数
   	List<Pet> getPets(in Person owner);
}
