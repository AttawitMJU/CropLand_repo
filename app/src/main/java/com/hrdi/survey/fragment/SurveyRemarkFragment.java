package com.hrdi.survey.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.SurveyDetailEtcBean;

/**
 * Created by attawit on 3/16/15 AD.
 */
public class SurveyRemarkFragment extends Fragment implements View.OnClickListener{

    EditText edt_remark;
    TextView txt_title;
    Button button_cancel, button_ok;

    //SurveyBean surveyBean;
    SurveyDetailEtcBean bean;

    SurveyDAO surveyDAO;
    String etcType;
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
        //surveyBean = bundle.getParcelable("surveyBean");
        etcType = bundle.getString("action");

        surveyID = bundle.getString("surveyID");
        landcode= bundle.getString("landcode");
        cardno= bundle.getString("cardno");

        View rootView = inflater.inflate(R.layout.layout_remark, container, false);

        findView(rootView);

        setListeners();

        return rootView;

    }


    private void findView(View view) {
        txt_title = (TextView)view.findViewById(R.id.txt_title);
        edt_remark = (EditText) view.findViewById(R.id.edt_remark);
        button_cancel = (Button) view.findViewById(R.id.button_cancel);
        button_ok = (Button) view.findViewById(R.id.button_ok);

        String str = "";
        if("support".equals(etcType))
            str= getString(R.string.act_support);
        else if("problem".equals(etcType))
            str= getString(R.string.act_problem);
        else if("want".equals(etcType))
            str= getString(R.string.act_want);

        txt_title.setText(str);

    }

    private void setListeners() {
        button_cancel.setOnClickListener(this);
        button_ok.setOnClickListener(this);
    }

    private  void backToSurveyEtc(){
        SurveyEtcFragment fragment = new SurveyEtcFragment();

        FragmentManager fragmentManager = getFragmentManager();


        // Send parameter surveybaen to next page
        Bundle surveyDataBundle = new Bundle();

        surveyDataBundle.putString("action", "update");

        surveyDataBundle.putString("surveyID", surveyID);
        surveyDataBundle.putString("landcode", landcode);
        surveyDataBundle.putString("cardno", cardno);

        //Log.i("surveyBean #3", surveyBean.toString());

        fragment.setArguments(surveyDataBundle);

        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

    }

    @Override
    public void onClick(View v) {
        if(v == button_cancel){
            backToSurveyEtc();
        }else if(v == button_ok){
            bean = new SurveyDetailEtcBean();

            bean.setSurvey_id(surveyID);
            bean.setLand_No(landcode);
            bean.setCard_no(cardno);
            bean.setDetail(edt_remark.getText().toString());


            long newID = surveyDAO.addSurveyEtc(bean, etcType);
            Log.i("addSurveyEct-ID", "" + newID + "   " + etcType);
            if (newID > 0) {
                Toast.makeText(getActivity(),
                        "เพิ่มข้อมูลเรียบร้อย",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(),
                        "เพิ่มข้อมูลไม่ได้",
                        Toast.LENGTH_SHORT).show();
            }
            backToSurveyEtc();
        }
    }
}
