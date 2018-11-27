package org.techtown.a1006_bibly;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment_RecyclerviewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private OnClickListener clickListener;
    List<DataModel> dataModelArrayList;

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //constructor
    public FeedFragment_RecyclerviewAdapter(List<DataModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    //holder
    public class Myholder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_title)
        TextView postTitle;
        @BindView(R.id.post_content)
        TextView postContent;
        @BindView(R.id.cardview)
        CardView cardview;
//        @BindView(R.id.category)
//        TextView category;

        public Myholder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class Myholder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.post_title)
        TextView postTitle;
        @BindView(R.id.post_content)
        TextView postContent;
        @BindView(R.id.cardview)
        CardView cardview;
//        @BindView(R.id.category)
//        TextView category;

        public Myholder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2 * 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //XML 가져오는 부분
        View view;
        switch (viewType) {
            case 0: //return new ViewHolder0(...);   //1번 뷰타입
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.feed_fragment_cardview_item, parent, false);
                return new Myholder(view);
            case 2: //return new ViewHolder2(...);   //2번 뷰타입
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.feed_fragment_cardview_item2, parent, false);
                return new Myholder2(view);

        }
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_fragment_cardview_item, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final DataModel dataModel = dataModelArrayList.get(position);
        TextView postTitle = null, postContent = null;
        //, category = null;
        CardView cardView = null;

        if (position % 2 * 2 == 0) {
            postTitle = ((Myholder) holder).postTitle;
            postContent = ((Myholder) holder).postContent;
            //category = ((Myholder) holder).category;
            cardView = ((Myholder) holder).cardview;
        }
        else if (position % 2 * 2 == 2) {
            postTitle = ((Myholder2) holder).postTitle;
            postContent = ((Myholder2) holder).postContent;
            //category = ((Myholder2) holder).category;
            cardView = ((Myholder2) holder).cardview;
        }

        postTitle.setText(dataModel.getPost_title());
        postContent.setText(dataModel.getPost_content());
        //category.setText(dataModel.getCate());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cardview:
                        clickListener.onPostClick(dataModel);
                        break;
                }
            }

        };
        cardView.setOnClickListener(listener);


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

}
