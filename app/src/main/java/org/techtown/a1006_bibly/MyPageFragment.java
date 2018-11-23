package org.techtown.a1006_bibly;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyPageFragment extends Fragment {

    private ImageButton imageButton;
    private TextView tv_ID;
    private Button all_book, rating, comment, read_date;

    //bottom fragment view pager
    private TabLayout tabLayout;
    private ViewPager viewpager;
    //   private ViewPagerAdapter adapter;

//    android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
//    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();


    //
//    public MyPageFragment() {
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_fragment, container, false);

//        MyListFragment myListFragment = new MyListFragment();
//        fragmentTransaction.add(myListFragment,);
//        fragmentTransaction.commit();

        imageButton = (ImageButton) view.findViewById(R.id.profile_img);

        imageButton.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT>=21){
            imageButton.setClipToOutline(true);
        }

        tv_ID =(TextView) view.findViewById(R.id.profile_ID);
        tv_ID.setText("Wonny");

        //button event
        all_book = (Button) view.findViewById(R.id.all_book);
        rating = (Button) view.findViewById(R.id.rating);
        comment = (Button) view.findViewById(R.id.comment);
        read_date = (Button) view.findViewById(R.id.read_date);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.profile_img:
                        Intent intent0 = new Intent(getActivity(), My_Profile.class);
                        startActivity(intent0);
                        break;
                    case R.id.all_book:
                        Intent intent1 = new Intent(getActivity() , My_AllBook.class);
                        startActivity(intent1);
                        break;
                    case R.id.rating:
                        Intent intent2 = new Intent(getActivity() , My_Rating.class);
                        startActivity(intent2);
                        break;
                    case R.id.comment:
                        Intent intent3 = new Intent(getActivity() , My_Comment.class);
                        startActivity(intent3);
                        break;
                    case R.id.read_date:
                        Intent intent4 = new Intent(getActivity() , My_ReadDate.class);
                        startActivity(intent4);
                        break;
                }
            }
        };
        imageButton.setOnClickListener(listener);
        all_book.setOnClickListener(listener);
        rating.setOnClickListener(listener);
        comment.setOnClickListener(listener);
        read_date.setOnClickListener(listener);

        return view;
    }//OnCreateView


}
