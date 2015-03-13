package com.hrdi.survey.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hrdi.survey.R;
import com.hrdi.survey.model.LandUseBean;

import java.util.List;

public class LandUseListAdapter extends ArrayAdapter<LandUseBean> {


    List<LandUseBean> landUseList;
    private Context context;

    public LandUseListAdapter(Context context, List<LandUseBean> landUseList) {
        super(context, R.layout.list_survey_item, landUseList);
        this.context = context;
        this.landUseList = landUseList;
    }

    @Override
    public int getCount() {
        return landUseList.size();
    }

    @Override
    public LandUseBean getItem(int position) {
        return landUseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_landuse_item, null);

            holder = new ViewHolder();
            holder.txt_plant_type = (TextView) convertView.findViewById(R.id.txt_plant_type);
            holder.txt_plant_name = (TextView) convertView.findViewById(R.id.txt_plant_name);
            holder.txt_plant_detail = (TextView) convertView.findViewById(R.id.txt_plant_detail);
            holder.txt_area = (TextView) convertView.findViewById(R.id.txt_area);
            holder.txt_start_crop = (TextView) convertView.findViewById(R.id.txt_start_crop);
            holder.txt_end_crop = (TextView) convertView.findViewById(R.id.txt_end_crop);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LandUseBean landUse = (LandUseBean) getItem(position);
        holder.txt_plant_type.setText(landUse.getPlant_TypeName());
        holder.txt_plant_name.setText(landUse.getPlant_idName());
        holder.txt_plant_detail.setText(landUse.getPlant_detail_idName());
        holder.txt_area.setText(landUse.getArea());
        holder.txt_start_crop.setText(landUse.getStart_crop());
        holder.txt_end_crop.setText(landUse.getEnd_crop());


        return convertView;
    }

    @Override
    public void add(LandUseBean landUse) {
        landUseList.add(landUse);
        notifyDataSetChanged();
        super.add(landUse);
    }

    @Override
    public void remove(LandUseBean landUse) {
        landUseList.remove(landUse);
        notifyDataSetChanged();
        super.remove(landUse);
    }

    private class ViewHolder {
        TextView txt_plant_type;
        TextView txt_plant_name;
        TextView txt_plant_detail;
        TextView txt_area;
        TextView txt_start_crop;
        TextView txt_end_crop;
    }
}

