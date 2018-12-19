package com.example.parcelableserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private String name;
    public int age;
    public int number;

    public Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.number = in.readInt();
    }

    public Person(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    // 实现Parcelable接口必须实现的方法
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 把该对象所包含的数据写到Parcel
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeInt(number);
    }

    // 添加一个静态成员，名为CREATOR，该对象实现了Parcelable.Creator接口
    public static Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            // 从Parcel中读取数据，恢复Person对象
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    // 实现Parcelable接口必须实现的方法
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name +
                ", age=" + age +
                ", number=" + number +
                '}';
    }

}
