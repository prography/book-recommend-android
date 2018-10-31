package org.techtown.a1006_bibly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainFragment extends Fragment implements OnClickListener {

    Context context;
    RecyclerView recyclerView;
    View view;
    LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        //recyclerview
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.main_fragment_recommend_type_recyclerview);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        MainFragment_RecommendType_RecyclerviewAdapter adapter
                = new MainFragment_RecommendType_RecyclerviewAdapter(context);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);


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


//    public ArrayList<RecommendType> getLists(){
//        ArrayList<RecommendType> recommendTypes = new ArrayList<>();
//        recommendTypes.add(new RecommendType("장르"));
//        recommendTypes.add(new RecommendType("기분"));
//        recommendTypes.add(new RecommendType("날씨"));
//        return recommendTypes;
//    }

    @Override
    public void onButtonClick(RecyclerView recyclerView, String type, String btnKind, int btnNum) {
        Toast.makeText(context, "type:" + type + " / " + "kind:" + btnKind + "를 선택하였습니다.", Toast.LENGTH_SHORT).show();

        //recyclerview
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        MainFragment_Books_RecyclerviewAdapter adapter
                = new MainFragment_Books_RecyclerviewAdapter(btnNum, context);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onBookClick(BookInfo bookInfo) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("bookInfo", bookInfo);
        startActivity(intent);
    }

    @Override
    public void onRecommendDetailButtonClick(String type, String[] type_kinds) {
        Intent intent = new Intent(context, RecommendDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("type_kinds", type_kinds);
        startActivity(intent);
//        Gson gson = new Gson();
//        Book book = gson.toJson(tmp, Book.class);
    }

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
}

