package com.animationdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by NITISH on 2/17/2016.
 */
public class DemoActivity extends Activity {
    Button buttonSelf, startAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAnim = (Button) findViewById(R.id.startAnim);
        buttonSelf= (Button) findViewById(R.id.buttonSelf);
        startAnim.setText(DemoActivity.this.getClass().getSimpleName());

        buttonSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                resetView();
//                onAnimationStart();
//                new CreateNotification().execute();

//                if (mBound) {
//                    // Call a method from the LocalService.
//                    // However, if this call were something that might hang, then this request should
//                    // occur in a separate thread to avoid slowing down the activity performance.
//                    int num = mService.getRandomNumber();
//                    Toast.makeText(MainActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(DemoActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });
        startAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                resetView();
//                onAnimationStart();
//                new CreateNotification().execute();

//                if (mBound) {
//                    // Call a method from the LocalService.
//                    // However, if this call were something that might hang, then this request should
//                    // occur in a separate thread to avoid slowing down the activity performance.
//                    int num = mService.getRandomNumber();
//                    Toast.makeText(MainActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(DemoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        buttonSelf.setText("onNewIntent");
    }
}
