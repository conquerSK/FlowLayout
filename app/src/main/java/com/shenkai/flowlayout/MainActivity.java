package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shenkai.flowlayout.view.FlowLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnFlowLayout;
    private Button btnFlexBox;
    private Button btnFlexboxmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFlowLayout = findViewById(R.id.btn_flowLayout);
        btnFlowLayout.setOnClickListener(this);
        btnFlexBox = findViewById(R.id.btn_flexbox);
        btnFlexBox.setOnClickListener(this);
        btnFlexboxmanager = findViewById(R.id.btn_flexboxmanager);
        btnFlexboxmanager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flowLayout:
                Intent intent = new Intent(this, FlowLayout.class);
                startActivity(intent);
                break;
            case R.id.btn_flexbox:
                Intent intent2 = new Intent(this, FlexboxLayoutActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_flexboxmanager:
                Intent intent3 = new Intent(this, FlexboxLayoutManagerActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
