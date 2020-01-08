package com.destack.decision;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.destack.decision.ui.fragments.YesNoFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(YesNoFragment.newInstance(), "Yes/No");

        viewPager = findViewById(R.id.vpContainer);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setOffscreenPageLimit(mPagerAdapter.getCount()-1);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
