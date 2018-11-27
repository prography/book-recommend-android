package org.techtown.a1006_bibly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    String post_title;
    String post_content;
    String category;

    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.btn_post)
    Button btnPost;
    @BindView(R.id.spinner_category)
    Spinner spi;
    @BindView(R.id.spi_test)
    TextView spiTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ButterKnife.bind(this);

        databaseHelper = new DatabaseHelper(this);
        btnPost.setOnClickListener(this);

        //spinner
        String[] str = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(adapter);


        //스피너 이벤트 발생
        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //각 항목 클릭시 포지션값을 토스트에 띄운다.
                //Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                category = "~";
                if (spi.getSelectedItemPosition() > 0) {
                    category = (String) spi.getAdapter().getItem(spi.getSelectedItemPosition());
                    spiTest.setText(category);
                }
                //if (category != "")
                //~~
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                post_title = editTitle.getText().toString();
                post_content = editContent.getText().toString();
                //Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();

//                if (post_title.isEmpty() || post_content.isEmpty()) {
//                    Toast.makeText(this, "please fill details", Toast.LENGTH_SHORT).show();
//                }
                //category = "wow";
//                databaseHelper.insertdata(post_title, post_content, category);
                databaseHelper.insertdata(post_title, post_content);
                editTitle.setText("");
                editContent.setText("");
//                String s = databaseHelper.getdata().get(0).getCate();
//                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

}
