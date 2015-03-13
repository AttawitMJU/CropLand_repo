package com.hrdi.survey;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hrdi.survey.control.MetaDAO;
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

    LoadingTask loadingTask;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_META = "meta_s";
    private static final String TAG_ID = "meta_id";
    private static final String TAG_NAME = "meta_name";
    private static final String TAG_REF = "meta_ref";
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<MetaBean> metaList;
    // MetaData JSONArray
    JSONArray metaJson = null;
    String metaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        metaDAO = new MetaDAO(this);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar3);
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
            Log.i("Tutorial", "Starting task with url: " + params[0]);
            if (resourcesDontAlreadyExist()) {
                downloadResources();
            }
            // Perhaps you want to return something to your post execute
            return 1234;
        }

        private boolean resourcesDontAlreadyExist() {
            Log.i("metaDAO.countRecord", "" + metaDAO.countRecord("LAND"));
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
            int count = 20;
            int i = 1;
            try {

                // Update the progress bar after every step
                int progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                // Do some long loading things
                updateMetaData("card");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("doc");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("waterresource");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);


                updateMetaData("unit");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("market");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("fertilizer");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("fertilizercode");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("hormone");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("hormonetype");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("jobactivity");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("jobsource");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("extproject");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("planttype");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("plant");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("plantdetail");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("province");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("amphoe");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);

                updateMetaData("tambol");
                progress = (int) ((i++ / (float) count) * 100);
                publishProgress(progress);


                try { Thread.sleep(1000); } catch (InterruptedException ignore) {ignore.printStackTrace();}


            } catch (Exception e) {
                Log.e("updateMetaData ", e.toString());
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("onProgressUpdate", values.toString());
            progressBar.setProgress(values[0]); // This is ran on the UI thread so it is ok to update our progress bar ( a UI view ) here
            txt_loading.setText("กำลัง Load ข้อมูลจาก Server  " + values[0] + "%");
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

                            // Storing each json item in variable
                            id = c.getString(TAG_ID);
                            name = c.getString(TAG_NAME);
                            ref = c.getString(TAG_REF);

                            metaBean = new MetaBean(Integer.parseInt(id), name, ref, "");

                            // adding MetaBean to ArrayList
                            metaList.add(metaBean);
                        }


                        metaDAO.cleanMeta(source);
                        //Log.i("metaDAO.importMeta -->", source + metaList.size());
                        count = metaDAO.importMeta(metaList, source);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return String.valueOf(count);
        }

    }

}
