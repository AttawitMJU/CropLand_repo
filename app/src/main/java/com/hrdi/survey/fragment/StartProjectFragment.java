package com.hrdi.survey.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.adapter.LandUseListAdapter;
import com.hrdi.survey.control.LandUseDAO;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.swipemenu.SwipeMenu;
import com.hrdi.survey.swipemenu.SwipeMenuCreator;
import com.hrdi.survey.swipemenu.SwipeMenuItem;
import com.hrdi.survey.swipemenu.SwipeMenuListView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class StartProjectFragment extends Fragment implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, View.OnClickListener {

    // For Capture Image
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    final static String TAG_FRAGMENT = "STARTPROJECT_FRAGMENT";
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE1 = 101;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE2 = 102;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE3 = 103;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE1 = 201;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE2 = 202;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE3 = 203;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "HRDI_Pic";
    public static String survey_id = "";
    private static String landcode;
    // UI references
    EditText edt_Land_No, edit_Card_no, edit_FirstName, edt_LastName;
    ImageButton btn_LandCrop;
    SwipeMenuListView landUseListView;
    Button btn_Save;
    TextClock txt_DataTime;
    Activity activity;
    ArrayList<LandUseBean> landUseBeanList;
    LandUseDAO landUseDAO;
    SurveyDAO surveyDAO;
    SurveyBean surveyBean;
    LandUseListAdapter landUseListAdapter;
    private ImageView img_Preview1, img_Preview2, img_Preview3;
    private ImageButton img_btn_1, img_btn_2, img_btn_3;
    private Uri fileUri; // file url to store image/video
    private GetLandUseTask task;
    private UpdateStartProjectTask updateTask;


    public StartProjectFragment() {
    }

    /*
 * returning image / video
 */
    private static File getOutputMediaFile(int type, int i) {

        // External sdcard location
        File mediaStorageDir = new File(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // -->  /mnt/sdcard/ + IMAGE_DIRECTORY_NAME

        //File mediaStorageDir = new File("/storage/extSdCard/" );
        //Log.i("File", mediaStorageDir.toString());

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
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + landcode + "_" + i + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        landUseDAO = new LandUseDAO(activity);
        surveyDAO = new SurveyDAO(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            survey_id = getArguments().getString("survey_id");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_startproject, container, false);

        // Get All UI
        findViewsById(rootView);


        SwipeMenuCreator creator = getSwipeMenuCreator();

        // set creator
        landUseListView.setMenuCreator(creator);

        setListeners();


        return rootView;
    }

    private SwipeMenuCreator getSwipeMenuCreator() {
        // step 1. create a MenuCreator
        return new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem updateItem = new SwipeMenuItem(getActivity());
                // set item background
                updateItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                updateItem.setWidth(dp2px(90));
                // set item title
                updateItem.setTitle("Update");
                // set item title font size
                updateItem.setTitleSize(18);
                // set item title font color
                updateItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(updateItem);

            }
        };
    }

    /**
     * Map Input UI to Java Code
     */
    private void findViewsById(View rootView) {
        // แปลงเลขที่
        edt_Land_No = (EditText) rootView.findViewById(R.id.edt_Land_No);
        // ปุ่มค้นหาข้อมูลแปลง
        btn_LandCrop = (ImageButton) rootView.findViewById(R.id.btn_LandCrop);
        // หมายเลขบัตร
        edit_Card_no = (EditText) rootView.findViewById(R.id.edit_Card_no);
        // ชื่อ
        edit_FirstName = (EditText) rootView.findViewById(R.id.edit_FirstName);
        // นามสกุล
        edt_LastName = (EditText) rootView.findViewById(R.id.edt_LastName);

        landUseListView = (SwipeMenuListView) rootView.findViewById(R.id.list_landuse);

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

        // ปุ่มบันทึก
        btn_Save = (Button) rootView.findViewById(R.id.btn_Save);


        txt_DataTime = (TextClock) rootView.findViewById(R.id.textClock);

    }

    private void setListeners() {
        btn_LandCrop.setOnClickListener(this);
        img_btn_1.setOnClickListener(this);
        img_btn_2.setOnClickListener(this);
        img_btn_3.setOnClickListener(this);
        btn_Save.setOnClickListener(this);

        // step 2. listener item click event
        landUseListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //LandUseBean item = landUseBeanList.get(position);
                switch (index) {
                    case 0:
                        // open
                        // open(item);
                        //openSurvey(item);
                        break;
                    case 1:
                        // delete survey in database
                        //deleteSurvey(item);
                        //landUseBeanList.remove(position);
                        //landUseListAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        landUseListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        landUseListView.setOnItemClickListener(this);
        landUseListView.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_LandCrop) {
            // get Land Crop Information
            task = new GetLandUseTask(activity);
            task.execute((Void) null);

        } else if (view == img_btn_1) {
            // Take Picture1
            captureImage(1);
        } else if (view == img_btn_2) {
            // Take Picture2
            captureImage(2);
        } else if (view == img_btn_3) {
            // Take Picture3
            captureImage(3);
        } else if (view == btn_Save) {
            // Call Task to Add SQLite
            // task = new UpdateStartProjectTask(getActivity());
            // task.execute();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LandUseBean landUse = (LandUseBean) landUseListView.getItemAtPosition(position);

        if (landUse != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("selectedLandUse", landUse);
            SurveyPlantFragment surveyPlantFragment = new SurveyPlantFragment();
            surveyPlantFragment.setArguments(arguments);
            // surveyPlantFragment.show(getFragmentManager(),  SurveyPlantFragment.ARG_ITEM_ID);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        LandUseBean landUse = (LandUseBean) parent.getItemAtPosition(position);
        // Use AsyncTask to delete from database
        //landUseDAO.deleteLandUse(landUse);
        //landUseListAdapter.remove(landUse);

        return true;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private void openSurvey(LandUseBean landUseBean) {
        // TODO : Open Survey data for update

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

    public class GetLandUseTask extends AsyncTask<Void, Void, ArrayList<LandUseBean>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetLandUseTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<LandUseBean> doInBackground(Void... params) {

            ArrayList<LandUseBean> landUseList = landUseDAO.getLandUseListbyLandCode(edt_Land_No.getText().toString());
            Log.i("doInBackground -->", "Start" + landUseList.size());

            surveyBean = surveyDAO.getFarmerByLandCode(edt_Land_No.getText().toString());

            return landUseList;
        }

        @Override
        protected void onPostExecute(ArrayList<LandUseBean> landUseList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (surveyBean != null) {
                    edit_Card_no.setText(surveyBean.getCard_no());
                    edit_FirstName.setText(surveyBean.getFirstName());
                    edt_LastName.setText(surveyBean.getLastName());
                    landcode = edt_Land_No.getText().toString().trim();

                    if (surveyBean.getPicture1() != null) {
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, 1);
                        previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE1);
                    }
                    if (surveyBean.getPicture2() != null) {
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, 2);
                        previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE2);
                    }
                    if (surveyBean.getPicture3() != null) {
                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE, 3);
                        previewCapturedImage(CAMERA_CAPTURE_IMAGE_REQUEST_CODE3);
                    }


                }

                landUseBeanList = landUseList;
                if (landUseList != null) {
                    Log.i("landUseList != null ", "" + landUseList.size());
                    if (landUseList.size() != 0) {
                        landUseListAdapter = new LandUseListAdapter(activity, landUseList);
                        landUseListView.setAdapter(landUseListAdapter);
                    } else {
                        Toast.makeText(activity, "No Survey Records",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }


    /*
         Background Async Task to Create new Survey
    * */
    class UpdateStartProjectTask extends AsyncTask<String, String, String> {
        private final WeakReference<Activity> activityWeakRef;

        public UpdateStartProjectTask(Activity context) {
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
            }
        }


        @Override
        protected String doInBackground(String... params) {
            long result = 0;

            getGUI2Bean();

            Log.i("before update ", surveyBean.toString());
            result = surveyDAO.updateSurveyPic(surveyBean);

            return String.valueOf(result);
        }
    }

    private void getGUI2Bean() {
        if (img_Preview1.getTag() != null) {
            //surveyBean.setPicture1(img_Preview1.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_1" + ".jpg");
        }
        if (img_Preview2.getTag() != null) {
            //surveyBean.setPicture2(img_Preview2.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_2" + ".jpg");
        }
        if (img_Preview3.getTag() != null) {
            //surveyBean.setPicture3(img_Preview3.getTag().toString());
            surveyBean.setPicture1("IMG_" + landcode + "_3" + ".jpg");
        }

    }


}
