package org.techtown.a1006_bibly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment implements OnClickListener, View.OnClickListener {

    DatabaseHelper database;
    RecyclerView recyclerView;
    FeedFragment_RecyclerviewAdapter recycler;
    List<DataModel> datamodel;

    Context context;
    View view;

    @BindView(R.id.btn_new_post)
    ImageView btnNewPost;
    @BindView(R.id.text_new_post)
    TextView textNewPost;

    //LinearLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.feed_fragment, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();

        datamodel = new ArrayList<DataModel>();
        recyclerView = (RecyclerView) view.findViewById(R.id.home_fragment_post_recyclerview);

        database = new DatabaseHelper(context);
        datamodel = database.getdata();
        recycler = new FeedFragment_RecyclerviewAdapter(datamodel);


        Log.i("HIteshdata", "" + datamodel);
        //recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(reLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycler);
        recycler.setClickListener(this);

        btnNewPost.setOnClickListener(this);
        textNewPost.setOnClickListener(this);

//        String temp = "{\"key\":1}";
//
//
//        try {
//            JSONObject jsonObject = new JSONObject(temp);
//            int str = jsonObject.getInt("key");
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return view;
    }

    @Override
    public void onButtonClick(RecyclerView recyclerView, String type, String btnKind, int btnNum) {

    }

    @Override
    public void onBookClick(BookInfo bookInfo) {

    }

    @Override
    public void onRecommendDetailButtonClick(String type, String[] type_kinds) {

    }

    @Override
    public void onPostClick(DataModel dataModel) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra("dataModel", dataModel);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_post:
            case R.id.text_new_post:
                Intent intent = new Intent(context, NewPostActivity.class);
                startActivity(intent);
                break;
        }
    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        null.unbind();
//    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        null.unbind();
//    }

//Gson gson = new Gson();
    //Book book = gson.toJson(tmp, Book.class);
//    String tmp  = "{\n" +
//            "\t\"title\":\"책이름\",\n" +
//            "\"author\":\"저자이름\",\n" +
//            "\"count\" :1\n" +
//            "\n" +
//            "\n" +
//            "}";
//
//    class Book{
//        String title;
//        String author;
//        int count;
//
//    }
//
//
////    public ArrayList<RecommendType> getLists(){
////        ArrayList<RecommendType> recommendTypes = new ArrayList<>();
////        recommendTypes.add(new RecommendType("장르"));
////        recommendTypes.add(new RecommendType("기분"));
////        recommendTypes.add(new RecommendType("날씨"));
////        return recommendTypes;
////    }


}

