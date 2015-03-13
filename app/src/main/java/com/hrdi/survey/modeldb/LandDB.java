package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class LandDB {

    // Attribute Column Name
    public static final String LAND_ID = "LAND_ID";
    public static final String LAND_NO = "LAND_NO";
    public static final String LANDDOC_TYPE = "LANDDOC_TYPE";
    public static final String AREA_TOTAL = "AREA_TOTAL";
    public static final String AREA_RAI = "AREA_RAI";
    public static final String AREA_NGAN = "AREA_NGAN";
    public static final String AREA_WA = "AREA_WA";
    public static final String COORDINATE = "COORDINATE";
    public static final String LANDPICTURE = "LANDPICTURE";
    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";
    public static final String REMARK1 = "REMARK1";
    public static final String REMARK2 = "REMARK2";
    private final static String TABLE_NAME = "LAND";

    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(LAND_ID);
        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(LAND_NO);
        sql.append(" TEXT,");
        sql.append(LANDDOC_TYPE);
        sql.append(" TEXT,");
        sql.append(AREA_TOTAL);
        sql.append(" TEXT,");
        sql.append(AREA_RAI);
        sql.append(" TEXT,");
        sql.append(AREA_NGAN);
        sql.append(" TEXT,");
        sql.append(AREA_WA);
        sql.append(" TEXT,");
        sql.append(COORDINATE);
        sql.append(" TEXT,");
        sql.append(LANDPICTURE);
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
}
