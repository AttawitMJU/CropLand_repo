package com.hrdi.survey.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.adapter.ActListAdapter;
import com.hrdi.survey.adapter.EtcListAdapter;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.SurveyActivityBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.model.SurveyDetailEtcBean;
import com.hrdi.survey.swipemenu.SwipeMenu;
import com.hrdi.survey.swipemenu.SwipeMenuListView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SurveyEtcFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    final static String TAG_FRAGMENT = "SURVEY_ETC_FRAGMENT";
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 = 101;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 = 102;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 = 103;

    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE1 = 201;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE2 = 202;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE3 = 203;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "HRDI_Pic";

    private static String surveyID, landcode, cardno;

    private Uri fileUri; // file url to store image/video

    //private EditText edt_org1, edt_org2, edt_org3;
    //private EditText edt_problem1, edt_problem2, edt_problem3;
    // private EditText edt_request1, edt_request2, edt_request3;
    private ImageView img_Preview1, img_Preview2, img_Preview3;
    private ImageButton img_btn_1, img_btn_2, img_btn_3,
            btn_addActivity, btn_support, btn_problem, btn_want;
    private TextView txt_activity;
    private Button btn_next;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;

    Activity activity;
    private SwipeMenuListView act_Swipe, support_Swipe, want_Swipe, problem_Swipe;
    ArrayList<SurveyBean> actListShow, supportListShow, wantListShow, problemListShow;
    ActListAdapter actListAdapter;
    EtcListAdapter supportAdapter, wantAdapter, problemAdapter;

    private SurveyBean surveyBean;
    private SurveyDAO surveyDAO;
    private UpdateSurveyTask task;

    SurveyActFragment activity_showDialog;

    public SurveyEtcFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        surveyDAO = new SurveyDAO(activity);
    }

    /*
             * returning image / video
             */
    private static File getOutputMediaFile(int type, int i) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);
        // -->  /mnt/sdcard/ + IMAGE_DIRECTORY_NAME


        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        // String timeStamp = new SimpleDateFormat("yyMMddHHmmss", Locale.getDefault()).format(new Date());

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + landcode + "_" + i + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + landcode + "_" + i + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle bundle = this.getArguments();
        surveyID = bundle.getString("surveyID");
        String action = bundle.getString("action");
        landcode= bundle.getString("landcode");
        cardno= bundle.getString("cardno");


        View rootView = inflater.inflate(R.layout.fragment_survey_etc, container, false);


        // Get All UI
        findViewsById(rootView);

        // Set Spinner/List Data
        // setListItemAdapter();

        // set Button Listeners
        setListeners();

        if ("update".equals(action)) {
            surveyBean = surveyDAO.getSurveyByID(surveyID);
            Log.i("getSurveyEtcByID *** ", surveyBean.toString());
            setUpdateData(surveyBean);
        }

        showSurveyActivityList(surveyID);
        showSurveyRemarkList(surveyID, "support");
        showSurveyRemarkList(surveyID, "problem");
        showSurveyRemarkList(surveyID, "want");


        return rootView;
    }

    private int showSurveyRemarkList(String surveyID, String etc) {
        int i = 0;
        Log.i("surveyID", "showSurveyRemarkList....." + surveyID);
        ArrayList<SurveyDetailEtcBean> etcList = surveyDAO.getSurveyEtc(surveyID, etc);
        Log.i("surveyList", "showSurveyRemarkList....." + etcList.size());
        if (etcList != null) {
            if (etcList.size() != 0) {
                i = etcList.size();
                if("support".equals(etc)) {
                    supportAdapter = new EtcListAdapter(activity, etcList);
                    support_Swipe.setAdapter(supportAdapter);

                    if (i > 1) {
                        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, i * 80);
                        linearLayout2.setLayoutParams(parms);
                    }
                }
                else if("problem".equals(etc)) {
                    problemAdapter = new EtcListAdapter(activity, etcList);
                    problem_Swipe.setAdapter(problemAdapter);

                    if (i > 1) {
                        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, i * 80);
                        linearLayout3.setLayoutParams(parms);
                    }
                }else if("want".equals(etc)) {
                    wantAdapter = new EtcListAdapter(activity, etcList);
                    want_Swipe.setAdapter(wantAdapter);

                    if (i > 1) {
                        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, i * 80);
                        linearLayout4.setLayoutParams(parms);
                    }
                }
            } else {

                //Toast.makeText(activity, "No Survey Records",
                //        Toast.LENGTH_LONG).show();
            }

        }
        return i;
    }

    private int showSurveyActivityList(String surveyID) {

        int i = 0;
        Log.i("surveyID", "showSurveyActivityList....." + surveyID);
        ArrayList<SurveyActivityBean> surveyList = surveyDAO.getSurveyAcitvity(surveyID);
        Log.i("surveyList", "showSurveyActivityList....." + surveyList.size());
        if (surveyList != null) {
            if (surveyList.size() != 0) {
                i = surveyList.size();
                actListAdapter = new ActListAdapter(activity, surveyList);
                act_Swipe.setAdapter(actListAdapter);

                if (i > 1) {
                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, i * 140);
                    linearLayout1.setLayoutParams(parms);
                }
            } else {

                //Toast.makeText(activity, "No Survey Records",
                //        Toast.LENGTH_LONG).show();
            }

        }
        return i;
    }

    private void setUpdateData(SurveyBean bean) {

        if (bean.getPicture1() != null) {
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, 1);
            previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);
        }
        if (bean.getPicture2() != null) {
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, 2);
            previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE2);
        }
        if (bean.getPicture3() != null) {
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, 3);
            previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE3);
        }
    }

    private void setListeners() {
        txt_activity.setOnClickListener(this);
        img_btn_1.setOnClickListener(this);
        img_btn_2.setOnClickListener(this);
        img_btn_3.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_addActivity.setOnClickListener(this);
        btn_support.setOnClickListener(this);
        btn_problem.setOnClickListener(this);
        btn_want.setOnClickListener(this);


        // step 2. listener item click event
        act_Swipe.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Log.i("position   ....", "" + position);
                SurveyBean item = actListShow.get(position);

                switch (index) {
                    case 0:     // open button
                        // openSurvey(item);
                        break;
                    case 1:     // delete button
                        //deleteSurvey(item);
                        actListShow.remove(position);
                        actListAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        act_Swipe.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        act_Swipe.setOnItemClickListener(this);
        act_Swipe.setOnItemLongClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //SurveyBean item = actListShow.get(position);
        // openSurvey(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //SurveyBean surveyBean = (SurveyBean) parent.getItemAtPosition(position);
        // Use AsyncTask to delete from database
        //surveyDAO.deleteSurvey(surveyBean);
        //actListAdapter.remove(surveyBean);

        return true;
    }

    /**
     * Map Input UI to Java Code
     */
    private void findViewsById(View rootView) {
        linearLayout1 = (LinearLayout) rootView.findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) rootView.findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) rootView.findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) rootView.findViewById(R.id.linearLayout4);



        txt_activity = (TextView) rootView.findViewById(R.id.txt_activity);

        btn_addActivity = (ImageButton) rootView.findViewById(R.id.btn_addActivity);
        btn_support = (ImageButton) rootView.findViewById(R.id.btn_support);
        btn_problem = (ImageButton) rootView.findViewById(R.id.btn_problem);
        btn_want = (ImageButton) rootView.findViewById(R.id.btn_want);

        act_Swipe = (SwipeMenuListView) rootView.findViewById(R.id.list_activity);

        support_Swipe= (SwipeMenuListView) rootView.findViewById(R.id.list_support);
        problem_Swipe= (SwipeMenuListView) rootView.findViewById(R.id.list_problem);
        want_Swipe= (SwipeMenuListView) rootView.findViewById(R.id.list_want);


        // ภาพ1
        img_Preview1 = (ImageView) rootView.findViewById(R.id.img_Preview1);
        // ปุ่มถ่ายรูป1
        img_btn_1 = (ImageButton) rootView.findViewById(R.id.img_btn_1);

        // ภาพ2
        img_Preview2 = (ImageView) rootView.findViewById(R.id.img_Preview2);
        // ปุ่มถ่ายรูป1
        img_btn_2 = (ImageButton) rootView.findViewById(R.id.img_btn_2);

        // ภาพ1
        img_Preview3 = (ImageView) rootView.findViewById(R.id.img_Preview3);
        // ปุ่มถ่ายรูป1
        img_btn_3 = (ImageButton) rootView.findViewById(R.id.img_btn_3);

        btn_next = (Button) rootView.findViewById(R.id.btn_next);

    }


    @Override
    public void onClick(View view) {
        if (view == img_btn_1) {
            // Take Picture1
            captureImage(1);

        } else if (view == img_btn_2) {
            // Take Picture2
            captureImage(2);
        } else if (view == img_btn_3) {
            // Take Picture3
            captureImage(3);
        } else if (view == btn_next) {
            updateSurveyEtc();
            goNextPage();
        } else if (view == txt_activity) {
            showSurveyActivityList(surveyID);

        } else if (view == btn_addActivity) {
            SurveyActFragment fragment = new SurveyActFragment();
            FragmentManager fragmentManager = getFragmentManager();

            // Send parameter surveybaen to next page
            Bundle surveyDataBundle = new Bundle();
            surveyDataBundle.putString("surveyID", surveyID);
            surveyDataBundle.putString("landcode", landcode);
            surveyDataBundle.putString("cardno", cardno);
            fragment.setArguments(surveyDataBundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

        } else if (view == btn_support) {
            SurveyRemarkFragment fragment = new SurveyRemarkFragment();
            FragmentManager fragmentManager = getFragmentManager();

            // Send parameter surveybaen to next page
            Bundle bundle = new Bundle();
            bundle.putString("surveyID", surveyID);
            bundle.putString("action", "support");
            bundle.putString("landcode", landcode);
            bundle.putString("cardno", cardno);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

        } else if (view == btn_problem) {
            SurveyRemarkFragment fragment = new SurveyRemarkFragment();
            FragmentManager fragmentManager = getFragmentManager();

            // Send parameter surveybaen to next page
            Bundle bundle = new Bundle();
            bundle.putString("surveyID", surveyID);
            bundle.putString("action", "problem");
            bundle.putString("landcode", landcode);
            bundle.putString("cardno", cardno);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

        } else if (view == btn_want) {
            SurveyRemarkFragment fragment = new SurveyRemarkFragment();
            FragmentManager fragmentManager = getFragmentManager();

            // Send parameter surveybaen to next page
            Bundle bundle = new Bundle();
            bundle.putString("surveyID", surveyID);
            bundle.putString("action", "want");
            bundle.putString("landcode", landcode);
            bundle.putString("cardno", cardno);
            fragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

        }
    }

    private void updateSurveyEtc() {
        // Call Task to Add SQLite
        task = new UpdateSurveyTask(getActivity());
        task.execute();
    }


    private void goNextPage() {
        // Popup Insert complete
        // Add to SQLite
        SurveyLandUseFragment fragment = new SurveyLandUseFragment();
        FragmentManager fragmentManager = getFragmentManager();

        // Send parameter surveybaen to next page
        Bundle surveyDataBundle = new Bundle();

        surveyDataBundle.putString("surveyID", surveyID);
        surveyDataBundle.putString("landcode", landcode);
        surveyDataBundle.putString("cardno",cardno);
        fragment.setArguments(surveyDataBundle);

        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).addToBackStack(null).commit();
    }

    /*
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    private void captureImage(int i) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, i);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        int request_code = 0;
        if (1 == i) {
            request_code = CAMERA_CAPTURE_IMAGE_REQUEST_CODE1;
        } else if (2 == i) {
            request_code = CAMERA_CAPTURE_IMAGE_REQUEST_CODE2;
        } else if (3 == i) {
            request_code = CAMERA_CAPTURE_IMAGE_REQUEST_CODE3;
        }
        startActivityForResult(intent, request_code);
    }

    /*
     @Override
     protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
     }
    */


    /*
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", fileUri);
    }


    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE1) {
            if (resultCode == Activity.RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE2) {
            if (resultCode == Activity.RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE2);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE3) {
            if (resultCode == Activity.RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE3);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getActivity(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getActivity(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE1) {
            if (resultCode == Activity.RESULT_OK) {
                // video successfully recorded
                // preview the recorded video

                // previewVideo();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled recording
                Toast.makeText(getActivity(),
                        "User cancelled video recording", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to record video
                Toast.makeText(getActivity(),
                        "Sorry! Failed to record video", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /*
     * Display image from a path to ImageView
     */
    private void previewCapturedImage(int request_code) {
        try {
            // hide video preview
            // videoPreview.setVisibility(View.GONE);

            img_Preview1.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
            if (CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 == request_code) {
                img_Preview1.setImageBitmap(bitmap);
                img_Preview1.setTag(fileUri.getPath());
            } else if (CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 == request_code) {
                img_Preview2.setImageBitmap(bitmap);
                img_Preview2.setTag(fileUri.getPath());
            } else if (CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 == request_code) {
                img_Preview3.setImageBitmap(bitmap);
                img_Preview3.setTag(fileUri.getPath());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /*
 * Previewing recorded video
 */
    private void previewVideo() {
        try {
            // hide image preview
            img_Preview1.setVisibility(View.GONE);

            //videoPreview.setVisibility(View.VISIBLE);
            //videoPreview.setVideoPath(fileUri.getPath());
            // start playing
            //videoPreview.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ------------ Helper Methods ----------------------
     */

	/*
     * Creating file uri to store image/video
	 */
    public Uri getOutputMediaFileUri(int type, int i) {
        return Uri.fromFile(getOutputMediaFile(type, i));
    }


    /*
        update SurveyBean Attribute with new GUI value
     */
    private void getGUI2Bean() {
        surveyBean.setSurvey_id(surveyID);

        if (img_Preview1.getTag() != null) {
            // surveyBean.setPicture1(img_Preview1.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_1" + ".jpg");
            Log.i("img_Preview1.getTag()", surveyBean.getPicture1());
        }
        if (img_Preview2.getTag() != null) {
            //surveyBean.setPicture2(img_Preview2.getTag().toString());
            surveyBean.setPicture2("IMG_" + landcode + "_2" + ".jpg");
        }
        if (img_Preview3.getTag() != null) {
            //surveyBean.setPicture3(img_Preview3.getTag().toString());
            surveyBean.setPicture3("IMG_" + landcode + "_3" + ".jpg");
        }


    }


    /*
      Background Async Task to Create new Survey
 * */
    class UpdateSurveyTask extends AsyncTask<String, String, String> {
        private final WeakReference<Activity> activityWeakRef;

        public UpdateSurveyTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }


        @Override
        protected void onPostExecute(String s) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {

                if (!"-1".equals(s)) {

                    //txt_hidden_id.setText(String.valueOf(s));
                    Toast.makeText(activityWeakRef.get(), "บันทึกข้อมูลเรียบร้อย ",
                            Toast.LENGTH_LONG).show();
                }
            }
        }


        @Override
        protected String doInBackground(String... params) {
            getGUI2Bean();


            Log.i("before update ", surveyBean.toString());

            long result = surveyDAO.updateSurveyEtc(surveyBean);
            Log.i("after update ", ""+result );

            SurveyBean surveyBeanXX = surveyDAO.getSurveyByID(surveyBean.getSurvey_id());
            Log.i("after update ", ""+surveyBeanXX.toString() );
            return String.valueOf(result);
        }
    }

}
