package org.techtown.a1006_bibly;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingFragment extends Fragment implements OnClickListener {
    @BindView(R.id.fragment2_recyclerview1)
    RecyclerView fragment2Recyclerview1;

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rating_fragment, container, false);
        ButterKnife.bind(this, view);

        //recyclerview
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment2_recyclerview1);
        context = view.getContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RatingFragment_RecyclerViewAdapter adapter = new RatingFragment_RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        return view;
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


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        null.unbind();
//    }

//    @OnClick(R.id.review_btn) void click(View v) {
//        //평가완료 버튼 누르면 해당 책 상세정보로 가기
//        Intent intent = new Intent(getActivity(), BookDetailActivity.class);
//        intent.putExtra("book", book.getId());
//        intent.putExtra("title", title.getText().toString());
//        intent.putExtra("author", author.getText().toString());
//        startActivity(intent);
//    }

}