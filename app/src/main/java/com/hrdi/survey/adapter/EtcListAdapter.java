package com.hrdi.survey.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hrdi.survey.R;
import com.hrdi.survey.model.SurveyDetailEtcBean;

import java.util.List;

/**
 * Created by attawit on 2/1/15 AD.
 */
public class EtcListAdapter extends ArrayAdapter<SurveyDetailEtcBean> {


    List<SurveyDetailEtcBean> surveyEtcList;
    private Context context;

    public EtcListAdapter(Context context, List<SurveyDetailEtcBean> beans) {
        super(context, R.layout.list_survey_activity, beans);
        this.context = context;
        this.surveyEtcList = beans;
    }

    @Override
    public int getCount() {
        return surveyEtcList.size();
    }

    @Override
    public SurveyDetailEtcBean getItem(int position) {
        return surveyEtcList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(surveyEtcList.get(position).getEtc_id());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_survey_remark, null);
            holder = new ViewHolder();

            holder.txt_detail = (TextView) convertView
                    .findViewById(R.id.txt_detail);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SurveyDetailEtcBean bean = (SurveyDetailEtcBean) getItem(position);
        holder.txt_detail.setText(bean.getDetail());



        return convertView;
    }

    @Override
    public void add(SurveyDetailEtcBean surveyBean) {
        surveyEtcList.add(surveyBean);
        notifyDataSetChanged();
        super.add(surveyBean);
    }

    @Override
    public void remove(SurveyDetailEtcBean surveyBean) {
        surveyEtcList.remove(surveyBean);
        notifyDataSetChanged();
        super.remove(surveyBean);
    }

    @Override
    public void clear() {
        super.clear();
    }

    private class ViewHolder {
        TextView txt_detail;
    }
}
