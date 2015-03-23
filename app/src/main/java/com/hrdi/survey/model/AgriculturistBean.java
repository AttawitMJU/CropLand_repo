package com.hrdi.survey.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class AgriculturistBean implements Parcelable {

    // Attribute Column Name
    private String agriculturist_id;
    private String card_no;
    private String card_type;
    private String title;
    private String firstname;
    private String lastname;
    private String picture;
    private String home_no;
    private String moo_no;
    private String group_no;
    private String village_no;
    private String tambol_id;
    private String amphur_id;
    private String province_id;
    private String zipcode;
    private String occupation1;
    private String occupation2;
    private String free_time;
    private String member_all;
    private String member_type1;
    private String member_type2;
    private String member_type3;
    private String income_all;
    private String incomes1;
    private String incomes2;
    private String expenses_all;
    private String expenses1;
    private String expenses2;
    private String update_date;
    private String update_by;
    private String remark1;
    private String remark2;

    private String tambol_name;
    private String amphur_name;

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getAmphur_name() {
        return amphur_name;
    }

    public void setAmphur_name(String amphur_name) {
        this.amphur_name = amphur_name;
    }

    public String getTambol_name() {
        return tambol_name;
    }

    public void setTambol_name(String tambol_name) {
        this.tambol_name = tambol_name;
    }

    private String province_name;

    public AgriculturistBean() {
        super();
    }

    public AgriculturistBean(Parcel in) {
        super();

        this.agriculturist_id = in.readString();
        this.card_no = in.readString();
        this.card_type = in.readString();
        this.title = in.readString();
        this.firstname = in.readString();
        this.lastname = in.readString();
        this.picture = in.readString();
        this.home_no = in.readString();
        this.moo_no = in.readString();
        this.group_no = in.readString();
        this.village_no = in.readString();
        this.tambol_id = in.readString();
        this.amphur_id = in.readString();
        this.province_id = in.readString();
        this.zipcode = in.readString();
        this.occupation1 = in.readString();
        this.occupation2 = in.readString();
        this.free_time = in.readString();
        this.member_all = in.readString();
        this.member_type1 = in.readString();
        this.member_type2 = in.readString();
        this.member_type3 = in.readString();
        this.income_all = in.readString();
        this.incomes1 = in.readString();
        this.incomes2 = in.readString();
        this.expenses_all = in.readString();
        this.expenses1 = in.readString();
        this.expenses2 = in.readString();
        this.update_date = in.readString();
        this.update_by = in.readString();
        this.remark1 = in.readString();
        this.remark2 = in.readString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AgriculturistBean other = (AgriculturistBean) obj;
        if (agriculturist_id != other.agriculturist_id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AgriculturistBean{");
        sb.append("agriculturist_id='").append(agriculturist_id).append('\'');
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", card_type='").append(card_type).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", home_no='").append(home_no).append('\'');
        sb.append(", moo_no='").append(moo_no).append('\'');
        sb.append(", group_no='").append(group_no).append('\'');
        sb.append(", village_no='").append(village_no).append('\'');
        sb.append(", tambol_id='").append(tambol_id).append('\'');
        sb.append(", amphur_id='").append(amphur_id).append('\'');
        sb.append(", province_id='").append(province_id).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append(", occupation1='").append(occupation1).append('\'');
        sb.append(", occupation2='").append(occupation2).append('\'');
        sb.append(", free_time='").append(free_time).append('\'');
        sb.append(", member_all='").append(member_all).append('\'');
        sb.append(", member_type1='").append(member_type1).append('\'');
        sb.append(", member_type2='").append(member_type2).append('\'');
        sb.append(", member_type3='").append(member_type3).append('\'');
        sb.append(", income_all='").append(income_all).append('\'');
        sb.append(", incomes1='").append(incomes1).append('\'');
        sb.append(", incomes2='").append(incomes2).append('\'');
        sb.append(", expenses_all='").append(expenses_all).append('\'');
        sb.append(", expenses1='").append(expenses1).append('\'');
        sb.append(", expenses2='").append(expenses2).append('\'');
        sb.append(", update_date='").append(update_date).append('\'');
        sb.append(", update_by='").append(update_by).append('\'');
        sb.append(", remark1='").append(remark1).append('\'');
        sb.append(", remark2='").append(remark2).append('\'');
        sb.append(", tambol_name='").append(tambol_name).append('\'');
        sb.append(", amphur_name='").append(amphur_name).append('\'');
        sb.append(", province_name='").append(province_name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {


        parcel.writeString(this.agriculturist_id);
        parcel.writeString(this.card_no);
        parcel.writeString(this.card_type);
        parcel.writeString(this.title);
        parcel.writeString(this.firstname);
        parcel.writeString(this.lastname);
        parcel.writeString(this.picture);
        parcel.writeString(this.home_no);
        parcel.writeString(this.moo_no);
        parcel.writeString(this.group_no);
        parcel.writeString(this.village_no);
        parcel.writeString(this.tambol_id);
        parcel.writeString(this.amphur_id);
        parcel.writeString(this.province_id);
        parcel.writeString(this.zipcode);
        parcel.writeString(this.occupation1);
        parcel.writeString(this.occupation2);
        parcel.writeString(this.free_time);
        parcel.writeString(this.member_all);
        parcel.writeString(this.member_type1);
        parcel.writeString(this.member_type2);
        parcel.writeString(this.member_type3);
        parcel.writeString(this.income_all);
        parcel.writeString(this.incomes1);
        parcel.writeString(this.incomes2);
        parcel.writeString(this.expenses_all);
        parcel.writeString(this.expenses1);
        parcel.writeString(this.expenses2);
        parcel.writeString(this.update_date);
        parcel.writeString(this.update_by);
        parcel.writeString(this.remark1);
        parcel.writeString(this.remark2);

    }

    public String getAgriculturist_id() {
        return agriculturist_id;
    }

    public void setAgriculturist_id(String agriculturist_id) {
        this.agriculturist_id = agriculturist_id;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getHome_no() {
        return home_no;
    }

    public void setHome_no(String home_no) {
        this.home_no = home_no;
    }

    public String getMoo_no() {
        return moo_no;
    }

    public void setMoo_no(String moo_no) {
        this.moo_no = moo_no;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getVillage_no() {
        return village_no;
    }

    public void setVillage_no(String village_no) {
        this.village_no = village_no;
    }

    public String getTambol_id() {
        return tambol_id;
    }

    public void setTambol_id(String tambol_id) {
        this.tambol_id = tambol_id;
    }

    public String getAmphur_id() {
        return amphur_id;
    }

    public void setAmphur_id(String amphur_id) {
        this.amphur_id = amphur_id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getOccupation1() {
        return occupation1;
    }

    public void setOccupation1(String occupation1) {
        this.occupation1 = occupation1;
    }

    public String getOccupation2() {
        return occupation2;
    }

    public void setOccupation2(String occupation2) {
        this.occupation2 = occupation2;
    }

    public String getFree_time() {
        return free_time;
    }

    public void setFree_time(String free_time) {
        this.free_time = free_time;
    }

    public String getMember_all() {
        return member_all;
    }

    public void setMember_all(String member_all) {
        this.member_all = member_all;
    }

    public String getMember_type1() {
        return member_type1;
    }

    public void setMember_type1(String member_type1) {
        this.member_type1 = member_type1;
    }

    public String getMember_type2() {
        return member_type2;
    }

    public void setMember_type2(String member_type2) {
        this.member_type2 = member_type2;
    }

    public String getMember_type3() {
        return member_type3;
    }

    public void setMember_type3(String member_type3) {
        this.member_type3 = member_type3;
    }

    public String getIncome_all() {
        return income_all;
    }

    public void setIncome_all(String income_all) {
        this.income_all = income_all;
    }

    public String getIncomes1() {
        return incomes1;
    }

    public void setIncomes1(String incomes1) {
        this.incomes1 = incomes1;
    }

    public String getIncomes2() {
        return incomes2;
    }

    public void setIncomes2(String incomes2) {
        this.incomes2 = incomes2;
    }

    public String getExpenses_all() {
        return expenses_all;
    }

    public void setExpenses_all(String expenses_all) {
        this.expenses_all = expenses_all;
    }

    public String getExpenses1() {
        return expenses1;
    }

    public void setExpenses1(String expenses1) {
        this.expenses1 = expenses1;
    }

    public String getExpenses2() {
        return expenses2;
    }

    public void setExpenses2(String expenses2) {
        this.expenses2 = expenses2;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
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
}
