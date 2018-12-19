package com.example.parcelableserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    public int id = 0;
    public String name = "";
    public String pass = "";

    public Person(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.pass = in.readString();
    }

    public Person(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    // 实现Parcelable接口必须实现的方法
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 把该对象所包含的数据写到Parcel
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pass);
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

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + this.name.hashCode();
        result = prime * result + this.pass.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this instanceof Object) {
            return false;
        }
        Person other = (Person) obj;
        return (this.name == other.name && this.pass == other.pass);
    }

    // 实现Parcelable接口必须实现的方法
    @Override
    public int describeContents() {
        return 0;
    }

}
