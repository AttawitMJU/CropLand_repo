package com.hrdi.survey.control;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by attawit on 1/31/15 AD.
 */
public class HrdiDBDAO {

    protected SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context mContext;

    public HrdiDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DBHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DBHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

	public void close() {
		dbHelper.close();
		database = null;
	}
}
