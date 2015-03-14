package com.hrdi.survey.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.modeldb.AgriculturistDB;
import com.hrdi.survey.modeldb.LandUseDB;
import com.hrdi.survey.modeldb.SurveyDB;
import com.hrdi.survey.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyDAO extends HrdiDBDAO {

    private static final String WHERE_ID_EQUALS = SurveyDB.SURVEY_ID + " =?";
    private JSONParser jsonParser;

    public SurveyDAO(Context context) {
        super(context);
    }


    public long addSurveySQLite(SurveyBean survey) {

        ContentValues values = new ContentValues();

        values.put(SurveyDB.LAND_NO, survey.getLand_No());
        values.put(SurveyDB.CARD_NO, survey.getCard_no());
        values.put(SurveyDB.CARD_TYPE, survey.getCard_type());

        values.put(SurveyDB.SURVEY_DATE, survey.getSurvey_Date());
        values.put(SurveyDB.LATLONG, survey.getLatlong());

        values.put(SurveyDB.EXT_PROJECT, survey.getExt_Project());
        values.put(SurveyDB.LAND_DOC_TYPE, survey.getLand_doc_type());
        values.put(SurveyDB.AREA_STATUS, survey.getArea_status());
        values.put(SurveyDB.AREA_STATUS_YEAR, survey.getArea_status_year());

        values.put(SurveyDB.FIRSTNAME, survey.getFirstName());
        values.put(SurveyDB.LASTNAME, survey.getLastName());
        values.put(SurveyDB.ADDRESS, survey.getAddress());
        values.put(SurveyDB.HISTORY, survey.getHistory());

        values.put(SurveyDB.OWNER_TYPE, survey.getOwner_Type());
        values.put(SurveyDB.OWNER_TYPE_DETAIL, survey.getOwner_Type_Detail());
        values.put(SurveyDB.INSTITUTE_SUPPORT, survey.getInstitute_Support());
        values.put(SurveyDB.WATER, survey.getWater());
        values.put(SurveyDB.WATER_PERIOD, survey.getWater_Period());
        values.put(SurveyDB.WATER_USE, survey.getWater_Use());
        values.put(SurveyDB.SOIL_MOISTURE, survey.getSoil_moisture());
        values.put(SurveyDB.TEMPERATURE, survey.getTemperature());
        values.put(SurveyDB.HASACTIVITY, survey.getHasActivity());

        values.put(SurveyDB.ACTIVITY1, survey.getActivity1());
        values.put(SurveyDB.REPEAT1, survey.getRepeat1());
        values.put(SurveyDB.OUTCOME1, survey.getOutcome1());
        values.put(SurveyDB.SURVIVE1, survey.getSurvive1());

        values.put(SurveyDB.ACTIVITY2, survey.getActivity2());
        values.put(SurveyDB.REPEAT2, survey.getRepeat2());
        values.put(SurveyDB.OUTCOME2, survey.getOutcome2());
        values.put(SurveyDB.SURVIVE2, survey.getSurvive2());

        values.put(SurveyDB.ACTIVITY3, survey.getActivity3());
        values.put(SurveyDB.REPEAT3, survey.getRepeat3());
        values.put(SurveyDB.OUTCOME3, survey.getOutcome3());
        values.put(SurveyDB.SURVIVE3, survey.getSurvive3());

        values.put(SurveyDB.ACTIVITY4, survey.getActivity4());
        values.put(SurveyDB.REPEAT4, survey.getRepeat4());
        values.put(SurveyDB.OUTCOME4, survey.getOutcome4());
        values.put(SurveyDB.SURVIVE4, survey.getSurvive4());

        values.put(SurveyDB.HASOTHERSUPPORT, survey.getHasOtherSupport());
        values.put(SurveyDB.ORG1, survey.getOrg1());
        values.put(SurveyDB.ORG2, survey.getOrg2());
        values.put(SurveyDB.ORG3, survey.getOrg3());

        values.put(SurveyDB.PROBLEM1, survey.getProblem1());
        values.put(SurveyDB.PROBLEM2, survey.getProblem2());
        values.put(SurveyDB.PROBLEM3, survey.getProblem3());

        values.put(SurveyDB.REQUEST1, survey.getRequest1());
        values.put(SurveyDB.REQUEST2, survey.getRequest2());
        values.put(SurveyDB.REQUEST3, survey.getRequest3());

        values.put(SurveyDB.PICTURE1, survey.getPicture1());
        values.put(SurveyDB.PICTURE2, survey.getPicture2());
        values.put(SurveyDB.PICTURE3, survey.getPicture3());


        values.put(SurveyDB.UPDATE_BY, survey.getUpdate_By());
        values.put(SurveyDB.UPDATE_DATE, survey.getUpdate_Date());
        values.put(SurveyDB.REMARK1, survey.getRemark1());
        values.put(SurveyDB.REMARK2, survey.getRemark2());

        values.put(SurveyDB.MOOBAN, survey.getMooban());


        // Inserting Row
        long newID = database.insert(SurveyDB.TABLE_NAME, null, values);
        // database.close(); // Closing database connection
        return newID;
    }

    public List<NameValuePair> setLandUse2Parameter(LandUseBean bean) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "AddLandUse"));

        //params.add(new BasicNameValuePair(LandUseDB.LANDUSE_ID = "LANDUSE_ID";
        //params.add(new BasicNameValuePair(LandUseDB.WHERE_ID_EQUALS, bean.getSurvey_id()));
        params.add(new BasicNameValuePair(LandUseDB.SURVEY_ID, bean.getSurvey_ID()));
        params.add(new BasicNameValuePair(LandUseDB.LAND_NO, bean.getLand_No()));
        params.add(new BasicNameValuePair(LandUseDB.CARD_NO, bean.getCard_no()));
        params.add(new BasicNameValuePair(LandUseDB.SURVEY_DATE, bean.getSurvey_Date()));
        params.add(new BasicNameValuePair(LandUseDB.PLANT_TYPE, bean.getPlant_Type()));
        params.add(new BasicNameValuePair(LandUseDB.PLANT_YEAR, bean.getPlant_Year()));
        params.add(new BasicNameValuePair(LandUseDB.AREA, bean.getArea()));
        params.add(new BasicNameValuePair(LandUseDB.HAVEST_STATUS, bean.getHarvest_Status()));
        params.add(new BasicNameValuePair(LandUseDB.SEEDS, bean.getSeeds()));
        params.add(new BasicNameValuePair(LandUseDB.HAS_HIRE, bean.getHas_Hire()));
        params.add(new BasicNameValuePair(LandUseDB.LABOUR, bean.getLabour()));
        params.add(new BasicNameValuePair(LandUseDB.FUEL, bean.getFuel()));
        params.add(new BasicNameValuePair(LandUseDB.OTHER_PAID, bean.getOther_Paid()));
        params.add(new BasicNameValuePair(LandUseDB.PRODUCT_USE, bean.getProduct_Use()));
        params.add(new BasicNameValuePair(LandUseDB.PRODUCT_SALE, bean.getProduct_Sale()));
        params.add(new BasicNameValuePair(LandUseDB.PRICE, bean.getPrice()));
        params.add(new BasicNameValuePair(LandUseDB.INCOME_YEAR, bean.getIncome_Year()));
        params.add(new BasicNameValuePair(LandUseDB.MARKET, bean.getMarket()));
        params.add(new BasicNameValuePair(LandUseDB.EMPLOY_TYPE, bean.getEmploy_Type()));
        params.add(new BasicNameValuePair(LandUseDB.EMPLOY_FROM, bean.getEmploy_From()));
        params.add(new BasicNameValuePair(LandUseDB.LABOUR_TOTAL, bean.getLabour_Total()));
        params.add(new BasicNameValuePair(LandUseDB.LABOUR_PAID, bean.getLabour_Paid()));
        params.add(new BasicNameValuePair(LandUseDB.LABOUR_TIME, bean.getLabour_Time()));
        params.add(new BasicNameValuePair(LandUseDB.START_CROP, bean.getStart_crop()));
        params.add(new BasicNameValuePair(LandUseDB.END_CROP, bean.getEnd_crop()));
        params.add(new BasicNameValuePair(LandUseDB.PLANT_ID, bean.getPlant_id()));
        params.add(new BasicNameValuePair(LandUseDB.PLANT_DETAIL_ID, bean.getPlant_detail_id()));
        // ปุ๋ย
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER1, bean.getFertilizer1()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_CODE1, bean.getFertilizer_code1()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_TOTAL1, bean.getFertilizer_total1()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_PRICE1, bean.getFertilizer_price1()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_SUM1, bean.getFertilizer_sum1()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER2, bean.getFertilizer2()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_CODE2, bean.getFertilizer_code2()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_TOTAL2, bean.getFertilizer_total1()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_PRICE2, bean.getFertilizer_price2()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_SUM2, bean.getFertilizer_sum2()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER3, bean.getFertilizer3()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_CODE3, bean.getFertilizer_code3()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_TOTAL3, bean.getFertilizer_total3()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_PRICE3, bean.getFertilizer_price3()));
        params.add(new BasicNameValuePair(LandUseDB.FERTILIZER_SUM3, bean.getFertilizer_sum3()));
        // ยา/ฮอร์โมน
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_TYPE1, bean.getHormone_type1()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_BRAND1, bean.getHormone_brand1()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_TOTAL1, bean.getHormone_total1()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_PRICE1, bean.getHormone_price2()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_SUM1, bean.getHormone_sum1()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_TYPE2, bean.getHormone_type2()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_BRAND2, bean.getHormone_brand2()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_TOTAL2, bean.getHormone_total2()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_PRICE2, bean.getHormone_price2()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_SUM2, bean.getHormone_sum2()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_TYPE3, bean.getHormone_type3()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_BRAND3, bean.getHormone_brand3()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_TOTAL3, bean.getHormone_total3()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_PRICE3, bean.getHormone_price3()));
        params.add(new BasicNameValuePair(LandUseDB.HORMONE_SUM3, bean.getHormone_sum3()));
        params.add(new BasicNameValuePair(LandUseDB.UPDATE_DATE, bean.getUpdate_Date()));
        params.add(new BasicNameValuePair(LandUseDB.UPDATE_BY, bean.getUpdate_By()));
        params.add(new BasicNameValuePair(LandUseDB.REMARK1, bean.getRemark1()));
        params.add(new BasicNameValuePair(LandUseDB.REMARK2, bean.getRemark2()));


        return params;
    }

    public List<NameValuePair> setBean2Parameter(SurveyBean survey) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "AddSurvey"));

        params.add(new BasicNameValuePair(SurveyDB.SURVEY_ID, survey.getSurvey_id()));
        params.add(new BasicNameValuePair(SurveyDB.LAND_NO, survey.getLand_No()));
        params.add(new BasicNameValuePair(SurveyDB.CARD_NO, survey.getCard_no()));
        params.add(new BasicNameValuePair(SurveyDB.CARD_TYPE, survey.getCard_type()));

        params.add(new BasicNameValuePair(SurveyDB.SURVEY_DATE, survey.getSurvey_Date()));
        params.add(new BasicNameValuePair(SurveyDB.LATLONG, survey.getLatlong()));

        params.add(new BasicNameValuePair(SurveyDB.EXT_PROJECT, survey.getExt_Project()));
        params.add(new BasicNameValuePair(SurveyDB.LAND_DOC_TYPE, survey.getLand_doc_type()));
        params.add(new BasicNameValuePair(SurveyDB.AREA_STATUS, survey.getArea_status()));
        params.add(new BasicNameValuePair(SurveyDB.AREA_STATUS_YEAR, survey.getArea_status_year()));

        params.add(new BasicNameValuePair(SurveyDB.FIRSTNAME, survey.getFirstName()));
        params.add(new BasicNameValuePair(SurveyDB.LASTNAME, survey.getLastName()));
        params.add(new BasicNameValuePair(SurveyDB.ADDRESS, survey.getAddress()));
        params.add(new BasicNameValuePair(SurveyDB.HISTORY, survey.getHistory()));

        params.add(new BasicNameValuePair(SurveyDB.OWNER_TYPE, survey.getOwner_Type()));
        params.add(new BasicNameValuePair(SurveyDB.OWNER_TYPE_DETAIL, survey.getOwner_Type_Detail()));
        params.add(new BasicNameValuePair(SurveyDB.INSTITUTE_SUPPORT, survey.getInstitute_Support()));
        params.add(new BasicNameValuePair(SurveyDB.WATER, survey.getWater()));
        params.add(new BasicNameValuePair(SurveyDB.WATER_PERIOD, survey.getWater_Period()));
        params.add(new BasicNameValuePair(SurveyDB.WATER_USE, survey.getWater_Use()));
        params.add(new BasicNameValuePair(SurveyDB.SOIL_MOISTURE, survey.getSoil_moisture()));
        params.add(new BasicNameValuePair(SurveyDB.TEMPERATURE, survey.getTemperature()));
        params.add(new BasicNameValuePair(SurveyDB.HASACTIVITY, survey.getHasActivity()));

        params.add(new BasicNameValuePair(SurveyDB.ACTIVITY1, survey.getActivity1()));
        params.add(new BasicNameValuePair(SurveyDB.REPEAT1, survey.getRepeat1()));
        params.add(new BasicNameValuePair(SurveyDB.OUTCOME1, survey.getOutcome1()));
        params.add(new BasicNameValuePair(SurveyDB.SURVIVE1, survey.getSurvive1()));

        params.add(new BasicNameValuePair(SurveyDB.ACTIVITY2, survey.getActivity2()));
        params.add(new BasicNameValuePair(SurveyDB.REPEAT2, survey.getRepeat2()));
        params.add(new BasicNameValuePair(SurveyDB.OUTCOME2, survey.getOutcome2()));
        params.add(new BasicNameValuePair(SurveyDB.SURVIVE2, survey.getSurvive2()));

        params.add(new BasicNameValuePair(SurveyDB.ACTIVITY3, survey.getActivity3()));
        params.add(new BasicNameValuePair(SurveyDB.REPEAT3, survey.getRepeat3()));
        params.add(new BasicNameValuePair(SurveyDB.OUTCOME3, survey.getOutcome3()));
        params.add(new BasicNameValuePair(SurveyDB.SURVIVE3, survey.getSurvive3()));

        params.add(new BasicNameValuePair(SurveyDB.ACTIVITY4, survey.getActivity4()));
        params.add(new BasicNameValuePair(SurveyDB.REPEAT4, survey.getRepeat4()));
        params.add(new BasicNameValuePair(SurveyDB.OUTCOME4, survey.getOutcome4()));
        params.add(new BasicNameValuePair(SurveyDB.SURVIVE4, survey.getSurvive4()));

        params.add(new BasicNameValuePair(SurveyDB.HASOTHERSUPPORT, survey.getHasOtherSupport()));
        params.add(new BasicNameValuePair(SurveyDB.ORG1, survey.getOrg1()));
        params.add(new BasicNameValuePair(SurveyDB.ORG2, survey.getOrg2()));
        params.add(new BasicNameValuePair(SurveyDB.ORG3, survey.getOrg3()));

        params.add(new BasicNameValuePair(SurveyDB.PROBLEM1, survey.getProblem1()));
        params.add(new BasicNameValuePair(SurveyDB.PROBLEM2, survey.getProblem2()));
        params.add(new BasicNameValuePair(SurveyDB.PROBLEM3, survey.getProblem3()));

        params.add(new BasicNameValuePair(SurveyDB.REQUEST1, survey.getRequest1()));
        params.add(new BasicNameValuePair(SurveyDB.REQUEST2, survey.getRequest2()));
        params.add(new BasicNameValuePair(SurveyDB.REQUEST3, survey.getRequest3()));

        params.add(new BasicNameValuePair(SurveyDB.PICTURE1, survey.getPicture1()));
        params.add(new BasicNameValuePair(SurveyDB.PICTURE2, survey.getPicture2()));
        params.add(new BasicNameValuePair(SurveyDB.PICTURE3, survey.getPicture3()));

        params.add(new BasicNameValuePair(SurveyDB.UPDATE_BY, survey.getUpdate_By()));
        params.add(new BasicNameValuePair(SurveyDB.UPDATE_DATE, survey.getUpdate_Date()));
        params.add(new BasicNameValuePair(SurveyDB.REMARK1, survey.getRemark1()));
        params.add(new BasicNameValuePair(SurveyDB.REMARK2, survey.getRemark2()));

        params.add(new BasicNameValuePair(SurveyDB.MOOBAN, survey.getMooban()));
        //params.add(new BasicNameValuePair(SurveyDB.UP, survey.getMooban()));

        Log.i("params", params.toString());

        return params;
    }

    public long updateSurvey1(SurveyBean survey) {
        //Log.i("updateSurvey1 >>", survey.toString());
        ContentValues values = new ContentValues();

        values.put(SurveyDB.LAND_NO, survey.getLand_No());
        values.put(SurveyDB.CARD_NO, survey.getCard_no());
        values.put(SurveyDB.CARD_TYPE, survey.getCard_type());

        values.put(SurveyDB.SURVEY_DATE, survey.getSurvey_Date());
        values.put(SurveyDB.LATLONG, survey.getLatlong());

        values.put(SurveyDB.EXT_PROJECT, survey.getExt_Project());
        values.put(SurveyDB.LAND_DOC_TYPE, survey.getLand_doc_type());
        values.put(SurveyDB.AREA_STATUS, survey.getArea_status());
        values.put(SurveyDB.AREA_STATUS_YEAR, survey.getArea_status_year());
        values.put(SurveyDB.FIRSTNAME, survey.getFirstName());
        values.put(SurveyDB.LASTNAME, survey.getLastName());

        values.put(SurveyDB.OWNER_TYPE, survey.getOwner_Type());
        values.put(SurveyDB.OWNER_TYPE_DETAIL, survey.getOwner_Type_Detail());
        values.put(SurveyDB.HISTORY, survey.getHistory());

        values.put(SurveyDB.UPDATE_BY, survey.getUpdate_By());
        values.put(SurveyDB.UPDATE_DATE, survey.getUpdate_Date());
        values.put(SurveyDB.REMARK1, survey.getRemark1());
        values.put(SurveyDB.REMARK2, survey.getRemark2());

        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(survey.getSurvey_id())});
        //database.close(); // Closing database connection
        return result;
    }

    public long updateSurveyEtc(SurveyBean survey) {

        ContentValues values = new ContentValues();

        values.put(SurveyDB.OWNER_TYPE, survey.getOwner_Type());
        values.put(SurveyDB.OWNER_TYPE_DETAIL, survey.getOwner_Type_Detail());
        values.put(SurveyDB.INSTITUTE_SUPPORT, survey.getInstitute_Support());
        values.put(SurveyDB.WATER, survey.getWater());
        values.put(SurveyDB.WATER_PERIOD, survey.getWater_Period());
        values.put(SurveyDB.WATER_USE, survey.getWater_Use());
        values.put(SurveyDB.SOIL_MOISTURE, survey.getSoil_moisture());
        values.put(SurveyDB.TEMPERATURE, survey.getTemperature());
        values.put(SurveyDB.HASACTIVITY, survey.getHasActivity());

        values.put(SurveyDB.ACTIVITY1, survey.getActivity1());
        values.put(SurveyDB.REPEAT1, survey.getRepeat1());
        values.put(SurveyDB.OUTCOME1, survey.getOutcome1());
        values.put(SurveyDB.SURVIVE1, survey.getSurvive1());

        values.put(SurveyDB.ACTIVITY2, survey.getActivity2());
        values.put(SurveyDB.REPEAT2, survey.getRepeat2());
        values.put(SurveyDB.OUTCOME2, survey.getOutcome2());
        values.put(SurveyDB.SURVIVE2, survey.getSurvive2());

        values.put(SurveyDB.ACTIVITY3, survey.getActivity3());
        values.put(SurveyDB.REPEAT3, survey.getRepeat3());
        values.put(SurveyDB.OUTCOME3, survey.getOutcome3());
        values.put(SurveyDB.SURVIVE3, survey.getSurvive3());

        values.put(SurveyDB.ACTIVITY4, survey.getActivity4());
        values.put(SurveyDB.REPEAT4, survey.getRepeat4());
        values.put(SurveyDB.OUTCOME4, survey.getOutcome4());
        values.put(SurveyDB.SURVIVE4, survey.getSurvive4());

        values.put(SurveyDB.HASOTHERSUPPORT, survey.getHasOtherSupport());
        values.put(SurveyDB.ORG1, survey.getOrg1());
        values.put(SurveyDB.ORG2, survey.getOrg2());
        values.put(SurveyDB.ORG3, survey.getOrg3());

        values.put(SurveyDB.PROBLEM1, survey.getProblem1());
        values.put(SurveyDB.PROBLEM2, survey.getProblem2());
        values.put(SurveyDB.PROBLEM3, survey.getProblem3());

        values.put(SurveyDB.REQUEST1, survey.getRequest1());
        values.put(SurveyDB.REQUEST2, survey.getRequest2());
        values.put(SurveyDB.REQUEST3, survey.getRequest3());

        values.put(SurveyDB.PICTURE1, survey.getPicture1());
        values.put(SurveyDB.PICTURE2, survey.getPicture2());
        values.put(SurveyDB.PICTURE3, survey.getPicture3());


        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(survey.getSurvey_id())});
        //database.close(); // Closing database connection
        return result;
    }

    // TODO: update getID from Agicultirist
    public String[] getIDCard(){
        try {
            String arrData[] = null;

            String strSQL = "SELECT DISTINCT CARD_NO FROM " + AgriculturistDB.TABLE_NAME + " ORDER BY CARD_NO";
            Cursor cursor = database.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    /***
                     *  [x] = Name
                     */
                    int i= 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }
    }



    public long updateSurveyPic(SurveyBean survey) {

        ContentValues values = new ContentValues();

        values.put(SurveyDB.PICTURE1, survey.getPicture1());
        values.put(SurveyDB.PICTURE2, survey.getPicture2());
        values.put(SurveyDB.PICTURE3, survey.getPicture3());


        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(survey.getSurvey_id())});
        //database.close(); // Closing database connection
        return result;
    }

    public long resetStatus(String status) {

        ContentValues values = new ContentValues();

        values.put(SurveyDB.REMARK1, status);

        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values, null, null);
        //database.close(); // Closing database connection
        return result;
    }

    public long updateStatus(SurveyBean survey, String status) {

        ContentValues values = new ContentValues();

        values.put(SurveyDB.REMARK1, status);

        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(survey.getSurvey_id())});
        //database.close(); // Closing database connection
        return result;
    }

    public int deleteSurvey(SurveyBean sv) {
        return database.delete(SurveyDB.TABLE_NAME, WHERE_ID_EQUALS, new String[]{sv.getSurvey_id() + ""});
    }

    public ArrayList<SurveyBean> getSurveyList2Show(String status) {
        // status = all, waiting, send
        ArrayList<SurveyBean> surveyBeanArrayList = new ArrayList<>();

        String query = SurveyDB.getSelectSQL2Show(status);
        Log.d("*** query ArrayList ***", SurveyDB.getSelectSQL2Show(status));
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            SurveyBean surveyBean = new SurveyBean();
            surveyBean.setSurvey_id(String.valueOf(cursor.getInt(i++)));

            surveyBean.setSurvey_Date(cursor.getString(i++));
            surveyBean.setLand_No(cursor.getString(i++));
            surveyBean.setMooban(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setExt_Project_name(cursor.getString(i++));
            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setCard_no(cursor.getString(i++));

            //surveyBean.setTitle  8
            i++;

            surveyBean.setFirstName(cursor.getString(i++));
            surveyBean.setLastName(cursor.getString(i++));
            surveyBean.setRemark1(cursor.getString(i++));

            surveyBeanArrayList.add(surveyBean);
        }
        cursor.close();
        return surveyBeanArrayList;
    }

    public ArrayList<LandUseBean> getLandUseInSurvey(String surveyID) {
        ArrayList<LandUseBean> landUseBeans = new ArrayList<>();

        String query = LandUseDB.getLandUseInSurveySQL(surveyID);
        Cursor cursor = database.rawQuery(query, null);
        int i;
        while (cursor.moveToNext()) {
            LandUseBean landUseBean = new LandUseBean();

            i = 0;
            landUseBean.setSurvey_ID(String.valueOf(cursor.getInt(i++)));
            landUseBean.setLand_No(String.valueOf(cursor.getString(i++)));
            landUseBean.setCard_no(String.valueOf(cursor.getString(i++)));
            landUseBean.setPlant_Year(String.valueOf(cursor.getString(i++)));
            landUseBean.setArea(String.valueOf(cursor.getString(i++)));
            landUseBean.setHarvest_Status(String.valueOf(cursor.getString(i++)));
            landUseBean.setSeeds(String.valueOf(cursor.getString(i++)));
            landUseBean.setHas_Hire(String.valueOf(cursor.getString(i++)));
            landUseBean.setLabour(String.valueOf(cursor.getString(i++)));
            landUseBean.setFuel(String.valueOf(cursor.getString(i++)));
            landUseBean.setOther_Paid(String.valueOf(cursor.getString(i++)));
            landUseBean.setProduct_Use(String.valueOf(cursor.getString(i++)));
            landUseBean.setProduct_Sale(String.valueOf(cursor.getString(i++)));
            landUseBean.setPrice(String.valueOf(cursor.getString(i++)));
            landUseBean.setIncome_Year(String.valueOf(cursor.getString(i++)));
            landUseBean.setMarket(String.valueOf(cursor.getString(i++)));

            landUseBean.setEmploy_Type(String.valueOf(cursor.getString(i++)));
            landUseBean.setEmploy_From(String.valueOf(cursor.getString(i++)));
            landUseBean.setLabour_Total(String.valueOf(cursor.getString(i++)));
            landUseBean.setLabour_Paid(String.valueOf(cursor.getString(i++)));
            landUseBean.setLabour_Time(String.valueOf(cursor.getString(i++)));

            landUseBean.setStart_crop(String.valueOf(cursor.getString(i++)));
            landUseBean.setEnd_crop(String.valueOf(cursor.getString(i++)));
            landUseBean.setPlant_Type(String.valueOf(cursor.getString(i++)));
            landUseBean.setPlant_id(String.valueOf(cursor.getString(i++)));
            landUseBean.setPlant_detail_id(String.valueOf(cursor.getString(i++)));

            landUseBean.setFertilizer1(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer2(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer3(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_code1(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_code2(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_code3(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_total1(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_total2(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_total3(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_price1(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_price2(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_price3(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_sum1(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_sum2(String.valueOf(cursor.getString(i++)));
            landUseBean.setFertilizer_sum3(String.valueOf(cursor.getString(i++)));

            landUseBean.setHormone_type1(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_type2(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_type3(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_brand1(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_brand2(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_brand3(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_total1(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_total2(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_total3(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_price1(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_price2(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_price3(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_sum1(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_sum2(String.valueOf(cursor.getString(i++)));
            landUseBean.setHormone_sum3(String.valueOf(cursor.getString(i++)));

            landUseBean.setUpdate_Date(String.valueOf(cursor.getString(i++)));
            landUseBean.setUpdate_By(String.valueOf(cursor.getString(i++)));
            landUseBean.setRemark1(String.valueOf(cursor.getString(i++)));
            landUseBean.setRemark2(String.valueOf(cursor.getString(i++)));

            landUseBeans.add(landUseBean);
        }
        cursor.close();
        return landUseBeans;
    }

    public ArrayList<SurveyBean> getSurveyList(String status) {
        // status = all, waiting, send
        ArrayList<SurveyBean> surveyBeanArrayList = new ArrayList<>();

        String query = SurveyDB.getSelectSQLAllDetail(status);
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            SurveyBean surveyBean = new SurveyBean();

            surveyBean.setSurvey_id(String.valueOf(cursor.getInt(i++)));

            surveyBean.setSurvey_Date(cursor.getString(i++));
            surveyBean.setLand_No(cursor.getString(i++));
            surveyBean.setLatlong(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setMooban(cursor.getString(i++));
            surveyBean.setLand_doc_type(cursor.getString(i++));
            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setArea_status_year(cursor.getString(i++));

            surveyBean.setCard_no(cursor.getString(i++));

            surveyBean.setCard_type(cursor.getString(i++));
            surveyBean.setFirstName(cursor.getString(i++));
            surveyBean.setLastName(cursor.getString(i++));

            surveyBean.setAddress(cursor.getString(i++));
            surveyBean.setOwner_Type(cursor.getString(i++));
            surveyBean.setOwner_Type_Detail(cursor.getString(i++));
            surveyBean.setHistory(cursor.getString(i++));


            surveyBean.setInstitute_Support(cursor.getString(i++));
            surveyBean.setWater(cursor.getString(i++));
            surveyBean.setWater_Period(cursor.getString(i++));
            surveyBean.setWater_Use(cursor.getString(i++));

            surveyBean.setSoil_moisture(cursor.getString(i++));
            surveyBean.setTemperature(cursor.getString(i++));
            surveyBean.setHasActivity(cursor.getString(i++));

            surveyBean.setActivity1(cursor.getString(i++));
            surveyBean.setRepeat1(cursor.getString(i++));
            surveyBean.setOutcome1(cursor.getString(i++));
            surveyBean.setSurvive1(cursor.getString(i++));

            surveyBean.setActivity2(cursor.getString(i++));
            surveyBean.setRepeat2(cursor.getString(i++));
            surveyBean.setOutcome2(cursor.getString(i++));

            surveyBean.setSurvive2(cursor.getString(i++));

            surveyBean.setActivity3(cursor.getString(i++));
            surveyBean.setRepeat3(cursor.getString(i++));
            surveyBean.setOutcome3(cursor.getString(i++));
            surveyBean.setSurvive3(cursor.getString(i++));

            surveyBean.setActivity4(cursor.getString(i++));
            surveyBean.setRepeat4(cursor.getString(i++));
            surveyBean.setOutcome4(cursor.getString(i++));
            surveyBean.setSurvive4(cursor.getString(i++));

            surveyBean.setHasOtherSupport(cursor.getString(i++));

            surveyBean.setOrg1(cursor.getString(i++));
            surveyBean.setOrg2(cursor.getString(i++));
            surveyBean.setOrg3(cursor.getString(i++));

            surveyBean.setProblem1(cursor.getString(i++));
            surveyBean.setProblem2(cursor.getString(i++));
            surveyBean.setProblem3(cursor.getString(i++));

            surveyBean.setRequest1(cursor.getString(i++));
            surveyBean.setRequest2(cursor.getString(i++));
            surveyBean.setRequest3(cursor.getString(i++));

            surveyBean.setPicture1(cursor.getString(i++));
            surveyBean.setPicture2(cursor.getString(i++));
            surveyBean.setPicture3(cursor.getString(i++));

            surveyBean.setUpdate_Date(cursor.getString(i++));
            surveyBean.setUpdate_By(cursor.getString(i++));
            surveyBean.setRemark1(cursor.getString(i++));
            surveyBean.setRemark2(cursor.getString(i++));

            Log.i("getSurveyList cursor", surveyBean.toString());
            surveyBeanArrayList.add(surveyBean);
        }
        cursor.close();
        return surveyBeanArrayList;
    }

    public SurveyBean getFarmerByLandCode(String landCode) {

        SurveyBean surveyBean = new SurveyBean();
        String query = SurveyDB.getSelectSQLByLandCode(landCode);
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            surveyBean.setSurvey_id(String.valueOf(cursor.getInt(i++)));  //0

            surveyBean.setSurvey_Date(cursor.getString(i++));
            surveyBean.setLand_No(cursor.getString(i++));
            surveyBean.setLatlong(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setMooban(cursor.getString(i++));            //5
            surveyBean.setLand_doc_type(cursor.getString(i++));
            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setArea_status_year(cursor.getString(i++));

            surveyBean.setCard_no(cursor.getString(i++));
            surveyBean.setCard_type(cursor.getString(i++));     //10
            surveyBean.setFirstName(cursor.getString(i++));
            surveyBean.setLastName(cursor.getString(i++));

            surveyBean.setAddress(cursor.getString(i++));
            surveyBean.setOwner_Type(cursor.getString(i++));
            surveyBean.setOwner_Type_Detail(cursor.getString(i++));  //15
            surveyBean.setHistory(cursor.getString(i++));
            surveyBean.setRemark1(cursor.getString(i++));
            surveyBean.setRemark2(cursor.getString(i++));

            surveyBean.setPicture1(cursor.getString(i++));
            surveyBean.setPicture2(cursor.getString(i++));
            surveyBean.setPicture3(cursor.getString(i++));


        }
        cursor.close();
        return surveyBean;
    }

    public SurveyBean getSurveyByID(String surveyID) {

        SurveyBean surveyBean = new SurveyBean();
        String query = SurveyDB.getSelectSQLByID(surveyID);
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            surveyBean.setSurvey_id(String.valueOf(cursor.getInt(i++)));  //0

            surveyBean.setSurvey_Date(cursor.getString(i++));
            surveyBean.setLand_No(cursor.getString(i++));
            surveyBean.setLatlong(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setMooban(cursor.getString(i++));            //5
            surveyBean.setLand_doc_type(cursor.getString(i++));
            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setArea_status_year(cursor.getString(i++));

            surveyBean.setCard_no(cursor.getString(i++));
            surveyBean.setCard_type(cursor.getString(i++));     //10
            surveyBean.setFirstName(cursor.getString(i++));
            surveyBean.setLastName(cursor.getString(i++));

            surveyBean.setAddress(cursor.getString(i++));
            surveyBean.setOwner_Type(cursor.getString(i++));
            surveyBean.setOwner_Type_Detail(cursor.getString(i++));  //15
            surveyBean.setHistory(cursor.getString(i++));
            surveyBean.setRemark1(cursor.getString(i++));
            surveyBean.setRemark2(cursor.getString(i++));


        }
        cursor.close();
        return surveyBean;
    }


    public SurveyBean getSurveyEtcByID(String surveyID) {

        SurveyBean surveyBean = new SurveyBean();
        String query = SurveyDB.getSelectSQLSurveyEtc(surveyID);
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            surveyBean.setSurvey_id(String.valueOf(cursor.getInt(i++)));       // 0

            surveyBean.setSurvey_Date(cursor.getString(i++));
            surveyBean.setLand_No(cursor.getString(i++));
            surveyBean.setLatlong(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setMooban(cursor.getString(i++));
            surveyBean.setLand_doc_type(cursor.getString(i++));
            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setArea_status_year(cursor.getString(i++));

            surveyBean.setCard_no(cursor.getString(i++));
            surveyBean.setCard_type(cursor.getString(i++));  //10
            surveyBean.setFirstName(cursor.getString(i++));
            surveyBean.setLastName(cursor.getString(i++));

            surveyBean.setAddress(cursor.getString(i++));
            surveyBean.setOwner_Type(cursor.getString(i++));
            surveyBean.setOwner_Type_Detail(cursor.getString(i++));
            surveyBean.setHistory(cursor.getString(i++));

            surveyBean.setInstitute_Support(cursor.getString(i++));
            surveyBean.setWater(cursor.getString(i++));
            surveyBean.setWater_Period(cursor.getString(i++));
            surveyBean.setWater_Use(cursor.getString(i++));         //20

            surveyBean.setSoil_moisture(cursor.getString(i++));
            surveyBean.setTemperature(cursor.getString(i++));
            surveyBean.setHasActivity(cursor.getString(i++));

            surveyBean.setActivity1(cursor.getString(i++));
            surveyBean.setRepeat1(cursor.getString(i++));
            surveyBean.setOutcome1(cursor.getString(i++));
            surveyBean.setSurvive1(cursor.getString(i++));

            surveyBean.setActivity2(cursor.getString(i++));
            surveyBean.setRepeat2(cursor.getString(i++));
            surveyBean.setOutcome2(cursor.getString(i++));    //30
            surveyBean.setSurvive2(cursor.getString(i++));

            surveyBean.setActivity3(cursor.getString(i++));
            surveyBean.setRepeat3(cursor.getString(i++));
            surveyBean.setOutcome3(cursor.getString(i++));
            surveyBean.setSurvive3(cursor.getString(i++));

            surveyBean.setActivity4(cursor.getString(i++));
            surveyBean.setRepeat4(cursor.getString(i++));
            surveyBean.setOutcome4(cursor.getString(i++));
            surveyBean.setSurvive4(cursor.getString(i++));

            surveyBean.setHasOtherSupport(cursor.getString(i++));  //40
            surveyBean.setOrg1(cursor.getString(i++));
            surveyBean.setOrg2(cursor.getString(i++));
            surveyBean.setOrg3(cursor.getString(i++));

            surveyBean.setProblem1(cursor.getString(i++));
            surveyBean.setProblem2(cursor.getString(i++));
            surveyBean.setProblem3(cursor.getString(i++));

            surveyBean.setRequest1(cursor.getString(i++));
            surveyBean.setRequest2(cursor.getString(i++));
            surveyBean.setRequest3(cursor.getString(i++));

            surveyBean.setPicture1(cursor.getString(i++));    //50
            surveyBean.setPicture2(cursor.getString(i++));
            surveyBean.setPicture3(cursor.getString(i++));

            surveyBean.setUpdate_Date(cursor.getString(i++));
            surveyBean.setUpdate_By(cursor.getString(i++));
            surveyBean.setRemark1(cursor.getString(i++));
            surveyBean.setRemark2(cursor.getString(i++));
        }
        cursor.close();
        return surveyBean;
    }

}
