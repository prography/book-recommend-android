package org.techtown.a1006_bibly;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public class Myholder extends RecyclerView.ViewHolder {
        @BindView(R.id.profile_img)
        ImageView profileImg;
        @BindView(R.id.profile_name)
        TextView profileName;
        @BindView(R.id.post_time)
        TextView postTime;
        @BindView(R.id.bulb)
        ImageView bulb;
        @BindView(R.id.book_img)
        ImageView bookImg;
        @BindView(R.id.post_title)
        TextView postTitle;
        @BindView(R.id.post_content)
        TextView postContent;

        public Myholder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //XML 가져오는 부분
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_fragment_recyclerview_item, parent, false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DataModel dataModel=dataModelArrayList.get(position);
        ((Myholder) holder).postTitle.setText(dataModel.getPost_title());
        ((Myholder) holder).postContent.setText(dataModel.getPost_content());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


}
