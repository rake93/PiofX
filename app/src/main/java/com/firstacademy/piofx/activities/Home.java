package com.firstacademy.piofx.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.fragments.AboutUs;
import com.firstacademy.piofx.fragments.HomeFragment;
import com.firstacademy.piofx.fragments.Profile;

public class Home extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initializeViews();
    }

    private void initializeViews() {
        toolbar=(Toolbar)findViewById(R.id.home_toolbar);
        loadDefaultFragment();
    }

    private void loadDefaultFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_container_body, new HomeFragment());
        setSupportActionBar(toolbar);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_container_body, new AboutUs());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_home) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_container_body, new HomeFragment());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_profile) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.home_container_body, new Profile());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
