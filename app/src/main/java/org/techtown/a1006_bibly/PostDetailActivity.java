package org.techtown.a1006_bibly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.category)
    TextView category;
    @BindView(R.id.post_title)
    TextView postTitle;
    @BindView(R.id.post_content)
    TextView postContent;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        DataModel dataModel = (DataModel) intent.getSerializableExtra("dataModel");

        this.postTitle.setText(dataModel.getPost_title());
        this.postContent.setText(dataModel.getPost_content());
        //this.category.setText(dataModel.getCate());

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
