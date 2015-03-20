package com.hrdi.survey.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.hrdi.survey.R;
import com.hrdi.survey.control.AgriculturistDAO;
import com.hrdi.survey.control.MetaDAO;
import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.model.MetaBean;
import com.hrdi.survey.modeldb.MetaAmphoeDB;
import com.hrdi.survey.modeldb.MetaCardDB;
import com.hrdi.survey.modeldb.MetaProvinceDB;
import com.hrdi.survey.modeldb.MetaTambolDB;
import com.hrdi.survey.modeldb.MetaTitleDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by attawit on 3/10/15 AD.
 */
public class FarmerDialog extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // UI references
    private EditText edt_cardNo, edt_fname, edt_lname, edt_home, edt_mooNo, edt_group, edt_zip,
            edt_occupation1, edt_occupation2, edt_freetime,
            edt_memberAll, edt_memberType1, edt_memberType2, edt_memberType3,
            edt_incomeAll, edt_income1, edt_income2, edt_expensesAll, edt_expenses1, edt_expenses2;
    private Spinner spn_cardType, spn_title, spn_province, spn_amphoe, spn_tambol;
    private AutoCompleteTextView edit_village;
    private LinearLayout submitLayout;
    ArrayAdapter<MetaBean> cardDataAdapter, titleDataAdapter, occupationDataAdapter;

    AgriculturistBean agriculturistBean;
    AgriculturistDAO agriculturistDAO;
    String landNo = "";
    public static final String ARG_ITEM_ID = "farmer_dialog_fragment";

    public interface FarmerDialogListener {
        void onFinishDialog(String str);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        agriculturistDAO = new AgriculturistDAO(getActivity());

        Bundle bundle = this.getArguments();
        landNo = bundle.getString("landno");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_farmer, null);
        builder.setView(view);
        findView(view);
        // Set Spinner/List Data
        setListItemAdapter();
        setListeners();


        builder.setTitle("เพิ่มเกษตรกร");
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                agriculturistBean = getGUI2Bean();
                long newID = agriculturistDAO.addPersonSQLite(agriculturistBean);
                Log.i("agriculturist-ID", "" + newID);
                if (newID > 0) {
                    Toast.makeText(getActivity(),
                            "เพิ่มข้อมูลเกษตรกรเรียบร้อย",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),
                            "ไม่สามารถเพิ่มข้อมูลเกษตรกร",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton(R.string.reset, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    private void findView(View view) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
        Date date = new Date();

        spn_cardType = (Spinner) view.findViewById(R.id.spn_cardType);
        spn_title = (Spinner) view.findViewById(R.id.spn_title);
        edt_cardNo = (EditText) view.findViewById(R.id.edt_cardNo);
        edt_cardNo.setText(landNo + "_" + dateFormat.format(date));

        edt_fname = (EditText) view.findViewById(R.id.edt_fname);
        edt_lname = (EditText) view.findViewById(R.id.edt_lname);
        edt_home = (EditText) view.findViewById(R.id.edt_home);
        edt_mooNo = (EditText) view.findViewById(R.id.edt_mooNo);
        edt_group = (EditText) view.findViewById(R.id.edt_group);
        spn_province = (Spinner) view.findViewById(R.id.spn_province);
        spn_amphoe = (Spinner) view.findViewById(R.id.spn_amphoe);
        spn_tambol = (Spinner) view.findViewById(R.id.spn_tambol);
        edit_village = (AutoCompleteTextView) view.findViewById(R.id.edit_village);
        edt_zip = (EditText) view.findViewById(R.id.edt_zip);
        edt_occupation1 = (EditText) view.findViewById(R.id.edt_occupation1);
        edt_occupation2 = (EditText) view.findViewById(R.id.edt_occupation2);
        edt_freetime = (EditText) view.findViewById(R.id.edt_freetime);
        edt_memberAll = (EditText) view.findViewById(R.id.edt_memberAll);
        edt_memberType1 = (EditText) view.findViewById(R.id.edt_memberType1);
        edt_memberType2 = (EditText) view.findViewById(R.id.edt_memberType2);
        edt_memberType3 = (EditText) view.findViewById(R.id.edt_memberType3);
        edt_incomeAll = (EditText) view.findViewById(R.id.edt_incomeAll);
        edt_income1 = (EditText) view.findViewById(R.id.edt_income1);
        edt_income2 = (EditText) view.findViewById(R.id.edt_income2);
        edt_expensesAll = (EditText) view.findViewById(R.id.edt_expensesAll);
        edt_expenses1 = (EditText) view.findViewById(R.id.edt_expenses1);
        edt_expenses2 = (EditText) view.findViewById(R.id.edt_expenses2);

        submitLayout = (LinearLayout) view
                .findViewById(R.id.layout_submit);
        submitLayout.setVisibility(View.GONE);

    }


    private void setListItemAdapter() {
        MetaDAO metaDAO = new MetaDAO(getActivity());

        List<MetaBean> provinceList = metaDAO.getMetaByType(MetaProvinceDB.getSelectAllSQL());
        ArrayAdapter<MetaBean> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, provinceList);
        spn_province.setAdapter(adapter);

        // Spinner ประเภทบัตร
        List<MetaBean> cardList = metaDAO.getMetaByType(MetaCardDB.getSelectAllSQL());
        cardDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cardList);
        spn_cardType.setAdapter(cardDataAdapter);

        // Spinner คำนำหน้า
        List<MetaBean> titleList = metaDAO.getMetaByType(MetaTitleDB.getSelectAllSQL());
        titleDataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, titleList);
        spn_title.setAdapter(titleDataAdapter);

    }

    private void setListeners() {
        //btn_ok.setOnClickListener(this);

        spn_province.setOnItemSelectedListener(this);
        spn_amphoe.setOnItemSelectedListener(this);
        spn_tambol.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MetaDAO metaDAO = new MetaDAO(getActivity());
        Spinner spiner = (Spinner) parent;

        ArrayAdapter<MetaBean> dataAdapter;
        List<MetaBean> metaBeanList;
        MetaBean metaBean = (MetaBean) parent.getItemAtPosition(position);
        if (spiner.getId() == R.id.spn_province) {
            // จังหวัด --> อำเภอ
            metaBeanList = metaDAO.getMetaByType(MetaAmphoeDB.getSelectAllSQLRef(metaBean.getItemId()));
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_amphoe.setAdapter(dataAdapter);
        } else if (spiner.getId() == R.id.spn_amphoe) {
            // อำเภอ --> ตำบล
            metaBeanList = metaDAO.getMetaByType(MetaTambolDB.getSelectAllSQLRef(metaBean.getItemId()));
            dataAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_spinner_item, metaBeanList);
            spn_tambol.setAdapter(dataAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private AgriculturistBean getGUI2Bean() {
        AgriculturistBean bean = new AgriculturistBean();

        bean.setCard_type(String.valueOf(((MetaBean) spn_cardType.getSelectedItem()).getItemId()));
        bean.setTitle(String.valueOf(((MetaBean) spn_title.getSelectedItem()).getItemId()));
        bean.setCard_no(edt_cardNo.getText().toString());
        bean.setFirstname(edt_fname.getText().toString());
        bean.setLastname(edt_lname.getText().toString());

        bean.setHome_no(edt_home.getText().toString());
        bean.setMoo_no(edt_mooNo.getText().toString());
        bean.setGroup_no(edt_group.getText().toString());
        bean.setProvince_id(String.valueOf(((MetaBean) spn_province.getSelectedItem()).getItemId()));
        bean.setAmphur_id(String.valueOf(((MetaBean) spn_amphoe.getSelectedItem()).getItemId()));
        bean.setTambol_id(String.valueOf(((MetaBean) spn_tambol.getSelectedItem()).getItemId()));
        bean.setVillage_no(edit_village.getText().toString());
        bean.setZipcode(edt_zip.getText().toString());

        bean.setOccupation1(edt_occupation1.getText().toString());
        bean.setOccupation2(edt_occupation2.getText().toString());
        bean.setFree_time(edt_freetime.getText().toString());
        bean.setMember_all(edt_memberAll.getText().toString());
        bean.setMember_type1(edt_memberType1.getText().toString());
        bean.setMember_type2(edt_memberType2.getText().toString());
        bean.setMember_type3(edt_memberType3.getText().toString());

        bean.setIncome_all(edt_incomeAll.getText().toString());
        bean.setIncomes1(edt_income1.getText().toString());
        bean.setIncomes2(edt_income2.getText().toString());

        bean.setExpenses_all(edt_expensesAll.getText().toString());
        bean.setExpenses1(edt_expenses1.getText().toString());
        bean.setExpenses2(edt_expenses2.getText().toString());

        bean.setRemark1("waiting");

        return bean;
    }
}
