package org.techtown.a1006_bibly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class RecommendDetailKindFragment_Kind5 extends Fragment implements OnClickListener {
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_detail_kind_fragment, container, false);
        ButterKnife.bind(this, view);

        //recyclerview
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recommendDetailKindFragment_Recyclerview);
        context = view.getContext();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        RecommendDetailKindFragment_RecyclerViewAdapter adapter
                = new RecommendDetailKindFragment_RecyclerViewAdapter(5);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        return view;
    }

    public RecommendDetailKindFragment_Kind5() {

    }


    @Override
    public void onButtonClick(RecyclerView recyclerView, String type, String btnKind, int btnNum) {

    }

    @Override
    public void onBookClick(BookInfo bookInfo) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra("bookInfo", bookInfo);
        startActivity(intent);
    }

    @Override
    public void onRecommendDetailButtonClick(String type, String[] type_kinds) {

    }
}
