package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shenkai.flowlayout.view.TagAdapter;
import com.shenkai.flowlayout.view.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class TagFlowLayoutActivity extends AppCompatActivity {
    private TagFlowLayout tagFlowLayout;
    private ArrayList<String> mDatas = new ArrayList<>(Arrays.asList(("The first one is FlexboxLayout " +
            "that extends the ViewGroup like LinearLayout and RelativeLayout. You can specify the attributes from a layout XML like").split(" ")));
    private TagAdapter adapter;
    private Button btn_change_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_flow_layout);

        tagFlowLayout = findViewById(R.id.tagflowlayout);
        tagFlowLayout.setMaxSelectCount(3);
        tagFlowLayout.setAdapter(adapter = new TagAdapter() {
            @Override
            public int getItemCount() {
                return mDatas.size();
            }

            @Override
            public View createView(LayoutInflater inflater, ViewGroup parent, int position) {
                return inflater.inflate(R.layout.item_select_tag, parent, false);
            }

            @Override
            public void bindView(View view, int position) {
                TextView tv = view.findViewById(R.id.tv_tag);
                tv.setText(mDatas.get(position));
            }

            @Override
            public void onItemViewClick(View v, int position) {
                Toast.makeText(TagFlowLayoutActivity.this, tagFlowLayout.getSelectedItemPositionList().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void tipForSelectMax(View v, int maxSelectCount) {
                super.tipForSelectMax(v, maxSelectCount);
                Toast.makeText(TagFlowLayoutActivity.this, "最多选择"+maxSelectCount+"个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemSelected(View v, int position) {
                TextView tvTag = v.findViewById(R.id.tv_tag);
                tvTag.setTextColor(Color.RED);
            }

            @Override
            public void onItemUnSelected(View v, int position) {
                TextView tvTag = v.findViewById(R.id.tv_tag);
                tvTag.setTextColor(Color.BLACK);
            }
        });

        btn_change_data = findViewById(R.id.btn_change_data);
        btn_change_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDatas();
            }
        });
    }

    public void changeDatas() {
        mDatas.clear();
        mDatas.addAll(new ArrayList<String>(Arrays.asList("There are two ways of using Flexbox in your layout".split(" "))));
        tagFlowLayout.setMaxSelectCount(1);
        adapter.notifyDataSetChanged();
    }
}
