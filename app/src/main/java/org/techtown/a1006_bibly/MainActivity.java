package org.techtown.a1006_bibly;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;

    class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    MainFragment tab1 = new MainFragment();
                    return tab1;

                case 1:
                    RatingFragment tab2 = new RatingFragment();
                    return tab2;

                case 2:
                    TabFragment3 tab3 = new TabFragment3();
                    return tab3;
                case 3:
                    MyPageFragment tab4 = new MyPageFragment();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("home"));
        tabLayout.addTab(tabLayout.newTab().setText("rating"));
        tabLayout.addTab(tabLayout.newTab().setText("friend"));
        tabLayout.addTab(tabLayout.newTab().setText("mypage"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();

    }

}
