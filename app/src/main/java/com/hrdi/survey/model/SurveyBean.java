package com.hrdi.survey.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyBean implements Parcelable {


    public static final Parcelable.Creator<SurveyBean> CREATOR = new Parcelable.Creator<SurveyBean>() {
        public SurveyBean createFromParcel(Parcel in) {
            return new SurveyBean(in);
        }

        public SurveyBean[] newArray(int size) {
            return new SurveyBean[size];
        }
    };

    // Attribute
    private String survey_id;
    private String land_No;
    private String ext_Project;
    private String ext_Project_name;
    private String latlong;
    private String card_no;
    private String land_doc_type;
    private String land_doc_type_name;
    private String area_status;
    private String area_status_year;
    private String firstName;
    private String lastName;
    private String card_type;
    private String card_type_name;
    private String address;
    private String survey_Date;
    private String owner_Type;
    private String owner_Type_Detail;
    private String institute_Support;
    private String water;
    private String water_Period;
    private String water_Use;
    private String soil_moisture;
    private String temperature;
    private String hasActivity;
    private String activity1;
    private String repeat1;
    private String outcome1;
    private String survive1;
    private String activity2;
    private String repeat2;
    private String outcome2;
    private String survive2;
    private String activity3;
    private String repeat3;
    private String outcome3;
    private String survive3;
    private String activity4;
    private String repeat4;
    private String outcome4;
    private String survive4;
    private String hasOtherSupport;
    private String org1;
    private String org2;
    private String org3;
    private String problem1;
    private String problem2;
    private String problem3;
    private String request1;
    private String request2;
    private String request3;
    private String history;
    private String picture1;
    private String picture2;
    private String picture3;
    private String update_Date;
    private String update_By;
    private String remark1;
    private String remark2;
    private String mooban;
    private String staff;
    private AgriculturistBean agriculturistBean;
    private ArrayList<LandUseBean> landuseList = new ArrayList<>();

    public SurveyBean() {
        super();
    }

    public SurveyBean(Parcel in) {
        super();
        this.survey_id = in.readString();
        this.land_No = in.readString();
        this.ext_Project = in.readString();
        this.latlong = in.readString();
        this.card_no = in.readString();
        this.land_doc_type = in.readString();
        this.area_status = in.readString();
        this.area_status_year = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.card_type = in.readString();
        this.survey_Date = in.readString();
        this.owner_Type = in.readString();
        this.owner_Type_Detail = in.readString();
        this.institute_Support = in.readString();
        this.water = in.readString();
        this.water_Period = in.readString();
        this.water_Use = in.readString();
        this.soil_moisture = in.readString();
        this.temperature = in.readString();
        this.hasActivity = in.readString();
        this.activity1 = in.readString();
        this.repeat1 = in.readString();
        this.outcome1 = in.readString();
        this.survive1 = in.readString();
        this.activity2 = in.readString();
        this.repeat2 = in.readString();
        this.outcome2 = in.readString();
        this.survive2 = in.readString();
        this.activity3 = in.readString();
        this.repeat3 = in.readString();
        this.outcome3 = in.readString();
        this.survive3 = in.readString();
        this.activity4 = in.readString();
        this.repeat4 = in.readString();
        this.outcome4 = in.readString();
        this.survive4 = in.readString();
        this.hasOtherSupport = in.readString();
        this.org1 = in.readString();
        this.org2 = in.readString();
        this.org3 = in.readString();
        this.problem1 = in.readString();
        this.problem2 = in.readString();
        this.problem3 = in.readString();
        this.request1 = in.readString();
        this.request2 = in.readString();
        this.request3 = in.readString();
        this.history = in.readString();
        this.picture1 = in.readString();
        this.picture2 = in.readString();
        this.picture3 = in.readString();
        this.update_Date = in.readString();
        this.update_By = in.readString();
        this.remark1 = in.readString();
        this.remark2 = in.readString();

        this.mooban = in.readString();

        this.landuseList = in.readArrayList(LandUseBean.class.getClassLoader());

    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLand_doc_type_name() {
        return land_doc_type_name;
    }

    public void setLand_doc_type_name(String land_doc_type_name) {
        this.land_doc_type_name = land_doc_type_name;
    }

    public String getCard_type_name() {
        return card_type_name;
    }

    public void setCard_type_name(String card_type_name) {
        this.card_type_name = card_type_name;
    }

    public String getExt_Project_name() {
        return ext_Project_name;
    }

    public void setExt_Project_name(String ext_Project_name) {
        this.ext_Project_name = ext_Project_name;
    }

    public String getMooban() {
        return mooban;
    }

    public void setMooban(String mooban) {
        this.mooban = mooban;
    }

    public AgriculturistBean getAgriculturistBean() {
        return agriculturistBean;
    }

    public void setAgriculturistBean(AgriculturistBean agriculturistBean) {
        this.agriculturistBean = agriculturistBean;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SurveyBean{");
        sb.append("survey_id='").append(survey_id).append('\'');
        sb.append(", land_No='").append(land_No).append('\'');
        sb.append(", ext_Project='").append(ext_Project).append('\'');
        sb.append(", ext_Project_name='").append(ext_Project_name).append('\'');
        sb.append(", latlong='").append(latlong).append('\'');
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", land_doc_type='").append(land_doc_type).append('\'');
        sb.append(", land_doc_type_name='").append(land_doc_type_name).append('\'');
        sb.append(", area_status='").append(area_status).append('\'');
        sb.append(", area_status_year='").append(area_status_year).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", card_type='").append(card_type).append('\'');
        sb.append(", card_type_name='").append(card_type_name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", survey_Date='").append(survey_Date).append('\'');
        sb.append(", owner_Type='").append(owner_Type).append('\'');
        sb.append(", owner_Type_Detail='").append(owner_Type_Detail).append('\'');
        sb.append(", institute_Support='").append(institute_Support).append('\'');
        sb.append(", water='").append(water).append('\'');
        sb.append(", water_Period='").append(water_Period).append('\'');
        sb.append(", water_Use='").append(water_Use).append('\'');
        sb.append(", soil_moisture='").append(soil_moisture).append('\'');
        sb.append(", temperature='").append(temperature).append('\'');
        sb.append(", hasActivity='").append(hasActivity).append('\'');
        sb.append(", activity1='").append(activity1).append('\'');
        sb.append(", repeat1='").append(repeat1).append('\'');
        sb.append(", outcome1='").append(outcome1).append('\'');
        sb.append(", survive1='").append(survive1).append('\'');
        sb.append(", activity2='").append(activity2).append('\'');
        sb.append(", repeat2='").append(repeat2).append('\'');
        sb.append(", outcome2='").append(outcome2).append('\'');
        sb.append(", survive2='").append(survive2).append('\'');
        sb.append(", activity3='").append(activity3).append('\'');
        sb.append(", repeat3='").append(repeat3).append('\'');
        sb.append(", outcome3='").append(outcome3).append('\'');
        sb.append(", survive3='").append(survive3).append('\'');
        sb.append(", activity4='").append(activity4).append('\'');
        sb.append(", repeat4='").append(repeat4).append('\'');
        sb.append(", outcome4='").append(outcome4).append('\'');
        sb.append(", survive4='").append(survive4).append('\'');
        sb.append(", hasOtherSupport='").append(hasOtherSupport).append('\'');
        sb.append(", org1='").append(org1).append('\'');
        sb.append(", org2='").append(org2).append('\'');
        sb.append(", org3='").append(org3).append('\'');
        sb.append(", problem1='").append(problem1).append('\'');
        sb.append(", problem2='").append(problem2).append('\'');
        sb.append(", problem3='").append(problem3).append('\'');
        sb.append(", request1='").append(request1).append('\'');
        sb.append(", request2='").append(request2).append('\'');
        sb.append(", request3='").append(request3).append('\'');
        sb.append(", history='").append(history).append('\'');
        sb.append(", picture1='").append(picture1).append('\'');
        sb.append(", picture2='").append(picture2).append('\'');
        sb.append(", picture3='").append(picture3).append('\'');
        sb.append(", update_Date='").append(update_Date).append('\'');
        sb.append(", update_By='").append(update_By).append('\'');
        sb.append(", remark1='").append(remark1).append('\'');
        sb.append(", remark2='").append(remark2).append('\'');
        sb.append(", mooban='").append(mooban).append('\'');
        sb.append(", staff='").append(staff).append('\'');
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
        SurveyBean other = (SurveyBean) obj;
        if (survey_id != other.survey_id)
            return false;
        return true;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(this.survey_id);
        parcel.writeString(this.land_No);
        parcel.writeString(this.ext_Project);
        parcel.writeString(this.latlong);
        parcel.writeString(this.card_no);
        parcel.writeString(this.land_doc_type);
        parcel.writeString(this.area_status);
        parcel.writeString(this.area_status_year);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeString(this.card_type);
        parcel.writeString(this.survey_Date);
        parcel.writeString(this.owner_Type);
        parcel.writeString(this.owner_Type_Detail);
        parcel.writeString(this.institute_Support);
        parcel.writeString(this.water);
        parcel.writeString(this.water_Period);
        parcel.writeString(this.water_Use);
        parcel.writeString(this.soil_moisture);
        parcel.writeString(this.temperature);
        parcel.writeString(this.hasActivity);
        parcel.writeString(this.activity1);
        parcel.writeString(this.repeat1);
        parcel.writeString(this.outcome1);
        parcel.writeString(this.survive1);
        parcel.writeString(this.activity2);
        parcel.writeString(this.repeat2);
        parcel.writeString(this.outcome2);
        parcel.writeString(this.survive2);
        parcel.writeString(this.activity3);
        parcel.writeString(this.repeat3);
        parcel.writeString(this.outcome3);
        parcel.writeString(this.survive3);
        parcel.writeString(this.activity4);
        parcel.writeString(this.repeat4);
        parcel.writeString(this.outcome4);
        parcel.writeString(this.survive4);
        parcel.writeString(this.hasOtherSupport);
        parcel.writeString(this.org1);
        parcel.writeString(this.org2);
        parcel.writeString(this.org3);
        parcel.writeString(this.problem1);
        parcel.writeString(this.problem2);
        parcel.writeString(this.problem3);
        parcel.writeString(this.request1);
        parcel.writeString(this.request2);
        parcel.writeString(this.request3);
        parcel.writeString(this.history);
        parcel.writeString(this.picture1);
        parcel.writeString(this.picture2);
        parcel.writeString(this.picture3);
        parcel.writeString(this.update_Date);
        parcel.writeString(this.update_By);
        parcel.writeString(this.remark1);
        parcel.writeString(this.remark2);

        parcel.writeString(this.mooban);

        // parcel.writeParcelable(getDepartment(), flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getArea_status_year() {
        if (area_status_year == null || "".equals(area_status_year))
            area_status_year = "0";

        return area_status_year;
    }

    public void setArea_status_year(String area_status_year) {
        this.area_status_year = area_status_year;
    }

    public String getArea_status() {
        return area_status;
    }

    public void setArea_status(String area_status) {
        this.area_status = area_status;
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

    public String getExt_Project() {

        if (ext_Project == null || "".equals(ext_Project))
            ext_Project = "0";
        return ext_Project;
    }

    public void setExt_Project(String ext_Project) {
        this.ext_Project = ext_Project;
    }

    public String getLatlong() {

        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getLand_doc_type() {
        if (land_doc_type == null || "".equals(land_doc_type) || "null".equals(land_doc_type))
            land_doc_type = "0";
        return land_doc_type;
    }

    public void setLand_doc_type(String land_doc_type) {
        this.land_doc_type = land_doc_type;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getSurvey_Date() {
        return survey_Date;
    }

    public void setSurvey_Date(String survey_Date) {
        this.survey_Date = survey_Date;
    }

    public String getOwner_Type() {
        if (owner_Type == null || "".equals(owner_Type))
            owner_Type = "0";
        return owner_Type;
    }

    public void setOwner_Type(String owner_Type) {
        this.owner_Type = owner_Type;
    }

    public String getOwner_Type_Detail() {
        return owner_Type_Detail;
    }

    public void setOwner_Type_Detail(String owner_Type_Detail) {
        this.owner_Type_Detail = owner_Type_Detail;
    }

    public String getInstitute_Support() {
        return institute_Support;
    }

    public void setInstitute_Support(String institute_Support) {
        this.institute_Support = institute_Support;
    }

    public String getWater() {
        if (water == null || "".equals(water))
            water = "0";
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getWater_Period() {
        return water_Period;
    }

    public void setWater_Period(String water_Period) {
        this.water_Period = water_Period;
    }

    public String getWater_Use() {
        return water_Use;
    }

    public void setWater_Use(String water_Use) {
        this.water_Use = water_Use;
    }

    public String getSoil_moisture() {
        return soil_moisture;
    }

    public void setSoil_moisture(String soil_moisture) {
        this.soil_moisture = soil_moisture;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHasActivity() {
        return hasActivity;
    }

    public void setHasActivity(String hasActivity) {
        this.hasActivity = hasActivity;
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

    public String getActivity2() {
        return activity2;
    }

    public void setActivity2(String activity2) {
        this.activity2 = activity2;
    }

    public String getRepeat2() {
        if (repeat2 == null || "".equals(repeat2))
            repeat2 = "0";
        return repeat2;
    }

    public void setRepeat2(String repeat2) {
        this.repeat2 = repeat2;
    }

    public String getOutcome2() {
        return outcome2;
    }

    public void setOutcome2(String outcome2) {
        this.outcome2 = outcome2;
    }

    public String getSurvive2() {
        return survive2;
    }

    public void setSurvive2(String survive2) {
        this.survive2 = survive2;
    }

    public String getActivity3() {
        return activity3;
    }

    public void setActivity3(String activity3) {
        this.activity3 = activity3;
    }

    public String getRepeat3() {
        if (repeat3 == null || "".equals(repeat3))
            repeat3 = "0";
        return repeat3;
    }

    public void setRepeat3(String repeat3) {
        this.repeat3 = repeat3;
    }

    public String getOutcome3() {
        return outcome3;
    }

    public void setOutcome3(String outcome3) {
        this.outcome3 = outcome3;
    }

    public String getSurvive3() {
        return survive3;
    }

    public void setSurvive3(String survive3) {
        this.survive3 = survive3;
    }

    public String getActivity4() {
        return activity4;
    }

    public void setActivity4(String activity4) {
        this.activity4 = activity4;
    }

    public String getRepeat4() {

        if (repeat4 == null || "".equals(repeat4))
            repeat4 = "0";
        return repeat4;
    }

    public void setRepeat4(String repeat4) {
        this.repeat4 = repeat4;
    }

    public String getOutcome4() {
        return outcome4;
    }

    public void setOutcome4(String outcome4) {
        this.outcome4 = outcome4;
    }

    public String getSurvive4() {
        return survive4;
    }

    public void setSurvive4(String survive4) {
        this.survive4 = survive4;
    }

    public String getHasOtherSupport() {
        if (hasOtherSupport == null || "".equals(hasOtherSupport) || "null".equals(hasOtherSupport))
            hasOtherSupport = "0";
        else
            hasOtherSupport = "1";
        return hasOtherSupport;
    }

    public void setHasOtherSupport(String hasOtherSupport) {
        this.hasOtherSupport = hasOtherSupport;
    }

    public String getOrg1() {
        return org1;
    }

    public void setOrg1(String org1) {
        this.org1 = org1;
    }

    public String getOrg2() {
        return org2;
    }

    public void setOrg2(String org2) {
        this.org2 = org2;
    }

    public String getOrg3() {
        return org3;
    }

    public void setOrg3(String org3) {
        this.org3 = org3;
    }

    public String getProblem1() {
        return problem1;
    }

    public void setProblem1(String problem1) {
        this.problem1 = problem1;
    }

    public String getProblem2() {
        return problem2;
    }

    public void setProblem2(String problem2) {
        this.problem2 = problem2;
    }

    public String getProblem3() {
        return problem3;
    }

    public void setProblem3(String problem3) {
        this.problem3 = problem3;
    }

    public String getRequest1() {
        return request1;
    }

    public void setRequest1(String request1) {
        this.request1 = request1;
    }

    public String getRequest2() {
        return request2;
    }

    public void setRequest2(String request2) {
        this.request2 = request2;
    }

    public String getRequest3() {
        return request3;
    }

    public void setRequest3(String request3) {
        this.request3 = request3;
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

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public ArrayList<LandUseBean> getLanduseList() {
        return landuseList;
    }

    public void setLanduseList(ArrayList<LandUseBean> landuseList) {
        this.landuseList = landuseList;
    }


}
