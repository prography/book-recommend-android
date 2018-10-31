package org.techtown.a1006_bibly;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends AppCompatActivity implements View.OnClickListener, OnClickListener {

    @BindView(R.id.book)
    ImageView book;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.review_text)
    TextView review_text;
    @BindView(R.id.wise_saying)
    TextView wise_saying;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.publisher)
    TextView publisher;



    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.like)
    ImageView like;
    int i = 0;

    private BookInfo bookInfo;
    RecyclerView recyclerView;
    View view;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        bookInfo = (BookInfo) intent.getSerializableExtra("bookInfo");

        book.setImageResource(bookInfo.getBook());
        title.setText(bookInfo.getTitle());
        author.setText(bookInfo.getAuthor());
        ratingBar.setRating(bookInfo.getRate());
        review_text.setText(bookInfo.getReview());
        publisher.setText(bookInfo.getPublisher());

        like.setOnClickListener(this);
        back.setOnClickListener(this);

        wise_saying.setText(getResources().getString(R.string.dummy_wise_saying));
        summary.setText(getResources().getString(R.string.dummy_summary));

        makeTextViewResizable(summary, 5, "View More", true);



        //other 책 추천 recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.otherBookRecommend_InBookDetail_RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        BookDetailActivity_OtherBookRecommend_RecyclerviewAdapter adapter
                = new BookDetailActivity_OtherBookRecommend_RecyclerviewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.like:
                if (i == 0) {
                    like.setImageResource(R.drawable.ic_like_full);
                    i = 1;
                }
                else if (i == 1) {
                    like.setImageResource(R.drawable.ic_like_empty);
                    i = 0;
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                String text;
                int lineEndIndex;
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                }
                tv.setText(text);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                tv.setText(
                        addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                viewMore), TextView.BufferType.SPANNABLE);
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    tv.setLayoutParams(tv.getLayoutParams());
                    tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                    tv.invalidate();
                    if (viewMore) {
                        makeTextViewResizable(tv, -1, "View Less", false);
                    } else {
                        makeTextViewResizable(tv, 3, "View More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

    @Override
    public void onButtonClick(RecyclerView recyclerView, String type, String btnKind, int btnNum) {

    }

    @Override
    public void onBookClick(BookInfo bookInfo) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("bookInfo", bookInfo);
        startActivity(intent);
    }

    @Override
    public void onRecommendDetailButtonClick(String type, String[] type_kinds) {

    }
}
