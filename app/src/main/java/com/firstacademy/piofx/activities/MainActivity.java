package com.firstacademy.piofx.activities;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.firstacademy.piofx.R;
import com.firstacademy.piofx.fragments.AboutUs;
import com.firstacademy.piofx.fragments.HomeFragment;
import com.firstacademy.piofx.fragments.Profile;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setTitle(null);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

    }

    private void initializeViews() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        loadDefaultFragment();
    }

    private void loadDefaultFragment() {
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
            fragmentTransaction.replace(R.id.home_container_body, new AboutUs());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_home) {
            fragmentTransaction.replace(R.id.home_container_body, new HomeFragment());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }
        if (id == R.id.action_profile) {
            fragmentTransaction.replace(R.id.home_container_body, new Profile());
            setSupportActionBar(toolbar);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
