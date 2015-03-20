package com.hrdi.survey;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hrdi.survey.control.AgriculturistDAO;
import com.hrdi.survey.control.MetaDAO;
import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.model.MetaBean;
import com.hrdi.survey.util.JSONParser;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends Activity {


    private MetaDAO metaDAO;
    private AgriculturistDAO agriDAO;

    LoadingTask loadingTask;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_META = "meta_s";
    private static final String TAG_ID = "meta_id";
    private static final String TAG_NAME = "meta_name";
    private static final String TAG_REF = "meta_ref";
    private static final String TAG_VAL = "meta_val";
    private static final String TAG_REMARK = "meta_remark";


    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<MetaBean> metaList;
    ArrayList<AgriculturistBean> agriList;
    JSONArray metaJson = null;
    JSONArray agriJson = null;
    final int total_mata = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        metaDAO = new MetaDAO(this);
        agriDAO = new AgriculturistDAO(this);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setMax(total_mata);

        TextView txt_loading = (TextView) findViewById(R.id.txt_loading);


        // Start your loading
        loadingTask = new LoadingTask(progressBar, txt_loading);
        loadingTask.execute(getString(R.string.url_get_meta));


    }


    private void completeSplash() {
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {

        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);

    }


    public class LoadingTask extends AsyncTask<String, Integer, Integer> {

        // This is the progress bar you want to update while the task is in progress
        private final ProgressBar progressBar;
        private final TextView txt_loading;


        public LoadingTask(ProgressBar progressBar, TextView txt) {
            this.progressBar = progressBar;
            this.txt_loading = txt;

        }

        @Override
        protected Integer doInBackground(String... params) {
            //Log.i("Tutorial", "Starting task with url: " + params[0]);
            if (resourcesDontAlreadyExist()) {
                downloadResources();
            }
            // Perhaps you want to return something to your post execute
            return 1234;
        }

        private boolean resourcesDontAlreadyExist() {
            //Log.i("metaDAO.countRecord", "" + metaDAO.countRecord("LAND"));
            if (metaDAO.countRecord("META_CARD") == 0) {
                // Here you would query your app's internal state to see if this download had been performed before
                // Perhaps once checked save this in a shared preference for speed of access next time
                return true; // returning true so we show the splash every time
            } else {
                return false;
            }
        }


        private void downloadResources() {
            // We are just imitating some process thats takes a bit of time (loading of resources / downloading)
            //int count = 22;
            //int i = 1;
            int progress =0;
            try {
                publishProgress(++progress);
                updateMetaData("title");

                publishProgress(++progress);
                updateMetaData("card");

                publishProgress(++progress);
                updateMetaData("doc");

                publishProgress(++progress);
                updateMetaData("waterresource");


                publishProgress(++progress);
                updateMetaData("unit");

                publishProgress(++progress);
                updateMetaData("market");

                publishProgress(++progress);
                updateMetaData("fertilizer");

                publishProgress(++progress);
                updateMetaData("fertilizercode");

                publishProgress(++progress);
                updateMetaData("hormone");

                publishProgress(++progress);
                updateMetaData("hormonetype");

                publishProgress(++progress);
                updateMetaData("jobactivity");

                publishProgress(++progress);
                updateMetaData("jobsource");

                publishProgress(++progress);
                updateMetaData("extproject");

                publishProgress(++progress);
                updateMetaData("planttype");

                publishProgress(++progress);
                updateMetaData("plant");

                publishProgress(++progress);
                updateMetaData("plantdetail");

                publishProgress(++progress);
                updateMetaData("province");

                publishProgress(++progress);
                updateMetaData("amphoe");

                publishProgress(++progress);
                updateMetaData("tambol");

                publishProgress(++progress);
                updateMetaData("mooban");

                publishProgress(++progress);
                updateMetaData("projectarea");


                //Log.i("Start updateAgri","updateAgri();");
                publishProgress(++progress);
                updateAgri();

                publishProgress(++progress);

                try { Thread.sleep(1000); } catch (InterruptedException ignore) {ignore.printStackTrace();}


            } catch (Exception e) {
                Log.e("updateMetaData Error", e.toString());
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Log.i("onProgressUpdate", values.toString());
            progressBar.setProgress(values[0]); // This is ran on the UI thread so it is ok to update our progress bar ( a UI view ) here
            txt_loading.setText("กำลัง Load ข้อมูลจาก Server  " + values[0] + "/"+total_mata);
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            completeSplash();
        }


        private String updateMetaData(String source) {
            List<NameValuePair> paramsList = new ArrayList<>();


            // Check your log cat for JSON reponse
            // Log.d("All Meta: ", json.toString());
            String id = "";
            String name = "";
            String ref = "";
            String value="";
            String remark="";
            int count = -1;
            int statuscode = 0;

            MetaBean metaBean;

            try {
                HttpGet httpRequest = new HttpGet(getString(R.string.url_get_meta));
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpRequest);

                statuscode = response.getStatusLine().getStatusCode();
                //Log.i("HttpResponse status ", String.valueOf(statuscode));

                if (HttpStatus.SC_OK == statuscode) {
                    // getting JSON string from URL
                    JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_get_meta) + "?source=" + source, paramsList);
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);
                    //Log.i("JSONObject status ", String.valueOf(success));

                    if (success == 1) {
                        // MetaData found
                        // Getting Array of MetaData
                        metaJson = json.getJSONArray(TAG_META);
                        metaList = new ArrayList<>();

                        // looping through All MetaData
                        for (int i = 0; i < metaJson.length(); i++) {
                            JSONObject c = metaJson.getJSONObject(i);
                            //Log.i("JSONObject c ", c.toString());
                            // Storing each json item in variable
                            id = c.getString(TAG_ID);
                            name = c.getString(TAG_NAME);
                            ref = c.getString(TAG_REF);
                            value = c.getString(TAG_VAL);
                            remark = c.getString(TAG_REMARK);
                            metaBean = new MetaBean(Integer.parseInt(id), name, ref, value,remark);

                            // adding MetaBean to ArrayList
                            metaList.add(metaBean);
                        }


                        metaDAO.cleanMeta(source);

                        count = metaDAO.importMeta(metaList, source);
                        //Log.i("metaDAO.importMeta -->", source + metaList.size());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return String.valueOf(count);
        }

        private String updateAgri() {
            List<NameValuePair> paramsList = new ArrayList<>();

            int count = -1;
            int statuscode = 0;

            AgriculturistBean bean;

            try {
                HttpGet httpRequest = new HttpGet(getString(R.string.url_get_agri));
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpRequest);

                statuscode = response.getStatusLine().getStatusCode();
                //Log.i("HttpResponse status ", String.valueOf(statuscode));

                if (HttpStatus.SC_OK == statuscode) {
                    // getting JSON string from URL
                    JSONObject json = jParser.getJSONFromUrl(getString(R.string.url_get_agri) , paramsList);
                    //Log.i("JSONObject url_get_agri", json.toString());
                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);
                    //Log.i("JSONObject status ", String.valueOf(success));

                    if (success == 1) {
                        // MetaData found
                        // Getting Array of MetaData
                        agriJson = json.getJSONArray("agri_s");
                        agriList = new ArrayList<>();

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

    }

}
