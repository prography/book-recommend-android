package org.techtown.a1006_bibly;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity_OtherBookRecommend_RecyclerviewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<BookInfo> bookInfos = new ArrayList<>();
    int recommendBookNum = 5;

    private OnClickListener clickListener;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
    int[] dummyBookImage = {
            R.drawable.dummy_1_1, R.drawable.dummy_1_2, R.drawable.dummy_1_3,
                    R.drawable.dummy_1_4, R.drawable.dummy_1_5, R.drawable.dummy_1_6,

            R.drawable.dummy_2_1, R.drawable.dummy_2_2, R.drawable.dummy_2_3,
                    R.drawable.dummy_2_4, R.drawable.dummy_2_5, R.drawable.dummy_2_6,

            R.drawable.dummy_3_1, R.drawable.dummy_3_2, R.drawable.dummy_3_3,
                    R.drawable.dummy_3_4, R.drawable.dummy_3_5, R.drawable.dummy_3_6,

            R.drawable.dummy_4_1, R.drawable.dummy_4_2, R.drawable.dummy_4_3,
                    R.drawable.dummy_4_4, R.drawable.dummy_4_5, R.drawable.dummy_4_6};

    //constructor
    public BookDetailActivity_OtherBookRecommend_RecyclerviewAdapter() {
        Random random = new Random();
        int[] randomBookId = new int[recommendBookNum];

        //recommendNum 만큼의 랜덤한 책 추첨, bookInfo에 넣는다.
        for (int i = 0; i < recommendBookNum; i++) {
            int radomNum = random.nextInt(dummyBookImage.length);
            randomBookId[i] = dummyBookImage[radomNum];

            bookInfos.add(new BookInfo(randomBookId[i],
                    "book_" + (radomNum / 6 + 1) + "_" + (radomNum % 6 + 1),
                    "author_" + (radomNum / 6 + 1) + "_" + (radomNum % 6 + 1)));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //XML 가져오는 부분
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_fragment_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).dummyBook.setImageResource(bookInfos.get(position).book);
        ((ViewHolder) holder).ratingBar.setRating(bookInfos.get(position).rate);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.dummy_book:
                    case R.id.ratingBar:
                        clickListener.onBookClick(bookInfos.get(position));
                        break;
                }
            }

        };
        ((ViewHolder) holder).dummyBook.setOnClickListener(listener);
        ((ViewHolder) holder).ratingBar.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return recommendBookNum;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dummy_book)
        ImageView dummyBook;
        @BindView(R.id.ratingBar)
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
