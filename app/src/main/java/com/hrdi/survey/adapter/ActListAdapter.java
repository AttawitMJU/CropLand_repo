package com.hrdi.survey.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hrdi.survey.R;
import com.hrdi.survey.model.SurveyActivityBean;

import java.util.List;

/**
 * Created by attawit on 2/1/15 AD.
 */
public class ActListAdapter extends ArrayAdapter<SurveyActivityBean> {


    List<SurveyActivityBean> surveyActivityList;
    private Context context;

    public ActListAdapter(Context context, List<SurveyActivityBean> beans) {
        super(context, R.layout.list_survey_activity, beans);
        this.context = context;
        this.surveyActivityList = beans;
    }

    @Override
    public int getCount() {
        return surveyActivityList.size();
    }

    @Override
    public SurveyActivityBean getItem(int position) {
        return surveyActivityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(surveyActivityList.get(position).getActivity_id());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_survey_activity, null);
            holder = new ViewHolder();

            holder.edt_activity = (TextView) convertView
                    .findViewById(R.id.edt_activity);
            holder.edt_outcome = (TextView) convertView
                    .findViewById(R.id.edt_outcome);
            holder.edt_repeat = (TextView) convertView
                    .findViewById(R.id.edt_repeat);
            holder.edt_survive = (TextView) convertView
                    .findViewById(R.id.edt_survive);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SurveyActivityBean surveyBean = (SurveyActivityBean) getItem(position);
        holder.edt_activity.setText(surveyBean.getActivity1());
        holder.edt_outcome.setText(surveyBean.getOutcome1());
        holder.edt_repeat.setText(surveyBean.getRepeat1());
        holder.edt_survive.setText(surveyBean.getSurvive1());


        return convertView;
    }

    @Override
    public void add(SurveyActivityBean surveyBean) {
        surveyActivityList.add(surveyBean);
        notifyDataSetChanged();
        super.add(surveyBean);
    }

    @Override
    public void remove(SurveyActivityBean surveyBean) {
        surveyActivityList.remove(surveyBean);
        notifyDataSetChanged();
        super.remove(surveyBean);
    }

    @Override
    public void clear() {
        super.clear();
    }

    private class ViewHolder {
        TextView edt_activity;
        TextView edt_outcome;
        TextView edt_repeat;
        TextView edt_survive;
    }
}
