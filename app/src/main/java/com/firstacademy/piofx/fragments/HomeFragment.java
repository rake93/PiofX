package com.firstacademy.piofx.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.adapters.HomeAdapter;
import com.firstacademy.piofx.models.HomeModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView homeRecycler;
    private Button signup;
    private TextView signin;
    private static ArrayList<HomeModel> data;

    Integer[] idArray={1,2,3,4,5,6,7};
    Integer[] imagesArray={R.drawable.bv_socks_shadow,R.drawable.bv_shoess_hadow,
            R.drawable.iv_bicycle_shadow,R.drawable.iv_scooter_shadow,R.drawable.motor_cycle,
            R.drawable.car,R.drawable.team};
   String[] headingArray={"Basic Vocabulary","Basic Vocabulary","Intermediate Vocabulary",
           "Intermediate Vocabulary","Intermediate Vocabulary","Intermediate Vocabulary",
           "Intermediate Vocabulary"};
   String[] descArray={"Enough for you to walk around the bedroom, and to keep your feet warm, " +
            "but not much else.",
            "You can go out of the bedroom and take a walk.But you can go only as far as your feet" +
                    " can take you,and it is not too far!",
            "Shoes and running are all good, but a bicycle will get you places faster and" +
                    " farther! But, you dont get to save the world",
            "Mastered the bicycle. Proceed to the scooter. But whoever saved the world on scooter ?",
            "Mastered the bicycle. Proceed to the scooter. But whoever saved the world on scooter ?",
            "Mastered the bicycle. Proceed to the scooter. But whoever saved the world on scooter ?",
            "Mastered the bicycle. Proceed to the scooter. But whoever saved the world on scooter ?"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(view);
        initializeClickListeners();
        return view;
    }


    private void initializeViews(View view) {
        homeRecycler=(RecyclerView)view.findViewById(R.id.fhome_recycler);
        signup=(Button) view.findViewById(R.id.fhome_bnSignup);
        signin=(TextView)view.findViewById(R.id.fhome_tvSingin);
        data=new ArrayList<HomeModel>();

        setRecyclerView();

    }

    private void setRecyclerView() {
        for (int i=0;i<idArray.length;i++){
            data.add(new HomeModel(idArray[i],imagesArray[i],headingArray[i],descArray[i]));
        }
        homeRecycler.setHasFixedSize(true);
        homeRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        homeRecycler.setAdapter(new HomeAdapter(getActivity(),data));
    }


    private void initializeClickListeners() {
        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
