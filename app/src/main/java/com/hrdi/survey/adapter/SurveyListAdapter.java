package com.hrdi.survey.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hrdi.survey.R;
import com.hrdi.survey.model.SurveyBean;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by attawit on 2/1/15 AD.
 */
public class SurveyListAdapter extends ArrayAdapter<SurveyBean> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy/MM/dd hh:mm", Locale.ENGLISH);
    List<SurveyBean> surveyBeans;
    private Context context;

    public SurveyListAdapter(Context context, List<SurveyBean> surveyBeans) {
        super(context, R.layout.list_survey_item, surveyBeans);
        this.context = context;
        this.surveyBeans = surveyBeans;
    }

    @Override
    public int getCount() {
        return surveyBeans.size();
    }

    @Override
    public SurveyBean getItem(int position) {
        return surveyBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(surveyBeans.get(position).getSurvey_id());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_survey_item, null);
            holder = new ViewHolder();

            holder.txt_survey_date = (TextView) convertView
                    .findViewById(R.id.txt_survey_date);
            holder.txt_land_code = (TextView) convertView
                    .findViewById(R.id.txt_land_code);
            holder.txt_agri_name = (TextView) convertView
                    .findViewById(R.id.txt_agri_name);
            holder.txt_moo = (TextView) convertView
                    .findViewById(R.id.txt_moo);
            holder.txt_area = (TextView) convertView
                    .findViewById(R.id.txt_area);
            holder.txt_ext_project = (TextView) convertView
                    .findViewById(R.id.txt_ext_project);
            holder.txt_status = (TextView) convertView
                    .findViewById(R.id.txt_status);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SurveyBean surveyBean = (SurveyBean) getItem(position);
        //holder.txt_survey_date.setText(formatter.format(surveyBean.getSurvey_Date()));
        holder.txt_survey_date.setText(surveyBean.getSurvey_Date());
        holder.txt_land_code.setText(surveyBean.getLand_No() );
//        holder.txt_agri_name.setText(surveyBean.getAgriculturistBean().getFirstname() + "  " + surveyBean.getAgriculturistBean().getLastname());
        holder.txt_agri_name.setText(surveyBean.getFirstName() + "  " + surveyBean.getLastName());
        holder.txt_moo.setText(surveyBean.getMooban() );
        holder.txt_area.setText(surveyBean.getArea_status());
        holder.txt_ext_project.setText(surveyBean.getExt_Project_name());
        holder.txt_status.setText(surveyBean.getRemark1());

        return convertView;
    }

    @Override
    public void add(SurveyBean surveyBean) {
        surveyBeans.add(surveyBean);
        notifyDataSetChanged();
        super.add(surveyBean);
    }

    @Override
    public void remove(SurveyBean surveyBean) {
        surveyBeans.remove(surveyBean);
        notifyDataSetChanged();
        super.remove(surveyBean);
    }

    @Override
    public void clear() {
        super.clear();
    }

    private class ViewHolder {
        TextView txt_survey_date;
        TextView txt_land_code;
        TextView txt_agri_name;
        TextView txt_moo;
        TextView txt_area;
        TextView txt_ext_project;
        TextView txt_status;
    }
}
