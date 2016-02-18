package com.animationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by NITISH on 2/17/2016.
 */
public class ThirdActivity extends Activity {
    Button buttonSelf, startAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAnim = (Button) findViewById(R.id.startAnim);
        buttonSelf = (Button) findViewById(R.id.buttonSelf);
        startAnim.setText("third activity");
        buttonSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
        startAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ThirdActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
