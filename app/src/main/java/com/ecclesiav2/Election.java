package com.ecclesiav2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Election implements Parcelable {
    private String elecId;
    private String title;
    private String orgId;
    private String startCast;
    private String endCast;
    private String status;
    private String description;
    private ArrayList<String> options;
    private String optionsDescriptions;
    private int selectedIndex;
    private String question;

    public Election(String elecId, String title, String orgId, String startCast, String endCast, String status, String question, String description, ArrayList<String> options, String optionsDescriptions) {
        this.elecId = elecId;
        this.title = title;
        this.orgId = orgId;
        this.startCast = startCast;
        this.endCast = endCast;
        this.status = status;
        this.question = question;
        this.description = description;
        this.options = options;
        this.optionsDescriptions = optionsDescriptions;
        selectedIndex = -1;
    }

    protected Election(Parcel in) {
        elecId = in.readString();
        title = in.readString();
        orgId = in.readString();
        startCast = in.readString();
        endCast = in.readString();
        status = in.readString();
        question = in.readString();
        description = in.readString();
        options = in.createStringArrayList();
        optionsDescriptions = in.readString();
        selectedIndex = in.readInt();
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

    public String getElecId() {
        return elecId;
    }

    public void setElecId(String elecId) {
        this.elecId = elecId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getOptionsDescriptions() {
        return optionsDescriptions;
    }

    public void setOptionsDescriptions(String optionsDescriptions) {
        this.optionsDescriptions = optionsDescriptions;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public String getStatus() {
        if(this.elecId.equals("0")){
            status = "Voting started";
            selectedIndex = 1;
        }

        if (this.elecId.equals("4")){
            if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 45))){
                if (this.selectedIndex > -1){
                    status = "Vote casted";
                } else {
                    status = "Voting started";
                }
            }

//            if (this.selectedIndex > -1){
//                if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 48))){
//                    status = "Vote recorded true";
//                } if (LocalDateTime.now().isAfter(LocalDateTime.of(2023, 02, 21, 18, 50))){
//                    status = "Results calculated";
//                }
//            }
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Election{" +
                "elecId='" + elecId + '\'' +
                ", title='" + title + '\'' +
                ", orgId='" + orgId + '\'' +
                ", startCast='" + startCast + '\'' +
                ", endCast='" + endCast + '\'' +
                ", status='" + status + '\'' +
                ", question='" + question + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                ", optionsDescriptions=" + optionsDescriptions +
                ", selectedIndex=" + selectedIndex +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(elecId);
        parcel.writeString(title);
        parcel.writeString(orgId);
        parcel.writeString(startCast);
        parcel.writeString(endCast);
        parcel.writeString(status);
        parcel.writeString(question);
        parcel.writeString(description);
        parcel.writeStringList(options);
        parcel.writeString(optionsDescriptions);
        parcel.writeInt(selectedIndex);
    }
}
