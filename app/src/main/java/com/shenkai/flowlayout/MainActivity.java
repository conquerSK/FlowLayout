package com.shenkai.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shenkai.flowlayout.view.FlowLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFlowLayout = findViewById(R.id.btn_flowLayout);
        btnFlowLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flowLayout:
                Intent intent = new Intent(this, FlowLayout.class);
                startActivity(intent);
                break;
        }
    }
}
