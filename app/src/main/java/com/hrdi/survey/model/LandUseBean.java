package com.hrdi.survey.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class LandUseBean implements Parcelable {
    public static final Parcelable.Creator<LandUseBean> CREATOR = new Parcelable.Creator<LandUseBean>() {
        public LandUseBean createFromParcel(Parcel in) {
            return new LandUseBean(in);
        }

        public LandUseBean[] newArray(int size) {
            return new LandUseBean[size];
        }
    };


    private String landUse_ID;
    private String land_No;
    private String card_no;
    private String survey_ID;
    private String survey_Date;
    private String plant_Type;
    private String plant_TypeName;
    private String plant_Year;
    private String area;
    private String harvest_Status;
    private String seeds;
    private String has_Hire;
    private String labour;
    private String fuel;
    private String other_Paid;
    private String product_Use;
    private String product_Sale;
    private String price;
    private String income_Year;
    private String market;
    private String employ_Type;
    private String employ_From;
    private String labour_Total;
    private String labour_Paid;
    private String labour_Time;


    private String start_crop;
    private String end_crop;
    private String plant_id;
    private String plant_idName;
    private String plant_detail_id;
    private String plant_detail_idName;

    // ปุ๋ย
    private String fertilizer1;
    private String fertilizer_code1;
    private String fertilizer_total1;
    private String fertilizer_price1;
    private String fertilizer_sum1;
    private String fertilizer2;
    private String fertilizer_code2;
    private String fertilizer_total2;
    private String fertilizer_price2;
    private String fertilizer_sum2;
    private String fertilizer3;
    private String fertilizer_code3;
    private String fertilizer_total3;
    private String fertilizer_price3;
    private String fertilizer_sum3;
    // ยา/ฮอร์โมน
    private String hormone_type1;
    private String hormone_brand1;
    private String hormone_total1;
    private String hormone_price1;
    private String hormone_sum1;
    private String hormone_type2;
    private String hormone_brand2;
    private String hormone_total2;
    private String hormone_price2;
    private String hormone_sum2;
    private String hormone_type3;
    private String hormone_brand3;
    private String hormone_total3;
    private String hormone_price3;
    private String hormone_sum3;
    private String Update_Date;
    private String Update_By;
    private String Remark1;
    private String Remark2;

    public LandUseBean() {
        super();
    }

    private LandUseBean(Parcel in) {
        super();
        this.landUse_ID = in.readString();
        this.land_No = in.readString();
        this.card_no = in.readString();
        this.survey_ID = in.readString();
        this.survey_Date = in.readString();

        this.plant_Type = in.readString();
        this.plant_Year = in.readString();
        this.area = in.readString();
        this.harvest_Status = in.readString();
        this.seeds = in.readString();
        this.has_Hire = in.readString();
        this.labour = in.readString();
        this.fuel = in.readString();
        this.other_Paid = in.readString();
        this.product_Use = in.readString();
        this.product_Sale = in.readString();
        this.price = in.readString();
        this.income_Year = in.readString();
        this.market = in.readString();
        this.employ_Type = in.readString();
        this.employ_From = in.readString();
        this.labour_Total = in.readString();
        this.labour_Paid = in.readString();
        this.labour_Time = in.readString();

        this.start_crop = in.readString();
        this.end_crop = in.readString();
        this.plant_id = in.readString();
        this.plant_detail_id = in.readString();


        // ปุ๋ย
        this.fertilizer1 = in.readString();
        this.fertilizer_code1 = in.readString();
        this.fertilizer_total1 = in.readString();
        this.fertilizer_price1 = in.readString();
        this.fertilizer_sum1 = in.readString();

        this.fertilizer2 = in.readString();
        this.fertilizer_code2 = in.readString();
        this.fertilizer_total2 = in.readString();
        this.fertilizer_price2 = in.readString();
        this.fertilizer_sum2 = in.readString();

        this.fertilizer3 = in.readString();
        this.fertilizer_code3 = in.readString();
        this.fertilizer_total3 = in.readString();
        this.fertilizer_price3 = in.readString();
        this.fertilizer_sum3 = in.readString();

        // ยา/ฮอร์โมน
        this.hormone_type1 = in.readString();
        this.hormone_brand1 = in.readString();
        this.hormone_total1 = in.readString();
        this.hormone_price1 = in.readString();
        this.hormone_sum1 = in.readString();

        this.hormone_type2 = in.readString();
        this.hormone_brand2 = in.readString();
        this.hormone_total2 = in.readString();
        this.hormone_price2 = in.readString();
        this.hormone_sum2 = in.readString();

        this.hormone_type3 = in.readString();
        this.hormone_brand3 = in.readString();
        this.hormone_total3 = in.readString();
        this.hormone_price3 = in.readString();
        this.hormone_sum3 = in.readString();


        this.survey_ID = in.readString();
        this.Update_Date = in.readString();
        this.Update_By = in.readString();
        this.Remark1 = in.readString();
        this.Remark2 = in.readString();

    }

    private String toZero(String str) {
        String value = str;
        if (str == null || "".equals(str) || "null".equals(str))
            value = "0";
        return value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LandUseBean{");
        sb.append("landUse_ID='").append(landUse_ID).append('\'');
        sb.append(", land_No='").append(land_No).append('\'');
        sb.append(", card_no='").append(card_no).append('\'');
        sb.append(", survey_ID='").append(survey_ID).append('\'');
        sb.append(", survey_Date='").append(survey_Date).append('\'');
        sb.append(", plant_Type='").append(plant_Type).append('\'');
        sb.append(", plant_TypeName='").append(plant_TypeName).append('\'');
        sb.append(", plant_Year='").append(plant_Year).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", harvest_Status='").append(harvest_Status).append('\'');
        sb.append(", seeds='").append(seeds).append('\'');
        sb.append(", has_Hire='").append(has_Hire).append('\'');
        sb.append(", labour='").append(labour).append('\'');
        sb.append(", fuel='").append(fuel).append('\'');
        sb.append(", other_Paid='").append(other_Paid).append('\'');
        sb.append(", product_Use='").append(product_Use).append('\'');
        sb.append(", product_Sale='").append(product_Sale).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", income_Year='").append(income_Year).append('\'');
        sb.append(", market='").append(market).append('\'');
        sb.append(", employ_Type='").append(employ_Type).append('\'');
        sb.append(", employ_From='").append(employ_From).append('\'');
        sb.append(", labour_Total='").append(labour_Total).append('\'');
        sb.append(", labour_Paid='").append(labour_Paid).append('\'');
        sb.append(", labour_Time='").append(labour_Time).append('\'');
        sb.append(", start_crop='").append(start_crop).append('\'');
        sb.append(", end_crop='").append(end_crop).append('\'');
        sb.append(", plant_id='").append(plant_id).append('\'');
        sb.append(", plant_idName='").append(plant_idName).append('\'');
        sb.append(", plant_detail_id='").append(plant_detail_id).append('\'');
        sb.append(", plant_detail_idName='").append(plant_detail_idName).append('\'');
        sb.append(", fertilizer1='").append(fertilizer1).append('\'');
        sb.append(", fertilizer_code1='").append(fertilizer_code1).append('\'');
        sb.append(", fertilizer_total1='").append(fertilizer_total1).append('\'');
        sb.append(", fertilizer_price1='").append(fertilizer_price1).append('\'');
        sb.append(", fertilizer_sum1='").append(fertilizer_sum1).append('\'');
        sb.append(", fertilizer2='").append(fertilizer2).append('\'');
        sb.append(", fertilizer_code2='").append(fertilizer_code2).append('\'');
        sb.append(", fertilizer_total2='").append(fertilizer_total2).append('\'');
        sb.append(", fertilizer_price2='").append(fertilizer_price2).append('\'');
        sb.append(", fertilizer_sum2='").append(fertilizer_sum2).append('\'');
        sb.append(", fertilizer3='").append(fertilizer3).append('\'');
        sb.append(", fertilizer_code3='").append(fertilizer_code3).append('\'');
        sb.append(", fertilizer_total3='").append(fertilizer_total3).append('\'');
        sb.append(", fertilizer_price3='").append(fertilizer_price3).append('\'');
        sb.append(", fertilizer_sum3='").append(fertilizer_sum3).append('\'');
        sb.append(", hormone_type1='").append(hormone_type1).append('\'');
        sb.append(", hormone_brand1='").append(hormone_brand1).append('\'');
        sb.append(", hormone_total1='").append(hormone_total1).append('\'');
        sb.append(", hormone_price1='").append(hormone_price1).append('\'');
        sb.append(", hormone_sum1='").append(hormone_sum1).append('\'');
        sb.append(", hormone_type2='").append(hormone_type2).append('\'');
        sb.append(", hormone_brand2='").append(hormone_brand2).append('\'');
        sb.append(", hormone_total2='").append(hormone_total2).append('\'');
        sb.append(", hormone_price2='").append(hormone_price2).append('\'');
        sb.append(", hormone_sum2='").append(hormone_sum2).append('\'');
        sb.append(", hormone_type3='").append(hormone_type3).append('\'');
        sb.append(", hormone_brand3='").append(hormone_brand3).append('\'');
        sb.append(", hormone_total3='").append(hormone_total3).append('\'');
        sb.append(", hormone_price3='").append(hormone_price3).append('\'');
        sb.append(", hormone_sum3='").append(hormone_sum3).append('\'');
        sb.append(", Update_Date='").append(Update_Date).append('\'');
        sb.append(", Update_By='").append(Update_By).append('\'');
        sb.append(", Remark1='").append(Remark1).append('\'');
        sb.append(", Remark2='").append(Remark2).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getPlant_TypeName() {
        return plant_TypeName;
    }

    public void setPlant_TypeName(String plant_TypeName) {
        this.plant_TypeName = plant_TypeName;
    }

    public String getPlant_idName() {
        return plant_idName;
    }

    public void setPlant_idName(String plant_idName) {
        this.plant_idName = plant_idName;
    }

    public String getPlant_detail_idName() {
        return plant_detail_idName;
    }

    public void setPlant_detail_idName(String plant_detail_idName) {
        this.plant_detail_idName = plant_detail_idName;
    }

    public String getStart_crop() {
        return start_crop;
    }

    public void setStart_crop(String start_crop) {
        this.start_crop = start_crop;
    }

    public String getEnd_crop() {
        return end_crop;
    }

    public void setEnd_crop(String end_crop) {
        this.end_crop = end_crop;
    }

    public String getPlant_id() {
        return toZero(plant_id);
    }

    public void setPlant_id(String plant_id) {
        this.plant_id = plant_id;
    }

    public String getPlant_detail_id() {
        return toZero(plant_detail_id);
    }

    public void setPlant_detail_id(String plant_detail_id) {
        this.plant_detail_id = plant_detail_id;
    }


    public String getFertilizer1() {
        return toZero(fertilizer1);
    }

    public void setFertilizer1(String fertilizer1) {
        this.fertilizer1 = fertilizer1;
    }

    public String getFertilizer_code1() {
        return toZero(fertilizer_code1);
    }

    public void setFertilizer_code1(String fertilizer_code1) {
        this.fertilizer_code1 = fertilizer_code1;
    }

    public String getFertilizer_total1() {
        return toZero(fertilizer_total1);
    }

    public void setFertilizer_total1(String fertilizer_total1) {
        this.fertilizer_total1 = fertilizer_total1;
    }

    public String getFertilizer_price1() {
        return toZero(fertilizer_price1);
    }

    public void setFertilizer_price1(String fertilizer_price1) {
        this.fertilizer_price1 = fertilizer_price1;
    }

    public String getFertilizer_sum1() {
        return toZero(fertilizer_sum1);
    }

    public void setFertilizer_sum1(String fertilizer_sum1) {
        this.fertilizer_sum1 = fertilizer_sum1;
    }

    public String getFertilizer2() {
        return toZero(fertilizer2);
    }

    public void setFertilizer2(String fertilizer2) {
        this.fertilizer2 = fertilizer2;
    }

    public String getFertilizer_code2() {
        return toZero(fertilizer_code2);
    }

    public void setFertilizer_code2(String fertilizer_code2) {
        this.fertilizer_code2 = fertilizer_code2;
    }

    public String getFertilizer_total2() {
        return toZero(fertilizer_total2);
    }

    public void setFertilizer_total2(String fertilizer_total2) {
        this.fertilizer_total2 = fertilizer_total2;
    }

    public String getFertilizer_price2() {
        return toZero(fertilizer_price2);
    }

    public void setFertilizer_price2(String fertilizer_price2) {
        this.fertilizer_price2 = fertilizer_price2;
    }

    public String getFertilizer_sum2() {
        return toZero(fertilizer_sum2);
    }

    public void setFertilizer_sum2(String fertilizer_sum2) {
        this.fertilizer_sum2 = fertilizer_sum2;
    }

    public String getFertilizer3() {
        return toZero(fertilizer3);
    }

    public void setFertilizer3(String fertilizer3) {
        this.fertilizer3 = fertilizer3;
    }

    public String getFertilizer_code3() {
        return toZero(fertilizer_code3);
    }

    public void setFertilizer_code3(String fertilizer_code3) {
        this.fertilizer_code3 = fertilizer_code3;
    }

    public String getFertilizer_total3() {
        return toZero(fertilizer_total3);
    }

    public void setFertilizer_total3(String fertilizer_total3) {
        this.fertilizer_total3 = fertilizer_total3;
    }

    public String getFertilizer_price3() {
        return toZero(fertilizer_price3);
    }

    public void setFertilizer_price3(String fertilizer_price3) {
        this.fertilizer_price3 = fertilizer_price3;
    }

    public String getFertilizer_sum3() {
        return toZero(fertilizer_sum3);
    }

    public void setFertilizer_sum3(String fertilizer_sum3) {
        this.fertilizer_sum3 = fertilizer_sum3;
    }

    public String getHormone_type1() {
        return toZero(hormone_type1);
    }

    public void setHormone_type1(String hormone_type1) {
        this.hormone_type1 = hormone_type1;
    }

    public String getHormone_brand1() {
        return toZero(hormone_brand1);
    }

    public void setHormone_brand1(String hormone_brand1) {
        this.hormone_brand1 = hormone_brand1;
    }

    public String getHormone_total1() {
        return toZero(hormone_total1);
    }

    public void setHormone_total1(String hormone_total1) {
        this.hormone_total1 = hormone_total1;
    }

    public String getHormone_price1() {
        return toZero(hormone_price1);
    }

    public void setHormone_price1(String hormone_price1) {
        this.hormone_price1 = hormone_price1;
    }

    public String getHormone_sum1() {
        return toZero(hormone_sum1);
    }

    public void setHormone_sum1(String hormone_sum1) {
        this.hormone_sum1 = hormone_sum1;
    }

    public String getHormone_type2() {
        return toZero(hormone_type2);
    }

    public void setHormone_type2(String hormone_type2) {
        this.hormone_type2 = hormone_type2;
    }

    public String getHormone_brand2() {
        return toZero(hormone_brand2);
    }

    public void setHormone_brand2(String hormone_brand2) {
        this.hormone_brand2 = hormone_brand2;
    }

    public String getHormone_total2() {
        return toZero(hormone_total2);
    }

    public void setHormone_total2(String hormone_total2) {
        this.hormone_total2 = hormone_total2;
    }

    public String getHormone_price2() {
        return toZero(hormone_price2);
    }

    public void setHormone_price2(String hormone_price2) {
        this.hormone_price2 = hormone_price2;
    }

    public String getHormone_sum2() {
        return toZero(hormone_sum2);
    }

    public void setHormone_sum2(String hormone_sum2) {
        this.hormone_sum2 = hormone_sum2;
    }

    public String getHormone_type3() {
        return toZero(hormone_type3);
    }

    public void setHormone_type3(String hormone_type3) {
        this.hormone_type3 = hormone_type3;
    }

    public String getHormone_brand3() {
        return toZero(hormone_brand3);
    }

    public void setHormone_brand3(String hormone_brand3) {
        this.hormone_brand3 = hormone_brand3;
    }

    public String getHormone_total3() {
        return toZero(hormone_total3);
    }

    public void setHormone_total3(String hormone_total3) {
        this.hormone_total3 = hormone_total3;
    }

    public String getHormone_price3() {
        return toZero(hormone_price3);
    }

    public void setHormone_price3(String hormone_price3) {
        this.hormone_price3 = hormone_price3;
    }

    public String getHormone_sum3() {
        return toZero(hormone_sum3);
    }

    public void setHormone_sum3(String hormone_sum3) {
        this.hormone_sum3 = hormone_sum3;
    }

    public String getLandUse_ID() {
        return toZero(landUse_ID);
    }

    public void setLandUse_ID(String landUse_ID) {
        this.landUse_ID = landUse_ID;
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

    public String getSurvey_Date() {
        return survey_Date;
    }

    public void setSurvey_Date(String survey_Date) {
        this.survey_Date = survey_Date;
    }

    public String getPlant_Type() {
        return toZero(plant_Type);
    }

    public void setPlant_Type(String plant_Type) {
        this.plant_Type = plant_Type;
    }

    public String getPlant_Year() {
        return plant_Year;
    }

    public void setPlant_Year(String plant_Year) {
        this.plant_Year = plant_Year;
    }

    public String getArea() {
        return toZero(area);
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHarvest_Status() {
        return toZero(harvest_Status);
    }

    public void setHarvest_Status(String harvest_Status) {
        this.harvest_Status = harvest_Status;
    }

    public String getSeeds() {
        return toZero(seeds);
    }

    public void setSeeds(String seeds) {
        this.seeds = seeds;
    }

    public String getHas_Hire() {
        return toZero(has_Hire);
    }

    public void setHas_Hire(String has_Hire) {
        this.has_Hire = has_Hire;
    }

    public String getLabour() {
        return toZero(labour);
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public String getFuel() {
        return toZero(fuel);
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getOther_Paid() {
        return toZero(other_Paid);
    }

    public void setOther_Paid(String other_Paid) {
        this.other_Paid = other_Paid;
    }

    public String getProduct_Use() {
        return toZero(product_Use);
    }

    public void setProduct_Use(String product_Use) {
        this.product_Use = product_Use;
    }

    public String getProduct_Sale() {
        return toZero(product_Sale);
    }

    public void setProduct_Sale(String product_Sale) {
        this.product_Sale = product_Sale;
    }

    public String getPrice() {
        return toZero(price);
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIncome_Year() {
        return toZero(income_Year);
    }

    public void setIncome_Year(String income_Year) {
        this.income_Year = income_Year;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getEmploy_Type() {
        return toZero(employ_Type);
    }

    public void setEmploy_Type(String employ_Type) {
        this.employ_Type = employ_Type;
    }

    public String getEmploy_From() {
        return toZero(employ_From);
    }

    public void setEmploy_From(String employ_From) {
        this.employ_From = employ_From;
    }

    public String getLabour_Total() {
        return toZero(labour_Total);
    }

    public void setLabour_Total(String labour_Total) {
        this.labour_Total = labour_Total;
    }

    public String getLabour_Paid() {
        return toZero(labour_Paid);
    }

    public void setLabour_Paid(String labour_Paid) {
        this.labour_Paid = labour_Paid;
    }

    public String getLabour_Time() {
        return labour_Time;
    }

    public void setLabour_Time(String labour_Time) {
        this.labour_Time = labour_Time;
    }

    public String getSurvey_ID() {
        return survey_ID;
    }

    public void setSurvey_ID(String survey_ID) {
        this.survey_ID = survey_ID;
    }

    public String getUpdate_Date() {
        return Update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        Update_Date = update_Date;
    }

    public String getUpdate_By() {
        return Update_By;
    }

    public void setUpdate_By(String update_By) {
        Update_By = update_By;
    }

    public String getRemark1() {
        return Remark1;
    }

    public void setRemark1(String remark1) {
        Remark1 = remark1;
    }

    public String getRemark2() {
        return Remark2;
    }

    public void setRemark2(String remark2) {
        Remark2 = remark2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.parseInt(landUse_ID);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LandUseBean other = (LandUseBean) obj;
        if (getLandUse_ID() != other.getLandUse_ID())
            return false;
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.landUse_ID);
        dest.writeString(this.land_No);
        dest.writeString(this.card_no);
        dest.writeString(this.survey_ID);
        dest.writeString(this.survey_Date);
        dest.writeString(this.plant_Type);
        dest.writeString(this.plant_Year);
        dest.writeString(this.area);
        dest.writeString(this.harvest_Status);
        dest.writeString(this.seeds);
        dest.writeString(this.has_Hire);
        dest.writeString(this.labour);
        dest.writeString(this.fuel);
        dest.writeString(this.other_Paid);
        dest.writeString(this.product_Use);
        dest.writeString(this.product_Sale);
        dest.writeString(this.price);
        dest.writeString(this.income_Year);
        dest.writeString(this.market);
        dest.writeString(this.employ_Type);
        dest.writeString(this.employ_From);
        dest.writeString(this.labour_Total);
        dest.writeString(this.labour_Paid);
        dest.writeString(this.labour_Time);

        dest.writeString(this.start_crop);
        dest.writeString(this.end_crop);
        dest.writeString(this.plant_id);
        dest.writeString(this.plant_detail_id);


        // ปุ๋ย
        dest.writeString(this.fertilizer1);
        dest.writeString(this.fertilizer_code1);
        dest.writeString(this.fertilizer_total1);
        dest.writeString(this.fertilizer_price1);
        dest.writeString(this.fertilizer_sum1);

        dest.writeString(this.fertilizer2);
        dest.writeString(this.fertilizer_code2);
        dest.writeString(this.fertilizer_total2);
        dest.writeString(this.fertilizer_price2);
        dest.writeString(this.fertilizer_sum2);

        dest.writeString(this.fertilizer3);
        dest.writeString(this.fertilizer_code3);
        dest.writeString(this.fertilizer_total3);
        dest.writeString(this.fertilizer_price3);
        dest.writeString(this.fertilizer_sum3);

        // ยา/ฮอร์โมน
        dest.writeString(this.hormone_type1);
        dest.writeString(this.hormone_brand1);
        dest.writeString(this.hormone_total1);
        dest.writeString(this.hormone_price1);
        dest.writeString(this.hormone_sum1);

        dest.writeString(this.hormone_type2);
        dest.writeString(this.hormone_brand2);
        dest.writeString(this.hormone_total2);
        dest.writeString(this.hormone_price2);
        dest.writeString(this.hormone_sum2);

        dest.writeString(this.hormone_type3);
        dest.writeString(this.hormone_brand3);
        dest.writeString(this.hormone_total3);
        dest.writeString(this.hormone_price3);
        dest.writeString(this.hormone_sum3);

        dest.writeString(this.Update_Date);
        dest.writeString(this.Update_By);
        dest.writeString(this.Remark1);
        dest.writeString(this.Remark2);

        // dest.writeParcelable(getDepartment(), flags);
    }
}
