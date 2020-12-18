package com.example.laporankeuangan;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class PaymentActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getTabs();

    }

    public void getTabs(){
        final viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                viewPagerAdapter.addFragment(DashboardFragment.getInstance(), "Dashboard");
                viewPagerAdapter.addFragment(PaymentFragment.getInstance(), "Payment");
                viewPagerAdapter.addFragment(ReportFragment.getInstance(), "Report");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_dashboard_24);
                tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_payment_24);
                tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_assignment_24);

                BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).getOrCreateBadge();
                badgeDrawable.setVisible(true);
                badgeDrawable.setNumber(10);
            }
        });
    }



}