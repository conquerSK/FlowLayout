package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.Arrays;
import java.util.List;

public class FlexboxLayoutActivity extends AppCompatActivity {
    private FlexboxLayout flexboxLayout;
    private Button btnAddTag;
    private List<String> mDataList = Arrays.asList("shenkai", "hello world", "powerful", "shenkai",
            "hello world", "powerful", "shenkai", "hello world", "powerful");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box_layout);

        flexboxLayout = findViewById(R.id.flexbox);
        btnAddTag = findViewById(R.id.btn_add_tag);
        btnAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tag = (TextView) LayoutInflater.from(FlexboxLayoutActivity.this).inflate(R.layout.item_tag, flexboxLayout, false);
                tag.setText(mDataList.get((int) (Math.random() * (mDataList.size() - 1))));
                flexboxLayout.addView(tag);
            }
        });
    }


}
