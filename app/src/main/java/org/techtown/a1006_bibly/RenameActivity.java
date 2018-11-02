package org.techtown.a1006_bibly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RenameActivity extends AppCompatActivity {

    EditText edit_rename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename);

        edit_rename = (EditText)findViewById(R.id.edit_name);
    }
}
