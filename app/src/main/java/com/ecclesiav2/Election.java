package com.ecclesiav2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Election implements Parcelable {
    private String id;
    private String title;
    private String orgId;
    private String startCast;
    private String endCast;
    private String status;
    private String description;
    private ArrayList<String> options;
    private ArrayList<String> optionsDescriptions;

    public Election(String id, String title, String orgId, String startCast, String endCast, String status, String description, ArrayList<String> options, ArrayList<String> optionsDescriptions) {
        this.id = id;
        this.title = title;
        this.orgId = orgId;
        this.startCast = startCast;
        this.endCast = endCast;
        this.status = status;
        this.description = description;
        this.options = options;
        this.optionsDescriptions = optionsDescriptions;
    }

    protected Election(Parcel in) {
        id = in.readString();
        title = in.readString();
        orgId = in.readString();
        startCast = in.readString();
        endCast = in.readString();
        status = in.readString();
        description = in.readString();
        options = in.createStringArrayList();
        optionsDescriptions = in.createStringArrayList();
    }

    public static final Creator<Election> CREATOR = new Creator<Election>() {
        @Override
        public Election createFromParcel(Parcel in) {
            return new Election(in);
        }

        @Override
        public Election[] newArray(int size) {
            return new Election[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStartCast() {
        return startCast;
    }

    public void setStartCast(String startCast) {
        this.startCast = startCast;
    }

    public String getEndCast() {
        return endCast;
    }

    public void setEndCast(String endCast) {
        this.endCast = endCast;
    }

    public String getStatus() {
        if (this.id.equals("0")){
            status = "Joined";
//            if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 45))){
//                status = "Voting started";
//            } if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 46))){
//                status = "Vote casted";
//            } if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 48))){
//                status = "Vote recorded true";
//            } if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 50))){
//                status = "Results calculated";
//            }
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Election{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", orgId='" + orgId + '\'' +
                ", startCast='" + startCast + '\'' +
                ", endCast='" + endCast + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                ", optionsDescriptions=" + optionsDescriptions +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(orgId);
        parcel.writeString(startCast);
        parcel.writeString(endCast);
        parcel.writeString(status);
        parcel.writeString(description);
        parcel.writeStringList(options);
        parcel.writeStringList(optionsDescriptions);
    }
}
