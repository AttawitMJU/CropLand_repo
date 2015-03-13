package com.hrdi.survey.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hrdi.survey.R;

/**
 * Created by attawit on 3/10/15 AD.
 */
public class FarmerDialog extends DialogFragment {

    // UI references
    private EditText empNameEtxt;
    private EditText empSalaryEtxt;
    private LinearLayout submitLayout;

    public static final String ARG_ITEM_ID = "farmer_dialog_fragment";

    public interface FarmerDialogListener {
        void onFinishDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_farmer, null);
        builder.setView(view);
        findView(view);
        builder.setTitle("เพิ่มเกษตรกร");
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO : Create Task for Add New Farmer

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

    private void findView(View view){
        empNameEtxt = (EditText) view.findViewById(R.id.etxt_name);
        empSalaryEtxt = (EditText) view.findViewById(R.id.etxt_salary);
        submitLayout = (LinearLayout) view
                .findViewById(R.id.layout_submit);
        submitLayout.setVisibility(View.GONE);
    }
}
