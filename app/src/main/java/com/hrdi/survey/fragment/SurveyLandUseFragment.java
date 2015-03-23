package com.hrdi.survey.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.adapter.LandUseListAdapter;
import com.hrdi.survey.control.LandUseDAO;
import com.hrdi.survey.model.LandUseBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.swipemenu.SwipeMenu;
import com.hrdi.survey.swipemenu.SwipeMenuCreator;
import com.hrdi.survey.swipemenu.SwipeMenuItem;
import com.hrdi.survey.swipemenu.SwipeMenuListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class SurveyLandUseFragment extends Fragment implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener, View.OnClickListener {

    final static String TAG_FRAGMENT = "LANDUSE_FRAGMENT";

    Activity activity;

    ArrayList<LandUseBean> landUseBeanList;
    LandUseDAO landUseDAO;
    LandUseListAdapter landUseListAdapter;

    SwipeMenuListView landUseListView;
    Button btn_save, btn_add;

    String surveyID, landcode, cardno;

    //private SurveyBean surveyBean;

    private GetLandUseTask task;

    public SurveyLandUseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        landUseDAO = new LandUseDAO(activity);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_survey_landuse, container, false);

        landUseDAO = new LandUseDAO(getActivity());

        Bundle bundle = this.getArguments();
        //surveyBean = bundle.getParcelable("surveyBean");
        //Log.i("surveyBean -> Landuse", surveyBean.toString());
        surveyID = bundle.getString("surveyID");
        landcode= bundle.getString("landcode");
        cardno= bundle.getString("cardno");


        findViewsById(rootView);

        task = new GetLandUseTask(activity);
        task.execute((Void) null);

        // set creator
        SwipeMenuCreator creator = getSwipeMenuCreator();
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

        landUseListView = (SwipeMenuListView) view.findViewById(R.id.list_landuse);
        btn_save = (Button) view.findViewById(R.id.btn_save);
        btn_add = (Button) view.findViewById(R.id.btn_add);

    }


    private void setListeners() {

        btn_save.setOnClickListener(this);
        btn_add.setOnClickListener(this);

        // step 2. listener item click event
        landUseListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                LandUseBean item = landUseBeanList.get(position);
                switch (index) {
                    case 0:
                        // open
                        // open(item);
                        openSurvey(item);
                        break;
                    case 1:
                        // delete survey in database
                        deleteSurvey(item);
                        landUseBeanList.remove(position);
                        landUseListAdapter.notifyDataSetChanged();
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
        landUseDAO.deleteLandUse(landUse);
        landUseListAdapter.remove(landUse);

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == btn_add) {     // Show LandUse Dialog
            goSurveyPlantPage();

        } else if (view == btn_save) {      //
            goDataListPage();
        }
    }


    private void goSurveyPlantPage() {
        // Change Fragment
        SurveyPlantFragment fragment = new SurveyPlantFragment();
        FragmentManager fragmentManager = getFragmentManager();

        // Send parameter surveybaen to next page
        Bundle surveyDataBundle = new Bundle();

        surveyDataBundle.putString("surveyID", surveyID);
        surveyDataBundle.putString("landcode", landcode);
        surveyDataBundle.putString("cardno", cardno);
        //Log.i("surveyBean-->Plant", surveyBean.toString());
        fragment.setArguments(surveyDataBundle);

        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, TAG_FRAGMENT).addToBackStack(null).commit();

    }

    private void goDataListPage() {
        // Change Fragment
        Fragment fragment = new SurveyDataListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, TAG_FRAGMENT).addToBackStack(null).commit();

    }

    /*
     * This method is invoked from MainActivity onFinishDialog() method. It is
     * called from CustomEmpDialogFragment when an employee record is updated.
     * This is used for communicating between fragments.
     */
    public void updateView() {
        task = new GetLandUseTask(activity);
        task.execute((Void) null);
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.app_name);
        getActivity().getActionBar().setTitle(R.string.app_name);
        super.onResume();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int deleteSurvey(LandUseBean landUseBean) {
        landUseDAO.deleteLandUse(landUseBean);
        //surveyListAdapter.remove(surveyBean);
        return 0;
    }

    private void openSurvey(LandUseBean landUseBean) {
        // TODO : Open Survey data for update

    }

    public class GetLandUseTask extends AsyncTask<Void, Void, ArrayList<LandUseBean>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetLandUseTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<LandUseBean> doInBackground(Void... params) {
            //Log.i("doInBackground -->", "Start" + surveyBean.getSurvey_id());
            ArrayList<LandUseBean> landUseList = landUseDAO.getLandUseList(surveyID);
            //Log.i("doInBackground -->", "Start" + landUseList.size());
            return landUseList;
        }

        @Override
        protected void onPostExecute(ArrayList<LandUseBean> landUseList) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
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

}
