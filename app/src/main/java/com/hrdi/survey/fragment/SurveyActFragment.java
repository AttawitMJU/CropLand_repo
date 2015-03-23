package com.hrdi.survey.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.SurveyActivityBean;
import com.hrdi.survey.model.SurveyBean;

/**
 * Created by attawit on 3/16/15 AD.
 */
public class SurveyActFragment extends Fragment implements View.OnClickListener{

    EditText edt_activity, edt_outcome, edt_repeat, edt_survive;
    Button button_cancel, button_ok;
    SurveyBean surveyBean;
    SurveyDAO surveyDAO;
    SurveyActivityBean activityBean;
    Activity activity;

    String surveyID, landcode, cardno;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        surveyDAO = new SurveyDAO(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();

        surveyID = bundle.getString("surveyID");
        landcode= bundle.getString("landcode");
        cardno= bundle.getString("cardno");


        View rootView = inflater.inflate(R.layout.layout_survey_activitys, container, false);

        findView(rootView);

        setListeners();

        return rootView;

    }

    @Override
    public void onClick(View v) {
        if(v == button_cancel){
            backToSurveyEtc();
        }else if(v == button_ok){
            activityBean = getGUI2Bean();
            activityBean.setSurvey_id(surveyID);
            activityBean.setLand_No(landcode);
            activityBean.setCard_no(cardno);

            Log.i("----getGUI2Bean---", "" + activityBean.toString());
            long newID = surveyDAO.addSurveyActivity(activityBean);
            Log.i("addSurveyActivity-ID", "" + newID);
            if (newID > 0) {
                // TODO: Call back to refresh ActivityList

                Toast.makeText(getActivity(),
                        "เพิ่มข้อมูลกิจกรรมเรียบร้อย",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(),
                        "ไม่สามารถเพิ่มข้อมูลกิจกรรม",
                        Toast.LENGTH_SHORT).show();
            }
            backToSurveyEtc();
        }
    }

    private  void backToSurveyEtc(){
        SurveyEtcFragment fragment = new SurveyEtcFragment();

        FragmentManager fragmentManager = getFragmentManager();


        // Send parameter surveybaen to next page
        Bundle surveyDataBundle = new Bundle();

        surveyDataBundle.putString("action","update");

        surveyDataBundle.putString("surveyID", surveyID);
        surveyDataBundle.putString("landcode", landcode);
        surveyDataBundle.putString("cardno", cardno);

        fragment.setArguments(surveyDataBundle);

        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

    }

    private void findView(View view) {
        edt_activity = (EditText) view.findViewById(R.id.edt_activity);
        edt_outcome = (EditText) view.findViewById(R.id.edt_outcome);
        edt_repeat = (EditText) view.findViewById(R.id.edt_repeat);
        edt_survive = (EditText) view.findViewById(R.id.edt_survive);
        button_cancel = (Button) view.findViewById(R.id.button_cancel);
        button_ok = (Button) view.findViewById(R.id.button_ok);
    }

    private void setListeners() {
        button_cancel.setOnClickListener(this);
        button_ok.setOnClickListener(this);
    }

    private SurveyActivityBean getGUI2Bean() {
        SurveyActivityBean bean = new SurveyActivityBean();

        bean.setActivity1(edt_activity.getText().toString());
        bean.setOutcome1(edt_outcome.getText().toString());
        bean.setRepeat1(edt_repeat.getText().toString());
        bean.setSurvive1(edt_survive.getText().toString());

        return bean;
    }



}
