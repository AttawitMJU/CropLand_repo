package com.hrdi.survey.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.control.LandUseDAO;
import com.hrdi.survey.control.MetaDAO;
import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.model.MetaBean;
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

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

public class SurveyPlantFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    final static String TAG_FRAGMENT = "PLANT_FRAGMENT";

    LandUseDAO landUseDAO;
    Button btn_ok;
    CreateSurveyPlantsTask task;
    int mYear, mMonth, mDay;
    //private SurveyBean surveyBean;
    private LandUseBean landUseBean;
    // UI references
    private EditText edt_seed, edt_unit_price, edt_sum_seed, edt_crop_area, edt_crop_time1, edt_crop_time2,
            edt_product_use, edt_product_sale, edt_sale_price, edt_income_year,
            edt_labour_total, edt_labour_paid, edt_labour_time,
            edt_fuel, edt_other_paid,
            edt_amount1, edt_amount2, edt_amount3, edt_price1, edt_price2, edt_price3,
            edt_hormone_amount1, edt_hormone_amount2, edt_hormone_amount3,
            edt_hormone_price1, edt_hormone_price2, edt_hormone_price3,
            edt_sum_f1, edt_sum_f2, edt_sum_f3, edt_sum_h1, edt_sum_h2, edt_sum_h3;
    private Spinner spn_plantType, spn_plant, spn_plant_detail,
            spn_jobactivity, spn_jobsource, spn_market,
            spn_fertilizer1, spn_fertilizer_code1,
            spn_fertilizer2, spn_fertilizer_code2,
            spn_fertilizer3, spn_fertilizer_code3,
            spn_hormonetype1, spn_hormone1,
            spn_hormonetype2, spn_hormone2,
            spn_hormonetype3, spn_hormone3;

    private static String surveyID, landcode, cardno;

    public SurveyPlantFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get parameter
        Bundle bundle = this.getArguments();
        surveyID = bundle.getString("surveyID");
        landcode = bundle.getString("landcode");
        cardno = bundle.getString("cardno");
        //surveyBean = bundle.getParcelable("surveyBean");
        //Log.i("surveyBean Plant", surveyBean.toString());
        //-----------------

        landUseDAO = new LandUseDAO(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_survey_plant, container, false);

        //To show current date in the datepicker
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        findViewsById(rootView);

        // Set Spinner/List Data
        setListItemAdapter();

        setListeners();

        return rootView;
    }


    private void findViewsById(View view) {
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        edt_seed = (EditText) view.findViewById(R.id.edt_seed);
        edt_unit_price = (EditText) view.findViewById(R.id.edt_unit_price);
        edt_sum_seed = (EditText) view.findViewById(R.id.edt_sum_seed);
        edt_crop_area = (EditText) view.findViewById(R.id.edt_crop_area);
        edt_crop_time1 = (EditText) view.findViewById(R.id.edt_crop_time1);
        edt_crop_time2 = (EditText) view.findViewById(R.id.edt_crop_time2);
        edt_product_use = (EditText) view.findViewById(R.id.edt_product_use);
        edt_product_sale = (EditText) view.findViewById(R.id.edt_product_sale);
        edt_sale_price = (EditText) view.findViewById(R.id.edt_sale_price);
        edt_income_year = (EditText) view.findViewById(R.id.edt_income_year);

        edt_labour_total = (EditText) view.findViewById(R.id.edt_labour_total);
        edt_labour_paid = (EditText) view.findViewById(R.id.edt_labour_paid);
        edt_labour_time = (EditText) view.findViewById(R.id.edt_labour_time);
        edt_fuel = (EditText) view.findViewById(R.id.edt_fuel);
        edt_other_paid = (EditText) view.findViewById(R.id.edt_other_paid);
        edt_amount1 = (EditText) view.findViewById(R.id.edt_amount1);
        edt_amount2 = (EditText) view.findViewById(R.id.edt_amount2);
        edt_amount3 = (EditText) view.findViewById(R.id.edt_amount3);
        edt_price1 = (EditText) view.findViewById(R.id.edt_price1);
        edt_price2 = (EditText) view.findViewById(R.id.edt_price2);
        edt_price3 = (EditText) view.findViewById(R.id.edt_price3);
        edt_hormone_amount1 = (EditText) view.findViewById(R.id.edt_hormone_amount1);
        edt_hormone_amount2 = (EditText) view.findViewById(R.id.edt_hormone_amount2);
        edt_hormone_amount3 = (EditText) view.findViewById(R.id.edt_hormone_amount3);
        edt_hormone_price1 = (EditText) view.findViewById(R.id.edt_hormone_price1);
        edt_hormone_price2 = (EditText) view.findViewById(R.id.edt_hormone_price2);
        edt_hormone_price3 = (EditText) view.findViewById(R.id.edt_hormone_price3);

        edt_sum_f1 = (EditText) view.findViewById(R.id.edt_sum_f1);
        edt_sum_f2 = (EditText) view.findViewById(R.id.edt_sum_f2);
        edt_sum_f3 = (EditText) view.findViewById(R.id.edt_sum_f3);
        edt_sum_h1 = (EditText) view.findViewById(R.id.edt_sum_h1);
        edt_sum_h2 = (EditText) view.findViewById(R.id.edt_sum_h2);
        edt_sum_h3 = (EditText) view.findViewById(R.id.edt_sum_h3);

        spn_plantType = (Spinner) view.findViewById(R.id.spn_plantType);
        spn_plant = (Spinner) view.findViewById(R.id.spn_plant);
        spn_plant_detail = (Spinner) view.findViewById(R.id.spn_plant_detail);
        spn_jobactivity = (Spinner) view.findViewById(R.id.spn_jobactivity);
        spn_jobsource = (Spinner) view.findViewById(R.id.spn_jobsource);
        spn_market = (Spinner) view.findViewById(R.id.spn_market);
        spn_fertilizer1 = (Spinner) view.findViewById(R.id.spn_fertilizer1);
        spn_fertilizer2 = (Spinner) view.findViewById(R.id.spn_fertilizer2);
        spn_fertilizer3 = (Spinner) view.findViewById(R.id.spn_fertilizer3);
        spn_fertilizer_code1 = (Spinner) view.findViewById(R.id.spn_fertilizer_code1);
        spn_fertilizer_code2 = (Spinner) view.findViewById(R.id.spn_fertilizer_code2);
        spn_fertilizer_code3 = (Spinner) view.findViewById(R.id.spn_fertilizer_code3);
        spn_hormonetype1 = (Spinner) view.findViewById(R.id.spn_hormonetype1);
        spn_hormonetype2 = (Spinner) view.findViewById(R.id.spn_hormonetype2);
        spn_hormonetype3 = (Spinner) view.findViewById(R.id.spn_hormonetype3);
        spn_hormone1 = (Spinner) view.findViewById(R.id.spn_hormone1);
        spn_hormone2 = (Spinner) view.findViewById(R.id.spn_hormone2);
        spn_hormone3 = (Spinner) view.findViewById(R.id.spn_hormone3);

    }

    private void setListeners() {
        btn_ok.setOnClickListener(this);
        edt_crop_time1.setOnClickListener(this);
        edt_crop_time2.setOnClickListener(this);
        spn_plantType.setOnItemSelectedListener(this);
        spn_plant.setOnItemSelectedListener(this);
        spn_fertilizer1.setOnItemSelectedListener(this);
        spn_fertilizer2.setOnItemSelectedListener(this);
        spn_fertilizer3.setOnItemSelectedListener(this);
        spn_hormonetype1.setOnItemSelectedListener(this);
        spn_hormonetype2.setOnItemSelectedListener(this);
        spn_hormonetype3.setOnItemSelectedListener(this);

        edt_seed.setOnClickListener(this);
        edt_unit_price.setOnClickListener(this);
        edt_sum_seed.setOnClickListener(this);


        edt_amount1.setOnClickListener(this);
        edt_amount2.setOnClickListener(this);
        edt_amount3.setOnClickListener(this);
        edt_price1.setOnClickListener(this);
        edt_price2.setOnClickListener(this);
        edt_price3.setOnClickListener(this);
        edt_hormone_amount1.setOnClickListener(this);
        edt_hormone_amount2.setOnClickListener(this);
        edt_hormone_amount3.setOnClickListener(this);
        edt_hormone_price1.setOnClickListener(this);
        edt_hormone_price2.setOnClickListener(this);
        edt_hormone_price3.setOnClickListener(this);

        edt_sum_f1.setOnClickListener(this);
        edt_sum_f2.setOnClickListener(this);
        edt_sum_f3.setOnClickListener(this);
        edt_sum_h1.setOnClickListener(this);
        edt_sum_h2.setOnClickListener(this);
        edt_sum_h3.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MetaDAO metaDAO = new MetaDAO(getActivity());
        Spinner spinner = (Spinner) parent;

        ArrayAdapter<MetaBean> dataAdapter;
        List<MetaBean> metaBeanList;
        MetaBean metaBean = (MetaBean) parent.getItemAtPosition(position);

        if (spinner.getId() == R.id.spn_plantType) {
            // Spinner พืช
            metaBeanList = metaDAO.getMetaByType(MetaPlantDB.getSelectAllSQLRef(metaBean.getItemId()));
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_plant.setAdapter(dataAdapter);
        } else if (spinner.getId() == R.id.spn_plant) {
            // Spinner พันธุ์
            metaBeanList = metaDAO.getMetaByType(MetaPlantDetailDB.getSelectAllSQLRef(metaBean.getItemId()));
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_plant_detail.setAdapter(dataAdapter);

        } else if (spinner.getId() == R.id.spn_fertilizer1) {
            // Spinner สูตรปุ๋ย
            // metaBeanList = metaDAO.getMetaByType(MetaFertilizerCodeDB.getSelectAllSQLRef(metaBean.getItemId()));
            metaBeanList = metaDAO.getMetaByType(MetaFertilizerCodeDB.getSelectAllSQL());
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_fertilizer_code1.setAdapter(dataAdapter);
        } else if (spinner.getId() == R.id.spn_fertilizer2) {
            // Spinner สูตรปุ๋ย
            metaBeanList = metaDAO.getMetaByType(MetaFertilizerCodeDB.getSelectAllSQL());
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_fertilizer_code2.setAdapter(dataAdapter);
        } else if (spinner.getId() == R.id.spn_fertilizer3) {
            // Spinner สูตรปุ๋ย
            metaBeanList = metaDAO.getMetaByType(MetaFertilizerCodeDB.getSelectAllSQL());
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_fertilizer_code3.setAdapter(dataAdapter);
        } else if (spinner.getId() == R.id.spn_hormonetype1) {
            // Spinner สูตรปุ๋ย
            // metaBeanList = metaDAO.getMetaByType(MetaHormoneDB.getSelectAllSQLRef(metaBean.getItemId()));
            metaBeanList = metaDAO.getMetaByType(MetaHormoneDB.getSelectAllSQL());
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_hormone1.setAdapter(dataAdapter);
        } else if (spinner.getId() == R.id.spn_hormonetype2) {
            // Spinner สูตรปุ๋ย
            metaBeanList = metaDAO.getMetaByType(MetaHormoneDB.getSelectAllSQL());
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_hormone2.setAdapter(dataAdapter);
        } else if (spinner.getId() == R.id.spn_hormonetype3) {
            // Spinner สูตรปุ๋ย
            metaBeanList = metaDAO.getMetaByType(MetaHormoneDB.getSelectAllSQL());
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_hormone3.setAdapter(dataAdapter);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setListItemAdapter() {
        MetaDAO metaDAO = new MetaDAO(getActivity());
        ArrayAdapter<MetaBean> dataAdapter, dataAdapterDefault;

        List<MetaBean> defaultList = metaDAO.getMetaByType("");
        dataAdapterDefault = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, defaultList);


        // Spinner ประเภทพืช
        List<MetaBean> plantTypeList = metaDAO.getMetaByType(MetaPlantTypeDB.getSelectAllSQL());
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, plantTypeList);
        spn_plantType.setAdapter(dataAdapter);

        // Spinner พืช
        List<MetaBean> plantList = metaDAO.getMetaByType("");
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, plantList);
        spn_plant.setAdapter(dataAdapter);

        // Spinner พันธุ์
        List<MetaBean> plantDetailList = metaDAO.getMetaByType("");
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, plantDetailList);
        spn_plant_detail.setAdapter(dataAdapter);

        // Spinner ลักษณะการจ้างงาน
        List<MetaBean> jobActivityList = metaDAO.getMetaByType(MetaJobActivityDB.getSelectAllSQL());
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, jobActivityList);
        spn_jobactivity.setAdapter(dataAdapter);

        // Spinner การจ้างงาน
        List<MetaBean> jobSourceList = metaDAO.getMetaByType(MetaJobSourceDB.getSelectAllSQL());
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, jobSourceList);
        spn_jobsource.setAdapter(dataAdapter);

        // Spinner ตลาด
        List<MetaBean> marketList = metaDAO.getMetaByType(MetaMarketDB.getSelectAllSQL());
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, marketList);
        spn_market.setAdapter(dataAdapter);

        // Spinner ยี่ห้อปุ๋ย
        List<MetaBean> fertilizerList = metaDAO.getMetaByType(MetaFertilizerDB.getSelectAllSQL());
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, fertilizerList);
        spn_fertilizer1.setAdapter(dataAdapter);
        spn_fertilizer2.setAdapter(dataAdapter);
        spn_fertilizer3.setAdapter(dataAdapter);


        // Spinner สูตรปุ๋ย
        List<MetaBean> fertilizerCodeList = metaDAO.getMetaByType("");
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, fertilizerCodeList);
        spn_fertilizer_code1.setAdapter(dataAdapterDefault);
        spn_fertilizer_code2.setAdapter(dataAdapterDefault);
        spn_fertilizer_code3.setAdapter(dataAdapterDefault);

        // Spinner ประเภทยา/ฮอร์โมน
        List<MetaBean> hormoneTypeList = metaDAO.getMetaByType(MetaHormoneTypeDB.getSelectAllSQL());
        dataAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, hormoneTypeList);
        spn_hormonetype1.setAdapter(dataAdapter);
        spn_hormonetype2.setAdapter(dataAdapter);
        spn_hormonetype3.setAdapter(dataAdapter);

        // Spinner ยา/ฮอร์โมน
        // List<MetaBean> hormoneList = metaDAO.getMetaByType("");
        // dataAdapter = new ArrayAdapter<>(getActivity(),
        //        android.R.layout.simple_spinner_item, hormoneList);
        spn_hormone1.setAdapter(dataAdapterDefault);
        spn_hormone2.setAdapter(dataAdapterDefault);
        spn_hormone3.setAdapter(dataAdapterDefault);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_ok) {
            //Log.e("getGUI2Bean", surveyBean.toString());

            landUseBean = getGUI2Bean();
            Log.e("landUseBean", landUseBean.toString());

            // Call Task to Add SQLite
            task = new CreateSurveyPlantsTask(getActivity());
            task.execute();

            backToLandUseFragment();
        } else if (view == edt_crop_time1) {
            DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edt_crop_time1.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.show();

        } else if (view == edt_crop_time2) {
            DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edt_crop_time2.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.show();
        } else if (view == edt_seed || view == edt_unit_price || view == edt_sum_seed) {
            autoSum(edt_seed, edt_unit_price, edt_sum_seed);
        } else if (view == edt_amount1 || view == edt_price1 || view == edt_sum_f1) {
            autoSum(edt_amount1, edt_price1, edt_sum_f1);
        } else if (view == edt_amount2 || view == edt_price2 || view == edt_sum_f2) {
            autoSum(edt_amount2, edt_price2, edt_sum_f2);
        } else if (view == edt_amount3 || view == edt_price3 || view == edt_sum_f3) {
            autoSum(edt_amount3, edt_price3, edt_sum_f3);
        } else if (view == edt_hormone_amount1 || view == edt_hormone_price1 || view == edt_sum_h1) {
            autoSum(edt_hormone_amount1, edt_hormone_price1, edt_sum_h1);
        }

    }

    private void autoSum(EditText t1, EditText t2, EditText t3) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (!t1.getText().toString().isEmpty() && !t2.getText().toString().isEmpty()) {
            t3.setText(
                    "" + df.format(Double.valueOf(t1.getText().toString()) *
                            Double.valueOf(t2.getText().toString()))
            );
        } else if (!t1.getText().toString().isEmpty() && !t3.getText().toString().isEmpty()) {
            t2.setText(
                    "" + df.format(Double.valueOf(t3.getText().toString()) /
                            Double.valueOf(t1.getText().toString()))
            );
        } else if (!t2.getText().toString().isEmpty() && !t3.getText().toString().isEmpty()) {
            t1.setText(
                    "" + df.format(Double.valueOf(t3.getText().toString()) /
                            Double.valueOf(t2.getText().toString()))
            );
        }

    }

    private LandUseBean getGUI2Bean() {
        LandUseBean landUse = new LandUseBean();

        landUse.setSurvey_ID(surveyID);
        landUse.setLand_No(landcode);
        landUse.setCard_no(cardno);
        //landUse.setSurvey_Date(surveyBean.getSurvey_Date());

        //landUse.setPlant_Year(  );
        landUse.setArea(edt_crop_area.getText().toString());
        //landUse.setHarvest_Status();
        landUse.setSeeds(edt_seed.getText().toString());
        //landUse.setHas_Hire();
        //landUse.setLabour( );
        landUse.setFuel(edt_fuel.getText().toString());
        landUse.setOther_Paid(edt_other_paid.getText().toString());
        landUse.setProduct_Use(edt_product_use.getText().toString());
        landUse.setProduct_Sale(edt_product_sale.getText().toString());
        landUse.setPrice(edt_sale_price.getText().toString());
        landUse.setIncome_Year(edt_income_year.getText().toString());

        landUse.setMarket(String.valueOf(((MetaBean) spn_market.getSelectedItem()).getItemId()));

        landUse.setEmploy_Type(String.valueOf(((MetaBean) spn_jobactivity.getSelectedItem()).getItemId()));
        landUse.setEmploy_From(String.valueOf(((MetaBean) spn_jobsource.getSelectedItem()).getItemId()));
        landUse.setLabour_Total(edt_labour_total.getText().toString());
        landUse.setLabour_Paid(edt_labour_paid.getText().toString());
        landUse.setLabour_Time(edt_labour_time.getText().toString());

        landUse.setStart_crop(edt_crop_time1.getText().toString());
        landUse.setEnd_crop(edt_crop_time2.getText().toString());

        landUse.setPlant_Type(String.valueOf(((MetaBean) spn_plantType.getSelectedItem()).getItemId()));
        landUse.setPlant_id(String.valueOf(((MetaBean) spn_plant.getSelectedItem()).getItemId()));
        landUse.setPlant_detail_id(String.valueOf(((MetaBean) spn_plant_detail.getSelectedItem()).getItemId()));

        landUse.setFertilizer1(String.valueOf(((MetaBean) spn_fertilizer1.getSelectedItem()).getItemId()));
        landUse.setFertilizer2(String.valueOf(((MetaBean) spn_fertilizer2.getSelectedItem()).getItemId()));
        landUse.setFertilizer3(String.valueOf(((MetaBean) spn_fertilizer3.getSelectedItem()).getItemId()));
        landUse.setFertilizer_code1(String.valueOf(((MetaBean) spn_fertilizer_code1.getSelectedItem()).getItemId()));
        landUse.setFertilizer_code2(String.valueOf(((MetaBean) spn_fertilizer_code2.getSelectedItem()).getItemId()));
        landUse.setFertilizer_code3(String.valueOf(((MetaBean) spn_fertilizer_code3.getSelectedItem()).getItemId()));
        landUse.setFertilizer_total1(edt_amount1.getText().toString());
        landUse.setFertilizer_total2(edt_amount2.getText().toString());
        landUse.setFertilizer_total3(edt_amount3.getText().toString());
        landUse.setFertilizer_price1(edt_price1.getText().toString());
        landUse.setFertilizer_price2(edt_price2.getText().toString());
        landUse.setFertilizer_price3(edt_price3.getText().toString());
        landUse.setFertilizer_sum1(edt_sum_f1.getText().toString());
        landUse.setFertilizer_sum2(edt_sum_f2.getText().toString());
        landUse.setFertilizer_sum3(edt_sum_f3.getText().toString());

        landUse.setHormone_type1(String.valueOf(((MetaBean) spn_hormonetype1.getSelectedItem()).getItemId()));
        landUse.setHormone_type2(String.valueOf(((MetaBean) spn_hormonetype2.getSelectedItem()).getItemId()));
        landUse.setHormone_type3(String.valueOf(((MetaBean) spn_hormonetype3.getSelectedItem()).getItemId()));
        landUse.setHormone_brand1(String.valueOf(((MetaBean) spn_hormone1.getSelectedItem()).getItemId()));
        landUse.setHormone_brand2(String.valueOf(((MetaBean) spn_hormone2.getSelectedItem()).getItemId()));
        landUse.setHormone_brand3(String.valueOf(((MetaBean) spn_hormone3.getSelectedItem()).getItemId()));
        landUse.setHormone_total1(edt_hormone_amount1.getText().toString());
        landUse.setHormone_total2(edt_hormone_amount2.getText().toString());
        landUse.setHormone_total3(edt_hormone_amount3.getText().toString());
        landUse.setHormone_price1(edt_hormone_price1.getText().toString());
        landUse.setHormone_price2(edt_hormone_price2.getText().toString());
        landUse.setHormone_price3(edt_hormone_price3.getText().toString());
        landUse.setHormone_sum1(edt_sum_h1.getText().toString());
        landUse.setHormone_sum2(edt_sum_h2.getText().toString());
        landUse.setHormone_sum3(edt_sum_h3.getText().toString());
        // landUse.setUpdate_Date();
        // landUse.setUpdate_By();
        // landUse.setRemark1();
        // landUse.setRemark2());

        //Log.i("set planttype", landUse.getPlant_Type());
        //Log.i("set plant",landUse.getPlant_id());
        //Log.i("set plantDetail",landUse.getPlant_detail_id());

        return landUse;
    }

    private void backToLandUseFragment() {
        // Change Fragment
        SurveyLandUseFragment fragment = new SurveyLandUseFragment();
        FragmentManager fragmentManager = getFragmentManager();

        // Send parameter surveybaen to next page
        Bundle dataBundle = new Bundle();

        dataBundle.putString("surveyID", surveyID);
        dataBundle.putString("landcode", landcode);
        dataBundle.putString("cardno", cardno);

        dataBundle.putParcelable("landuseBean", landUseBean);
        Log.i("landuseBean +Plant", landUseBean.toString());

        fragment.setArguments(dataBundle);


        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, TAG_FRAGMENT).addToBackStack(null).commit();

    }


    /*
     Background Async Task to Create new Survey
* */
    class CreateSurveyPlantsTask extends AsyncTask<String, String, String> {
        private final WeakReference<Activity> activityWeakRef;

        public CreateSurveyPlantsTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }


        @Override
        protected void onPostExecute(String s) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {

                if (!"-1".equals(s)) {
                    Toast.makeText(activityWeakRef.get(), "บันทึกข้อมูลเรียบร้อย ",
                            Toast.LENGTH_LONG).show();
                }
                //backToLandUseFragment();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            long result = landUseDAO.addSurveyPlant(landUseBean);

            return String.valueOf(result);
        }
    }
}
