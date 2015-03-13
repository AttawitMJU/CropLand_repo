package com.hrdi.survey.modeldb;

/**
 * Created by attawit on 1/19/15 AD.
 */
public class LandUseDB {

    public final static String TABLE_NAME = "LAND_USE";

    // Attribute Column Name
    public static final String LANDUSE_ID = "LANDUSE_ID";
    public static final String WHERE_ID_EQUALS = LANDUSE_ID + " =?";
    public static final String SURVEY_ID = "SURVEY_ID";
    public static final String LAND_NO = "LAND_NO";
    public static final String CARD_NO = "CARD_NO";
    public static final String SURVEY_DATE = "SURVEY_DATE";
    public static final String PLANT_TYPE = "PLANT_TYPE";
    public static final String PLANT_YEAR = "PLANT_YEAR";
    public static final String AREA = "AREA";
    public static final String HAVEST_STATUS = "HAVEST_STATUS";
    public static final String SEEDS = "SEEDS";
    public static final String HAS_HIRE = "HAS_HIRE";
    public static final String LABOUR = "LABOUR";
    public static final String FUEL = "FUEL";
    public static final String OTHER_PAID = "OTHER_PAID";
    public static final String PRODUCT_USE = "PRODUCT_USE";
    public static final String PRODUCT_SALE = "PRODUCT_SALE";
    public static final String PRICE = "PRICE";
    public static final String INCOME_YEAR = "INCOME_YEAR";
    public static final String MARKET = "MARKET";
    public static final String EMPLOY_TYPE = "EMPLOY_TYPE";
    public static final String EMPLOY_FROM = "EMPLOY_FROM";
    public static final String LABOUR_TOTAL = "LABOUR_TOTAL";
    public static final String LABOUR_PAID = "LABOUR_PAID";
    public static final String LABOUR_TIME = "LABOUR_TIME";
    public static final String START_CROP = "START_CROP";
    public static final String END_CROP = "END_CROP";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String PLANT_DETAIL_ID = "PLANT_DETAIL_ID";
    // ปุ๋ย
    public static final String FERTILIZER1 = "FERTILIZER1";
    public static final String FERTILIZER_CODE1 = "FERTILIZER_CODE1";
    public static final String FERTILIZER_TOTAL1 = "FERTILIZER_TOTAL1";
    public static final String FERTILIZER_PRICE1 = "FERTILIZER_PRICE1";
    public static final String FERTILIZER_SUM1 = "FERTILIZER_SUM1";
    public static final String FERTILIZER2 = "FERTILIZER2";
    public static final String FERTILIZER_CODE2 = "FERTILIZER_CODE2";
    public static final String FERTILIZER_TOTAL2 = "FERTILIZER_TOTAL2";
    public static final String FERTILIZER_PRICE2 = "FERTILIZER_PRICE2";
    public static final String FERTILIZER_SUM2 = "FERTILIZER_SUM2";
    public static final String FERTILIZER3 = "FERTILIZER3";
    public static final String FERTILIZER_CODE3 = "FERTILIZER_CODE3";
    public static final String FERTILIZER_TOTAL3 = "FERTILIZER_TOTAL3";
    public static final String FERTILIZER_PRICE3 = "FERTILIZER_PRICE3";
    public static final String FERTILIZER_SUM3 = "FERTILIZER_SUM3";
    // ยา/ฮอร์โมน
    public static final String HORMONE_TYPE1 = "HORMONE_TYPE1";
    public static final String HORMONE_BRAND1 = "HORMONE_BRAND1";
    public static final String HORMONE_TOTAL1 = "HORMONE_TOTAL1";
    public static final String HORMONE_PRICE1 = "HORMONE_PRICE1";
    public static final String HORMONE_SUM1 = "HORMONE_SUM1";
    public static final String HORMONE_TYPE2 = "HORMONE_TYPE2";
    public static final String HORMONE_BRAND2 = "HORMONE_BRAND2";
    public static final String HORMONE_TOTAL2 = "HORMONE_TOTAL2";
    public static final String HORMONE_PRICE2 = "HORMONE_PRICE2";
    public static final String HORMONE_SUM2 = "HORMONE_SUM2";
    public static final String HORMONE_TYPE3 = "HORMONE_TYPE3";
    public static final String HORMONE_BRAND3 = "HORMONE_BRAND3";
    public static final String HORMONE_TOTAL3 = "HORMONE_TOTAL3";
    public static final String HORMONE_PRICE3 = "HORMONE_PRICE3";
    public static final String HORMONE_SUM3 = "HORMONE_SUM3";
    public static final String UPDATE_DATE = "UPDATE_DATE";
    public static final String UPDATE_BY = "UPDATE_BY";
    public static final String REMARK1 = "REMARK1";
    public static final String REMARK2 = "REMARK2";

    public LandUseDB() {
        super();
    }


    public static String getCreateSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ");
        sql.append(TABLE_NAME);
        sql.append("(");

        sql.append(LANDUSE_ID);
        sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append(SURVEY_ID);
        sql.append(" TEXT,");
        sql.append(LAND_NO);
        sql.append(" TEXT,");
        sql.append(CARD_NO);
        sql.append(" TEXT,");
        sql.append(SURVEY_DATE);
        sql.append(" TEXT,");
        sql.append(PLANT_TYPE);
        sql.append(" TEXT,");
        sql.append(PLANT_YEAR);
        sql.append(" TEXT,");
        sql.append(AREA);
        sql.append(" TEXT,");
        sql.append(HAVEST_STATUS);
        sql.append(" TEXT,");
        sql.append(SEEDS);
        sql.append(" TEXT,");
        sql.append(HAS_HIRE);
        sql.append(" TEXT,");
        sql.append(LABOUR);
        sql.append(" TEXT,");
        sql.append(FUEL);
        sql.append(" TEXT,");
        sql.append(OTHER_PAID);
        sql.append(" TEXT,");
        sql.append(PRODUCT_USE);
        sql.append(" TEXT,");
        sql.append(PRODUCT_SALE);
        sql.append(" TEXT,");
        sql.append(PRICE);
        sql.append(" TEXT,");
        sql.append(INCOME_YEAR);
        sql.append(" TEXT,");
        sql.append(MARKET);
        sql.append(" TEXT,");
        sql.append(EMPLOY_TYPE);
        sql.append(" TEXT,");
        sql.append(EMPLOY_FROM);
        sql.append(" TEXT,");
        sql.append(LABOUR_TOTAL);
        sql.append(" TEXT,");
        sql.append(LABOUR_PAID);
        sql.append(" TEXT,");
        sql.append(LABOUR_TIME);
        sql.append(" TEXT,");
        sql.append(START_CROP);
        sql.append(" TEXT,");
        sql.append(END_CROP);
        sql.append(" TEXT,");
        sql.append(PLANT_ID);
        sql.append(" TEXT,");
        sql.append(PLANT_DETAIL_ID);
        sql.append(" TEXT,");

        sql.append(FERTILIZER1);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_CODE1);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_TOTAL1);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_PRICE1);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_SUM1);
        sql.append(" TEXT,");

        sql.append(FERTILIZER2);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_CODE2);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_TOTAL2);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_PRICE2);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_SUM2);
        sql.append(" TEXT,");

        sql.append(FERTILIZER3);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_CODE3);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_TOTAL3);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_PRICE3);
        sql.append(" TEXT,");
        sql.append(FERTILIZER_SUM3);
        sql.append(" TEXT,");

        sql.append(HORMONE_TYPE1);
        sql.append(" TEXT,");
        sql.append(HORMONE_BRAND1);
        sql.append(" TEXT,");
        sql.append(HORMONE_TOTAL1);
        sql.append(" TEXT,");
        sql.append(HORMONE_PRICE1);
        sql.append(" TEXT,");
        sql.append(HORMONE_SUM1);
        sql.append(" TEXT,");

        sql.append(HORMONE_TYPE2);
        sql.append(" TEXT,");
        sql.append(HORMONE_BRAND2);
        sql.append(" TEXT,");
        sql.append(HORMONE_TOTAL2);
        sql.append(" TEXT,");
        sql.append(HORMONE_PRICE2);
        sql.append(" TEXT,");
        sql.append(HORMONE_SUM2);
        sql.append(" TEXT,");

        sql.append(HORMONE_TYPE3);
        sql.append(" TEXT,");
        sql.append(HORMONE_BRAND3);
        sql.append(" TEXT,");
        sql.append(HORMONE_TOTAL3);
        sql.append(" TEXT,");
        sql.append(HORMONE_PRICE3);
        sql.append(" TEXT,");
        sql.append(HORMONE_SUM3);
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


    public static String getSelectAllSQL(String surveyID) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(LANDUSE_ID);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(PLANT_TYPE);
        sql.append(",");
        sql.append(PLANT_ID);
        sql.append(",");
        sql.append(PLANT_DETAIL_ID);
        sql.append(",");
        sql.append(START_CROP);
        sql.append(",");
        sql.append(END_CROP);
        sql.append(",");
        sql.append(SEEDS);
        sql.append(",");

        sql.append(MetaPlantTypeDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantTypeDB.META_NAME);
        sql.append(" as PLANTTYPE,");

        sql.append(MetaPlantDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDB.META_NAME);
        sql.append(" as PLANTNAME,");

        sql.append(MetaPlantDetailDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDetailDB.META_NAME);
        sql.append(" as PLANTDETAILNAME,");

        sql.append(AREA);
        sql.append(" ");


        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        sql.append(" LEFT JOIN ");
        sql.append(MetaPlantTypeDB.TABLE_NAME);
        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(PLANT_TYPE);
        sql.append("=");
        sql.append(MetaPlantTypeDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantTypeDB.META_ID);

        sql.append(" LEFT JOIN ");
        sql.append(MetaPlantDB.TABLE_NAME);
        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(PLANT_ID);
        sql.append("=");
        sql.append(MetaPlantDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDB.META_ID);

        sql.append(" LEFT JOIN ");
        sql.append(MetaPlantDetailDB.TABLE_NAME);
        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(PLANT_DETAIL_ID);
        sql.append("=");
        sql.append(MetaPlantDetailDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDetailDB.META_ID);

        sql.append(" WHERE ");
        sql.append(SURVEY_ID);
        sql.append("=");
        sql.append(surveyID);


        return sql.toString();
    }


    public static String getLandUseInSurveySQL(String surveyID) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ");
        sql.append(LandUseDB.SURVEY_ID);
        sql.append(",");

        sql.append(LandUseDB.LAND_NO);
        sql.append(",");
        sql.append(LandUseDB.CARD_NO);
        sql.append(",");
        sql.append(LandUseDB.PLANT_YEAR);
        sql.append(",");
        sql.append(LandUseDB.AREA);
        sql.append(",");
        sql.append(LandUseDB.HAVEST_STATUS);
        sql.append(",");
        sql.append(LandUseDB.SEEDS);
        sql.append(",");
        sql.append(LandUseDB.HAS_HIRE);
        sql.append(",");
        sql.append(LandUseDB.LABOUR);
        sql.append(",");
        sql.append(LandUseDB.FUEL);
        sql.append(",");
        sql.append(LandUseDB.OTHER_PAID);
        sql.append(",");
        sql.append(LandUseDB.PRODUCT_USE);
        sql.append(",");
        sql.append(LandUseDB.PRODUCT_SALE);
        sql.append(",");
        sql.append(LandUseDB.PRICE);
        sql.append(",");
        sql.append(LandUseDB.INCOME_YEAR);
        sql.append(",");
        sql.append(LandUseDB.MARKET);
        sql.append(",");
        sql.append(LandUseDB.EMPLOY_TYPE);
        sql.append(",");
        sql.append(LandUseDB.EMPLOY_FROM);
        sql.append(",");
        sql.append(LandUseDB.LABOUR_TOTAL);
        sql.append(",");
        sql.append(LandUseDB.LABOUR_PAID);
        sql.append(",");
        sql.append(LandUseDB.LABOUR_TIME);
        sql.append(",");

        sql.append(LandUseDB.START_CROP);
        sql.append(",");
        sql.append(LandUseDB.END_CROP);
        sql.append(",");
        sql.append(LandUseDB.PLANT_TYPE);
        sql.append(",");
        sql.append(LandUseDB.PLANT_ID);
        sql.append(",");
        sql.append(LandUseDB.PLANT_DETAIL_ID);
        sql.append(",");

        sql.append(LandUseDB.FERTILIZER1);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER2);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER3);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_CODE1);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_CODE2);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_CODE3);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_TOTAL1);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_TOTAL2);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_TOTAL3);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_PRICE1);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_PRICE2);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_PRICE3);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_SUM1);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_SUM2);
        sql.append(",");
        sql.append(LandUseDB.FERTILIZER_SUM3);
        sql.append(",");

        sql.append(LandUseDB.HORMONE_TYPE1);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_TYPE2);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_TYPE3);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_BRAND1);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_BRAND2);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_BRAND3);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_TOTAL1);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_TOTAL2);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_TOTAL3);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_PRICE1);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_PRICE2);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_PRICE3);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_SUM1);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_SUM2);
        sql.append(",");
        sql.append(LandUseDB.HORMONE_SUM3);
        sql.append(",");
        sql.append(LandUseDB.UPDATE_DATE);
        sql.append(",");
        sql.append(LandUseDB.UPDATE_BY);
        sql.append(",");
        sql.append(LandUseDB.REMARK1);
        sql.append(",");
        sql.append(LandUseDB.REMARK2);
        sql.append("");

        sql.append(" FROM ");
        sql.append(LandUseDB.TABLE_NAME);

        if (!surveyID.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(LandUseDB.SURVEY_ID);
            sql.append("=");
            sql.append(surveyID);
        }
        return sql.toString();
    }

    public static String getSelectAllSQLbyLandCode(String landNo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(LANDUSE_ID);
        sql.append(",");
        sql.append(SURVEY_ID);
        sql.append(",");
        sql.append(PLANT_TYPE);
        sql.append(",");
        sql.append(PLANT_ID);
        sql.append(",");
        sql.append(PLANT_DETAIL_ID);
        sql.append(",");
        sql.append(START_CROP);
        sql.append(",");
        sql.append(END_CROP);
        sql.append(",");
        sql.append(SEEDS);
        sql.append(",");

        sql.append(MetaPlantTypeDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantTypeDB.META_NAME);
        sql.append(" as PLANTTYPE,");

        sql.append(MetaPlantDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDB.META_NAME);
        sql.append(" as PLANTNAME,");

        sql.append(MetaPlantDetailDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDetailDB.META_NAME);
        sql.append(" as PLANTDETAILNAME,");

        sql.append(AREA);
        sql.append(" ");


        sql.append(" FROM ");
        sql.append(TABLE_NAME);

        sql.append(" LEFT JOIN ");
        sql.append(MetaPlantTypeDB.TABLE_NAME);
        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(PLANT_TYPE);
        sql.append("=");
        sql.append(MetaPlantTypeDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantTypeDB.META_ID);

        sql.append(" LEFT JOIN ");
        sql.append(MetaPlantDB.TABLE_NAME);
        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(PLANT_ID);
        sql.append("=");
        sql.append(MetaPlantDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDB.META_ID);

        sql.append(" LEFT JOIN ");
        sql.append(MetaPlantDetailDB.TABLE_NAME);
        sql.append(" ON ");
        sql.append(TABLE_NAME);
        sql.append(".");
        sql.append(PLANT_DETAIL_ID);
        sql.append("=");
        sql.append(MetaPlantDetailDB.TABLE_NAME);
        sql.append(".");
        sql.append(MetaPlantDetailDB.META_ID);

        sql.append(" WHERE ");
        sql.append(LAND_NO);
        sql.append("= '");
        sql.append(landNo);
        sql.append("'");

        sql.append(" ORDER BY ");
        sql.append(SURVEY_ID);
        sql.append(" DESC");

        return sql.toString();
    }

}
