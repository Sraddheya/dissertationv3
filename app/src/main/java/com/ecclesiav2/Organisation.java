package com.ecclesiav2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Organisation implements Parcelable {
    private String id;
    private String name;
    private String description;

    public Organisation (String id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected Organisation(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Organisation> CREATOR = new Creator<Organisation>() {
        @Override
        public Organisation createFromParcel(Parcel in) {
            return new Organisation(in);
        }

        @Override
        public Organisation[] newArray(int size) {
            return new Organisation[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
    }
}
