package com.hrdi.survey.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.adapter.FarmerListAdapter;
import com.hrdi.survey.control.AgriculturistDAO;
import com.hrdi.survey.control.MetaDAO;
import com.hrdi.survey.control.SurveyDAO;
import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.model.MetaBean;
import com.hrdi.survey.model.SurveyBean;
import com.hrdi.survey.modeldb.MetaCardDB;
import com.hrdi.survey.modeldb.MetaDocDB;
import com.hrdi.survey.modeldb.MetaExtProjectDB;
import com.hrdi.survey.modeldb.MetaProjectAreaDB;
import com.hrdi.survey.modeldb.MetaProjectMooDB;
import com.hrdi.survey.modeldb.MetaWaterResourceDB;
import com.hrdi.survey.util.GPSTracker;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SurveyFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, FarmerDialog.FarmerDialogListener {


    final static String TAG_FRAGMENT = "SURVEY_FRAGMENT";

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
            "yyyy/MM/dd", Locale.ENGLISH);

    private static final SimpleDateFormat dateFormatterShow = new SimpleDateFormat(
            "d/M/yyyy", Locale.ENGLISH);

    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
            "yyyy-MM-dd  HH:mm:ss", Locale.ENGLISH);

    public static String survey_id = "";

    AutoCompleteTextView edit_Card_no;

    // UI references
    EditText edt_Land_No, edt_Area, edt_LATITUDE,
            edit_Do_Year, edit_FirstName, edt_LastName, edit_Address, edt_CardType,
            edt_Owner_Type, edt_LandUse_History, edt_Staff, edt_surveyDate;
    ImageButton btn_LandCrop, btn_LatLong, img_btn_addPerson;
    Spinner spn_projectArea, spn_extProject, spn_LandDoc_Type, spn_Moo, spn_water;
    RadioButton rdb_Do_Current_Year, rdb_Empty, rdb_Owner, rdb_Rent, rdb_Other_Owner_Type;
    RadioGroup rdb_group_Owner;
    Button btn_Save, btn_Next;
    TextClock txt_DataTime;

    ArrayAdapter<MetaBean> cardDataAdapter, docDataAdapter, extProjectDataAdapter, projectAreaDataAdapter, projectMooBanDataAdapter, waterDataAdapter;

    private SurveyDAO surveyDAO;
    private AgriculturistDAO agriculturistDAO;
    private MetaDAO metaDAO;
    private SurveyBean surveyBeanUpdate, surveyBean;
    private String surveyBeanUpdateID;
    private String action;
    private CreateSurveyTask task;
    private UpdateSurveyTask updateTask;
    int mYear, mMonth, mDay;

    public SurveyFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        surveyDAO = new SurveyDAO(getActivity());
        agriculturistDAO = new AgriculturistDAO(getActivity());
        metaDAO = new MetaDAO(getActivity());

        //if (getArguments() != null) {
        //    survey_id = getArguments().getString("survey_id");
        //}

        //super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    //getFragmentManager.finish();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            survey_id = getArguments().getString("survey_id");


            btn_Save.setVisibility(View.GONE);
            btn_Next.setVisibility(View.VISIBLE);
        }
        refreshCardNo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();


        View rootView = inflater.inflate(R.layout.fragment_survey, container, false);

        // Get All UI
        findViewsById(rootView);

        //To show current date in the datepicker
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);
        mMonth = mcurrentDate.get(Calendar.MONTH);
        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        // Set Spinner/List Data
        setListItemAdapter();

        // set Button Listeners
        setListeners();

        // Get parameter from Survey Data List
        if (bundle != null) {
            surveyBeanUpdateID = bundle.getString("surveyBeanUpdateID");
            action = bundle.getString("action");

            //Log.i("ID ??", surveyBeanUpdateID);
            //Log.i("action ??", action);

            if ("update".equals(action)) {
                surveyBeanUpdate = surveyDAO.getSurveyByID(surveyBeanUpdateID);
                //Log.i("surveyBeanUpdate ??", surveyBeanUpdate.toString());
                setUpdateData(surveyBeanUpdate);
            }
        }

        return rootView;
    }

    private void setUpdateData(SurveyBean surveyBean) {
        MetaBean metaBean;
        //txt_hidden_id.setText(surveyBean.getSurvey_id());
        //Log.i("surveyBeanUpdate#2 ??", surveyBean.toString());


        metaBean = metaDAO.getMetaByType(surveyBean.getProject_Area(), MetaProjectAreaDB.TABLE_NAME);
        spn_projectArea.setSelection(projectAreaDataAdapter.getPosition(metaBean));

        metaBean = metaDAO.getMetaByType(surveyBean.getExt_Project(), MetaExtProjectDB.TABLE_NAME);
        //Log.i("getExt_Project# ??", metaBean.toString()+"   "+extProjectDataAdapter.getPosition(metaBean));
        spn_extProject.setSelection(extProjectDataAdapter.getPosition(metaBean));

        try {
            metaBean = metaDAO.getMetaByType(surveyBean.getProject_MooBan(), MetaProjectMooDB.TABLE_NAME);
            //Log.i("getProject_MooBan# ??", metaBean.toString() + "   " + projectMooBanDataAdapter.getPosition(metaBean));
            spn_Moo.setSelection(projectMooBanDataAdapter.getPosition(metaBean));
        }catch (Exception e){

        }
        //Log.i("getLand_No#2 ??", surveyBean.getLand_No());
        edt_Land_No.setText(surveyBean.getLand_No());

        edt_LATITUDE.setText(surveyBean.getLatlong());

        // พื้นที่  edt_Area

        // เอกสารสิทธิ์     spn_LandDoc_Type
        //Log.i("getLand_doc_type", surveyBean.getLand_doc_type());
        if (surveyBean.getLand_doc_type() != null) {
            metaBean = new MetaBean(Integer.parseInt(surveyBean.getLand_doc_type()), surveyBean.getLand_doc_type_name());
            spn_LandDoc_Type.setSelection(docDataAdapter.getPosition(metaBean));
        }

        if (surveyBean.getWater() != null) {
            metaBean = metaDAO.getMetaByType(surveyBean.getWater(), MetaWaterResourceDB.TABLE_NAME);
           // Log.i("getWater# ??", surveyBean.getWater() +"  "+ metaBean.toString()+"   "+ waterDataAdapter.getPosition(metaBean));
            //+"   "+waterDataAdapter.getPosition(metaBean)
            spn_water.setSelection(waterDataAdapter.getPosition(metaBean));
        }

        // สภาพพื้นที่ - ทำปีปัจจุบัน        rdb_Do_Current_Year
        // สภาพพื้นที่ - พื้นที่ท้ิงไว้        rdb_Empty
        //Log.i("getArea_status",surveyBean.getArea_status());

        if ("ทำปีปัจจุบัน".equals(surveyBean.getArea_status())) {
            rdb_Do_Current_Year.setChecked(true);
        } else if ("พื้นที่ทิ้งไว้".equals(surveyBean.getArea_status())) {
            rdb_Empty.setChecked(true);
        }

        // สภาพพื้นที่ - ปี        edit_Do_Year
        edit_Do_Year.setText(surveyBean.getArea_status_year());

        AgriculturistBean farmer =  agriculturistDAO.getPerson(surveyBean.getCard_no());
        // หมายเลขบัตร        edit_Card_no
        edit_Card_no.setText(surveyBean.getCard_no() + "    " + farmer.getFirstname() + " " + farmer.getLastname());
        // ชื่อ        edit_FirstName
        edit_FirstName.setText(farmer.getFirstname());
        // นามสกุล        edt_LastName
        edt_LastName.setText(farmer.getLastname());

        if (farmer.getCard_type() != null) {
            //MetaBean metaBean = new MetaBean(Integer.parseInt(bean.getCard_type()), null);
            MetaBean mBean = metaDAO.getMetaByType(farmer.getCard_type(), MetaCardDB.TABLE_NAME);

            //Log.i("metaBean", "" + metaBean.toString());

            edt_CardType.setText(mBean.toString());
        }
        StringBuilder address = new StringBuilder();
        address.append(farmer.getHome_no());
        if (farmer.getMoo_no() != null && "".equals(farmer.getMoo_no().trim())) {
            address.append("  หมู่ ");
            address.append(farmer.getMoo_no());
        }
        if (farmer.getGroup_no() != null && "".equals(farmer.getGroup_no().trim())) {
            address.append("  กลุ่มหมู่ ");
            address.append(farmer.getGroup_no());
        }
        if (farmer.getTambol_name() != null) {
            address.append("  ตำบล ");
            address.append(farmer.getTambol_name());
        }
        if (farmer.getAmphur_name() != null) {
            address.append("  อำเภอ ");
            address.append(farmer.getAmphur_name());
        }
        if (farmer.getProvince_name() != null) {
            address.append("  จังหวัด ");
            address.append(farmer.getProvince_name());
        }
        edit_Address.setText(address.toString());

        // สิทธิการถือครอง
        if ("0".equals(surveyBean.getOwner_Type())) {
            rdb_Owner.setChecked(true);
        } else if ("1".equals(surveyBean.getOwner_Type())) {
            rdb_Rent.setChecked(true);
        } else if ("2".equals(surveyBean.getOwner_Type())) {
            rdb_Other_Owner_Type.setChecked(true);
        }

        // สิทธิการถือครอง - ระบุ        edt_Owner_Type
        edt_Owner_Type.setText(surveyBean.getOwner_Type_Detail());

        edt_LandUse_History.setText(surveyBean.getHistory());
        edt_Staff.setText(surveyBean.getRemark2());
        edt_surveyDate.setText(surveyBean.getSurvey_Date());


        surveyBean.setUpdate_Date( txt_DataTime.getText().toString());

    }


    /**
     * Map Input UI to Java Code
     */
    private void findViewsById(View rootView) {
        // แปลงเลขที่
        edt_Land_No = (EditText) rootView.findViewById(R.id.edt_Land_No);
        // ปุ่มค้นหาข้อมูลแปลง
        btn_LandCrop = (ImageButton) rootView.findViewById(R.id.btn_LandCrop);
        btn_LandCrop.setVisibility(View.GONE);

        spn_projectArea = (Spinner) rootView.findViewById(R.id.spn_projectArea);
        // โครงการขยายผลฯ
        spn_extProject = (Spinner) rootView.findViewById(R.id.spn_extProject);
        //่ หมู่บ้าน
        spn_Moo = (Spinner) rootView.findViewById(R.id.spn_Moo);

        spn_water = (Spinner) rootView.findViewById(R.id.spn_water);

        // พื้นที่
        edt_Area = (EditText) rootView.findViewById(R.id.edt_Area);
        // พิกัด
        edt_LATITUDE = (EditText) rootView.findViewById(R.id.edt_LatLong);
        // ปุ่มค้นหาพิกัด
        btn_LatLong = (ImageButton) rootView.findViewById(R.id.btn_LatLong);
        // เอกสารสิทธิ์
        spn_LandDoc_Type = (Spinner) rootView.findViewById(R.id.spn_LandDoc_Type);
        // สภาพพื้นที่ - ทำปีปัจจุบัน
        rdb_Do_Current_Year = (RadioButton) rootView.findViewById(R.id.rdb_Do_Current_Year);
        // สภาพพื้นที่ - พื้นที่ท้ิงไว้
        rdb_Empty = (RadioButton) rootView.findViewById(R.id.rdb_Empty);
        // สภาพพื้นที่ - ปี
        edit_Do_Year = (EditText) rootView.findViewById(R.id.edit_Do_Year);
        // หมายเลขบัตร
        edit_Card_no = (AutoCompleteTextView) rootView.findViewById(R.id.edit_Card_no);
        // ชื่อ
        edit_FirstName = (EditText) rootView.findViewById(R.id.edit_FirstName);
        // นามสกุล
        edt_LastName = (EditText) rootView.findViewById(R.id.edt_LastName);
        // ประเภทบัตร
        edt_CardType = (EditText) rootView.findViewById(R.id.edt_CardType);
        // ที่อยู่
        edit_Address = (EditText) rootView.findViewById(R.id.edit_Address);

        rdb_group_Owner = (RadioGroup) rootView.findViewById(R.id.rdb_group_Owner);
        // สิทธิการถือครอง - ตนเอง
        rdb_Owner = (RadioButton) rootView.findViewById(R.id.rdb_Owner);
        // สิทธิการถือครอง - เช่าผู้อื่น
        rdb_Rent = (RadioButton) rootView.findViewById(R.id.rdb_Rent);
        // สิทธิการถือครอง - อื่นๆ
        rdb_Other_Owner_Type = (RadioButton) rootView.findViewById(R.id.rdb_Other_Owner_Type);


        // สิทธิการถือครอง - ระบุ
        edt_Owner_Type = (EditText) rootView.findViewById(R.id.edt_Owner_Type);
        // ประวัติการใช้ที่ดินย้อนหลัง
        edt_LandUse_History = (EditText) rootView.findViewById(R.id.edt_LandUse_History);


        // ปุ่มบันทึก
        btn_Save = (Button) rootView.findViewById(R.id.btn_Save);

        // ปุ่มต่อไป
        btn_Next = (Button) rootView.findViewById(R.id.btn_Next);
        btn_Next.setVisibility(View.GONE);

        // New Survey ID
        // txt_hidden_id = (EditText) rootView.findViewById(R.id.txt_hidden_id);

        txt_DataTime = (TextClock) rootView.findViewById(R.id.textClock);

        edt_Staff = (EditText) rootView.findViewById(R.id.edt_Staff);
        edt_surveyDate = (EditText) rootView.findViewById(R.id.edt_surveyDate);
        img_btn_addPerson = (ImageButton) rootView.findViewById(R.id.img_btn_addPerson);

    }

    private void refreshCardNo() {
        String[] list = agriculturistDAO.getIDCard();
        //Log.i("refreshCardNo", " " +list.length());
        if (list != null) {
            ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_dropdown_item_1line,list);
               Log.i("adapter", "" + adapter.getCount());
            edit_Card_no.setAdapter(adapter);
        }
    }

    private void setListItemAdapter() {
        MetaDAO metaDAO = new MetaDAO(getActivity());

        refreshCardNo();

        // Spinner ประเภทบัตร
        //List<MetaBean> cardTypes = metaDAO.getMetaByType(MetaCardDB.getSelectAllSQL());
        //cardDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cardTypes);
        //spn_CardType.setAdapter(cardDataAdapter);


        // Spinner เอกสารสิทธิ์
        List<MetaBean> docType = metaDAO.getMetaByType(MetaDocDB.getSelectAllSQL());
        docDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, docType);
        spn_LandDoc_Type.setAdapter(docDataAdapter);


        // Spinner พื้นที่โครงการหลวง
        List<MetaBean> projectAreaList = metaDAO.getMetaByType(MetaProjectAreaDB.getSelectAllSQL());
        projectAreaDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, projectAreaList);
        spn_projectArea.setAdapter(projectAreaDataAdapter);

        // Spinner โครงการขยายผลโครงการหลวง
        List<MetaBean> extProjectList = metaDAO.getMetaByType(MetaExtProjectDB.getSelectAllSQL());
        extProjectDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, extProjectList);
        spn_extProject.setAdapter(extProjectDataAdapter);

        List<MetaBean> projectMooBanList = metaDAO.getMetaByType(MetaProjectMooDB.getSelectAllSQL());
        projectMooBanDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, projectMooBanList);
        spn_Moo.setAdapter(projectMooBanDataAdapter);

        // Spinner แหล่งน้ำ
        List<MetaBean> waterList = metaDAO.getMetaByType(MetaWaterResourceDB.getSelectAllSQL());
        waterDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, waterList);
        spn_water.setAdapter(waterDataAdapter);

    }

    private void setListeners() {
        btn_LandCrop.setOnClickListener(this);
        btn_LatLong.setOnClickListener(this);
        btn_Save.setOnClickListener(this);
        btn_Next.setOnClickListener(this);
        img_btn_addPerson.setOnClickListener(this);
        edt_surveyDate.setOnClickListener(this);

        spn_projectArea.setOnItemSelectedListener(this);
        spn_extProject.setOnItemSelectedListener(this);

        edit_Card_no.setOnClickListener(this);
        edit_Card_no.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Log.i("arg0)", parent.getItemAtPosition(position).toString());

                if (edit_Card_no.getText() != null) {
                    String idcard= parent.getItemAtPosition(position).toString().split(" ")[0];
                    //Log.i("idcard",idcard);
                    AgriculturistBean bean = agriculturistDAO.getPerson(idcard);
                    if (bean != null) {
                        edit_FirstName.setText(bean.getFirstname());
                        edt_LastName.setText(bean.getLastname());
                        //Log.i("bean.getCard_type()", "" + bean.getCard_type());
                        if (bean.getCard_type() != null) {
                            //MetaBean metaBean = new MetaBean(Integer.parseInt(bean.getCard_type()), null);
                            MetaBean metaBean = metaDAO.getMetaByType(bean.getCard_type(), MetaCardDB.TABLE_NAME);

                            //Log.i("metaBean", "" + metaBean.toString());

                            edt_CardType.setText(metaBean.toString());
                        }
                        StringBuilder address = new StringBuilder();
                        address.append(bean.getHome_no());
                        if (bean.getMoo_no() != null && "".equals(bean.getMoo_no().trim())) {
                            address.append("  หมู่ ");
                            address.append(bean.getMoo_no());
                        }
                        if (bean.getGroup_no() != null && "".equals(bean.getGroup_no().trim())) {
                            address.append("  กลุ่มหมู่ ");
                            address.append(bean.getGroup_no());
                        }
                        if (bean.getTambol_name() != null) {
                            address.append("  ตำบล ");
                            address.append(bean.getTambol_name());
                        }
                        if (bean.getAmphur_name() != null) {
                            address.append("  อำเภอ ");
                            address.append(bean.getAmphur_name());
                        }
                        if (bean.getProvince_name() != null) {
                            address.append("  จังหวัด ");
                            address.append(bean.getProvince_name());
                        }
                        edit_Address.setText(address.toString());

                    }
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view == btn_LandCrop) {
            // TODO: get Land Crop Information

        } else if (view == btn_LatLong) {
            getLATLONG();

        } else if (view == edit_Card_no) {
            refreshCardNo();
        } else if (view == img_btn_addPerson) {
            if (edt_Land_No.getText() != null && edt_Land_No.getText().toString().length() > 3) {
                Bundle arguments = new Bundle();
                arguments.putString("landno", edt_Land_No.getText().toString());

                FarmerDialog farmerDialog = new FarmerDialog();
                farmerDialog.setArguments(arguments);
                farmerDialog.show(getFragmentManager(), FarmerDialog.ARG_ITEM_ID);

            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        getActivity()).create();

                // Setting Dialog Title
                alertDialog.setTitle("กรุณาป้อนข้อมูล");

                // Setting Dialog Message
                alertDialog.setMessage("เลขที่แปลง");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_whats_hot);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        //Toast.makeText(getActivity(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                        edt_Land_No.requestFocus();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }

        } else if (view == edt_surveyDate) {
            DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    edt_surveyDate.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.show();

        } else if ("update".equals(action) && view == btn_Save) {
            // Call Task to Update SQLite
            updateTask = new UpdateSurveyTask(getActivity());
            updateTask.execute();
            goNextPage();

        } else if (view == btn_Save) {
            // Call Task to Add SQLite
            task = new CreateSurveyTask(getActivity());
            task.execute();

            //btn_Next.setVisibility(View.VISIBLE);
            //btn_Save.setVisibility(View.GONE);
            //btn_Next.setBackground(new ColorDrawable(0xff99cc00));

        } else if (view == btn_Next) {
            // update data with hidden id
            updateTask = new UpdateSurveyTask(getActivity());
            updateTask.execute();
            // Next Fragment
            goNextPage();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MetaDAO metaDAO = new MetaDAO(getActivity());
        Spinner spiner = (Spinner) parent;

        if(!"update".equals(action)) {
            //ArrayAdapter<MetaBean> dataAdapter;
            List<MetaBean> metaBeanList;
            MetaBean metaBean = (MetaBean) parent.getItemAtPosition(position);
            if (spiner.getId() == R.id.spn_projectArea) {
                // พื้นที่ --> ศูนย์
                metaBeanList = metaDAO.getMetaByType(MetaExtProjectDB.getSelectAllSQLRef(metaBean.getItemId()));
                extProjectDataAdapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_item, metaBeanList);
                spn_extProject.setAdapter(extProjectDataAdapter);

            } else if (spiner.getId() == R.id.spn_extProject) {
                //  ศูนย์ --> หมู่บ้าน
Log.i("***spn_extProject1",MetaProjectMooDB.getSelectAllSQLRefRemark(String.valueOf(metaBean.getItemId())));
                metaBeanList = metaDAO.getMetaByType(MetaProjectMooDB.getSelectAllSQLRefRemark(String.valueOf(metaBean.getItemId())));
                projectMooBanDataAdapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_spinner_item, metaBeanList);
                spn_Moo.setAdapter(projectMooBanDataAdapter);
//Log.i("spn_extProject2",""+metaBean.getItemValue());
                edt_Land_No.setText(metaBean.getItemValue());
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private SurveyBean getGUI2Bean() {
        SurveyBean surveyBean = new SurveyBean();

        surveyBean.setProject_Area(String.valueOf(((MetaBean) spn_projectArea.getSelectedItem()).getItemId()));
        surveyBean.setExt_Project(String.valueOf(((MetaBean) spn_extProject.getSelectedItem()).getItemId()));
        surveyBean.setProject_MooBan(String.valueOf(((MetaBean) spn_Moo.getSelectedItem()).getItemId()));

        // แปลงเลขที่  edt_Land_No
        surveyBean.setLand_No(edt_Land_No.getText().toString());

        //่ หมู่บ้าน    edit_Moo
        // surveyBean.setMooban(edit_Moo.getText().toString());


        // พิกัด    edt_LATITUDE
        surveyBean.setLatlong(edt_LATITUDE.getText().toString());
        // ปุ่มค้นหาพิกัด   btn_LatLong
        // เอกสารสิทธิ์     spn_LandDoc_Type
        surveyBean.setLand_doc_type(String.valueOf(((MetaBean) spn_LandDoc_Type.getSelectedItem()).getItemId()));
        // พื้นที่  edt_Area
        surveyBean.setWater(String.valueOf(((MetaBean) spn_water.getSelectedItem()).getItemId()));

        // สภาพพื้นที่ - ทำปีปัจจุบัน        rdb_Do_Current_Year
        // สภาพพื้นที่ - พื้นที่ท้ิงไว้        rdb_Empty
        String status = "";
        if (rdb_Do_Current_Year.isChecked())
            status = "ทำปีปัจจุบัน";
        else if (rdb_Empty.isChecked())
            status = "พื้นที่ทิ้งไว้";
        surveyBean.setArea_status(status);

        // สภาพพื้นที่ - ปี        edit_Do_Year
        surveyBean.setArea_status_year(edit_Do_Year.getText().toString());
        // หมายเลขบัตร        edit_Card_no
        String idcard= edit_Card_no.getText().toString().split(" ")[0];
        surveyBean.setCard_no(idcard);
        // ชื่อ        edit_FirstName
        //surveyBean.setFirstName(edit_FirstName.getText().toString());
        // นามสกุล        edt_LastName
        //surveyBean.setLastName(edt_LastName.getText().toString());
        // ประเภทบัตร        spn_CardType
        //surveyBean.setCard_type(String.valueOf(((MetaBean) spn_CardType.getSelectedItem()).getItemId()));
        // ที่อยู่        edit_Address
        //surveyBean.setAddress(edit_Address.getText().toString());

        String owner_status = "";
        // สิทธิการถือครอง - ตนเอง        rdb_Owner
        if (rdb_Owner.isChecked()) {
            owner_status = "0";
        }
        // สิทธิการถือครอง - เช่าผู้อื่น        rdb_Rent
        else if (rdb_Rent.isChecked()) {
            owner_status = "1";
        }
        // สิทธิการถือครอง - อื่นๆ        rdb_Other_Owner_Type
        else if (rdb_Other_Owner_Type.isChecked()) {
            owner_status = "2";
        }
        surveyBean.setOwner_Type(owner_status);

        // สิทธิการถือครอง - ระบุ        edt_Owner_Type
        surveyBean.setOwner_Type_Detail(edt_Owner_Type.getText().toString());

        // ประวัติการใช้ที่ดินย้อนหลัง        edt_LandUse_History
        surveyBean.setHistory(edt_LandUse_History.getText().toString());

        // วันที่บันทึกข้อมูล
        surveyBean.setSurvey_Date(txt_DataTime.getText().toString());

        // Update Date
        surveyBean.setUpdate_Date(txt_DataTime.getText().toString());

        surveyBean.setRemark2(edt_Staff.getText().toString());
        // ปุ่มต่อไป        btn_Next

        Log.i("surveyBean Save**", surveyBean.toString());
        return surveyBean;
    }

    private void goNextPage() {
        // FragmentManager fragmentManager = getSupportFragmentManager();
        // fragmentManager.beginTransaction()
        //        .replace(R.id.frame_container, fragment, TAG_FRAGMENT).addToBackStack(TAG_FRAGMENT).commit();

        // Change Fragment  การใช้ประโยชน์ที่ดิน
        SurveyEtcFragment fragment = new SurveyEtcFragment();
        if (fragment != null) {
            // Send parameter surveybaen to next page
            Bundle surveyDataBundle = new Bundle();
            SurveyBean sb;

            if ("update".equals(action)) {
                sb = getGUI2Bean();
                sb.setRemark1("waiting");
                sb.setSurvey_id(surveyBeanUpdateID);


            } else {
                sb = surveyBean;
            }

            surveyDataBundle.putString("surveyID", sb.getSurvey_id());
            surveyDataBundle.putString("landcode", sb.getLand_No());
            surveyDataBundle.putString("cardno", sb.getCard_no());
            surveyDataBundle.putString("action", "update");
            Log.i("surveyBean #1", sb.toString());

            fragment.setArguments(surveyDataBundle);

            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
        }
    }

    private void getLATLONG() {
        // create class object
        GPSTracker gps = new GPSTracker(getActivity());

        // check if GPS enabled
        if (gps.canGetLocation()) {
            String Latitude = String.valueOf(gps.getLatitude());
            String Longitude = String.valueOf(gps.getLongitude());

            edt_LATITUDE.setText(Latitude + ", " + Longitude);

        } else {
            gps.showSettingsAlert();
        }
    }

    /*
         * Callback used to communicate with EmpListFragment to notify the list adapter.
         * Communication between fragments goes via their Activity class.
         */
    @Override
    public void onFinishDialog(String str) {
        if (str != null) {
            edit_Card_no.setText(str);
            rdb_Owner.setFocusable(true);
        }
    }

    /*
         Background Async Task to Create new Survey
    * */
    class CreateSurveyTask extends AsyncTask<String, String, String> {
        private final WeakReference<Activity> activityWeakRef;

        public CreateSurveyTask(Activity context) {
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

                    goNextPage();
                }
            }
        }


        @Override
        protected String doInBackground(String... params) {
            surveyBean = getGUI2Bean();
            surveyBean.setRemark1("waiting");
            long result = surveyDAO.addSurvey(surveyBean);

            // Set Last ID to bean
            surveyBean.setSurvey_id("" + result);

            Log.i("surveyBean doIn", surveyBean.toString());

            return String.valueOf(result);
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
            // Get Update Data Form
            SurveyBean sb = getGUI2Bean();

            // Set update ID
            sb.setSurvey_id(surveyBeanUpdate.getSurvey_id());
            sb.setRemark1("waiting");
           Log.i(" Update1 doInBackground", sb.toString());
            long result = surveyDAO.updateSurvey(sb);

            return String.valueOf(result);
        }
    }

}
