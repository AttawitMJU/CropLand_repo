package com.hrdi.survey.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.modeldb.AgriculturistDB;
import com.hrdi.survey.modeldb.SurveyDB;
import com.hrdi.survey.util.JSONParser;

import java.util.ArrayList;

/**
 * Created by attawit on 1/21/15 AD.
 */
public class AgriculturistDAO extends HrdiDBDAO {
    private static final String WHERE_ID_EQUALS = SurveyDB.SURVEY_ID + " =?";
    private JSONParser jsonParser;


    public AgriculturistDAO(Context context) {
        super(context);
    }

    public AgriculturistBean getPerson(String idCard) {

        AgriculturistBean bean=  new AgriculturistBean();
        try {

            Cursor cursor = database.rawQuery(AgriculturistDB.getSelectSQL(idCard), null);
            int i=0;
            if (cursor.moveToNext()) {

                bean.setAgriculturist_id(cursor.getString(i++));
                bean.setCard_no(cursor.getString(i++));
                bean.setCard_type(cursor.getString(i++));
                bean.setTitle(cursor.getString(i++));
                bean.setFirstname(cursor.getString(i++));
                bean.setLastname(cursor.getString(i++));
                bean.setHome_no(cursor.getString(i++));
                bean.setMoo_no(cursor.getString(i++));
                bean.setGroup_no(cursor.getString(i++));
                bean.setVillage_no(cursor.getString(i++));
                bean.setTambol_id(cursor.getString(i++));
                bean.setAmphur_id(cursor.getString(i++));
                bean.setProvince_id(cursor.getString(i++));
                bean.setZipcode(cursor.getString(i++));

                //TODO: Complete Adrress Data
                //bean.setRemark1(cursor.getString(i++));

            }
            cursor.close();
            return bean;

        } catch (Exception e) {
            return null;
        }
    }


    public ArrayList<AgriculturistBean> getAllAgriculturist() {
        ArrayList<AgriculturistBean> items = new ArrayList<AgriculturistBean>();
        try {
            AgriculturistBean bean;

            Cursor cursor = database.rawQuery(AgriculturistDB.getSelectAllSQL(), null);

            int i=0;
            while (cursor.moveToNext()) {
                bean = new AgriculturistBean();
                bean.setAgriculturist_id(cursor.getString(i++));
                bean.setCard_no(cursor.getString(i++));
                bean.setCard_type(cursor.getString(i++));
                bean.setTitle(cursor.getString(i++));
                bean.setFirstname(cursor.getString(i++));
                bean.setLastname(cursor.getString(i++));
                bean.setHome_no(cursor.getString(i++));
                bean.setMoo_no(cursor.getString(i++));
                bean.setGroup_no(cursor.getString(i++));
                bean.setVillage_no(cursor.getString(i++));
                bean.setTambol_id(cursor.getString(i++));
                bean.setAmphur_id(cursor.getString(i++));
                bean.setProvince_id(cursor.getString(i++));
                bean.setZipcode(cursor.getString(i++));

                items.add(bean);
            }

        } catch (Exception e) {
            Log.e("Error getCardType", e.getMessage());
        }
        return items;

    }

    public long addPersonSQLite(AgriculturistBean bean) {

        ContentValues values = new ContentValues();


        values.put(AgriculturistDB.AGRICULTURIST_ID, bean.getAgriculturist_id());
        values.put(AgriculturistDB.CARD_NO, bean.getCard_no());
        values.put(AgriculturistDB.CARD_TYPE, bean.getCard_type());
        values.put(AgriculturistDB.TITLE, bean.getTitle());
        values.put(AgriculturistDB.FIRSTNAME, bean.getFirstname());
        values.put(AgriculturistDB.LASTNAME, bean.getLastname());
        values.put(AgriculturistDB.PICTURE, bean.getPicture());
        values.put(AgriculturistDB.HOME_NO, bean.getHome_no());
        values.put(AgriculturistDB.MOO_NO, bean.getMoo_no());
        values.put(AgriculturistDB.GROUP_NO, bean.getGroup_no());
        values.put(AgriculturistDB.VILLAGE_NO, bean.getVillage_no());
        values.put(AgriculturistDB.TAMBOL_ID, bean.getTambol_id());
        values.put(AgriculturistDB.AMPHUR_ID, bean.getAmphur_id());
        values.put(AgriculturistDB.PROVINCE_ID, bean.getProvince_id());
        values.put(AgriculturistDB.ZIPCODE, bean.getZipcode());
        values.put(AgriculturistDB.OCCUPATION1, bean.getOccupation1());
        values.put(AgriculturistDB.OCCUPATION2, bean.getOccupation2());
        values.put(AgriculturistDB.FREE_TIME, bean.getFree_time());
        values.put(AgriculturistDB.MEMBER_ALL, bean.getMember_all());
        values.put(AgriculturistDB.MEMBER_TYPE1, bean.getMember_type1());
        values.put(AgriculturistDB.MEMBER_TYPE2, bean.getMember_type2());
        values.put(AgriculturistDB.MEMBER_TYPE3, bean.getMember_type3());
        values.put(AgriculturistDB.INCOME_ALL, bean.getIncome_all());
        values.put(AgriculturistDB.INCOMES1, bean.getIncomes1());
        values.put(AgriculturistDB.INCOMES2, bean.getIncomes2());
        values.put(AgriculturistDB.EXPENSES_ALL, bean.getExpenses_all());
        values.put(AgriculturistDB.EXPENSES1, bean.getExpenses1());
        values.put(AgriculturistDB.EXPENSES2, bean.getExpenses2());
        values.put(AgriculturistDB.UPDATE_DATE, bean.getUpdate_date());
        values.put(AgriculturistDB.UPDATE_BY, bean.getUpdate_by());
        values.put(AgriculturistDB.REMARK1, bean.getRemark1());
        values.put(AgriculturistDB.REMARK2, bean.getRemark2());

        // Inserting Row
        long newID = database.insert(AgriculturistDB.TABLE_NAME, null, values);
        // database.close(); // Closing database connection
        return newID;
    }

    public void cleanAgriculturist() {
        String tableName = AgriculturistDB.TABLE_NAME;

        database.delete(tableName, "", null);
    }

    public int importAgriculturist(ArrayList list) {
        int i = 0;
        String tableName = AgriculturistDB.TABLE_NAME;
        ContentValues values = new ContentValues();
        AgriculturistBean bean;

        while (i < list.size()) {
            bean = (AgriculturistBean) list.get(i);

            addPersonSQLite( bean);

            i++;
        }

        return i;
    }

}
