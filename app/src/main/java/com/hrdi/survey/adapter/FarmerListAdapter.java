package com.hrdi.survey.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.hrdi.survey.R;
import com.hrdi.survey.model.AgriculturistBean;
import com.hrdi.survey.model.SurveyActivityBean;

import java.util.List;

/**
 * Created by attawit on 2/1/15 AD.
 */
public class FarmerListAdapter extends ArrayAdapter<AgriculturistBean> implements Filterable{


    List<AgriculturistBean> list;
    private Context context;

    public FarmerListAdapter(Context context, List<AgriculturistBean> list) {
        super(context, R.layout.list_farmer_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public AgriculturistBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(list.get(position).getCard_no());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_farmer_item, null);
            holder = new ViewHolder();

            holder.txt_cardNo = (TextView) convertView
                    .findViewById(R.id.txt_cardNo);
            holder.txt_name = (TextView) convertView
                    .findViewById(R.id.txt_name);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AgriculturistBean bean = (AgriculturistBean) getItem(position);
        holder.txt_cardNo.setText(bean.getCard_no());
        holder.txt_name.setText(bean.getFirstname()+"  "+bean.getLastname());



        return convertView;
    }

    @Override
    public void add(AgriculturistBean bean) {
        list.add(bean);
        notifyDataSetChanged();
        super.add(bean);
    }

    @Override
    public void remove(AgriculturistBean bean) {
        list.remove(bean);
        notifyDataSetChanged();
        super.remove(bean);
    }

    @Override
    public void clear() {
        super.clear();
    }

    private class ViewHolder {
        TextView txt_cardNo;
        TextView txt_name;

    }


}
