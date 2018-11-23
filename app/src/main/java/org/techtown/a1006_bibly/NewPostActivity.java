package org.techtown.a1006_bibly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    String post_title;
    String post_content;


    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.btn_post)
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);
        btnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                post_title = editTitle.getText().toString();
                post_content = editContent.getText().toString();
                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                if (post_title.isEmpty() && post_content.isEmpty())
                    Toast.makeText(this, "please fill details", Toast.LENGTH_SHORT).show();
                else {
                    databaseHelper.insertdata(post_title, post_content);
                    editTitle.setText("");
                    editContent.setText("");
                }
                finish();
                break;
        }
    }

}
