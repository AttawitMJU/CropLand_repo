package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyDB {
    //---
    public static final String TABLE_NAME = "SURVEY";
    //---

    // Attribute Column Name
    public static final String SURVEY_ID = "SURVEY_ID";
    public static final String LAND_NO = "LAND_NO";

    public static final String CARD_NO = "CARD_NO";
    public static final String SURVEY_DATE = "SURVEY_DATE";
    public static final String OWNER_TYPE = "OWNER_TYPE";
    public static final String OWNER_TYPE_DETAIL = "OWNER_TYPE_DETAIL";
    public static final String INSTITUTE_SUPPORT = "INSTITUTE_SUPPORT";
    public static final String WATER = "WATER";
    public static final String WATER_PERIOD = "WATER_PERIOD";
    public static final String WATER_USE = "WATER_USE";
    public static final String SOIL_MOISTURE = "SOIL_MOISTURE";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String HASACTIVITY = "HASACTIVITY";
    public static final String ACTIVITY1 = "ACTIVITY1";
    public static final String REPEAT1 = "REPEAT1";
    public static final String OUTCOME1 = "OUTCOME1";
    public static final String SURVIVE1 = "SURVIVE1";
    public static final String ACTIVITY2 = "ACTIVITY2";
    public static final String REPEAT2 = "REPEAT2";
    public static final String OUTCOME2 = "OUTCOME2";
    public static final String SURVIVE2 = "SURVIVE2";
    public static final String ACTIVITY3 = "ACTIVITY3";
    public static final String REPEAT3 = "REPEAT3";
    public static final String OUTCOME3 = "OUTCOME3";
    public static final String SURVIVE3 = "SURVIVE3";
    public static final String ACTIVITY4 = "ACTIVITY4";
    public static final String REPEAT4 = "REPEAT4";
    public static final String OUTCOME4 = "OUTCOME4";
    public static final String SURVIVE4 = "SURVIVE4";
    public static final String HASOTHERSUPPORT = "HASOTHERSUPPORT";
    public static final String ORG1 = "ORG1";
    public static final String ORG2 = "ORG2";
    public static final String ORG3 = "ORG3";
    public static final String PROBLEM1 = "PROBLEM1";
    public static final String PROBLEM2 = "PROBLEM2";
    public static final String PROBLEM3 = "PROBLEM3";
    public static final String REQUEST1 = "REQUEST1";
    public static final String REQUEST2 = "REQUEST2";
    public static final String REQUEST3 = "REQUEST3";

    public static final String EXT_PROJECT = "EXT_PROJECT";
    public static final String LATLONG = "LATLONG";
    public static final String LAND_DOC_TYPE = "LAND_DOC_TYPE";
    public static final String AREA_STATUS = "AREA_STATUS";
    public static final String AREA_STATUS_YEAR = "AREA_STATUS_YEAR";
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String ADDRESS = "ADDRESS";
    public static final String CARD_TYPE = "CARD_TYPE";
    public static final String HISTORY = "HISTORY";
    public static final String PICTURE1 = "PICTURE1";
    public static final String PICTURE2 = "PICTURE2";
    public static final String PICTURE3 = "PICTURE3";

    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";
    public static final String REMARK1 = "REMARK1";
    public static final String REMARK2 = "REMARK2";

    public static final String MOOBAN = "MOOBAN";

    public SurveyDB() {
        super();
    }


    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(SURVEY_ID);
        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(LAND_NO);
        sql.append(" TEXT,");

        sql.append(CARD_NO);
        sql.append(" TEXT,");
        sql.append(CARD_TYPE);
        sql.append(" TEXT,");
        sql.append(SURVEY_DATE);
        sql.append(" TEXT,");
        sql.append(OWNER_TYPE);
        sql.append(" TEXT,");
        sql.append(OWNER_TYPE_DETAIL);
        sql.append(" TEXT,");
        sql.append(INSTITUTE_SUPPORT);
        sql.append(" TEXT,");
        sql.append(WATER);
        sql.append(" INTEGER,");
        sql.append(WATER_PERIOD);
        sql.append(" TEXT,");
        sql.append(WATER_USE);
        sql.append(" TEXT,");
        sql.append(SOIL_MOISTURE);
        sql.append(" TEXT,");
        sql.append(TEMPERATURE);
        sql.append(" TEXT,");
        sql.append(HASACTIVITY);
        sql.append(" TEXT,");

        sql.append(ACTIVITY1);
        sql.append(" TEXT,");
        sql.append(REPEAT1);
        sql.append(" INTEGER,");
        sql.append(OUTCOME1);
        sql.append(" TEXT,");
        sql.append(SURVIVE1);
        sql.append(" TEXT,");

        sql.append(ACTIVITY2);
        sql.append(" TEXT,");
        sql.append(REPEAT2);
        sql.append(" INTEGER,");
        sql.append(OUTCOME2);
        sql.append(" TEXT,");
        sql.append(SURVIVE2);
        sql.append(" TEXT,");

        sql.append(ACTIVITY3);
        sql.append(" TEXT,");
        sql.append(REPEAT3);
        sql.append(" INTEGER,");
        sql.append(OUTCOME3);
        sql.append(" TEXT,");
        sql.append(SURVIVE3);
        sql.append(" TEXT,");

        sql.append(ACTIVITY4);
        sql.append(" TEXT,");
        sql.append(REPEAT4);
        sql.append(" INTEGER,");
        sql.append(OUTCOME4);
        sql.append(" TEXT,");
        sql.append(SURVIVE4);
        sql.append(" TEXT,");

        sql.append(HASOTHERSUPPORT);
        sql.append(" TEXT,");
        sql.append(ORG1);
        sql.append(" TEXT,");
        sql.append(ORG2);
        sql.append(" TEXT,");
        sql.append(ORG3);
        sql.append(" TEXT,");

        sql.append(PROBLEM1);
        sql.append(" TEXT,");
        sql.append(PROBLEM2);
        sql.append(" TEXT,");
        sql.append(PROBLEM3);
        sql.append(" TEXT,");

        sql.append(REQUEST1);
        sql.append(" TEXT,");
        sql.append(REQUEST2);
        sql.append(" TEXT,");
        sql.append(REQUEST3);
        sql.append(" TEXT,");

        sql.append(EXT_PROJECT);
        sql.append(" TEXT,");
        sql.append(LATLONG);
        sql.append(" TEXT,");
        sql.append(LAND_DOC_TYPE);
        sql.append(" TEXT,");
        sql.append(AREA_STATUS);
        sql.append(" TEXT,");
        sql.append(AREA_STATUS_YEAR);
        sql.append(" TEXT,");
        sql.append(FIRSTNAME);
        sql.append(" TEXT,");
        sql.append(LASTNAME);
        sql.append(" TEXT,");
        sql.append(ADDRESS);
        sql.append(" TEXT,");

        sql.append(HISTORY);
        sql.append(" TEXT,");
        sql.append(PICTURE1);
        sql.append(" TEXT,");
        sql.append(PICTURE2);
        sql.append(" TEXT,");
        sql.append(PICTURE3);
        sql.append(" TEXT,");


        sql.append(UPDATE_DATE);
        sql.append(" TEXT,");
        sql.append(UPDATE_BY);
        sql.append(" TEXT,");
        sql.append(REMARK1);
        sql.append(" TEXT,");
        sql.append(REMARK2);
        sql.append(" TEXT,");

        sql.append(MOOBAN);
        sql.append(" TEXT");

        sql.append(")");

        return sql.toString();
    }

    public static String getDropSQL() {
        return "DROP TABLE IF EXISTS  " + TABLE_NAME;
    }

    public static String getSelectSQL2Show(String status) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(SURVEY_DATE);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(MOOBAN);
        sql.append(",");
        sql.append(EXT_PROJECT);
        sql.append(",");
        sql.append(MetaExtProjectDB.META_NAME);
        sql.append(" as EXT_PROJECT_Name,");
        sql.append(AREA_STATUS);
        sql.append(",");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(CARD_NO);
        sql.append(",");

        sql.append(AgriculturistDB.TITLE);
        sql.append(",");

        // TODO : Change for join table
        //sql.append(AgriculturistDB.TABLE_NAME);     sql.append("."); sql.append(AgriculturistDB.FIRSTNAME);  sql.append(",");
        //sql.append(AgriculturistDB.TABLE_NAME);     sql.append("."); sql.append(AgriculturistDB.LASTNAME);

        // TODO : Remove this if AgriculturistDB is on
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(FIRSTNAME);
        sql.append(",");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(LASTNAME);
        //-- end remove --
        sql.append(",");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(REMARK1);

        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        sql.append(" LEFT JOIN ");
        sql.append(AgriculturistDB.TABLE_NAME);

        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(CARD_NO);
        sql.append("=");
        sql.append(AgriculturistDB.TABLE_NAME);
        sql.append(".");
        sql.append(AgriculturistDB.CARD_NO);


        sql.append(" LEFT JOIN ");
        sql.append(MetaExtProjectDB.TABLE_NAME);

        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(EXT_PROJECT);
        sql.append("=");
        sql.append(MetaExtProjectDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaExtProjectDB.META_ID);

        if (!status.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(TABLE_NAME);
            sql.append(".");
            sql.append(REMARK1);
            sql.append("='");
            sql.append(status);
            sql.append("'");
        }


        sql.append(" ORDER BY ");
        sql.append(SURVEY_ID);
        sql.append(" DESC ");

        return sql.toString();
    }

    public static String getSelectSQLAllDetail(String status) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(SURVEY_ID); //0
        sql.append(",");
        sql.append(SURVEY_DATE);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(LATLONG);
        sql.append(",");
        sql.append(EXT_PROJECT);
        sql.append(",");
        sql.append(MOOBAN);
        sql.append(",");
        sql.append(LAND_DOC_TYPE);
        sql.append(",");
        sql.append(AREA_STATUS);
        sql.append(",");
        sql.append(AREA_STATUS_YEAR);
        sql.append(",");

        sql.append(CARD_NO);
        sql.append(",");
        sql.append(CARD_TYPE); //10
        sql.append(",");
        sql.append(FIRSTNAME);
        sql.append(",");
        sql.append(LASTNAME);
        sql.append(",");
        sql.append(ADDRESS);
        sql.append(",");
        sql.append(OWNER_TYPE);
        sql.append(",");
        sql.append(OWNER_TYPE_DETAIL);
        sql.append(",");
        sql.append(HISTORY);
        sql.append(",");


        sql.append(INSTITUTE_SUPPORT);
        sql.append(",");
        sql.append(WATER);
        sql.append(",");
        sql.append(WATER_PERIOD);
        sql.append(",");
        sql.append(WATER_USE); //20
        sql.append(",");
        sql.append(SOIL_MOISTURE);
        sql.append(",");
        sql.append(TEMPERATURE);
        sql.append(",");
        sql.append(HASACTIVITY);
        sql.append(",");

        sql.append(ACTIVITY1);
        sql.append(",");
        sql.append(REPEAT1);
        sql.append(",");
        sql.append(OUTCOME1);
        sql.append(",");
        sql.append(SURVIVE1);
        sql.append(",");

        sql.append(ACTIVITY2);
        sql.append(",");
        sql.append(REPEAT2);
        sql.append(",");
        sql.append(OUTCOME2); //30
        sql.append(",");
        sql.append(SURVIVE2);
        sql.append(",");

        sql.append(ACTIVITY3);
        sql.append(",");
        sql.append(REPEAT3);
        sql.append(",");
        sql.append(OUTCOME3);
        sql.append(",");
        sql.append(SURVIVE3);
        sql.append(",");

        sql.append(ACTIVITY4);
        sql.append(",");
        sql.append(REPEAT4);
        sql.append(",");
        sql.append(OUTCOME4);
        sql.append(",");
        sql.append(SURVIVE4);
        sql.append(",");

        sql.append(HASOTHERSUPPORT); //40
        sql.append(",");
        sql.append(ORG1);
        sql.append(",");
        sql.append(ORG2);
        sql.append(",");
        sql.append(ORG3);
        sql.append(",");

        sql.append(PROBLEM1);
        sql.append(",");
        sql.append(PROBLEM2);
        sql.append(",");
        sql.append(PROBLEM3);
        sql.append(",");

        sql.append(REQUEST1);
        sql.append(",");
        sql.append(REQUEST2);
        sql.append(",");
        sql.append(REQUEST3);
        sql.append(",");

        sql.append(PICTURE1); //50
        sql.append(",");
        sql.append(PICTURE2);
        sql.append(",");
        sql.append(PICTURE3);
        sql.append(",");


        sql.append(UPDATE_DATE);
        sql.append(",");
        sql.append(UPDATE_BY);
        sql.append(",");
        sql.append(REMARK1);
        sql.append(",");
        sql.append(REMARK2);
        sql.append("");


        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!status.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(REMARK1);
            sql.append("='");
            sql.append(status);
            sql.append("'");
        }


        return sql.toString();
    }

    public static String getSelectSQLSurveyEtc(String surveyID) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(SURVEY_ID); //0
        sql.append(",");
        sql.append(SURVEY_DATE);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(LATLONG);
        sql.append(",");
        sql.append(EXT_PROJECT);
        sql.append(",");
        sql.append(MOOBAN);
        sql.append(",");
        sql.append(LAND_DOC_TYPE);
        sql.append(",");
        sql.append(AREA_STATUS);
        sql.append(",");
        sql.append(AREA_STATUS_YEAR);
        sql.append(",");

        sql.append(CARD_NO);
        sql.append(",");
        sql.append(CARD_TYPE); //10
        sql.append(",");
        sql.append(FIRSTNAME);
        sql.append(",");
        sql.append(LASTNAME);
        sql.append(",");
        sql.append(ADDRESS);
        sql.append(",");
        sql.append(OWNER_TYPE);
        sql.append(",");
        sql.append(OWNER_TYPE_DETAIL);
        sql.append(",");
        sql.append(HISTORY);
        sql.append(",");


        sql.append(INSTITUTE_SUPPORT);
        sql.append(",");
        sql.append(WATER);
        sql.append(",");
        sql.append(WATER_PERIOD);
        sql.append(",");
        sql.append(WATER_USE); //20
        sql.append(",");
        sql.append(SOIL_MOISTURE);
        sql.append(",");
        sql.append(TEMPERATURE);
        sql.append(",");
        sql.append(HASACTIVITY);
        sql.append(",");

        sql.append(ACTIVITY1);
        sql.append(",");
        sql.append(REPEAT1);
        sql.append(",");
        sql.append(OUTCOME1);
        sql.append(",");
        sql.append(SURVIVE1);
        sql.append(",");

        sql.append(ACTIVITY2);
        sql.append(",");
        sql.append(REPEAT2);
        sql.append(",");
        sql.append(OUTCOME2); //30
        sql.append(",");
        sql.append(SURVIVE2);
        sql.append(",");

        sql.append(ACTIVITY3);
        sql.append(",");
        sql.append(REPEAT3);
        sql.append(",");
        sql.append(OUTCOME3);
        sql.append(",");
        sql.append(SURVIVE3);
        sql.append(",");

        sql.append(ACTIVITY4);
        sql.append(",");
        sql.append(REPEAT4);
        sql.append(",");
        sql.append(OUTCOME4);
        sql.append(",");
        sql.append(SURVIVE4);
        sql.append(",");

        sql.append(HASOTHERSUPPORT); //40
        sql.append(",");
        sql.append(ORG1);
        sql.append(",");
        sql.append(ORG2);
        sql.append(",");
        sql.append(ORG3);
        sql.append(",");

        sql.append(PROBLEM1);
        sql.append(",");
        sql.append(PROBLEM2);
        sql.append(",");
        sql.append(PROBLEM3);
        sql.append(",");

        sql.append(REQUEST1);
        sql.append(",");
        sql.append(REQUEST2);
        sql.append(",");
        sql.append(REQUEST3);
        sql.append(",");

        sql.append(PICTURE1); //50
        sql.append(",");
        sql.append(PICTURE2);
        sql.append(",");
        sql.append(PICTURE3);
        sql.append(",");


        sql.append(UPDATE_DATE);
        sql.append(",");
        sql.append(UPDATE_BY);
        sql.append(",");
        sql.append(REMARK1);
        sql.append(",");
        sql.append(REMARK2);
        sql.append("");

        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!surveyID.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(SURVEY_ID);
            sql.append("='");
            sql.append(surveyID);
            sql.append("'");
        }


        return sql.toString();
    }


    public static String getSelectSQLByID(String id) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(SURVEY_ID); //0
        sql.append(",");
        sql.append(SURVEY_DATE);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(LATLONG);
        sql.append(",");
        sql.append(EXT_PROJECT);
        sql.append(",");
        sql.append(MOOBAN);
        sql.append(",");
        sql.append(LAND_DOC_TYPE);
        sql.append(",");
        sql.append(AREA_STATUS);
        sql.append(",");
        sql.append(AREA_STATUS_YEAR);
        sql.append(",");

        sql.append(CARD_NO);
        sql.append(",");
        sql.append(CARD_TYPE); //10
        sql.append(",");
        sql.append(FIRSTNAME);
        sql.append(",");
        sql.append(LASTNAME);
        sql.append(",");
        sql.append(ADDRESS);
        sql.append(",");
        sql.append(OWNER_TYPE);
        sql.append(",");
        sql.append(OWNER_TYPE_DETAIL); //15
        sql.append(",");
        sql.append(HISTORY);
        sql.append(",");
        sql.append(REMARK1);
        sql.append(",");
        sql.append(REMARK2);
        sql.append("");


        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        if (!id.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(SURVEY_ID);
            sql.append("='");
            sql.append(id);
            sql.append("'");
        }


        return sql.toString();
    }

    public static String getSelectSQLByLandCode(String landcode) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(SURVEY_ID); //0
        sql.append(",");
        sql.append(SURVEY_DATE);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(LATLONG);
        sql.append(",");
        sql.append(EXT_PROJECT);
        sql.append(",");
        sql.append(MOOBAN);
        sql.append(",");
        sql.append(LAND_DOC_TYPE);
        sql.append(",");
        sql.append(AREA_STATUS);
        sql.append(",");
        sql.append(AREA_STATUS_YEAR);
        sql.append(",");

        sql.append(CARD_NO);
        sql.append(",");
        sql.append(CARD_TYPE); //10
        sql.append(",");
        sql.append(FIRSTNAME);
        sql.append(",");
        sql.append(LASTNAME);
        sql.append(",");
        sql.append(ADDRESS);
        sql.append(",");
        sql.append(OWNER_TYPE);
        sql.append(",");
        sql.append(OWNER_TYPE_DETAIL); //15
        sql.append(",");
        sql.append(HISTORY);
        sql.append(",");
        sql.append(REMARK1);
        sql.append(",");
        sql.append(REMARK2);
        sql.append(",");
        sql.append(PICTURE1);
        sql.append(",");
        sql.append(PICTURE2);
        sql.append(",");
        sql.append(PICTURE3);
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
