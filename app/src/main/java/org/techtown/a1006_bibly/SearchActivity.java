package org.techtown.a1006_bibly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.editText_search)
    EditText editTextSearch;
    @BindView(R.id.btn_search)
    ImageView btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);


    }

    @OnClick({R.id.back, R.id.editText_search, R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_search:
                String s = editTextSearch.getText().toString();
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
