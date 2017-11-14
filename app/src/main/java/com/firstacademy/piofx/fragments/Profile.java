package com.firstacademy.piofx.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.utils.Constants;
import com.firstacademy.piofx.utils.Fonts;

public class Profile extends Fragment {
    private Typeface openSansSemiBold,openSansRegular,openSansLight;
    private Button bnRateUs,bnShareTheApp,bnResetProgress,bnSignOut;
    private TextView tv_profile_welcome_name,tv_profile_Name,tv_profile_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
//        Fonts.setFont(personal,Fonts.opensans,"TextView");
        initializeViews(view);
        setFont();
        return view;
    }

    private void initializeViews(View view) {
        tv_profile_welcome_name = (TextView) view.findViewById(R.id.tv_profile_welcome_name);
        tv_profile_Name = (TextView) view.findViewById(R.id.tv_profile_Name);
        tv_profile_email = (TextView) view.findViewById(R.id.tv_profile_email);

        bnRateUs=(Button)view.findViewById(R.id.profile_rateUs);
        bnShareTheApp=(Button)view.findViewById(R.id.profile_share_app);
        bnResetProgress=(Button)view.findViewById(R.id.profile_reset_progress);
        bnSignOut=(Button)view.findViewById(R.id.profile_sign_out);
    }

    @Override
    public void onStart() {
        super.onStart();
        tv_profile_welcome_name.setText("Welcome " + Constants.userName + "!");
        tv_profile_Name.setText(Constants.userName);
        tv_profile_email.setText(Constants.email);
    }

    private void setFont() {
        openSansSemiBold=Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Open_Sans/OpenSans-SemiBold.ttf");
        openSansRegular=Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Open_Sans/OpenSans-Regular.ttf");
        openSansLight=Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/Open_Sans/OpenSans-Light.ttf");

//        bnRateUs.setTypeface(openSansRegular);
        Fonts.setFont(bnRateUs,Fonts.robotoMedium,"Button");
        Fonts.setFont(bnShareTheApp,Fonts.robotoMedium,"Button");
        Fonts.setFont(bnResetProgress,Fonts.robotoMedium,"Button");
        Fonts.setFont(bnSignOut,Fonts.robotoMedium,"Button");

    }
}
