package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class UserDB {
    private final static String TABLE_NAME = "User";

    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");
        sql.append("User_ID INTEGER PRIMARY KEY AUTOINCREMENT");
        //sql.append(",Card_no TEXT");
        //sql.append(",Card_type TEXT");
        //...
        sql.append(")");

        return sql.toString();
    }

    public static String getDropSQL() {
        return "DROP TABLE IF EXISTS  " + TABLE_NAME;
    }
}
