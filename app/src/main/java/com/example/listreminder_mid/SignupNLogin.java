package com.example.listreminder_mid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class SignupNLogin extends AppCompatActivity {
    // initialize variable
    TabLayout nav;
    ViewPager viewPager;
    FloatingActionButton fb, google, AppInst;
    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupnlogin);

        // identifies the variable from the id that has been set
        nav = (TabLayout) findViewById(R.id.tab_first);
        viewPager = findViewById(R.id.view_first);
        fb = (FloatingActionButton)findViewById(R.id.fab_fb);
        google = (FloatingActionButton)findViewById(R.id.fab_google);
        AppInst = (FloatingActionButton)findViewById(R.id.fab_instagram);

        // fill tab
        nav.addTab(nav.newTab().setText("Login"));
        nav.addTab(nav.newTab().setText("SignUp"));
        nav.setTabGravity(TabLayout.GRAVITY_FILL);

        // switch tab
        final LoginAdapterFragment adapter = new LoginAdapterFragment(getSupportFragmentManager(), this, nav.getTabCount());
        viewPager.setAdapter(adapter);

        // Switch tabs when the user click button
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(nav));
        nav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Log.i("TAG", "onTabSelected:" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Log.i("TAG", "onTabUnselected:"+ tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Log.i("TAG", "onTabReselected:"+ tab.getPosition());
            }
        });


        // floats translation
        fb.setTranslationY(300);
        google.setTranslationY(300);
        AppInst.setTranslationY(300);
        //nav.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        AppInst.setAlpha(v);
        //nav.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        AppInst.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        //nav.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }
}