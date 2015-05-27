package com.hrdi.survey.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.model.SurveyActivityBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.model.SurveyDetailEtcBean;
import com.hrdi.survey.modeldb.LandUseDB;
import com.hrdi.survey.modeldb.MetaExtProjectDB;
import com.hrdi.survey.modeldb.MetaProjectAreaDB;
import com.hrdi.survey.modeldb.MetaProjectMooDB;
import com.hrdi.survey.modeldb.SurveyActivityDB;
import com.hrdi.survey.modeldb.SurveyDB;
import com.hrdi.survey.modeldb.SurveyProblemDB;
import com.hrdi.survey.modeldb.SurveySupportDB;
import com.hrdi.survey.modeldb.SurveyTempDB;
import com.hrdi.survey.modeldb.SurveyWantDB;
import com.hrdi.survey.util.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyDAO extends HrdiDBDAO {

    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    private static final String WHERE_ID_EQUALS = SurveyDB.SURVEY_ID + " =?";
    private JSONParser jsonParser;
    MetaDAO metaDAO;

    public SurveyDAO(Context context) {

        super(context);
        metaDAO = new MetaDAO(context);
    }


    public long addSurvey(SurveyBean survey) {

        ContentValues values = putSurveyValues(survey);


        // Inserting Row
        long newID = database.insert(SurveyDB.TABLE_NAME, null, values);
        // database.close(); // Closing database connection
        return newID;
    }

    public long addSurveyTemp(ArrayList<SurveyBean> surveys) {
        long result = 0;
        SurveyBean sb;
        if (surveys != null) {
            for (int i = 0; i < surveys.size(); i++) {
                sb = surveys.get(i);
                ContentValues values = putSurveyTempValues(sb);
                // Inserting Row
                result += database.insert(SurveyTempDB.TABLE_NAME, null, values);
                // database.close(); // Closing database connection
            }
        }
        return result;
    }

    private ContentValues putSurveyValues(SurveyBean survey) {
        ContentValues values = new ContentValues();

        values.put(SurveyDB.PROJECT_AREA, survey.getProject_Area());
        values.put(SurveyDB.EXT_PROJECT, survey.getExt_Project());
        values.put(SurveyDB.PROJECT_MOOBAN, survey.getProject_MooBan());

        values.put(SurveyDB.LAND_NO, survey.getLand_No());
        values.put(SurveyDB.CARD_NO, survey.getCard_no());

        values.put(SurveyDB.SURVEY_DATE, survey.getSurvey_Date());
        values.put(SurveyDB.LATLONG, survey.getLatlong());


        values.put(SurveyDB.LAND_DOC_TYPE, survey.getLand_doc_type());
        values.put(SurveyDB.AREA_STATUS, survey.getArea_status());
        values.put(SurveyDB.AREA_STATUS_YEAR, survey.getArea_status_year());

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
        values.put(SurveyDB.HASOTHERSUPPORT, survey.getHasOtherSupport());

        //values.put(SurveyDB.PICTURE1, survey.getPicture1());
        //values.put(SurveyDB.PICTURE2, survey.getPicture2());
        //values.put(SurveyDB.PICTURE3, survey.getPicture3());

        values.put(SurveyDB.UPDATE_BY, survey.getUpdate_By());
        values.put(SurveyDB.UPDATE_DATE, survey.getUpdate_Date());
        values.put(SurveyDB.REMARK1, survey.getRemark1());
        values.put(SurveyDB.REMARK2, survey.getRemark2());
        return values;
    }

    private ContentValues putSurveyTempValues(SurveyBean survey) {
        ContentValues values = new ContentValues();

        values.put(SurveyDB.SURVEY_ID, survey.getSurvey_id());
        values.put(SurveyDB.PROJECT_AREA, survey.getProject_Area());
        values.put(SurveyDB.EXT_PROJECT, survey.getExt_Project());
        values.put(SurveyDB.PROJECT_MOOBAN, survey.getProject_MooBan());

        values.put(SurveyDB.LAND_NO, survey.getLand_No());
        values.put(SurveyDB.CARD_NO, survey.getCard_no());

        return values;
    }

    public long addSurveyActivity(SurveyActivityBean survey) {
        ContentValues values = new ContentValues();

        values.put(SurveyActivityDB.SURVEY_ID, survey.getSurvey_id());
        values.put(SurveyActivityDB.LAND_NO, survey.getLand_No());
        values.put(SurveyActivityDB.CARD_NO, survey.getCard_no());

        values.put(SurveyActivityDB.ACTIVITY1, survey.getActivity1());
        values.put(SurveyActivityDB.REPEAT1, survey.getRepeat1());
        values.put(SurveyActivityDB.OUTCOME1, survey.getOutcome1());
        values.put(SurveyActivityDB.SURVIVE1, survey.getSurvive1());

        values.put(SurveyActivityDB.UPDATE_BY, survey.getUpdate_By());
        values.put(SurveyActivityDB.UPDATE_DATE, survey.getUpdate_Date());

        long newID = database.insert(SurveyActivityDB.TABLE_NAME, null, values);

        return newID;
    }

    public long addSurveyEtc(SurveyDetailEtcBean survey, String etcType) {

        Log.i("addSurveyEtc", "addSurveyEtc...." + etcType);
        ContentValues values;
        long newID = 0;

        values = putValueSurveyEtc(survey);

        if ("support".equals(etcType)) {
            newID = database.insert(SurveySupportDB.TABLE_NAME, null, values);
        } else if ("want".equals(etcType)) {
            newID = database.insert(SurveyWantDB.TABLE_NAME, null, values);
        } else if ("problem".equals(etcType)) {
            newID = database.insert(SurveyProblemDB.TABLE_NAME, null, values);
        }

        return newID;
    }

    private ContentValues putValueSurveyEtc(SurveyDetailEtcBean survey) {
        ContentValues values = new ContentValues();
        values.put(SurveySupportDB.SURVEY_ID, survey.getSurvey_id());
        values.put(SurveySupportDB.LAND_NO, survey.getLand_No());

        values.put(SurveySupportDB.DETAIL, survey.getDetail());

        values.put(SurveyActivityDB.UPDATE_BY, survey.getUpdate_By());
        values.put(SurveyActivityDB.UPDATE_DATE, survey.getUpdate_Date());
        return values;
    }


    public List<NameValuePair> setSurvey2Parameter(SurveyBean survey) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "AddSurvey"));

        params.add(new BasicNameValuePair(SurveyDB.SURVEY_ID, survey.getSurvey_id()));
        params.add(new BasicNameValuePair(SurveyDB.LAND_NO, survey.getLand_No()));
        params.add(new BasicNameValuePair(SurveyDB.CARD_NO, survey.getCard_no()));

        params.add(new BasicNameValuePair(SurveyDB.SURVEY_DATE, survey.getSurvey_Date()));
        params.add(new BasicNameValuePair(SurveyDB.LATLONG, survey.getLatlong()));

        params.add(new BasicNameValuePair(SurveyDB.PROJECT_AREA, survey.getProject_Area()));
        params.add(new BasicNameValuePair(SurveyDB.EXT_PROJECT, survey.getExt_Project()));
        params.add(new BasicNameValuePair(SurveyDB.PROJECT_MOOBAN, survey.getProject_MooBan()));

        params.add(new BasicNameValuePair(SurveyDB.LAND_DOC_TYPE, survey.getLand_doc_type()));
        params.add(new BasicNameValuePair(SurveyDB.AREA_STATUS, survey.getArea_status()));
        params.add(new BasicNameValuePair(SurveyDB.AREA_STATUS_YEAR, survey.getArea_status_year()));


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

        params.add(new BasicNameValuePair(SurveyDB.HASOTHERSUPPORT, survey.getHasOtherSupport()));

        params.add(new BasicNameValuePair(SurveyDB.PICTURE1, survey.getPicture1()));
        params.add(new BasicNameValuePair(SurveyDB.PICTURE2, survey.getPicture2()));
        params.add(new BasicNameValuePair(SurveyDB.PICTURE3, survey.getPicture3()));

        params.add(new BasicNameValuePair(SurveyDB.UPDATE_BY, survey.getUpdate_By()));
        params.add(new BasicNameValuePair(SurveyDB.UPDATE_DATE, survey.getUpdate_Date()));
        params.add(new BasicNameValuePair(SurveyDB.REMARK1, survey.getRemark1()));
        params.add(new BasicNameValuePair(SurveyDB.REMARK2, survey.getRemark2()));

        Log.i("params", params.toString());

        return params;
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

    public List<NameValuePair> setActivity2Parameter(SurveyActivityBean bean) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "AddActivity"));

        params.add(new BasicNameValuePair(SurveyActivityDB.SURVEY_ID, bean.getSurvey_id()));
        params.add(new BasicNameValuePair(SurveyActivityDB.LAND_NO, bean.getLand_No()));
        params.add(new BasicNameValuePair(SurveyActivityDB.CARD_NO, bean.getCard_no()));
        params.add(new BasicNameValuePair(SurveyActivityDB.ACTIVITY1, bean.getActivity1()));
        params.add(new BasicNameValuePair(SurveyActivityDB.REPEAT1, bean.getRepeat1()));
        params.add(new BasicNameValuePair(SurveyActivityDB.OUTCOME1, bean.getOutcome1()));
        params.add(new BasicNameValuePair(SurveyActivityDB.SURVIVE1, bean.getSurvive1()));
        params.add(new BasicNameValuePair(SurveyActivityDB.UPDATE_DATE, bean.getUpdate_Date()));

        return params;
    }

    public List<NameValuePair> setDetailEtc2Parameter(SurveyDetailEtcBean bean) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "AddActivity"));

        params.add(new BasicNameValuePair(SurveySupportDB.SURVEY_ID, bean.getSurvey_id()));
        params.add(new BasicNameValuePair(SurveySupportDB.LAND_NO, bean.getLand_No()));
        params.add(new BasicNameValuePair(SurveySupportDB.DETAIL, bean.getDetail()));
        params.add(new BasicNameValuePair(SurveyActivityDB.UPDATE_DATE, bean.getUpdate_Date()));

        return params;
    }

    public long updateSurvey(SurveyBean survey) {
        ContentValues values = putSurveyValues(survey);

        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(survey.getSurvey_id())});
        //database.close(); // Closing database connection
        return result;
    }

    public long updateSurveyEtc(SurveyBean survey) {

        //Log.i("PICTURE1",survey.getPicture1());

        ContentValues values = new ContentValues();

        //if (survey.getPicture1() != null)
            values.put(SurveyDB.PICTURE1, survey.getPicture1());
        //if (survey.getPicture2() != null)
            values.put(SurveyDB.PICTURE2, survey.getPicture2());
        //if (survey.getPicture3() != null)
            values.put(SurveyDB.PICTURE3, survey.getPicture3());

        Log.i("values", values.toString());
        // Update Data with Survey_ID
        long result = database.update(SurveyDB.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[]{String.valueOf(survey.getSurvey_id())});
        //database.close(); // Closing database connection
        return result;
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

    public ArrayList<SurveyActivityBean> getSurveyAcitvity(String surveyID) {
        ArrayList<SurveyActivityBean> surveyBeanArrayList = new ArrayList<>();
        String query = SurveyActivityDB.getSelectSQLAllDetail(surveyID);
        Log.i("query", query);
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            SurveyActivityBean bean = new SurveyActivityBean();
            bean.setActivity_id(String.valueOf(cursor.getInt(i++)));
            bean.setSurvey_id(String.valueOf(cursor.getInt(i++)));
            bean.setLand_No(cursor.getString(i++));
            bean.setCard_no(cursor.getString(i++));
            bean.setActivity1(cursor.getString(i++));
            bean.setRepeat1(cursor.getString(i++));
            bean.setOutcome1(cursor.getString(i++));
            bean.setSurvive1(cursor.getString(i++));

            Date date = new Date();
            bean.setUpdate_Date(dateTimeFormatter.format(date));


            surveyBeanArrayList.add(bean);
        }
        cursor.close();
        return surveyBeanArrayList;
    }


    public ArrayList<SurveyDetailEtcBean> getSurveyEtc(String surveyID, String etcType) {
        ArrayList<SurveyDetailEtcBean> etcList = new ArrayList<>();
        String query = "";
        if ("support".equals(etcType)) {
            query = SurveySupportDB.getSelectSQLAllDetail(surveyID);
        } else if ("problem".equals(etcType)) {
            query = SurveyProblemDB.getSelectSQLAllDetail(surveyID);
        } else if ("want".equals(etcType)) {
            query = SurveyWantDB.getSelectSQLAllDetail(surveyID);
        }

        Log.i("query", query);
        int i;
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            SurveyDetailEtcBean bean = new SurveyDetailEtcBean();
            bean.setEtc_id(String.valueOf(cursor.getInt(i++)));
            bean.setSurvey_id(String.valueOf(cursor.getInt(i++)));
            bean.setLand_No(cursor.getString(i++));
            //bean.setCard_no(cursor.getString(i++));
            bean.setDetail(cursor.getString(i++));

            //Log.i("SurveyEtcBean", bean.toString());
            etcList.add(bean);
        }
        cursor.close();
        return etcList;
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
            surveyBean.setProject_Area(cursor.getString(i++));
            surveyBean.setProject_Area_Name(metaDAO.getMetaByType(surveyBean.getProject_Area(), MetaProjectAreaDB.TABLE_NAME).getItemName());

            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setExt_Project_name(metaDAO.getMetaByType(surveyBean.getExt_Project(), MetaExtProjectDB.TABLE_NAME).getItemName());

            surveyBean.setProject_MooBan(cursor.getString(i++));
            try {
                if (surveyBean.getProject_MooBan() != null) {
                    surveyBean.setProject_MooBan_Name(metaDAO.getMetaByType(surveyBean.getProject_MooBan(), MetaProjectMooDB.TABLE_NAME).getItemName());
                }
            } catch (Exception e) {

            }

            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setCard_no(cursor.getString(i++));
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


            try {
                String oldString = cursor.getString(i++);
                Date d = new SimpleDateFormat("d/M/yyyy").parse(oldString);
                String newstring = new SimpleDateFormat("yyyy-MM-dd").format(d);
                landUseBean.setStart_crop(newstring);

                oldString = cursor.getString(i++);
                d = new SimpleDateFormat("d/M/yyyy").parse(oldString);
                newstring = new SimpleDateFormat("yyyy-MM-dd").format(d);
                landUseBean.setEnd_crop(newstring);

            } catch (Exception e) {

            }
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

            landUseBean.setUpdate_By(String.valueOf(cursor.getString(i++)));
            landUseBean.setRemark1(String.valueOf(cursor.getString(i++)));
            landUseBean.setRemark2(String.valueOf(cursor.getString(i++)));

            Date date = new Date();
            landUseBean.setUpdate_Date(dateTimeFormatter.format(date));

            landUseBeans.add(landUseBean);
        }
        cursor.close();
        return landUseBeans;
    }

    public ArrayList<SurveyBean> getSurveyList(String status) {
        // status = all, waiting, send
        ArrayList<SurveyBean> surveyBeanArrayList = new ArrayList<>();

        String query = SurveyDB.getSelectSQLByStatus(status);
        int i;
        Log.i("query===", query);
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            i = 0;
            SurveyBean surveyBean = new SurveyBean();

            surveyBean.setSurvey_id(String.valueOf(cursor.getInt(i++)));

            String oldStringDate = cursor.getString(i++);
            try {
                Date d = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(oldStringDate);
                String newstring = new SimpleDateFormat("yyyy-MM-dd").format(d);
                surveyBean.setSurvey_Date(newstring);
            } catch (Exception e) {

            }
            surveyBean.setLand_No(cursor.getString(i++));

            surveyBean.setLatlong(cursor.getString(i++));

            surveyBean.setProject_Area(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));
            surveyBean.setProject_MooBan(cursor.getString(i++));

            surveyBean.setLand_doc_type(cursor.getString(i++));

            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setArea_status_year(cursor.getString(i++));

            surveyBean.setCard_no(cursor.getString(i++));

            surveyBean.setOwner_Type(cursor.getString(i++));
            surveyBean.setOwner_Type_Detail(cursor.getString(i++));
            surveyBean.setHistory(cursor.getString(i++));
            surveyBean.setHasOtherSupport(cursor.getString(i++));

            surveyBean.setInstitute_Support(cursor.getString(i++));
            surveyBean.setWater(cursor.getString(i++));
            surveyBean.setWater_Period(cursor.getString(i++));
            surveyBean.setWater_Use(cursor.getString(i++));

            surveyBean.setSoil_moisture(cursor.getString(i++));
            surveyBean.setTemperature(cursor.getString(i++));
            surveyBean.setHasActivity(cursor.getString(i++));

            surveyBean.setPicture1(cursor.getString(i++));
            surveyBean.setPicture2(cursor.getString(i++));
            surveyBean.setPicture3(cursor.getString(i++));

            surveyBean.setUpdate_By(cursor.getString(i++));
            surveyBean.setRemark1(cursor.getString(i++));
            surveyBean.setRemark2(cursor.getString(i++));


            Date date = new Date();
            surveyBean.setUpdate_Date(dateTimeFormatter.format(date));

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

            surveyBean.setProject_Area(cursor.getString(i++));
            surveyBean.setExt_Project(cursor.getString(i++));        //5
            surveyBean.setProject_MooBan(cursor.getString(i++));

            surveyBean.setLand_doc_type(cursor.getString(i++));
            surveyBean.setArea_status(cursor.getString(i++));
            surveyBean.setArea_status_year(cursor.getString(i++));

            surveyBean.setCard_no(cursor.getString(i++));           //10
            surveyBean.setOwner_Type(cursor.getString(i++));
            surveyBean.setOwner_Type_Detail(cursor.getString(i++));
            surveyBean.setHistory(cursor.getString(i++));
            surveyBean.setHasOtherSupport(cursor.getString(i++));
            surveyBean.setInstitute_Support(cursor.getString(i++)); //15

            surveyBean.setWater(cursor.getString(i++));
            surveyBean.setWater_Period(cursor.getString(i++));
            surveyBean.setWater_Use(cursor.getString(i++));
            surveyBean.setSoil_moisture(cursor.getString(i++));
            surveyBean.setTemperature(cursor.getString(i++));       //20

            surveyBean.setHasActivity(cursor.getString(i++));
            surveyBean.setPicture1(cursor.getString(i++));
            surveyBean.setPicture2(cursor.getString(i++));
            surveyBean.setPicture3(cursor.getString(i++));

            surveyBean.setUpdate_By(cursor.getString(i++));         //25
            surveyBean.setRemark1(cursor.getString(i++));
            surveyBean.setRemark2(cursor.getString(i++));


        }
        cursor.close();
        return surveyBean;
    }

}
