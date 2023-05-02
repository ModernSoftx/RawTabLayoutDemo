package com.nain.rawtablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    ViewPager2 viewPager;
    FragmentStateAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);

        //tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));


        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()); // Use the one defined in this package
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("TAB " + (position + 1))).attach();
    }
}