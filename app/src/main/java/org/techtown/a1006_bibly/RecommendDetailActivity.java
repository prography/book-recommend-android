package org.techtown.a1006_bibly;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecommendDetailActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbarText)
    TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        String[] type_kinds = intent.getStringArrayExtra("type_kinds");
        toolbarText.setText(type + "에 따른 책 추천");

        //kind tab
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < type_kinds.length; i++)
            tabLayout.addTab(tabLayout.newTab().setText(type_kinds[i]));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final RecommendDetailActivity.PagerAdapter adapter
                = new RecommendDetailActivity.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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


    }

    @OnClick(R.id.back)
    void Click(View v) {
        finish();
    }




    class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = new RecommendDetailKindFragment_Kind1();
                    break;
                case 1:
                    fragment = new RecommendDetailKindFragment_Kind2();
                    break;
                case 2:
                    fragment = new RecommendDetailKindFragment_Kind3();
                    break;
                case 3:
                    fragment = new RecommendDetailKindFragment_Kind4();
                    break;
                case 4:
                    fragment = new RecommendDetailKindFragment_Kind5();
                    break;

                default:
                    return null;
            }
            return fragment;

        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }


    }
}
