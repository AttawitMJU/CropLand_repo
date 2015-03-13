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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.SurveyBean;

import java.io.File;
import java.lang.ref.WeakReference;

public class SurveyEtcFragment extends Fragment implements View.OnClickListener {


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    final static String TAG_FRAGMENT = "SURVEY_FRAGMENT";
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 = 101;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 = 102;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 = 103;

    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE1 = 201;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE2 = 202;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE3 = 203;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "HRDI_Pic";
    private static String landcode;
    private Uri fileUri; // file url to store image/video
    private EditText edt_activity1, edt_outcome1, edt_survive1, edt_repeat1;
    private EditText edt_activity2, edt_outcome2, edt_survive2, edt_repeat2;
    private EditText edt_activity3, edt_outcome3, edt_survive3, edt_repeat3;
    private EditText edt_org1, edt_org2, edt_org3;
    private EditText edt_problem1, edt_problem2, edt_problem3;
    private EditText edt_request1, edt_request2, edt_request3;
    private ImageView img_Preview1, img_Preview2, img_Preview3;
    private ImageButton img_btn_1, img_btn_2, img_btn_3;
    private Button btn_next;
    private SurveyBean surveyBean;
    private SurveyBean surveyBeanUpdate;
    private SurveyDAO surveyDAO;
    private UpdateSurveyTask task;

    public SurveyEtcFragment() {
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

        surveyDAO = new SurveyDAO(getActivity());

        Bundle bundle = this.getArguments();
        surveyBean = bundle.getParcelable("surveyBean");
        String action = bundle.getString("action");

        Log.i("surveyBean #2", surveyBean.toString());
        landcode = surveyBean.getLand_No();


        View rootView = inflater.inflate(R.layout.fragment_survey_etc, container, false);


        // Get All UI
        findViewsById(rootView);

        // Set Spinner/List Data
        // setListItemAdapter();

        // set Button Listeners
        setListeners();

        if ("update".equals(action)) {
            surveyBeanUpdate = surveyDAO.getSurveyEtcByID(surveyBean.getSurvey_id());
            Log.i("getSurveyEtcByID *** ", surveyBeanUpdate.toString());
            setUpdateData(surveyBeanUpdate);
        }

        return rootView;
    }

    private void setUpdateData(SurveyBean bean) {

        edt_activity1.setText(bean.getActivity1());
        edt_outcome1.setText(bean.getOutcome1());
        edt_survive1.setText(bean.getSurvive1());
        edt_repeat1.setText(bean.getRepeat1());

        edt_activity2.setText(bean.getActivity2());
        edt_outcome2.setText(bean.getOutcome2());
        edt_survive2.setText(bean.getSurvive2());
        edt_repeat2.setText(bean.getRepeat2());

        edt_activity3.setText(bean.getActivity3());
        edt_outcome3.setText(bean.getOutcome3());
        edt_survive3.setText(bean.getSurvive3());
        edt_repeat3.setText(bean.getRepeat3());

        edt_org1.setText(bean.getOrg1());
        edt_org2.setText(bean.getOrg2());
        edt_org3.setText(bean.getOrg3());

        edt_problem1.setText(bean.getProblem1());
        edt_problem2.setText(bean.getProblem2());
        edt_problem3.setText(bean.getProblem3());

        edt_request1.setText(bean.getRequest1());
        edt_request2.setText(bean.getRequest2());
        edt_request3.setText(bean.getRequest3());


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
        img_btn_1.setOnClickListener(this);
        img_btn_2.setOnClickListener(this);
        img_btn_3.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    /**
     * Map Input UI to Java Code
     */
    private void findViewsById(View rootView) {

        edt_activity1 = (EditText) rootView.findViewById(R.id.edt_activity1);
        edt_outcome1 = (EditText) rootView.findViewById(R.id.edt_outcome1);
        edt_survive1 = (EditText) rootView.findViewById(R.id.edt_survive1);
        edt_repeat1 = (EditText) rootView.findViewById(R.id.edt_repeat1);

        edt_activity2 = (EditText) rootView.findViewById(R.id.edt_activity2);
        edt_outcome2 = (EditText) rootView.findViewById(R.id.edt_outcome2);
        edt_survive2 = (EditText) rootView.findViewById(R.id.edt_survive2);
        edt_repeat2 = (EditText) rootView.findViewById(R.id.edt_repeat2);

        edt_activity3 = (EditText) rootView.findViewById(R.id.edt_activity3);
        edt_outcome3 = (EditText) rootView.findViewById(R.id.edt_outcome3);
        edt_survive3 = (EditText) rootView.findViewById(R.id.edt_survive3);
        edt_repeat3 = (EditText) rootView.findViewById(R.id.edt_repeat3);

        edt_org1 = (EditText) rootView.findViewById(R.id.edt_org1);
        edt_org2 = (EditText) rootView.findViewById(R.id.edt_org2);
        edt_org3 = (EditText) rootView.findViewById(R.id.edt_org3);

        edt_problem1 = (EditText) rootView.findViewById(R.id.edt_problem1);
        edt_problem2 = (EditText) rootView.findViewById(R.id.edt_problem2);
        edt_problem3 = (EditText) rootView.findViewById(R.id.edt_problem3);

        edt_request1 = (EditText) rootView.findViewById(R.id.edt_request1);
        edt_request2 = (EditText) rootView.findViewById(R.id.edt_request2);
        edt_request3 = (EditText) rootView.findViewById(R.id.edt_request3);

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
        Fragment fragment = new SurveyLandUseFragment();

        FragmentManager fragmentManager = getFragmentManager();


        // Send parameter surveybaen to next page
        Bundle surveyDataBundle = new Bundle();

        surveyDataBundle.putParcelable("surveyBean", surveyBean);
        Log.i("surveyBean #3", surveyBean.toString());

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
        surveyBean.setActivity1(edt_activity1.getText().toString());
        surveyBean.setOutcome1(edt_outcome1.getText().toString());
        surveyBean.setSurvive1(edt_survive1.getText().toString());
        surveyBean.setRepeat1(edt_repeat1.getText().toString());

        surveyBean.setActivity2(edt_activity2.getText().toString());
        surveyBean.setOutcome2(edt_outcome2.getText().toString());
        surveyBean.setSurvive2(edt_survive2.getText().toString());
        surveyBean.setRepeat2(edt_repeat2.getText().toString());

        surveyBean.setActivity3(edt_activity3.getText().toString());
        surveyBean.setOutcome3(edt_outcome3.getText().toString());
        surveyBean.setSurvive3(edt_survive3.getText().toString());
        surveyBean.setRepeat3(edt_repeat3.getText().toString());

        surveyBean.setOrg1(edt_org1.getText().toString());
        surveyBean.setOrg2(edt_org2.getText().toString());
        surveyBean.setOrg3(edt_org3.getText().toString());

        surveyBean.setProblem1(edt_problem1.getText().toString());
        surveyBean.setProblem2(edt_problem2.getText().toString());
        surveyBean.setProblem3(edt_problem3.getText().toString());

        surveyBean.setRequest1(edt_request1.getText().toString());
        surveyBean.setRequest2(edt_request2.getText().toString());
        surveyBean.setRequest3(edt_request3.getText().toString());


        if (img_Preview1.getTag() != null) {
           // surveyBean.setPicture1(img_Preview1.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_1"  + ".jpg");
        }
        if (img_Preview2.getTag() != null) {
            //surveyBean.setPicture2(img_Preview2.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_2"  + ".jpg");
        }
        if (img_Preview3.getTag() != null) {
            //surveyBean.setPicture3(img_Preview3.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_3"  + ".jpg");
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

            return String.valueOf(result);
        }
    }
}
