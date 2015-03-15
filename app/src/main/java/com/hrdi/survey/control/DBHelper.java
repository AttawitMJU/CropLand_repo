package com.hrdi.survey.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hrdi.survey.modeldb.AgriculturistDB;
import com.hrdi.survey.modeldb.LandDB;
import com.hrdi.survey.modeldb.LandUseDB;
import com.hrdi.survey.modeldb.MetaAmphoeDB;
import com.hrdi.survey.modeldb.MetaCardDB;
import com.hrdi.survey.modeldb.MetaDocDB;
import com.hrdi.survey.modeldb.MetaExtProjectDB;
import com.hrdi.survey.modeldb.MetaFertilizerCodeDB;
import com.hrdi.survey.modeldb.MetaFertilizerDB;
import com.hrdi.survey.modeldb.MetaHormoneDB;
import com.hrdi.survey.modeldb.MetaHormoneTypeDB;
import com.hrdi.survey.modeldb.MetaJobActivityDB;
import com.hrdi.survey.modeldb.MetaJobSourceDB;
import com.hrdi.survey.modeldb.MetaMarketDB;
import com.hrdi.survey.modeldb.MetaOccupationDB;
import com.hrdi.survey.modeldb.MetaPlantDB;
import com.hrdi.survey.modeldb.MetaPlantDetailDB;
import com.hrdi.survey.modeldb.MetaPlantTypeDB;
import com.hrdi.survey.modeldb.MetaProjectAreaDB;
import com.hrdi.survey.modeldb.MetaProjectMooDB;
import com.hrdi.survey.modeldb.MetaProvinceDB;
import com.hrdi.survey.modeldb.MetaTambolDB;
import com.hrdi.survey.modeldb.MetaTitleDB;
import com.hrdi.survey.modeldb.MetaUnitDB;
import com.hrdi.survey.modeldb.MetaWaterResourceDB;
import com.hrdi.survey.modeldb.SurveyDB;
import com.hrdi.survey.modeldb.UserDB;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HRDI_Lite";
    private static final int DB_VERSION = 1;
    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized DBHelper getHelper(Context context) {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(AgriculturistDB.getCreateSQL());
            db.execSQL(LandDB.getCreateSQL());
            db.execSQL(LandUseDB.getCreateSQL());

            db.execSQL(MetaAmphoeDB.getCreateSQL());
            db.execSQL(MetaCardDB.getCreateSQL());
            db.execSQL(MetaDocDB.getCreateSQL());
            db.execSQL(MetaExtProjectDB.getCreateSQL());
            db.execSQL(MetaFertilizerCodeDB.getCreateSQL());
            db.execSQL(MetaFertilizerDB.getCreateSQL());
            db.execSQL(MetaHormoneDB.getCreateSQL());
            db.execSQL(MetaHormoneTypeDB.getCreateSQL());
            db.execSQL(MetaJobActivityDB.getCreateSQL());
            db.execSQL(MetaJobSourceDB.getCreateSQL());
            db.execSQL(MetaMarketDB.getCreateSQL());
            db.execSQL(MetaPlantDB.getCreateSQL());
            db.execSQL(MetaPlantDetailDB.getCreateSQL());
            db.execSQL(MetaPlantTypeDB.getCreateSQL());
            db.execSQL(MetaProvinceDB.getCreateSQL());
            db.execSQL(MetaTambolDB.getCreateSQL());
            db.execSQL(MetaUnitDB.getCreateSQL());
            db.execSQL(MetaWaterResourceDB.getCreateSQL());

            db.execSQL(SurveyDB.getCreateSQL());
            db.execSQL(UserDB.getCreateSQL());

            db.execSQL(MetaProjectAreaDB.getCreateSQL());
            db.execSQL(MetaTitleDB.getCreateSQL());
            db.execSQL(MetaOccupationDB.getCreateSQL());
            db.execSQL(MetaProjectMooDB.getCreateSQL());



        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Catch Create Table", Log.getStackTraceString(e));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If you need to add a column
         if (newVersion > oldVersion) {
             // Drop older table if existed
             try {

                 // db.execSQL("ALTER TABLE foo ADD COLUMN new_column INTEGER DEFAULT 0");
                 db.execSQL(AgriculturistDB.getCreateSQL());
                 db.execSQL(LandDB.getCreateSQL());
                 db.execSQL(LandUseDB.getCreateSQL());

                 db.execSQL(MetaAmphoeDB.getCreateSQL());
                 db.execSQL(MetaCardDB.getCreateSQL());
                 db.execSQL(MetaDocDB.getCreateSQL());
                 db.execSQL(MetaExtProjectDB.getCreateSQL());
                 db.execSQL(MetaFertilizerCodeDB.getCreateSQL());
                 db.execSQL(MetaFertilizerDB.getCreateSQL());
                 db.execSQL(MetaHormoneDB.getCreateSQL());
                 db.execSQL(MetaHormoneTypeDB.getCreateSQL());
                 db.execSQL(MetaJobActivityDB.getCreateSQL());
                 db.execSQL(MetaJobSourceDB.getCreateSQL());
                 db.execSQL(MetaMarketDB.getCreateSQL());
                 db.execSQL(MetaPlantDB.getCreateSQL());
                 db.execSQL(MetaPlantDetailDB.getCreateSQL());
                 db.execSQL(MetaPlantTypeDB.getCreateSQL());
                 db.execSQL(MetaProvinceDB.getCreateSQL());
                 db.execSQL(MetaTambolDB.getCreateSQL());
                 db.execSQL(MetaUnitDB.getCreateSQL());
                 db.execSQL(MetaWaterResourceDB.getCreateSQL());

                 db.execSQL(SurveyDB.getCreateSQL());
                 db.execSQL(UserDB.getCreateSQL());

                 db.execSQL(MetaProjectAreaDB.getCreateSQL());
                 db.execSQL(MetaTitleDB.getCreateSQL());
                 db.execSQL(MetaOccupationDB.getCreateSQL());
                 db.execSQL(MetaProjectMooDB.getCreateSQL());

             } catch (Exception e) {
                 e.printStackTrace();
                 Log.e("Catch Drop Table SQLite", Log.getStackTraceString(e));
             }
         }

    }




}
