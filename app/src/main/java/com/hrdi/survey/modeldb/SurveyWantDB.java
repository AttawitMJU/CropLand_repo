package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyWantDB {
    //---
    public static final String TABLE_NAME = "SURVEY_WANT";
    //---

    // Attribute Column Name
    public static final String PK = "WANT_ID";

    public static final String SURVEY_ID = "SURVEY_ID";
    public static final String LAND_NO = "LAND_NO";

    public static final String DETAIL = "DETAIL";

    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";


    public SurveyWantDB() {
        super();
    }


    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(PK);
        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(SURVEY_ID);
        sql.append(" TEXT,");
        sql.append(LAND_NO);
        sql.append(" TEXT,");

        sql.append(DETAIL);
        sql.append(" TEXT,");

        sql.append(UPDATE_DATE);
        sql.append(" TEXT,");
        sql.append(UPDATE_BY);
        sql.append(" TEXT");


        sql.append(")");

        return sql.toString();
    }

    public static String getDropSQL() {
        return "DROP TABLE IF EXISTS  " + TABLE_NAME;
    }


    public static String getSelectSQLAllDetail(String surveyID) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(PK);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(DETAIL);
        sql.append(",");

        sql.append(UPDATE_DATE);
        sql.append(",");
        sql.append(UPDATE_BY);
        sql.append("");

        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!surveyID.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(SURVEY_ID);
            sql.append("=");
            sql.append(surveyID);
            sql.append("");
        }


        return sql.toString();
    }



    public static String getSelectSQLByID(String pk) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(PK);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(DETAIL);
        sql.append(",");

        sql.append(UPDATE_DATE);
        sql.append(",");
        sql.append(UPDATE_BY);
        sql.append("");


        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!pk.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(PK);
            sql.append("='");
            sql.append(pk);
            sql.append("'");
        }


        return sql.toString();
    }

    public static String getSelectSQLByLandCode(String landcode) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(PK);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(DETAIL);
        sql.append(",");

        sql.append(UPDATE_DATE);
        sql.append(",");
        sql.append(UPDATE_BY);
        sql.append("");

        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!landcode.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(LAND_NO);
            sql.append("='");
            sql.append(landcode);
            sql.append("'");
        }


        return sql.toString();
    }


}
