package com.hrdi.survey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hrdi.survey.R;

public class UpdateSurveyFragment extends Fragment {

    public UpdateSurveyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_update_survey, container, false);

        return rootView;
    }
}
