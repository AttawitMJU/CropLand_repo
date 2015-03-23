package com.hrdi.survey.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.adapter.SurveyListAdapter;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.swipemenu.SwipeMenu;
import com.hrdi.survey.swipemenu.SwipeMenuCreator;
import com.hrdi.survey.swipemenu.SwipeMenuItem;
import com.hrdi.survey.swipemenu.SwipeMenuListView;
import com.hrdi.survey.util.ConnectionDetector;
import com.hrdi.survey.util.JSONParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SurveyDataListFragment extends Fragment
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    public static final String ARG_ITEM_ID = "employee_list";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    final static String TAG_FRAGMENT = "SURVEY_DATA_FRAGMENT";
    private static final String IMAGE_DIRECTORY_NAME = "HRDI_Pic";
    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ID = "surveyid";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    Activity activity;
    //ListView surveyListView;
    SwipeMenuListView surveyListView;
    ArrayList<SurveyBean> surveyListShow;
    ArrayList<SurveyBean> surveyListSend;
    SurveyListAdapter surveyListAdapter;
    SurveyDAO surveyDAO;
    ImageButton btn_upload;
    Button btn_all, btn_waiting;
    SurveyBean surveyBean;
    // JSON Parser object
    private JSONParser jsonParser;
    private ProgressDialog pDialog;
    private GetSurveyTask getTask;
    private SendSurveyTask sendTask;
    private String status;

    public SurveyDataListFragment() {
    }

    @Override
    public void onClick(View view) {
        if (view == btn_all) {
            if (surveyListAdapter != null) {
                surveyListAdapter.clear();
            }
            status = "";    // reset Status
            getTask = new GetSurveyTask(getActivity());
            getTask.execute();

        }
        if (view == btn_waiting) {
            if (surveyListAdapter != null) {
                surveyListAdapter.clear();
            }
            status = "waiting";
            getTask = new GetSurveyTask(getActivity());
            getTask.execute();

        } else if (view == btn_upload) {
            status = "waiting";
            // Check Connection
            ConnectionDetector cnt = new ConnectionDetector(getActivity());
            //Log.i("Connect", "" + cnt.isNetworkAvailable());
            if (cnt.isNetworkAvailable()) {

                surveyListSend = surveyDAO.getSurveyList(status);
                sendTask = new SendSurveyTask(getActivity());
                sendTask.execute();

                if (surveyListAdapter != null) {
                    surveyListAdapter.clear();
                }

            } else {
                Toast.makeText(getActivity(), getString(R.string.connection_off),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SurveyBean item = surveyListShow.get(position);
        openSurvey(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        SurveyBean surveyBean = (SurveyBean) parent.getItemAtPosition(position);
        // Use AsyncTask to delete from database
        surveyDAO.deleteSurvey(surveyBean);
        surveyListAdapter.remove(surveyBean);

        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        surveyDAO = new SurveyDAO(activity);
    }

    private void setListeners() {
        btn_upload.setOnClickListener(this);
        btn_all.setOnClickListener(this);
        btn_waiting.setOnClickListener(this);

        // step 2. listener item click event
        surveyListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Log.i("position   ....", "" + position);
                SurveyBean item = surveyListShow.get(position);

                switch (index) {
                    case 0:     // open button
                        // open(item);
                        openSurvey(item);
                        break;
                    case 1:     // delete button
                        // delete survey in database
                        deleteSurvey(item);
                        surveyListShow.remove(position);
                        surveyListAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        surveyListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        surveyListView.setOnItemClickListener(this);
        surveyListView.setOnItemLongClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        status = "waiting";
        View rootView = inflater.inflate(R.layout.fragment_survey_data_list, container, false);
        findViewsById(rootView);

        getTask = new GetSurveyTask(activity);
        getTask.execute((Void) null);

        SwipeMenuCreator creator = getSwipeMenuCreator();
        surveyListView.setMenuCreator(creator);

        setListeners();

        return rootView;
    }

    private SwipeMenuCreator getSwipeMenuCreator() {
        // step 1. create a MenuCreator
        return new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
    }


    private void findViewsById(View view) {
        //surveyListView = (ListView) view.findViewById(R.id.list_survey);
        surveyListView = (SwipeMenuListView) view.findViewById(R.id.list_survey);
        btn_upload = (ImageButton) view.findViewById(R.id.btn_upload);
        btn_all = (Button) view.findViewById(R.id.btn_all);
        btn_waiting = (Button) view.findViewById(R.id.btn_waiting);
    }

    public void updateView() {
        getTask = new GetSurveyTask(activity);
        getTask.execute((Void) null);
    }

    @Override
    public void onResume() {
        //getActivity().setTitle(R.string.app_name);
        //getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int deleteSurvey(SurveyBean surveyBean) {
        surveyDAO.deleteSurvey(surveyBean);
        //surveyListAdapter.remove(surveyBean);
        return 0;
    }

    private void openSurvey(SurveyBean surveyBean) {

        Log.i("openSurvey ....", surveyBean.getSurvey_id());
        SurveyFragment fragment = new SurveyFragment();

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();

            // Send parameter surveybaen to next page
            Bundle surveyDataBundle = new Bundle();

            surveyDataBundle.putString("surveyBeanUpdateID", surveyBean.getSurvey_id());
            surveyDataBundle.putString("action", "update");

            fragment.setArguments(surveyDataBundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment, TAG_FRAGMENT).addToBackStack(null).commit();
        }

    }

    public class SendSurveyTask extends AsyncTask<Void, Void, String> {

        private final WeakReference<Activity> activityWeakRef;
        private List<NameValuePair> params;
        private List<NameValuePair> paramsLandUse;

        public SendSurveyTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected String doInBackground(Void... params) {
            String status = "";

            status = sendData();

            return status;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Sending Data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
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
                } else {
                    Toast.makeText(activityWeakRef.get(), getString(R.string.connection_off),
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        private int checkServerStatus(String url) {
            int statuscode = 0;
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpRequest = new HttpGet(url);

                HttpResponse responseReq = httpclient.execute(httpRequest);
                statuscode = responseReq.getStatusLine().getStatusCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return statuscode;
        }


        private String httpResponse2String(HttpResponse response) {
            InputStream is = null;
            String line = null;
            try {
                HttpEntity entity = response.getEntity();
                is = entity.getContent();

                //Log.i("HttpEntity is", is.toString());


                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder strB = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    strB.append(line);
                }
                line = strB.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return line;
        }

        private String sendData() {
            jsonParser = new JSONParser();
            int count = 0;
            int statuscode = 0;
            String lastID = "";
            Log.i("========", "================");
            Log.i("==surveyListSend==", "" + surveyListSend.size());
            try {

                statuscode = checkServerStatus(getString(R.string.url_send_survey));
                Log.i("HttpResponse statuscode", "" + statuscode);

                if (HttpStatus.SC_OK == statuscode) {

                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(getString(R.string.url_send_survey));


                    for (SurveyBean sb : surveyListSend) {

                        Log.i("========", "================");
                        Log.i("surveyListSend i=", " " + count + "   id=" + sb.getSurvey_id());
                        Log.i("SurveyBean", sb.toString());

                        params = surveyDAO.setSuevey2Parameter(sb);
                        //Log.i("params sb", sb.toString());

                        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

                        HttpResponse response = httpclient.execute(httpPost);

                        String line = httpResponse2String(response);
Log.e("HttpResponse Line", line);
                        // getting JSON string from URL
                        JSONObject json = new JSONObject(line);

                        // jsonParser.getJSONFromUrl(getString(R.string.url_send_survey), params);
                        Log.i("json-->", "" + json.toString());

                        // Checking for SUCCESS TAG
                        if (json.getString(KEY_SUCCESS) != null) {
                            String res = json.getString(KEY_SUCCESS);
                            if (Integer.parseInt(res) == 1) {

                                // send data success
                                lastID = json.getString(KEY_ID);

                                surveyDAO.updateStatus(sb, KEY_SUCCESS);

                                Log.i("sendLandUseData", "  " + lastID);
                                sendLandUseData(lastID, sb.getSurvey_id());
                            }
                        }

                        if (sb.getPicture1() != null) {
                            uploadFile(getString(R.string.url_upload), sb.getLand_No(), 1);
                        }
                        if (sb.getPicture2() != null) {
                            uploadFile(getString(R.string.url_upload), sb.getLand_No(), 2);
                        }
                        if (sb.getPicture3() != null) {
                            uploadFile(getString(R.string.url_upload), sb.getLand_No(), 3);
                        }
                        count++;

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return String.valueOf(count);
        }

        private String uploadFile(String url_Upload, String landCode, int no) {
            Log.i("Start uploadFile ", landCode + "  " + no);
            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 5 * 1024 * 1024;

            int resCode = 0;
            String resMessage = "";

            try {
                Log.i("getOutputMediaFile ", landCode + "  " + no);

                File file = getOutputMediaFile(landCode, 1, no);

                Log.i("file.isFile() ", landCode + "  " + file.isFile());

                if (file.isFile()) {

                    Log.i("File getName ", file.getName());
                    Log.i("File getPath", file.getPath());
                    // open a URL connection to the server php
                    FileInputStream fileInputStream = new FileInputStream(file);
                    URL url = new URL(url_Upload);


                    // Open a HTTP  connection to  the URL
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true); // Allow Inputs
                    conn.setDoOutput(true); // Allow Outputs
                    conn.setUseCaches(false); // Don't use a Cached Copy
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    //conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    DataOutputStream outputStream = new DataOutputStream(conn
                            .getOutputStream());
                    outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                    outputStream
                            .writeBytes("Content-Disposition: form-data; name=\"fileUpload\";filename=\""
                                    + file.getName() + "\"" + lineEnd);
                    outputStream.writeBytes(lineEnd);

                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    // Read file
                    Log.i("Read file ", "" + bufferSize);

                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                    while (bytesRead > 0) {
                        outputStream.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }

                    outputStream.writeBytes(lineEnd);
                    outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    // Response Code and  Message
                    resCode = conn.getResponseCode();
                    if (resCode == HttpURLConnection.HTTP_OK) {
                        InputStream is = conn.getInputStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();

                        int read = 0;
                        while ((read = is.read()) != -1) {
                            bos.write(read);
                        }
                        byte[] result = bos.toByteArray();
                        bos.close();

                        resMessage = new String(result);

                    }

                    Log.i("resCode=", Integer.toString(resCode));
                    Log.i("resMessage=", resMessage.toString());

                    fileInputStream.close();
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resMessage.toString();
        }

        /*
         * returning image / video
        */
        private File getOutputMediaFile(String landcode, int type, int i) {

            // External sdcard location
            File mediaStorageDir = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    IMAGE_DIRECTORY_NAME);

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                            + IMAGE_DIRECTORY_NAME + " directory");
                    return null;
                }
            }

            File mediaFile;
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator
                        + "IMG_" + landcode + "_" + i + ".jpg");

                Log.i("MEDIA_TYPE_IMAGE-->", mediaStorageDir.getPath() + File.separator
                        + "IMG_" + landcode + "_" + i + ".jpg");

            } else if (type == MEDIA_TYPE_VIDEO) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator
                        + "VID_" + landcode + "_" + i + ".mp4");
            } else {
                return null;
            }

            return mediaFile;
        }


        private void sendLandUseData(String lastID, String surveyID) {
            JSONParser jsonParser = new JSONParser();
            int statuscode;
            ArrayList<LandUseBean> landUseBeans = surveyDAO.getLandUseInSurvey(surveyID);
            Log.i("---", "---");
            Log.i(" ArrayList<LandUseBean>", "" + landUseBeans.size());
            try {

                statuscode = checkServerStatus(getString(R.string.url_send_landuse));
                // Log.i("statuscode", "" + statuscode+" "+getString(R.string.url_send_survey));
                if (HttpStatus.SC_OK == statuscode) {

                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(getString(R.string.url_send_landuse));


                    for (LandUseBean bean : landUseBeans) {

                        bean.setSurvey_ID(lastID);      // Update new ID  from MSSQL
                        Log.i("LandUseBean to server", bean.toString());

                        paramsLandUse = surveyDAO.setLandUse2Parameter(bean);
                        Log.i("paramsLandUse to server", paramsLandUse.toString());


                        httpPost.setEntity(new UrlEncodedFormEntity(paramsLandUse, "UTF-8"));

                        HttpResponse response = httpclient.execute(httpPost);

                        String line = httpResponse2String(response);
                        Log.i("httpResponse2String -->", line);

                        // getting JSON string from URL
                        JSONObject json = new JSONObject(line);


                        // Checking for SUCCESS TAG
                        if (json.getString(KEY_SUCCESS) != null) {
                            String res = json.getString(KEY_SUCCESS);
                            if (Integer.parseInt(res) == 1) {
                                // send data success

                            }
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public class GetSurveyTask extends AsyncTask<Void, Void, ArrayList<SurveyBean>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetSurveyTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<SurveyBean> doInBackground(Void... params) {
            ArrayList<SurveyBean> surveyList = surveyDAO.getSurveyList2Show(status);
            return surveyList;
        }

        @Override
        protected void onPostExecute(ArrayList<SurveyBean> surveyList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                surveyListShow = surveyList;
                if (surveyList != null) {
                    if (surveyList.size() != 0) {
                        surveyListAdapter = new SurveyListAdapter(activity, surveyList);
                        surveyListView.setAdapter(surveyListAdapter);
                    } else {

                        Toast.makeText(activity, "No Survey Records",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
