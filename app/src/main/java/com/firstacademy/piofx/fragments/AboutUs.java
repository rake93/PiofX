package com.firstacademy.piofx.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.Home;
import com.firstacademy.piofx.activities.PracticeQuiz;
import com.firstacademy.piofx.utils.Constants;


public class AboutUs extends Fragment {
    private RelativeLayout meetTeamLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_about_us, container, false);
        initializeViews(view);
        initializeClickListeners();
        return view;
    }

    private void initializeViews(View view) {
        meetTeamLayout=(RelativeLayout)view.findViewById(R.id.faboutus_meet_team_layout);
    }


    private void initializeClickListeners() {
        meetTeamLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutus=new Intent(getActivity(), PracticeQuiz.class);
                aboutus.putExtra("intent","aboutus");
                startActivity(aboutus);
            }
        });
    }

}
