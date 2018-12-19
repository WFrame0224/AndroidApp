package com.example.parcelableserver;

import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {

    public String name = "";
    public Double weight = 0.0;

    public Pet(Parcel in) {
        this.name = in.readString();
        this.weight = in.readDouble();
    }

    public Pet(String name, Double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //把该对象所包含的数据写到Parcel
        dest.writeString(this.name);
        dest.writeDouble(this.weight);
    }

    public static Parcelable.Creator<Pet> CREATOR = new Parcelable.Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel source) {
            return new Pet(source);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    @Override
    public String toString() {
        return "Pet [name=" + this.name + ",weight=" + this.weight.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
