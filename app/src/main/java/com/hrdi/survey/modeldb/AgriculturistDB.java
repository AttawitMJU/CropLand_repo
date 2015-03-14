package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class AgriculturistDB {

    // Attribute Column Name
    public static final String AGRICULTURIST_ID = "AGRICULTURIST_ID";
    public static final String CARD_NO = "CARD_NO";
    public static final String CARD_TYPE = "CARD_TYPE";
    public static final String TITLE = "TITLE";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String PICTURE = "PICTURE";
    public static final String HOME_NO = "HOME_NO";
    public static final String MOO_NO = "MOO_NO";
    public static final String GROUP_NO = "GROUP_NO";
    public static final String VILLAGE_NO = "VILLAGE_NO";
    public static final String TAMBOL_ID = "TAMBOL_ID";
    public static final String AMPHUR_ID = "AMPHUR_ID";
    public static final String PROVINCE_ID = "PROVINCE_ID";
    public static final String ZIPCODE = "ZIPCODE";
    public static final String OCCUPATION1 = "OCCUPATION1";
    public static final String OCCUPATION2 = "OCCUPATION2";
    public static final String FREE_TIME = "FREE_TIME";
    public static final String MEMBER_ALL = "MEMBER_ALL";
    public static final String MEMBER_TYPE1 = "MEMBER_TYPE1";
    public static final String MEMBER_TYPE2 = "MEMBER_TYPE2";
    public static final String MEMBER_TYPE3 = "MEMBER_TYPE3";
    public static final String INCOME_ALL = "INCOME_ALL";
    public static final String INCOMES1 = "INCOMES1";
    public static final String INCOMES2 = "INCOMES2";
    public static final String EXPENSES_ALL = "EXPENSES_ALL";
    public static final String EXPENSES1 = "EXPENSES1";
    public static final String EXPENSES2 = "EXPENSES2";
    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";
    public static final String REMARK1 = "REMARK1";
    public static final String REMARK2 = "REMARK2";

    //---
    public static final String TABLE_NAME = "AGRICULTURIST";
    //---

    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(AGRICULTURIST_ID);
        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(CARD_NO);
        sql.append(" TEXT,");
        sql.append(CARD_TYPE);
        sql.append(" TEXT,");
        sql.append(TITLE);
        sql.append(" TEXT,");
        sql.append(FIRSTNAME);
        sql.append(" TEXT,");
        sql.append(LASTNAME);
        sql.append(" TEXT,");
        sql.append(PICTURE);
        sql.append(" TEXT,");
        sql.append(HOME_NO);
        sql.append(" TEXT,");
        sql.append(MOO_NO);
        sql.append(" TEXT,");
        sql.append(GROUP_NO);
        sql.append(" TEXT,");
        sql.append(VILLAGE_NO);
        sql.append(" TEXT,");
        sql.append(TAMBOL_ID);
        sql.append(" TEXT,");
        sql.append(AMPHUR_ID);
        sql.append(" TEXT,");
        sql.append(PROVINCE_ID);
        sql.append(" TEXT,");
        sql.append(ZIPCODE);
        sql.append(" TEXT,");
        sql.append(OCCUPATION1);
        sql.append(" TEXT,");
        sql.append(OCCUPATION2);
        sql.append(" TEXT,");
        sql.append(FREE_TIME);
        sql.append(" TEXT,");
        sql.append(MEMBER_ALL);
        sql.append(" TEXT,");
        sql.append(MEMBER_TYPE1);
        sql.append(" TEXT,");
        sql.append(MEMBER_TYPE2);
        sql.append(" TEXT,");
        sql.append(MEMBER_TYPE3);
        sql.append(" TEXT,");
        sql.append(INCOME_ALL);
        sql.append(" TEXT,");
        sql.append(INCOMES1);
        sql.append(" TEXT,");
        sql.append(INCOMES2);
        sql.append(" TEXT,");
        sql.append(EXPENSES_ALL);
        sql.append(" TEXT,");
        sql.append(EXPENSES1);
        sql.append(" TEXT,");
        sql.append(EXPENSES2);
        sql.append(" TEXT,");
        sql.append(UPDATE_DATE);
        sql.append(" TEXT,");
        sql.append(UPDATE_BY);
        sql.append(" TEXT,");
        sql.append(REMARK1);
        sql.append(" TEXT,");
        sql.append(REMARK2);
        sql.append(" TEXT");

        sql.append(")");

        return sql.toString();
    }

    public static String getDropSQL() {
        return "DROP TABLE IF EXISTS  " + TABLE_NAME;
    }

    public static String getSelectAllSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(AGRICULTURIST_ID);
        sql.append(" , ");
        sql.append(CARD_NO);
        sql.append(" ,");
        sql.append(CARD_TYPE);
        sql.append(" ,");
        sql.append(TITLE);
        sql.append(" ,");
        sql.append(FIRSTNAME);
        sql.append(" ,");
        sql.append(LASTNAME);
        sql.append(" ,");
        //sql.append(PICTURE);
        //sql.append(" ,");
        sql.append(HOME_NO);
        sql.append(" ,");
        sql.append(MOO_NO);
        sql.append(" ,");
        sql.append(GROUP_NO);
        sql.append(" ,");
        sql.append(VILLAGE_NO);
        sql.append(" ,");
        sql.append(TAMBOL_ID);
        sql.append(" ,");
        sql.append(AMPHUR_ID);
        sql.append(" ,");
        sql.append(PROVINCE_ID);
        sql.append(" ,");
        sql.append(ZIPCODE);

        sql.append(" FROM ");
        sql.append(TABLE_NAME);
        sql.append(" ORDER BY ");
        sql.append(CARD_NO);

        return sql.toString();
    }
    public static String getSelectSQL(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT ");
        sql.append(AGRICULTURIST_ID);
        sql.append(" , ");
        sql.append(CARD_NO);
        sql.append(" ,");
        sql.append(CARD_TYPE);
        sql.append(" ,");
        sql.append(TITLE);
        sql.append(" ,");
        sql.append(FIRSTNAME);
        sql.append(" ,");
        sql.append(LASTNAME);
        sql.append(" ,");
        //sql.append(PICTURE);
        //sql.append(" ,");
        sql.append(HOME_NO);
        sql.append(" ,");
        sql.append(MOO_NO);
        sql.append(" ,");
        sql.append(GROUP_NO);
        sql.append(" ,");
        sql.append(VILLAGE_NO);
        sql.append(" ,");
        sql.append(TAMBOL_ID);
        sql.append(" ,");
        sql.append(AMPHUR_ID);
        sql.append(" ,");
        sql.append(PROVINCE_ID);
        sql.append(" ,");
        sql.append(ZIPCODE);

        sql.append(" FROM ");
        sql.append(TABLE_NAME);
        sql.append(" WHERE ");
        sql.append(CARD_NO);
        sql.append(" = '");
        sql.append(id);
        sql.append("'");

        return sql.toString();
    }
}
