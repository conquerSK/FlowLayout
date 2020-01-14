package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.shenkai.flowlayout.adapter.FlexboxLayoutManagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlexboxLayoutManagerActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> mDatas = new ArrayList<>();
    private FlexboxLayoutManagerAdapter adapter;

    private List<String> mDataList = Arrays.asList("shenkai", "hello world", "powerful", "shenkai",
            "hello world", "powerful", "shenkai", "hello world", "powerful");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexbox_layout_manager);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new FlexboxLayoutManager(this));

        for (int i = 0; i < 400; i++) {
            mDatas.add(addTag());
        }

        adapter = new FlexboxLayoutManagerAdapter(this, mDatas);
        recyclerView.setAdapter(adapter);
    }

    private String addTag() {
        return mDataList.get((int) (Math.random() * (mDataList.size() - 1)));
    }
}
