package com.ecclesiav2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Election implements Parcelable {
    private String elecId;
    private String title;
    private String orgId;
    private String startTime;
    private String endTime;
    private String status;
    private String description;
    private ArrayList<String> options;
    private String optionsDescriptions;
    private int selectedIndex;
    private String question;
    private int needReCast;
    private String joinedTime, castTime, recordedTime, calculatedTime;


    public Election(String elecId, String title, String orgId, String startTime, String endTime, String status, String question, String description, ArrayList<String> options, String optionsDescriptions, int needReCast, String joinedTime) {
        this.elecId = elecId;
        this.title = title;
        this.orgId = orgId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.question = question;
        this.description = description;
        this.options = options;
        this.optionsDescriptions = optionsDescriptions;
        selectedIndex = -1;
        this.needReCast = needReCast;

        this.joinedTime = joinedTime;
        this.castTime = endTime;
        this.recordedTime = endTime;
        this.calculatedTime = LocalDateTime.parse(endTime.replace(" ", "T")).plusMinutes(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    protected Election(Parcel in) {
        elecId = in.readString();
        title = in.readString();
        orgId = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        status = in.readString();
        question = in.readString();
        description = in.readString();
        options = in.createStringArrayList();
        optionsDescriptions = in.readString();
        selectedIndex = in.readInt();
        needReCast = in.readInt();
        castTime = in.readString();
        joinedTime = in.readString();
        recordedTime = in.readString();
        calculatedTime = in.readString();
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        LocalDateTime joinedLDT = LocalDateTime.parse(joinedTime.replace(" ", "T"));
        LocalDateTime startLDT = LocalDateTime.parse(startTime.replace(" ", "T"));
        LocalDateTime endLDT = LocalDateTime.parse(endTime.replace(" ", "T"));
        LocalDateTime castLDT = LocalDateTime.parse(castTime.replace(" ", "T"));
        LocalDateTime recordedLDT = LocalDateTime.parse(recordedTime.replace(" ", "T"));
        LocalDateTime calculatedLDT = LocalDateTime.parse(calculatedTime.replace(" ", "T"));

        if (LocalDateTime.now().isBefore(startLDT)){
            status = "Joined";
        }
        if (LocalDateTime.now().isAfter(startLDT)){
            status = "Voting started";
        }
        if (LocalDateTime.now().isAfter(castLDT)){
            status = "Vote casted";
        }
        if (LocalDateTime.now().isAfter(recordedLDT)){
            if (this.needReCast==1){
                status = "Vote recorded false";
            } else{
                status = "Vote recorded true";
            }
        }
        if (LocalDateTime.now().isAfter(calculatedLDT)){
            status = "Results calculated";
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

    public int getNeedReCast() {
        return needReCast;
    }

    public void setNeedReCast(int needReCast) {
        this.needReCast = needReCast;
    }

    public String getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(String joinedTime) {
        this.joinedTime = joinedTime;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public String getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(String recordedTime) {
        this.recordedTime = recordedTime;
    }

    public String getCalculatedTime() {
        return calculatedTime;
    }

    public void setCalculatedTime(String calculatedTime) {
        this.calculatedTime = calculatedTime;
    }

    @Override
    public String toString() {
        return "Election{" +
                "elecId='" + elecId + '\'' +
                ", title='" + title + '\'' +
                ", orgId='" + orgId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                ", optionsDescriptions='" + optionsDescriptions + '\'' +
                ", selectedIndex=" + selectedIndex +
                ", question='" + question + '\'' +
                ", needReCast=" + needReCast +
                ", joinedTime='" + joinedTime + '\'' +
                ", castTime='" + castTime + '\'' +
                ", recordedTime='" + recordedTime + '\'' +
                ", calculatedTime='" + calculatedTime + '\'' +
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
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeString(status);
        parcel.writeString(question);
        parcel.writeString(description);
        parcel.writeStringList(options);
        parcel.writeString(optionsDescriptions);
        parcel.writeInt(selectedIndex);
        parcel.writeInt(needReCast);
        parcel.writeString(castTime);
        parcel.writeString(joinedTime);
        parcel.writeString(recordedTime);
        parcel.writeString(calculatedTime);
    }
}
