package com.hrdi.survey.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyActivityBean implements Parcelable {


    public static final Creator<SurveyActivityBean> CREATOR = new Creator<SurveyActivityBean>() {
        public SurveyActivityBean createFromParcel(Parcel in) {
            return new SurveyActivityBean(in);
        }

        public SurveyActivityBean[] newArray(int size) {
            return new SurveyActivityBean[size];
        }
    };

    // Attribute
    private String activity_id;
    private String survey_id;
    private String land_No;
    private String card_no;
    private String activity1;
    private String repeat1;
    private String outcome1;
    private String survive1;
    private String update_Date;
    private String update_By;

    public SurveyActivityBean() {

    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public SurveyActivityBean(Parcel in) {
        super();
        this.survey_id = in.readString();
        this.land_No = in.readString();
        this.card_no = in.readString();
        this.activity1 = in.readString();
        this.repeat1 = in.readString();
        this.outcome1 = in.readString();
        this.survive1 = in.readString();
        this.update_Date = in.readString();
        this.update_By = in.readString();

    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SurveyBean{");
        sb.append("survey_id='").append(survey_id).append('\'');
        sb.append(", land_No='").append(land_No).append('\'');
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", activity1='").append(activity1).append('\'');
        sb.append(", repeat1='").append(repeat1).append('\'');
        sb.append(", outcome1='").append(outcome1).append('\'');
        sb.append(", survive1='").append(survive1).append('\'');
        sb.append(", update_Date='").append(update_Date).append('\'');
        sb.append(", update_By='").append(update_By).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SurveyActivityBean other = (SurveyActivityBean) obj;
        if (survey_id != other.survey_id)
            return false;
        return true;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(this.survey_id);
        parcel.writeString(this.land_No);
        parcel.writeString(this.card_no);
        parcel.writeString(this.activity1);
        parcel.writeString(this.repeat1);
        parcel.writeString(this.outcome1);
        parcel.writeString(this.survive1);
        parcel.writeString(this.update_Date);
        parcel.writeString(this.update_By);

        // parcel.writeParcelable(getDepartment(), flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getLand_No() {
        return land_No;
    }

    public void setLand_No(String land_No) {
        this.land_No = land_No;
    }


    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }


    public String getActivity1() {
        return activity1;
    }

    public void setActivity1(String activity1) {
        this.activity1 = activity1;
    }

    public String getRepeat1() {
        if (repeat1 == null || "".equals(repeat1))
            repeat1 = "0";
        return repeat1;
    }

    public void setRepeat1(String repeat1) {
        this.repeat1 = repeat1;
    }

    public String getOutcome1() {
        return outcome1;
    }

    public void setOutcome1(String outcome1) {
        this.outcome1 = outcome1;
    }

    public String getSurvive1() {
        return survive1;
    }

    public void setSurvive1(String survive1) {
        this.survive1 = survive1;
    }

    public String getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        this.update_Date = update_Date;
    }

    public String getUpdate_By() {
        if (update_By == null || "".equals(update_By))
            update_By = "0";

        return update_By;
    }

    public void setUpdate_By(String update_By) {
        this.update_By = update_By;
    }


}
