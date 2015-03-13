package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class MetaDocDB extends MetaDB {

    public final static String TABLE_NAME = "META_DOC";

    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(META_ID);
        sql.append(" INTEGER PRIMARY KEY , ");
        sql.append(META_NAME);
        sql.append(" TEXT,");
        sql.append(META_REF);
        sql.append(" TEXT,");
        sql.append(META_VALUE);
        sql.append(" TEXT,");

        sql.append(UPDATE_DATE);
        sql.append(" TEXT,");
        sql.append(UPDATE_BY);
        sql.append(" TEXT,");
        sql.append(REMARK1);
        sql.append(" TEXT");

        sql.append(")");

        return sql.toString();
    }

    public static String getSelectAllSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(META_ID);
        sql.append(",");
        sql.append(META_NAME);
        sql.append(",");
        sql.append(META_REF);
        sql.append(",");
        sql.append(META_VALUE);
        //sql.append(",");

        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        return sql.toString();
    }

    public static String getDropSQL() {
        return "DROP TABLE IF EXISTS  " + TABLE_NAME;
    }
}
