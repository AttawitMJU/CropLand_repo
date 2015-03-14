package com.hrdi.survey.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import com.hrdi.survey.model.MetaBean;
import com.hrdi.survey.modeldb.MetaAmphoeDB;
import com.hrdi.survey.modeldb.MetaCardDB;
import com.hrdi.survey.modeldb.MetaDB;
import com.hrdi.survey.modeldb.MetaDocDB;
import com.hrdi.survey.modeldb.MetaExtProjectDB;
import com.hrdi.survey.modeldb.MetaFertilizerCodeDB;
import com.hrdi.survey.modeldb.MetaFertilizerDB;
import com.hrdi.survey.modeldb.MetaHormoneDB;
import com.hrdi.survey.modeldb.MetaHormoneTypeDB;
import com.hrdi.survey.modeldb.MetaJobActivityDB;
import com.hrdi.survey.modeldb.MetaJobSourceDB;
import com.hrdi.survey.modeldb.MetaMarketDB;
import com.hrdi.survey.modeldb.MetaPlantDB;
import com.hrdi.survey.modeldb.MetaPlantDetailDB;
import com.hrdi.survey.modeldb.MetaPlantTypeDB;
import com.hrdi.survey.modeldb.MetaProvinceDB;
import com.hrdi.survey.modeldb.MetaTambolDB;
import com.hrdi.survey.modeldb.MetaUnitDB;
import com.hrdi.survey.modeldb.MetaWaterResourceDB;

import java.util.ArrayList;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class MetaDAO extends HrdiDBDAO {

    //ArrayList<HashMap<String, String>> metaList;

    public MetaDAO(Context context) {
        super(context);
    }

    public long countRecord(String table) {
        return DatabaseUtils.queryNumEntries(database, table);
    }

    public long countRecord(String table, String selection) {
        return DatabaseUtils.queryNumEntries(database, table, selection);
    }

    public void cleanMeta(String metaType) {
        String tableName = "";

        if ("card".equals(metaType)) {
            tableName = MetaCardDB.TABLE_NAME;

        } else if ("doc".equalsIgnoreCase(metaType)) {
            tableName = MetaDocDB.TABLE_NAME;
        } else if ("waterresource".equalsIgnoreCase(metaType)) {
            tableName = MetaWaterResourceDB.TABLE_NAME;
        } else if ("unit".equalsIgnoreCase(metaType)) {
            tableName = MetaUnitDB.TABLE_NAME;
        } else if ("market".equalsIgnoreCase(metaType)) {
            tableName = MetaMarketDB.TABLE_NAME;
        } else if ("fertilizer".equalsIgnoreCase(metaType)) {
            tableName = MetaFertilizerDB.TABLE_NAME;
        } else if ("fertilizercode".equalsIgnoreCase(metaType)) {
            tableName = MetaFertilizerCodeDB.TABLE_NAME;
        } else if ("hormone".equalsIgnoreCase(metaType)) {
            tableName = MetaHormoneDB.TABLE_NAME;
        } else if ("hormonetype".equalsIgnoreCase(metaType)) {
            tableName = MetaHormoneTypeDB.TABLE_NAME;
        } else if ("jobactivity".equalsIgnoreCase(metaType)) {
            tableName = MetaJobActivityDB.TABLE_NAME;
        } else if ("jobsource".equalsIgnoreCase(metaType)) {
            tableName = MetaJobSourceDB.TABLE_NAME;
        } else if ("extproject".equalsIgnoreCase(metaType)) {
            tableName = MetaExtProjectDB.TABLE_NAME;
        } else if ("province".equalsIgnoreCase(metaType)) {
            tableName = MetaProvinceDB.TABLE_NAME;
        } else if ("amphoe".equalsIgnoreCase(metaType)) {
            tableName = MetaAmphoeDB.TABLE_NAME;
        } else if ("tambol".equalsIgnoreCase(metaType)) {
            tableName = MetaTambolDB.TABLE_NAME;
        } else if ("planttype".equalsIgnoreCase(metaType)) {
            tableName = MetaPlantTypeDB.TABLE_NAME;
        } else if ("plant".equalsIgnoreCase(metaType)) {
            tableName = MetaPlantDB.TABLE_NAME;
        } else if ("plantdetail".equalsIgnoreCase(metaType)) {
            tableName = MetaPlantDetailDB.TABLE_NAME;
        }
        database.delete(tableName, "", null);
    }


    public int importMeta(ArrayList metaList, String metaType) {


        int i = 0;
        String tableName = "";
        ContentValues values = new ContentValues();
        MetaBean metaBean;


        if ("card".equalsIgnoreCase(metaType)) {
            tableName = MetaCardDB.TABLE_NAME;
        } else if ("doc".equalsIgnoreCase(metaType)) {
            tableName = MetaDocDB.TABLE_NAME;
        } else if ("waterresource".equalsIgnoreCase(metaType)) {
            tableName = MetaWaterResourceDB.TABLE_NAME;
        } else if ("unit".equalsIgnoreCase(metaType)) {
            tableName = MetaUnitDB.TABLE_NAME;
        } else if ("market".equalsIgnoreCase(metaType)) {
            tableName = MetaMarketDB.TABLE_NAME;
        } else if ("fertilizer".equalsIgnoreCase(metaType)) {
            tableName = MetaFertilizerDB.TABLE_NAME;
        } else if ("fertilizercode".equalsIgnoreCase(metaType)) {
            tableName = MetaFertilizerCodeDB.TABLE_NAME;
        } else if ("hormone".equalsIgnoreCase(metaType)) {
            tableName = MetaHormoneDB.TABLE_NAME;
        } else if ("hormonetype".equalsIgnoreCase(metaType)) {
            tableName = MetaHormoneTypeDB.TABLE_NAME;
        } else if ("jobactivity".equalsIgnoreCase(metaType)) {
            tableName = MetaJobActivityDB.TABLE_NAME;
        } else if ("jobsource".equalsIgnoreCase(metaType)) {
            tableName = MetaJobSourceDB.TABLE_NAME;
        } else if ("extproject".equalsIgnoreCase(metaType)) {
            tableName = MetaExtProjectDB.TABLE_NAME;
        } else if ("province".equalsIgnoreCase(metaType)) {
            tableName = MetaProvinceDB.TABLE_NAME;
        } else if ("amphoe".equalsIgnoreCase(metaType)) {
            tableName = MetaAmphoeDB.TABLE_NAME;
        } else if ("tambol".equalsIgnoreCase(metaType)) {
            tableName = MetaTambolDB.TABLE_NAME;
        } else if ("planttype".equalsIgnoreCase(metaType)) {
            tableName = MetaPlantTypeDB.TABLE_NAME;
        } else if ("plant".equalsIgnoreCase(metaType)) {
            tableName = MetaPlantDB.TABLE_NAME;
        } else if ("plantdetail".equalsIgnoreCase(metaType)) {
            tableName = MetaPlantDetailDB.TABLE_NAME;
        }

        while (i < metaList.size()) {
            metaBean = (MetaBean) metaList.get(i);
            values.put(MetaDB.META_ID, metaBean.getItemId());
            values.put(MetaDB.META_NAME, metaBean.getItemName());
            values.put(MetaDB.META_REF, metaBean.getItemRef());
            values.put(MetaDB.META_VALUE, metaBean.getItemValue());

            //Log.i("MetaDAO Import = ", tableName);
            // Inserting Row
            database.insert(tableName, null, values);

            i++;
        }

        return i;
    }


    public ArrayList<MetaBean> getMetaByType(String sqlSelect) {
        ArrayList<MetaBean> items = new ArrayList<MetaBean>();
        try {
            MetaBean metaBean;

            metaBean = new MetaBean(0, "-เลือกรายการ-");
            items.add(metaBean);

            if (!sqlSelect.isEmpty()) {
                Cursor cursor = database.rawQuery(sqlSelect, null);


                while (cursor.moveToNext()) {
                    metaBean = new MetaBean();
                    metaBean.setItemId(cursor.getInt(0));
                    metaBean.setItemName(cursor.getString(1));
                    metaBean.setItemRef(cursor.getString(2));
                    metaBean.setItemValue(cursor.getString(3));

                    items.add(metaBean);
                }
            }
        } catch (Exception e) {
            Log.e("Error getCardType", e.getMessage());
        }
        return items;

    }

    public ArrayList<MetaBean> getMetaByTypeRef(String sqlSelect, String ref) {
        ArrayList<MetaBean> items = new ArrayList<MetaBean>();
        try {
            MetaBean metaBean;
            Cursor cursor = database.rawQuery(sqlSelect, null);

            metaBean = new MetaBean(0, "-เลือกรายการ-");
            items.add(metaBean);

            while (cursor.moveToNext()) {
                metaBean = new MetaBean();
                metaBean.setItemId(cursor.getInt(0));
                metaBean.setItemName(cursor.getString(1));
                metaBean.setItemRef(cursor.getString(2));
                metaBean.setItemValue(cursor.getString(3));

                items.add(metaBean);
            }
        } catch (Exception e) {
            Log.e("Error getCardType", e.getMessage());
        }
        return items;

    }


}
