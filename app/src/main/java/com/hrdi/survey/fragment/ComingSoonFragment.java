package com.hrdi.survey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hrdi.survey.R;
import com.hrdi.survey.control.SurveyDAO;

public class ComingSoonFragment extends Fragment {

    ImageView imageView;

    public ComingSoonFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.img_comming);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurveyDAO surveyDAO = new SurveyDAO(getActivity());
                surveyDAO.resetStatus("waiting");
            }
        });


        return rootView;
    }
}
