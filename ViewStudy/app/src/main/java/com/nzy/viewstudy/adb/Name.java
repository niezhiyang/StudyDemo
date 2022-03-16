package com.nzy.viewstudy.adb;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author niezhiyang
 * since 2022/3/3
 */
public class Name implements Parcelable {
    public String name;


    protected Name(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };
}
