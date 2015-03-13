package com.hrdi.survey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hrdi.survey.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    final static String TAG_FRAGMENT = "HOME_FRAGMENT";

    ImageView img_menu1, img_menu2, img_menu3, img_menu4, img_menu5, img_menu6;

    public HomeFragment() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        findViewsById(rootView);
        setListeners();
        return rootView;
    }

    private void findViewsById(View view) {
        img_menu1 = (ImageView) view.findViewById(R.id.img_menu1);
        img_menu2 = (ImageView) view.findViewById(R.id.img_menu2);
        img_menu3 = (ImageView) view.findViewById(R.id.img_menu3);
        img_menu4 = (ImageView) view.findViewById(R.id.img_menu4);
        img_menu5 = (ImageView) view.findViewById(R.id.img_menu5);
        img_menu6 = (ImageView) view.findViewById(R.id.img_menu6);
    }

    private void setListeners() {
        img_menu1.setOnClickListener(this);
        img_menu2.setOnClickListener(this);
        img_menu3.setOnClickListener(this);
        img_menu4.setOnClickListener(this);
        img_menu5.setOnClickListener(this);
        img_menu6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        if (view == img_menu1) {
            fragment = new SurveyFragment();
        } else if (view == img_menu2) {
            fragment = new SurveyDataListFragment();
        } else if (view == img_menu3) {
            fragment = new StartProjectFragment();
        } else if (view == img_menu4) {
            fragment = new PublicHearingFragment();
        } else if (view == img_menu5) {
            fragment = new SuggestFragment();
        } else if (view == img_menu6) {
            fragment = new ConservationFragment();
        }

        if (fragment != null) {
            FragmentTransaction transaction =getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment).addToBackStack(null).commit();
        }
    }
}
