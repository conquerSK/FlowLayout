package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shenkai.flowlayout.view.FlowLayout;

import java.util.Arrays;
import java.util.List;

public class FlowLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    private FlowLayout flowLayout;
    private Button btnAddTag;
    private List<String> mDataList = Arrays.asList("shenkai", "hello world", "powerful", "shenkai",
            "hello world", "powerful", "shenkai", "hello world", "powerful");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        flowLayout = findViewById(R.id.flow_layout);
        btnAddTag = findViewById(R.id.btn_add_tag);
        btnAddTag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_tag) {
            TextView tag = (TextView) LayoutInflater.from(this).inflate(R.layout.item_tag, flowLayout, false);
            tag.setText(mDataList.get((int) (Math.random() * (mDataList.size() - 1))));
            flowLayout.addView(tag);
        }
    }
}
