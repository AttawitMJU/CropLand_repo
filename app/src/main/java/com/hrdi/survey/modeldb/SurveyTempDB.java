package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class SurveyTempDB {
    //---
    public static final String TABLE_NAME = "SURVEY_TEMP";
    //---

    // Attribute Column Name
    public static final String PROJECT_AREA = "PROJECT_AREA";
    public static final String EXT_PROJECT = "EXT_PROJECT";
    public static final String PROJECT_MOOBAN = "PROJECT_MOOBAN";


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
    public static final String HASOTHERSUPPORT = "HASOTHERSUPPORT";
    /*
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

    public static final String ORG1 = "ORG1";
    public static final String ORG2 = "ORG2";
    public static final String ORG3 = "ORG3";
    public static final String PROBLEM1 = "PROBLEM1";
    public static final String PROBLEM2 = "PROBLEM2";
    public static final String PROBLEM3 = "PROBLEM3";
    public static final String REQUEST1 = "REQUEST1";
    public static final String REQUEST2 = "REQUEST2";
    public static final String REQUEST3 = "REQUEST3";
*/

    public static final String LATLONG = "LATLONG";
    public static final String LAND_DOC_TYPE = "LAND_DOC_TYPE";
    public static final String AREA_STATUS = "AREA_STATUS";
    public static final String AREA_STATUS_YEAR = "AREA_STATUS_YEAR";

    public static final String HISTORY = "HISTORY";
    public static final String PICTURE1 = "PICTURE1";
    public static final String PICTURE2 = "PICTURE2";
    public static final String PICTURE3 = "PICTURE3";

    public static final String PICTURE_UPDATE1 = "PICTURE_UPDATE1";
    public static final String PICTURE_UPDATE2 = "PICTURE_UPDATE2";
    public static final String PICTURE_UPDATE3 = "PICTURE_UPDATE3";

    public static final String PICTURE_PUBLIC1 = "PICTURE_PUBLIC1";
    public static final String PICTURE_PUBLIC2 = "PICTURE_PUBLIC2";
    public static final String PICTURE_PUBLIC3 = "PICTURE_PUBLIC3";

    public static final String PICTURE_SUGGEST1 = "PICTURE_SUGGEST1";
    public static final String PICTURE_SUGGEST2 = "PICTURE_SUGGEST2";
    public static final String PICTURE_SUGGEST3 = "PICTURE_SUGGEST3";

    public static final String PICTURE_CONS1 = "PICTURE_CONS1";
    public static final String PICTURE_CONS2 = "PICTURE_CONS2";
    public static final String PICTURE_CONS3 = "PICTURE_CONS3";

    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";
    public static final String REMARK1 = "REMARK1";
    public static final String REMARK2 = "REMARK2";

    public SurveyTempDB() {
        super();
    }


    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(SURVEY_ID);
        sql.append(" INTEGER PRIMARY KEY , ");

        sql.append(PROJECT_AREA);
        sql.append(" TEXT,");
        sql.append(EXT_PROJECT);
        sql.append(" TEXT,");
        sql.append(PROJECT_MOOBAN);
        sql.append(" TEXT,");

        sql.append(LAND_NO);
        sql.append(" TEXT,");
        sql.append(CARD_NO);
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

        sql.append(HASOTHERSUPPORT);
        sql.append(" TEXT,");

        sql.append(LATLONG);
        sql.append(" TEXT,");
        sql.append(LAND_DOC_TYPE);
        sql.append(" TEXT,");
        sql.append(AREA_STATUS);
        sql.append(" TEXT,");
        sql.append(AREA_STATUS_YEAR);
        sql.append(" TEXT,");

        sql.append(HISTORY);
        sql.append(" TEXT,");
        sql.append(PICTURE1);
        sql.append(" TEXT,");
        sql.append(PICTURE2);
        sql.append(" TEXT,");
        sql.append(PICTURE3);
        sql.append(" TEXT,");

        sql.append(PICTURE_UPDATE1);
        sql.append(" TEXT,");
        sql.append(PICTURE_UPDATE2);
        sql.append(" TEXT,");
        sql.append(PICTURE_UPDATE3);
        sql.append(" TEXT,");

        sql.append(PICTURE_PUBLIC1);
        sql.append(" TEXT,");
        sql.append(PICTURE_PUBLIC2);
        sql.append(" TEXT,");
        sql.append(PICTURE_PUBLIC3);
        sql.append(" TEXT,");

        sql.append(PICTURE_SUGGEST1);
        sql.append(" TEXT,");
        sql.append(PICTURE_SUGGEST2);
        sql.append(" TEXT,");
        sql.append(PICTURE_SUGGEST3);
        sql.append(" TEXT,");

        sql.append(PICTURE_CONS1);
        sql.append(" TEXT,");
        sql.append(PICTURE_CONS2);
        sql.append(" TEXT,");
        sql.append(PICTURE_CONS3);
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

    private static String getSQLColumn() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(SURVEY_ID);      //0
        sql.append(",");
        sql.append(SURVEY_DATE);
        sql.append(",");
        sql.append(LAND_NO);
        sql.append(",");
        sql.append(LATLONG);
        sql.append(",");

        sql.append(PROJECT_AREA);
        sql.append(",");
        sql.append(EXT_PROJECT);    //5
        sql.append(",");
        sql.append(PROJECT_MOOBAN);
        sql.append(",");

        sql.append(LAND_DOC_TYPE);
        sql.append(",");
        sql.append(AREA_STATUS);
        sql.append(",");
        sql.append(AREA_STATUS_YEAR);
        sql.append(",");

        sql.append(CARD_NO);        //10
        sql.append(",");
        sql.append(OWNER_TYPE);
        sql.append(",");
        sql.append(OWNER_TYPE_DETAIL);
        sql.append(",");
        sql.append(HISTORY);
        sql.append(",");
        sql.append(HASOTHERSUPPORT);
        sql.append(",");

        sql.append(INSTITUTE_SUPPORT); //15
        sql.append(",");
        sql.append(WATER);
        sql.append(",");
        sql.append(WATER_PERIOD);
        sql.append(",");
        sql.append(WATER_USE);
        sql.append(",");
        sql.append(SOIL_MOISTURE);
        sql.append(",");
        sql.append(TEMPERATURE);    //20
        sql.append(",");
        sql.append(HASACTIVITY);
        sql.append(",");

        sql.append(PICTURE1);
        sql.append(",");
        sql.append(PICTURE2);
        sql.append(",");
        sql.append(PICTURE3);
        sql.append(",");


        sql.append(UPDATE_BY);      //25
        sql.append(",");
        sql.append(REMARK1);
        sql.append(",");
        sql.append(REMARK2);        //27 ***
        sql.append("");

        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        return sql.toString();
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

        sql.append(PROJECT_AREA);
        sql.append(",");
        sql.append(EXT_PROJECT);
        sql.append(",");
        sql.append(PROJECT_MOOBAN);
        sql.append(",");

        sql.append(AREA_STATUS);
        sql.append(",");

        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(CARD_NO);
        sql.append(",");

        sql.append(AgriculturistDB.TABLE_NAME);
        sql.append(".");
        sql.append(AgriculturistDB.FIRSTNAME);
        sql.append(" as FIRSTNAME");
        sql.append(" ,");

        sql.append(AgriculturistDB.TABLE_NAME);
        sql.append(".");
        sql.append(AgriculturistDB.LASTNAME);
        sql.append(" as LASTNAME");
        sql.append(" ,");

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

    public static String getSelectSQLByStatus(String status) {
        StringBuilder sql = new StringBuilder();
        sql.append(getSQLColumn());

        if (!status.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(REMARK1);
            sql.append("='");
            sql.append(status);
            sql.append("'");
        }

        return sql.toString();
    }




    public static String getSelectSQLByID(String id) {
        StringBuilder sql = new StringBuilder();
        sql.append(getSQLColumn());

        if (!id.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(SURVEY_ID);
            sql.append("=");
            sql.append(id);
            sql.append("");
        }


        return sql.toString();
    }

    public static String getSelectSQLByLandCode(String landcode) {
        StringBuilder sql = new StringBuilder();

        sql.append(getSQLColumn());

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
