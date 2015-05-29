package com.hrdi.survey.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.modeldb.LandUseDB;

import java.util.ArrayList;

/**
 * Created by attawit on 1/27/15 AD.
 */
public class LandUseDAO extends HrdiDBDAO {


    public LandUseDAO(Context context) {
        super(context);
    }


    public int deleteLandUse(LandUseBean landUse) {
        return database.delete(LandUseDB.TABLE_NAME, LandUseDB.WHERE_ID_EQUALS,
                new String[]{landUse.getLandUse_ID() + ""});
    }

    public long addSurveyPlant(LandUseBean landUse) {
        long newID = -1;
        ContentValues values = new ContentValues();
        try {
            values.put(LandUseDB.SURVEY_ID, landUse.getSurvey_ID());
            values.put(LandUseDB.LAND_NO, landUse.getLand_No());
            values.put(LandUseDB.CARD_NO, landUse.getCard_no());
            values.put(LandUseDB.SURVEY_DATE, landUse.getSurvey_Date());
            values.put(LandUseDB.PLANT_YEAR, landUse.getPlant_Year());
            values.put(LandUseDB.AREA, landUse.getArea());
            values.put(LandUseDB.HAVEST_STATUS, landUse.getHarvest_Status());
            values.put(LandUseDB.SEEDS, landUse.getSeeds());
            values.put(LandUseDB.HAS_HIRE, landUse.getHas_Hire());
            values.put(LandUseDB.LABOUR, landUse.getLabour());
            values.put(LandUseDB.FUEL, landUse.getFuel());
            values.put(LandUseDB.OTHER_PAID, landUse.getOther_Paid());
            values.put(LandUseDB.PRODUCT_USE, landUse.getProduct_Use());
            values.put(LandUseDB.PRODUCT_SALE, landUse.getProduct_Sale());
            values.put(LandUseDB.PRICE, landUse.getPrice());
            values.put(LandUseDB.INCOME_YEAR, landUse.getIncome_Year());
            values.put(LandUseDB.MARKET, landUse.getMarket());
            values.put(LandUseDB.EMPLOY_TYPE, landUse.getEmploy_Type());
            values.put(LandUseDB.EMPLOY_FROM, landUse.getEmploy_From());
            values.put(LandUseDB.LABOUR_TOTAL, landUse.getLabour_Total());
            values.put(LandUseDB.LABOUR_PAID, landUse.getLabour_Paid());
            values.put(LandUseDB.LABOUR_TIME, landUse.getLabour_Time());

            values.put(LandUseDB.START_CROP, landUse.getStart_crop());
            values.put(LandUseDB.END_CROP, landUse.getEnd_crop());

            values.put(LandUseDB.PLANT_TYPE, landUse.getPlant_Type());
            values.put(LandUseDB.PLANT_ID, landUse.getPlant_id());
            values.put(LandUseDB.PLANT_DETAIL_ID, landUse.getPlant_detail_id());

            values.put(LandUseDB.FERTILIZER1, landUse.getFertilizer1());
            values.put(LandUseDB.FERTILIZER2, landUse.getFertilizer2());
            values.put(LandUseDB.FERTILIZER3, landUse.getFertilizer3());
            values.put(LandUseDB.FERTILIZER_CODE1, landUse.getFertilizer_code1());
            values.put(LandUseDB.FERTILIZER_CODE2, landUse.getFertilizer_code2());
            values.put(LandUseDB.FERTILIZER_CODE3, landUse.getFertilizer_code3());
            values.put(LandUseDB.FERTILIZER_TOTAL1, landUse.getFertilizer_total1());
            values.put(LandUseDB.FERTILIZER_TOTAL2, landUse.getFertilizer_total2());
            values.put(LandUseDB.FERTILIZER_TOTAL3, landUse.getFertilizer_total3());
            values.put(LandUseDB.FERTILIZER_PRICE1, landUse.getFertilizer_price1());
            values.put(LandUseDB.FERTILIZER_PRICE2, landUse.getFertilizer_price2());
            values.put(LandUseDB.FERTILIZER_PRICE3, landUse.getFertilizer_price3());
            values.put(LandUseDB.FERTILIZER_SUM1, landUse.getFertilizer_sum1());
            values.put(LandUseDB.FERTILIZER_SUM2, landUse.getFertilizer_sum2());
            values.put(LandUseDB.FERTILIZER_SUM3, landUse.getFertilizer_sum3());

            values.put(LandUseDB.HORMONE_TYPE1, landUse.getHormone_type1());
            values.put(LandUseDB.HORMONE_TYPE2, landUse.getHormone_type2());
            values.put(LandUseDB.HORMONE_TYPE3, landUse.getHormone_type3());
            values.put(LandUseDB.HORMONE_BRAND1, landUse.getHormone_brand1());
            values.put(LandUseDB.HORMONE_BRAND2, landUse.getHormone_brand2());
            values.put(LandUseDB.HORMONE_BRAND3, landUse.getHormone_brand3());
            values.put(LandUseDB.HORMONE_TOTAL1, landUse.getHormone_total1());
            values.put(LandUseDB.HORMONE_TOTAL2, landUse.getHormone_total2());
            values.put(LandUseDB.HORMONE_TOTAL3, landUse.getHormone_total3());
            values.put(LandUseDB.HORMONE_PRICE1, landUse.getHormone_price1());
            values.put(LandUseDB.HORMONE_PRICE2, landUse.getHormone_price2());
            values.put(LandUseDB.HORMONE_PRICE3, landUse.getHormone_price3());
            values.put(LandUseDB.HORMONE_SUM1, landUse.getHormone_sum1());
            values.put(LandUseDB.HORMONE_SUM2, landUse.getHormone_sum2());
            values.put(LandUseDB.HORMONE_SUM3, landUse.getHormone_sum3());
            values.put(LandUseDB.UPDATE_DATE, landUse.getUpdate_Date());
            values.put(LandUseDB.UPDATE_BY, landUse.getUpdate_By());
            values.put(LandUseDB.REMARK1, landUse.getRemark1());
            values.put(LandUseDB.REMARK2, landUse.getRemark2());


            //values.put(LandUseDB.PLANT_TYPE, landUse.getPlant_Type());
            //values.put(LandUseDB.PLANT_ID, landUse.getPlant_id());
            //values.put(LandUseDB.PLANT_DETAIL_ID, landUse.getPlant_detail_id());
            //Log.i("values PLANT_TYPE", values.getAsString(LandUseDB.PLANT_TYPE));
            //Log.i("values PLANT_ID", values.getAsString(LandUseDB.PLANT_ID));
            //Log.i("values PLANT_DETAIL_ID", values.getAsString(LandUseDB.PLANT_DETAIL_ID));

            // Inserting Row
            newID = database.insert(LandUseDB.TABLE_NAME, null, values);
            // database.close(); // Closing database connection
        } catch (Exception e) {
            Log.e("Insert LandUse Error", e.toString());
        }
        return newID;
    }

    public ArrayList<LandUseBean> getLandUseList(String surveyID) {


        //Log.i("getLandUseList --> ", "Start");

        ArrayList<LandUseBean> landUseBeans = new ArrayList<>();

        String query = LandUseDB.getSelectAllSQL(surveyID);

        //Log.i("LandUseDB query --> ", LandUseDB.getSelectAllSQL(surveyID));

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            LandUseBean landUseBean = new LandUseBean();
            landUseBean.setLandUse_ID(String.valueOf(cursor.getInt(0)));

            landUseBean.setSurvey_ID(cursor.getString(1));
            landUseBean.setPlant_Type(cursor.getString(2));
            landUseBean.setPlant_id(cursor.getString(3));
            landUseBean.setPlant_detail_id(cursor.getString(4));
            landUseBean.setStart_crop(cursor.getString(5));
            landUseBean.setEnd_crop(cursor.getString(6));
            landUseBean.setSeeds(cursor.getString(7));
            landUseBean.setPlant_TypeName(cursor.getString(8));
            landUseBean.setPlant_idName(cursor.getString(9));
            landUseBean.setPlant_detail_idName(cursor.getString(10));
            landUseBean.setArea(cursor.getString(11));


            landUseBeans.add(landUseBean);
        }
        //Log.i("landUseBeans --> ", "" + landUseBeans.size());
        return landUseBeans;
    }

    public ArrayList<LandUseBean> getLandUseListbyLandCode(String landNo) {


        //Log.i("getLandUseList --> ", "Start");

        ArrayList<LandUseBean> landUseBeans = new ArrayList<>();

        String query = LandUseDB.getSelectAllSQLbyLandCode(landNo);

        //Log.i("LandUseDB query --> ", LandUseDB.getSelectAllSQLbyLandCode(landNo));

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            LandUseBean landUseBean = new LandUseBean();
            landUseBean.setLandUse_ID(String.valueOf(cursor.getInt(0)));

            landUseBean.setSurvey_ID(cursor.getString(1));
            landUseBean.setPlant_Type(cursor.getString(2));
            landUseBean.setPlant_id(cursor.getString(3));
            landUseBean.setPlant_detail_id(cursor.getString(4));
            landUseBean.setStart_crop(cursor.getString(5));
            landUseBean.setEnd_crop(cursor.getString(6));
            landUseBean.setSeeds(cursor.getString(7));
            landUseBean.setPlant_TypeName(cursor.getString(8));
            landUseBean.setPlant_idName(cursor.getString(9));
            landUseBean.setPlant_detail_idName(cursor.getString(10));
            landUseBean.setArea(cursor.getString(11));


            landUseBeans.add(landUseBean);
        }
        //Log.i("landUseBeans --> ", "" + landUseBeans.size());
        return landUseBeans;
    }
}
