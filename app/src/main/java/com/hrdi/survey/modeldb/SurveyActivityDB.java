package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyActivityDB {
    //---
    public static final String TABLE_NAME = "SURVEY_ACTIVITY";
    //---

    // Attribute Column Name
    public static final String ACTIVITY_ID = "ACTIVITY_ID";

    public static final String SURVEY_ID = "SURVEY_ID";
    public static final String LAND_NO = "LAND_NO";

    public static final String CARD_NO = "CARD_NO";

    public static final String ACTIVITY1 = "ACTIVITY1";
    public static final String REPEAT1 = "REPEAT1";
    public static final String OUTCOME1 = "OUTCOME1";
    public static final String SURVIVE1 = "SURVIVE1";

    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";


    public SurveyActivityDB() {
        super();
    }


    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(ACTIVITY_ID);
        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(SURVEY_ID);
        sql.append(" TEXT,");
        sql.append(LAND_NO);
        sql.append(" TEXT,");

        sql.append(CARD_NO);
        sql.append(" TEXT,");

        sql.append(ACTIVITY1);
        sql.append(" TEXT,");
        sql.append(REPEAT1);
        sql.append(" INTEGER,");
        sql.append(OUTCOME1);
        sql.append(" TEXT,");
        sql.append(SURVIVE1);
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
        sql.append(ACTIVITY_ID);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(CARD_NO);
        sql.append(",");

        sql.append(ACTIVITY1);
        sql.append(",");
        sql.append(REPEAT1);
        sql.append(",");
        sql.append(OUTCOME1);
        sql.append(",");
        sql.append(SURVIVE1);
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



    public static String getSelectSQLByID(String activityId) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(ACTIVITY_ID);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(CARD_NO);
        sql.append(",");

        sql.append(ACTIVITY1);
        sql.append(",");
        sql.append(REPEAT1);
        sql.append(",");
        sql.append(OUTCOME1);
        sql.append(",");
        sql.append(SURVIVE1);
        sql.append(",");

        sql.append(UPDATE_DATE);
        sql.append(",");
        sql.append(UPDATE_BY);
        sql.append("");


        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!activityId.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(ACTIVITY_ID);
            sql.append("='");
            sql.append(activityId);
            sql.append("'");
        }


        return sql.toString();
    }

    public static String getSelectSQLByLandCode(String landcode) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(ACTIVITY_ID);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(CARD_NO);
        sql.append(",");

        sql.append(ACTIVITY1);
        sql.append(",");
        sql.append(REPEAT1);
        sql.append(",");
        sql.append(OUTCOME1);
        sql.append(",");
        sql.append(SURVIVE1);
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
