package org.techtown.a1006_bibly;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendDetailKindFragment_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private ArrayList<BookInfo> bookInfos = new ArrayList<>();
    private OnClickListener clickListener;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    int[][] dummyBookImage = {
            {R.drawable.dummy_1_1, R.drawable.dummy_1_2, R.drawable.dummy_1_3,
                    R.drawable.dummy_1_4, R.drawable.dummy_1_5, R.drawable.dummy_1_6},

            {R.drawable.dummy_2_1, R.drawable.dummy_2_2, R.drawable.dummy_2_3,
                    R.drawable.dummy_2_4, R.drawable.dummy_2_5, R.drawable.dummy_2_6},

            {R.drawable.dummy_3_1, R.drawable.dummy_3_2, R.drawable.dummy_3_3,
                    R.drawable.dummy_3_4, R.drawable.dummy_3_5, R.drawable.dummy_3_6},

            {R.drawable.dummy_4_1, R.drawable.dummy_4_2, R.drawable.dummy_4_3,
                    R.drawable.dummy_4_4, R.drawable.dummy_4_5, R.drawable.dummy_4_6}};

    public RecommendDetailKindFragment_RecyclerViewAdapter(int index) {
        if (index == 1) {
            for (int i = 0; i < dummyBookImage.length; i++)
                for (int j = 0; j < dummyBookImage[0].length; j++)
                    bookInfos.add(new BookInfo(dummyBookImage[i][j],
                            "book_" + (i + 1) + "_" + (j + 1),
                            "author_" + (i + 1) + "_" + (j + 1)));

        } else if (index == 2) {
            for (int i = 0; i < dummyBookImage[0].length; i++)
                bookInfos.add(new BookInfo(dummyBookImage[0][i],
                        "book_" + index + "_" + (i + 1),
                        "author_" + index + "_" + (i + 1)));

        } else if (index == 3) {
            for (int i = 0; i < dummyBookImage[1].length; i++)
                bookInfos.add(new BookInfo(dummyBookImage[1][i],
                        "book_" + index + "_" + (i + 1),
                        "author_" + index + "_" + (i + 1)));

        } else if (index == 4) {
            for (int i = 0; i < dummyBookImage[2].length; i++)
                bookInfos.add(new BookInfo(dummyBookImage[2][i],
                        "book_" + index + "_" + (i + 1),
                        "author_" + index + "_" + (i + 1)));

        } else if (index == 5) {
            for (int i = 0; i < dummyBookImage[3].length; i++)
                bookInfos.add(new BookInfo(dummyBookImage[3][i],
                        "book_" + index + "_" + (i + 1),
                        "author_" + index + "_" + (i + 1)));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //XML 가져오는 부분
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_detail_kind_fragment_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //데이터를 넣어주는 부분. 바인딩하는 부분
        ((ViewHolder) holder).book.setImageResource(bookInfos.get(position).book);
        ((ViewHolder) holder).title.setText(bookInfos.get(position).title);
        ((ViewHolder) holder).author.setText(bookInfos.get(position).author);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.book:
                    case R.id.title:
                    case R.id.author:
                    case R.id.layout:
                        clickListener.onBookClick(bookInfos.get(position));
                        break;
                }
            }

        };
        ((ViewHolder) holder).book.setOnClickListener(listener);
        ((ViewHolder) holder).title.setOnClickListener(listener);
        ((ViewHolder) holder).author.setOnClickListener(listener);
        ((ViewHolder) holder).reviewStar.setOnClickListener(listener);
        ((ViewHolder) holder).layout.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return bookInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.book)
        ImageView book;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.review_star)
        RatingBar reviewStar;
        @BindView(R.id.layout)
        ConstraintLayout layout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
