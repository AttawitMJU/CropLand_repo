package com.hrdi.survey.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.control.AgriculturistDAO;
import com.hrdi.survey.control.MetaDAO;
import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.model.MetaBean;
import com.hrdi.survey.modeldb.AgriculturistDB;
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
import com.hrdi.survey.modeldb.MetaPlantDB;
import com.hrdi.survey.modeldb.MetaPlantDetailDB;
import com.hrdi.survey.modeldb.MetaPlantTypeDB;
import com.hrdi.survey.modeldb.MetaProvinceDB;
import com.hrdi.survey.modeldb.MetaTambolDB;
import com.hrdi.survey.modeldb.MetaUnitDB;
import com.hrdi.survey.modeldb.MetaWaterResourceDB;
import com.hrdi.survey.util.ConnectionDetector;
import com.hrdi.survey.util.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class SetupFragment extends Fragment implements View.OnClickListener {

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_META = "meta_s";
    private static final String TAG_ID = "meta_id";
    private static final String TAG_NAME = "meta_name";
    private static final String TAG_REF = "meta_ref";

    ImageButton img_btn_loadAllData;

    Button btn_card, btn_doc, btn_water, btn_unit,
            btn_market, btn_fertilizer, btn_fertilizercode,
            btn_hormone, btn_hormonetype, btn_jobactivity,
            btn_jobsource, btn_extproject,
            btn_province, btn_amphoe, btn_tambol,
            btn_planttype, btn_plant, btn_plantdetail, btn_import_agri,btn_upload_agri;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<MetaBean> metaList;
    // MetaData JSONArray
    JSONArray metaJson = null;
    String metaType;
    private MetaDAO metaDAO;
    private ProgressDialog pDialog;
    private AgriculturistDAO agriDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        metaDAO = new MetaDAO(getActivity());
        agriDAO = new AgriculturistDAO(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_setup, container, false);
        findViewsById(rootView);
        setListeners();
        countTableRecord();
        return rootView;
    }

    private void countTableRecord() {
        btn_card.setText(getString(R.string.meta_card) + "  (" + metaDAO.countRecord(MetaCardDB.TABLE_NAME) + ")");
        btn_doc.setText(getString(R.string.meta_doc) + "  (" + metaDAO.countRecord(MetaDocDB.TABLE_NAME) + ")");
        btn_water.setText(getString(R.string.meta_water) + "  (" + metaDAO.countRecord(MetaWaterResourceDB.TABLE_NAME) + ")");
        btn_unit.setText(getString(R.string.meta_unit) + "  (" + metaDAO.countRecord(MetaUnitDB.TABLE_NAME) + ")");
        btn_market.setText(getString(R.string.meta_market) + "  (" + metaDAO.countRecord(MetaMarketDB.TABLE_NAME) + ")");
        btn_fertilizer.setText(getString(R.string.meta_fertilizer) + "  (" + metaDAO.countRecord(MetaFertilizerDB.TABLE_NAME) + ")");
        btn_fertilizercode.setText(getString(R.string.meta_fertilizercode) + "  (" + metaDAO.countRecord(MetaFertilizerCodeDB.TABLE_NAME) + ")");
        btn_hormone.setText(getString(R.string.meta_hormone) + "  (" + metaDAO.countRecord(MetaHormoneDB.TABLE_NAME) + ")");
        btn_hormonetype.setText(getString(R.string.meta_hormonetype) + "  (" + metaDAO.countRecord(MetaHormoneTypeDB.TABLE_NAME) + ")");
        btn_jobactivity.setText(getString(R.string.meta_jobactivity) + "  (" + metaDAO.countRecord(MetaJobActivityDB.TABLE_NAME) + ")");
        btn_jobsource.setText(getString(R.string.meta_jobsource) + "  (" + metaDAO.countRecord(MetaJobSourceDB.TABLE_NAME) + ")");
        btn_extproject.setText(getString(R.string.meta_extproject) + "  (" + metaDAO.countRecord(MetaExtProjectDB.TABLE_NAME) + ")");
        btn_province.setText(getString(R.string.meta_province) + "  (" + metaDAO.countRecord(MetaProvinceDB.TABLE_NAME) + ")");
        btn_amphoe.setText(getString(R.string.meta_amphoe) + "  (" + metaDAO.countRecord(MetaAmphoeDB.TABLE_NAME) + ")");
        btn_tambol.setText(getString(R.string.meta_tambol) + "  (" + metaDAO.countRecord(MetaTambolDB.TABLE_NAME) + ")");
        btn_planttype.setText(getString(R.string.meta_planttype) + "  (" + metaDAO.countRecord(MetaPlantTypeDB.TABLE_NAME) + ")");
        btn_plant.setText(getString(R.string.meta_plant) + "  (" + metaDAO.countRecord(MetaPlantDB.TABLE_NAME) + ")");
        btn_plantdetail.setText(getString(R.string.meta_plantdetail) + "  (" + metaDAO.countRecord(MetaPlantDetailDB.TABLE_NAME) + ")");
        btn_import_agri.setText("เกษตรกร" + "  (" + metaDAO.countRecord(AgriculturistDB.TABLE_NAME) + ")");
        btn_upload_agri.setText("เกษตรกรรายใหม่" + "  (" + metaDAO.countRecord(AgriculturistDB.TABLE_NAME, " REMARK1 LIKE 'waiting'") + ")");
    }

    private String setMocupData(String source, int total) {
        MetaBean metaBean;
        ArrayList metaList = new ArrayList<MetaBean>();
        for (int i = 1; i <= total; i++) {
            metaBean = new MetaBean(i, source + " name " + i, "ref " + i, "");

            // adding MetaBean to ArrayList
            metaList.add(metaBean);
        }


        metaDAO.cleanMeta(source);
        metaDAO.importMeta(metaList, source);

        return String.valueOf(total);
    }

    private void findViewsById(View view) {
        img_btn_loadAllData = (ImageButton) view.findViewById(R.id.img_btn_loadAllData);
        btn_card = (Button) view.findViewById(R.id.btn_card);
        btn_doc = (Button) view.findViewById(R.id.btn_doc);
        btn_water = (Button) view.findViewById(R.id.btn_water);
        btn_unit = (Button) view.findViewById(R.id.btn_unit);
        btn_market = (Button) view.findViewById(R.id.btn_market);
        btn_fertilizer = (Button) view.findViewById(R.id.btn_fertilizer);
        btn_fertilizercode = (Button) view.findViewById(R.id.btn_fertilizercode);
        btn_hormone = (Button) view.findViewById(R.id.btn_hormone);
        btn_hormonetype = (Button) view.findViewById(R.id.btn_hormonetype);
        btn_jobactivity = (Button) view.findViewById(R.id.btn_jobactivity);
        btn_jobsource = (Button) view.findViewById(R.id.btn_jobsource);
        btn_extproject = (Button) view.findViewById(R.id.btn_extproject);
        btn_province = (Button) view.findViewById(R.id.btn_province);
        btn_amphoe = (Button) view.findViewById(R.id.btn_amphoe);
        btn_tambol = (Button) view.findViewById(R.id.btn_tambol);
        btn_planttype = (Button) view.findViewById(R.id.btn_planttype);
        btn_plant = (Button) view.findViewById(R.id.btn_plant);
        btn_plantdetail = (Button) view.findViewById(R.id.btn_plantdetail);
        btn_import_agri = (Button) view.findViewById(R.id.btn_import_agri);
        btn_upload_agri = (Button) view.findViewById(R.id.btn_upload_agri);
    }

    private void setListeners() {
        img_btn_loadAllData.setOnClickListener(this);
        btn_card.setOnClickListener(this);
        btn_doc.setOnClickListener(this);
        btn_water.setOnClickListener(this);
        btn_unit.setOnClickListener(this);
        btn_market.setOnClickListener(this);
        btn_fertilizer.setOnClickListener(this);
        btn_fertilizercode.setOnClickListener(this);
        btn_hormone.setOnClickListener(this);
        btn_hormonetype.setOnClickListener(this);
        btn_jobactivity.setOnClickListener(this);
        btn_jobsource.setOnClickListener(this);
        btn_extproject.setOnClickListener(this);
        btn_province.setOnClickListener(this);
        btn_amphoe.setOnClickListener(this);
        btn_tambol.setOnClickListener(this);
        btn_planttype.setOnClickListener(this);
        btn_plant.setOnClickListener(this);
        btn_plantdetail.setOnClickListener(this);
        btn_import_agri.setOnClickListener(this);
        btn_upload_agri.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        metaList = new ArrayList<>();

        ConnectionDetector cnt = new ConnectionDetector(getActivity());
        //Log.e("isNetworkAvailable() =", ""+cnt.isNetworkAvailable());
        if (cnt.isNetworkAvailable()) {

            if (view == img_btn_loadAllData) {
                //loadMetaData();
            } else if (view == btn_card) {
                metaType = "card";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_doc) {
                metaType = "doc";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_water) {
                metaType = "waterresource";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_unit) {
                metaType = "unit";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_market) {
                metaType = "market";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_fertilizer) {
                metaType = "fertilizer";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_fertilizercode) {
                metaType = "fertilizercode";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_hormone) {
                metaType = "hormone";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_hormonetype) {
                metaType = "hormonetype";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_jobactivity) {
                metaType = "jobactivity";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_jobsource) {
                metaType = "jobsource";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_extproject) {
                metaType = "extproject";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_province) {
                metaType = "province";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_amphoe) {
                metaType = "amphoe";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_tambol) {
                metaType = "tambol";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_planttype) {
                metaType = "planttype";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_plant) {
                metaType = "plant";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_plantdetail) {
                metaType = "plantdetail";
                LoadMetaTask task = new LoadMetaTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else if (view == btn_import_agri) {
                LoadAgriTask task = new LoadAgriTask(getActivity());
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        } else {
            Toast.makeText(getActivity(), getString(R.string.connection_off),
                    Toast.LENGTH_LONG).show();
        }

    }

    /*
      Background Async Task to Load Meta_Card
    */
    class LoadMetaTask extends AsyncTask<String, String, String> {
        private final WeakReference<Activity> activityWeakRef;

        public LoadMetaTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Meta Data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            String result = updateMetaData(metaType);


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            // dismiss the dialog after getting all MetaData
            pDialog.dismiss();
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {

                if (!"-1".equals(s)) {

                    //txt_hidden_id.setText(String.valueOf(s));
                    Toast.makeText(activityWeakRef.get(), "บันทึกข้อมูลเรียบร้อย ",
                            Toast.LENGTH_LONG).show();
                    if ("card".equals(metaType))
                        btn_card.setText(getString(R.string.meta_card) + "  (" + s + ")");
                    else if ("doc".equals(metaType))
                        btn_doc.setText(getString(R.string.meta_doc) + "  (" + s + ")");
                    else if ("waterresource".equals(metaType))
                        btn_water.setText(getString(R.string.meta_water) + "  (" + s + ")");
                    else if ("unit".equals(metaType))
                        btn_unit.setText(getString(R.string.meta_unit) + "  (" + s + ")");
                    else if ("market".equals(metaType))
                        btn_market.setText(getString(R.string.meta_market) + "  (" + s + ")");
                    else if ("fertilizer".equals(metaType))
                        btn_fertilizer.setText(getString(R.string.meta_fertilizer) + "  (" + s + ")");
                    else if ("fertilizercode".equals(metaType))
                        btn_fertilizercode.setText(getString(R.string.meta_fertilizercode) + "  (" + s + ")");
                    else if ("hormone".equals(metaType))
                        btn_hormone.setText(getString(R.string.meta_hormone) + "  (" + s + ")");
                    else if ("hormonetype".equals(metaType))
                        btn_hormonetype.setText(getString(R.string.meta_hormonetype) + "  (" + s + ")");
                    else if ("jobactivity".equals(metaType))
                        btn_jobactivity.setText(getString(R.string.meta_jobactivity) + "  (" + s + ")");
                    else if ("jobsource".equals(metaType))
                        btn_jobsource.setText(getString(R.string.meta_jobsource) + "  (" + s + ")");
                    else if ("extproject".equals(metaType))
                        btn_extproject.setText(getString(R.string.meta_extproject) + "  (" + s + ")");
                    else if ("province".equals(metaType))
                        btn_province.setText(getString(R.string.meta_province) + "  (" + s + ")");
                    else if ("amphoe".equals(metaType))
                        btn_amphoe.setText(getString(R.string.meta_amphoe) + "  (" + s + ")");
                    else if ("tambol".equals(metaType))
                        btn_tambol.setText(getString(R.string.meta_tambol) + "  (" + s + ")");
                    else if ("planttype".equals(metaType))
                        btn_planttype.setText(getString(R.string.meta_planttype) + "  (" + s + ")");
                    else if ("plant".equals(metaType))
                        btn_plant.setText(getString(R.string.meta_plant) + "  (" + s + ")");
                    else if ("plantdetail".equals(metaType))
                        btn_plantdetail.setText(getString(R.string.meta_plantdetail) + "  (" + s + ")");

                    metaType = "";

                } else {
                    Toast.makeText(activityWeakRef.get(), getString(R.string.connection_off),
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        private String updateMetaData(String source) {
            List<NameValuePair> paramsList = new ArrayList<>();


            // Check your log cat for JSON reponse
            // Log.d("All Meta: ", json.toString());
            String id = "";
            String name = "";
            String ref = "";
            int count = -1;
            int statuscode = 0;

            MetaBean metaBean;

            try {
                HttpGet httpRequest = new HttpGet(getString(R.string.url_get_meta));
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpRequest);

                statuscode = response.getStatusLine().getStatusCode();
                //Log.e("statuscode ", String.valueOf(statuscode));

                if (HttpStatus.SC_OK == statuscode) {
                    // getting JSON string from URL
                    JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_get_meta) + "?source=" + source, paramsList);
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) {
                        // MetaData found
                        // Getting Array of MetaData
                        metaJson = json.getJSONArray(TAG_META);

                        // looping through All MetaData
                        for (int i = 0; i < metaJson.length(); i++) {
                            JSONObject c = metaJson.getJSONObject(i);

                            // Storing each json item in variable
                            id = c.getString(TAG_ID);
                            name = c.getString(TAG_NAME);
                            ref = c.getString(TAG_REF);

                            metaBean = new MetaBean(Integer.parseInt(id), name, ref, "");

                            // adding MetaBean to ArrayList
                            metaList.add(metaBean);
                        }


                        metaDAO.cleanMeta(source);
                        count = metaDAO.importMeta(metaList, source);

                    }
                }
            } catch (Exception e) {
                // e.printStackTrace();
            }
            return String.valueOf(count);
        }


    }


    /*
  Background Async Task to Load Agri
  */
    class LoadAgriTask extends AsyncTask<String, String, String> {
        private final WeakReference<Activity> activityWeakRef;

        public LoadAgriTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading Data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... params) {
            String result = updateAgri();


            return result;
        }

        private String updateAgri() {
            List<NameValuePair> paramsList = new ArrayList<>();

            int count = -1;
            int statuscode = 0;
            JSONArray agriJson;
            ArrayList<AgriculturistBean> agriList;
            AgriculturistBean bean;

            try {
                HttpGet httpRequest = new HttpGet(getString(R.string.url_get_agri));
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpRequest);

                statuscode = response.getStatusLine().getStatusCode();
                //Log.i("HttpResponse status ", String.valueOf(statuscode));

                if (HttpStatus.SC_OK == statuscode) {
                    // getting JSON string from URL
                    JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_get_agri), paramsList);
                    //Log.i("JSONObject url_get_agri", json.toString());
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);
                    //Log.i("JSONObject status ", String.valueOf(success));

                    if (success == 1) {
                        // MetaData found
                        // Getting Array of MetaData
                        agriJson = json.getJSONArray("agri_s");
                        agriList = new ArrayList<>();

                        // looping through All MetaData
                        for (int i = 0; i < agriJson.length(); i++) {
                            JSONObject c = agriJson.getJSONObject(i);


                            bean = new AgriculturistBean();
                            // Storing each json item in variable
                            bean.setAgriculturist_id(c.getString("Agriculturist_ID"));
                            bean.setCard_no(c.getString("Card_no"));
                            bean.setTitle(c.getString("Title"));
                            bean.setFirstname(c.getString("FirstName"));
                            bean.setLastname(c.getString("LastName"));
                            bean.setHome_no(c.getString("Home_no"));
                            bean.setMoo_no(c.getString("Moo_no"));
                            bean.setGroup_no(c.getString("Group_no"));
                            bean.setVillage_no(c.getString("Village_ID"));
                            bean.setTambol_id(c.getString("Tambol_ID"));
                            bean.setAmphur_id(c.getString("Amphoe_ID"));
                            bean.setProvince_id(c.getString("Province_ID"));
                            bean.setZipcode(c.getString("Zipcode"));
                            bean.setOccupation1(c.getString("Occupation1"));
                            bean.setOccupation2(c.getString("Occupation2"));
                            bean.setFree_time(c.getString("Free_time"));
                            bean.setMember_all(c.getString("Member_All"));
                            bean.setMember_type1(c.getString("Menber_Type1"));
                            bean.setMember_type2(c.getString("Menber_Type2"));
                            bean.setMember_type3(c.getString("Menber_Type3"));
                            bean.setIncome_all(c.getString("Incomes_All"));
                            bean.setIncomes1(c.getString("Incomes1"));
                            bean.setIncomes2(c.getString("Incomes2"));
                            bean.setExpenses_all(c.getString("Expenses_All"));
                            bean.setExpenses1(c.getString("Expenses1"));
                            bean.setExpenses2(c.getString("Expenses2"));

                            // adding MetaBean to ArrayList
                            agriList.add(bean);
                        }


                        agriDAO.cleanAgriculturist();
                        //Log.i("metaDAO.importMeta -->", source + metaList.size());
                        count = agriDAO.importAgriculturist(agriList);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return String.valueOf(count);
        }

        @Override
        protected void onPostExecute(String s) {
            // dismiss the dialog after getting all MetaData
            pDialog.dismiss();
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {

                if (!"-1".equals(s)) {

                    //txt_hidden_id.setText(String.valueOf(s));
                    Toast.makeText(activityWeakRef.get(), "บันทึกข้อมูลเรียบร้อย ",
                            Toast.LENGTH_LONG).show();

                    btn_import_agri.setText("เกษตร" + "  (" + s + ")");


                } else {
                    Toast.makeText(activityWeakRef.get(), getString(R.string.connection_off),
                            Toast.LENGTH_LONG).show();
                }
            }
        }


    }


}
