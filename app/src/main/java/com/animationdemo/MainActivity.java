package com.animationdemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    RelativeLayout mView;
    TextView largeText;
    Button showButton, startAnim, buttonSelf;

    EditText editText;

    ConnectionService mService;
    boolean mBound = false;


    float yPos;

    String sample_url = "http://images4.fanpop.com/image/photos/21800000/Banner-P-summer448-21813774-2560-1024.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = (RelativeLayout) findViewById(R.id.mView);
        startAnim = (Button) findViewById(R.id.startAnim);
        buttonSelf = (Button) findViewById(R.id.buttonSelf);
        largeText = (TextView) findViewById(R.id.largeText);
        showButton = (Button) findViewById(R.id.showButton);
        editText = (EditText) findViewById(R.id.editText);
        startAnim.setText(MainActivity.this.getClass().getSimpleName());

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
                Intent intent = new Intent(MainActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });

        buttonSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DemoActivity.class);
                startActivity(intent);
            }
        });
        editText.setFilters(new InputFilter[]{new InputFilterMinMax("1", "999", 1)});


    }

    void showNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification noti = setInboxStyleNotification();//setCustomViewNotification();//setBigTextStyleNotification();//setBigPictureStyleNotification();
        noti.defaults |= Notification.DEFAULT_LIGHTS;
        noti.defaults |= Notification.DEFAULT_VIBRATE;
        noti.defaults |= Notification.DEFAULT_SOUND;

        noti.flags |= Notification.FLAG_ONLY_ALERT_ONCE;

        mNotificationManager.notify(0, noti);
    }

    public class CreateNotification extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            showNotification();
            return null;
        }
    }

    private Notification setBigPictureStyleNotification() {
        Bitmap remote_picture = null;

        // Create the style object with BigPictureStyle subclass.
        NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
        notiStyle.setBigContentTitle("Big Picture Expanded");
        notiStyle.setSummaryText("Nice big picture.");

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(sample_url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the big picture to the style.
        notiStyle.bigPicture(remote_picture);

        // Creates an explicit intent for an MainActivity to receive.
        Intent resultIntent = new Intent(this, MainActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setLargeIcon(remote_picture)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("Big Picture Normal")
                .setContentText("This is an example of a Big Picture Style.This is an example of a Big Picture Style.This is an example of a Big Picture Style.")
                .setStyle(notiStyle).build();
    }

    private Notification setBigTextStyleNotification() {
        Bitmap remote_picture = null;

        // Create the style object with BigTextStyle subclass.
        NotificationCompat.BigTextStyle notiStyle = new NotificationCompat.BigTextStyle();
        notiStyle.setBigContentTitle("Big Text Expanded");
        notiStyle.setSummaryText("Nice big text.");

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(sample_url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the big text to the style.
        CharSequence bigText = "This is an example of a large string to demo how much " +
                "text you can show in a 'Big Text Style' notification.";
        notiStyle.bigText(bigText);

        // Creates an explicit intent for an MainActivity to receive.
        Intent resultIntent = new Intent(this, MainActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setLargeIcon(remote_picture)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("Big Text Normal")
                .setContentText("This is an example of a Big Text Style.")
                .setStyle(notiStyle).build();
    }

    private Notification setCustomViewNotification() {

        // Creates an explicit intent for an ResultActivity to receive.
        Intent resultIntent = new Intent(this, MainActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create remote view and set bigContentView.
        RemoteViews expandedView = new RemoteViews(this.getPackageName(), R.layout.notification_custom_remote);
        expandedView.setTextViewText(R.id.text, "Neat logo!");

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("This is an example of a Big Text Style.This is an example of a Big Text Style.This is an example of a Big Text Style.").build();

        notification.bigContentView = expandedView;

        return notification;
    }

    private Notification setInboxStyleNotification() {
        Bitmap remote_picture = null;

        // Create the style object with InboxStyle subclass.
        NotificationCompat.InboxStyle notiStyle = new NotificationCompat.InboxStyle();
        notiStyle.setBigContentTitle("Inbox Style Expanded,Inbox Style Expanded,Inbox Style Expanded,Inbox Style Expanded");

        // Add the multiple lines to the style.
        // This is strictly for providing an example of multiple lines.
        for (int i = 0; i < 5; i++) {
            notiStyle.addLine("(" + i + " of 6) Line one here.");
        }
        notiStyle.setSummaryText("+2 more Line Samples");

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(sample_url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creates an explicit intent for an ResultActivity to receive.
        Intent resultIntent = new Intent(this, MainActivity.class);

        // This ensures that the back button follows the recommended convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself).
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setLargeIcon(remote_picture)
                .setContentTitle("Inbox Style Normal")
                .setContentText("This is an example of a Inbox Style.")
                .setStyle(notiStyle).setPriority(Notification.PRIORITY_HIGH).build();
    }

    private void resetView() {
        mView.setVisibility(View.GONE);
        largeText.setVisibility(View.INVISIBLE);
        showButton.setVisibility(View.INVISIBLE);
    }

    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            if (((ObjectAnimator) animation).getTarget() == mView) {
                mView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if (((ObjectAnimator) animation).getTarget() == mView) {
                largeText.setVisibility(View.VISIBLE);
            }
            if (((ObjectAnimator) animation).getTarget() == largeText && (((ObjectAnimator) animation).getPropertyName().equals(View.SCALE_X.getName()) || ((ObjectAnimator) animation).getPropertyName().equals(View.SCALE_Y.getName()))) {

                showButton.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };


    private void onAnimationStart() {
        //for main container view fade out
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mView, View.ALPHA, 0f, 1f);
        fadeOut.addListener(animatorListener);
        fadeOut.setDuration(1500);
        AnimatorSet set = new AnimatorSet();


        ObjectAnimator moveToY = ObjectAnimator.ofFloat(largeText, View.Y, largeText.getY());
        moveToY.setDuration(800);

        //for button slide from left to right
        ObjectAnimator translationView = ObjectAnimator.ofFloat(showButton, View.TRANSLATION_X, -500f, 0f);
        translationView.setDuration(1000);

        AnimatorSet setTranslateWithMove = new AnimatorSet();
        setTranslateWithMove.playTogether(translationView);

        //for zoom text of dummy text
        ObjectAnimator zoomViewX = ObjectAnimator.ofFloat(largeText, View.SCALE_X, .5f, 1.6f, 1f);
        zoomViewX.addListener(animatorListener);
        zoomViewX.setDuration(1000);
        ObjectAnimator zoomViewY = ObjectAnimator.ofFloat(largeText, View.SCALE_Y, .5f, 1.6f, 1f);
        zoomViewX.addListener(animatorListener);
        zoomViewY.setDuration(1000);
        AnimatorSet setZoom = new AnimatorSet();
        setZoom.play(zoomViewX).with(zoomViewY).before(setTranslateWithMove);

        set.play(fadeOut).before(setZoom);
        set.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, ConnectionService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ConnectionService.LocalBinder binder = (ConnectionService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
