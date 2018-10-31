package org.techtown.a1006_bibly;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment_RecommendType_RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<RecommendType> recommendTypes = new ArrayList<>();
    private Context context;
    private OnClickListener clickListener;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MainFragment_Books_RecyclerviewAdapter adapter;
    View view;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public MainFragment_RecommendType_RecyclerviewAdapter(Context context) {
        this.context = context;

        recommendTypes.add(new RecommendType("장르"));
        recommendTypes.add(new RecommendType("기분"));
        recommendTypes.add(new RecommendType("날씨"));
        recommendTypes.add(new RecommendType("친구"));
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //XML 가져오는 부분
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_fragment_recyclerview_item_recommend_type, parent, false);

        //books recyclerview 초기화
        recyclerView = (RecyclerView) view.findViewById(R.id.main_fragment_books_recyclerview);
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainFragment_Books_RecyclerviewAdapter(1, context);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(clickListener);

        return new ViewHolder(view);
    }

    String[] type_kinds = {"전체", "문학", "인문/사회", "자기계발", "만화"};
    //        final int position 대신 holder.getAdapterPosition()!!
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        //데이터를 넣어주는 부분. 바인딩하는 부분
        ((ViewHolder) holder).type.setText(recommendTypes.get(holder.getAdapterPosition()).type + "에 따른 책 추천");

        ((ViewHolder) holder).btn1.setText(type_kinds[0]);
        ((ViewHolder) holder).btn2.setText(type_kinds[1]);
        ((ViewHolder) holder).btn3.setText(type_kinds[2]);
        ((ViewHolder) holder).btn4.setText(type_kinds[3]);
        ((ViewHolder) holder).btn5.setText(type_kinds[4]);

        //https://stackoverflow.com/questions/37194653/fragment-replacing-in-recyclerview-item
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btn1:
                        clickListener.onButtonClick(((ViewHolder) holder).mainFragmentBooks_Recyclerview, //recyclerview
                                                    recommendTypes.get(holder.getAdapterPosition()).type, //type
                                                    type_kinds[0],                                        //btnKind
                                                    1);                                          //btnNum
                        break;
                    case R.id.btn2:
                        clickListener.onButtonClick(((ViewHolder) holder).mainFragmentBooks_Recyclerview, //recyclerview
                                                    recommendTypes.get(holder.getAdapterPosition()).type, //type
                                                    type_kinds[1],                                        //btnKind
                                                   2);                                          //btnNum
                        break;
                    case R.id.btn3:
                        clickListener.onButtonClick(((ViewHolder) holder).mainFragmentBooks_Recyclerview, //recyclerview
                                                    recommendTypes.get(holder.getAdapterPosition()).type, //type
                                                    type_kinds[2],                                        //btnKind
                                                    3);                                          //btnNum
                        break;
                    case R.id.btn4:
                        clickListener.onButtonClick(((ViewHolder) holder).mainFragmentBooks_Recyclerview, //recyclerview
                                                    recommendTypes.get(holder.getAdapterPosition()).type, //type
                                                    type_kinds[3],                                        //btnKind
                                                    4);                                          //btnNum
                        break;
                    case R.id.btn5:
                        clickListener.onButtonClick(((ViewHolder) holder).mainFragmentBooks_Recyclerview, //recyclerview
                                                    recommendTypes.get(holder.getAdapterPosition()).type, //type
                                                    type_kinds[4],                                        //btnKind
                                                    5);                                          //btnNum
                        break;
                    case R.id.btn_detail:
                    case R.id.type:
                        clickListener.onRecommendDetailButtonClick(recommendTypes.get(holder.getAdapterPosition()).type.toString(),
                                                                    type_kinds);
                        break;
                }
            }
        };

        ((ViewHolder) holder).btn1.setOnClickListener(listener);
        ((ViewHolder) holder).btn2.setOnClickListener(listener);
        ((ViewHolder) holder).btn3.setOnClickListener(listener);
        ((ViewHolder) holder).btn4.setOnClickListener(listener);
        ((ViewHolder) holder).btn5.setOnClickListener(listener);
        ((ViewHolder) holder).btnDetail.setOnClickListener(listener);
        ((ViewHolder) holder).type.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        //카운터
        return recommendTypes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.type)
        TextView type;
        @BindView(R.id.btn_detail)
        TextView btnDetail;
        @BindView(R.id.btn1)
        Button btn1;
        @BindView(R.id.btn2)
        Button btn2;
        @BindView(R.id.btn3)
        Button btn3;
        @BindView(R.id.btn4)
        Button btn4;
        @BindView(R.id.btn5)
        Button btn5;
        @BindView(R.id.linearLayout)
        LinearLayout linearLayout;
        @BindView(R.id.main_fragment_books_recyclerview)
        RecyclerView mainFragmentBooks_Recyclerview;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
