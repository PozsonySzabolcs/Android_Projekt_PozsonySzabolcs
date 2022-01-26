package com.example.retrofitplayers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, new Fragment1());
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();

                int position = tab.getPosition();

                fragmentTransaction.replace(R.id.fragment_container, new Fragment1());

                if (position == 0) {
                    fragmentTransaction.replace(R.id.fragment_container, new Fragment1());
                    fragmentTransaction.commit();
                } else if (position == 1) {
                    fragmentTransaction.replace(R.id.fragment_container, new Fragment2());
                    fragmentTransaction.commit();
                } else if (position == 2) {
                    fragmentTransaction.replace(R.id.fragment_container, new Fragment3());
                    fragmentTransaction.commit();
                } else {
                    fragmentTransaction.replace(R.id.fragment_container, new Fragment4());
                    fragmentTransaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}