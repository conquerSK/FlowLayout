package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import com.shenkai.flowlayout.view.FlowLayout;

public class FlowLayoutActivity extends AppCompatActivity {
    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        flowLayout = findViewById(R.id.flow_layout);
        flowLayout.addView(generateBtn());
    }

    private Button generateBtn() {
        Button button = new Button(this);
        button.setText("add");

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(lp);
        return button;
    }
}
