package com.firstacademy.piofx.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firstacademy.piofx.PiofXApp;
import com.firstacademy.piofx.R;
import com.firstacademy.piofx.activities.SignIn;
import com.firstacademy.piofx.activities.SignUp;
import com.firstacademy.piofx.adapters.HomeAdapter;
import com.firstacademy.piofx.data.db.model.DaoSession;
import com.firstacademy.piofx.data.db.model.Question;
import com.firstacademy.piofx.data.db.repository.QuestionRepository;
import com.firstacademy.piofx.models.HomeModel;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.Fonts;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {
    private RecyclerView homeRecycler;
    private Button bnSignup,bnRateUs,bnShareApp;
    private TextView signin, fhome_display_name;
    private LinearLayout loginLayout;
    private RelativeLayout welcomeLayout;
    private static ArrayList<HomeModel> data;
    private List<Question> overAllProgressCount;
    Integer[] idArray;
    Integer[] imagesArray;
    String[] headingArray;
    String[] descArray;

    private DaoSession daoSession;
    private QuestionRepository mQuestionRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        daoSession = ((PiofXApp) getActivity().getApplication()).getDaoSession();
        mQuestionRepository = new QuestionRepository(daoSession);

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(view);
        initializeClickListeners();
        setFont();
        return view;
    }

    private void initializeViews(View view) {
        homeRecycler=(RecyclerView)view.findViewById(R.id.fhome_recycler);
        bnSignup=(Button) view.findViewById(R.id.fhome_bnSignup);
        bnRateUs=(Button) view.findViewById(R.id.fhome_bnRateUs);
        bnShareApp=(Button) view.findViewById(R.id.fhome_bnShareApp);
        signin=(TextView)view.findViewById(R.id.fhome_tvSingin);
        loginLayout=(LinearLayout) view.findViewById(R.id.fhome_login_layout);
        welcomeLayout=(RelativeLayout) view.findViewById(R.id.fhome_welcome_layout);
        fhome_display_name = (TextView) view.findViewById(R.id.fhome_display_name);
        data=new ArrayList<HomeModel>();

        idArray= new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        imagesArray= new Integer[]{R.drawable.sock, R.drawable.shoe,
                R.drawable.cycle, R.drawable.scooter, R.drawable.bike,
                R.drawable.car, R.drawable.suit, R.drawable.team};
        headingArray= new String[]{getActivity().getResources().getString(R.string.basic_1),
                getActivity().getResources().getString(R.string.basic_2),
                getActivity().getResources().getString(R.string.inter_1),
                getActivity().getResources().getString(R.string.inter_2),
                getActivity().getResources().getString(R.string.advanced_1),
                getActivity().getResources().getString(R.string.advanced_2),
                getActivity().getResources().getString(R.string.super_hero),
                getActivity().getResources().getString(R.string.team)};
        descArray= new String[]{ getActivity().getResources().getString(R.string.basic_vocab_card1_content),
                getActivity().getResources().getString(R.string.basic_vocab_card2_content),
                getActivity().getResources().getString(R.string.inter_vocab_card1_content),
                getActivity().getResources().getString(R.string.inter_vocab_card2_content),
                getActivity().getResources().getString(R.string.advanced_1_content),
                getActivity().getResources().getString(R.string.advanced_2_content),
                getActivity().getResources().getString(R.string.super_hero_content),
                getActivity().getResources().getString(R.string.team_content)};

        if (Constants.isLoggedIn) {
            loginLayout.setVisibility(View.GONE);
            fhome_display_name.setText("Welcome " + Constants.userName);
            welcomeLayout.setVisibility(View.VISIBLE);
        }
        else{
            loginLayout.setVisibility(View.VISIBLE);
        }
        setRecyclerView();

    }

    private void setRecyclerView() {
        for (int i=0;i<idArray.length;i++){
            if (i<7)
                overAllProgressCount = mQuestionRepository.getOverAllPracticedCount(Constants.getQuestionCategoryByKey(headingArray[i]));
            data.add(new HomeModel(idArray[i],imagesArray[i],headingArray[i],descArray[i],overAllProgressCount));
        }
        homeRecycler.setHasFixedSize(true);
        homeRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        homeRecycler.setAdapter(new HomeAdapter(getActivity(),data));
    }


    private void initializeClickListeners() {
        bnSignup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void setFont() {
        Fonts.setFont(bnSignup,Fonts.robotoMedium,"Button");
        Fonts.setFont(bnRateUs,Fonts.robotoMedium,"Button");
        Fonts.setFont(bnShareApp,Fonts.robotoMedium,"Button");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fhome_bnSignup:
                startActivity(new Intent(getActivity(), SignUp.class));
                break;
            case R.id.fhome_tvSingin:
                startActivity(new Intent(getActivity(), SignIn.class));
                break;
            case R.id.fhome_bnRateUs:

                break;
            case R.id.fhome_bnShareApp:
                break;
        }
    }

}
