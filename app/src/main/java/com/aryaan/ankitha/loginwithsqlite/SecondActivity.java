package com.aryaan.ankitha.loginwithsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    DbHelperAdapter dbHelperAdapter;
    TextView nameTextView,detailsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameTextView = (TextView)findViewById(R.id.nameText);
        detailsText = (TextView)findViewById(R.id.details);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        nameTextView.setText(name);

        dbHelperAdapter = new DbHelperAdapter(this);

    }

}
