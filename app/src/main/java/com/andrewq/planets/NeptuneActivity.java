package com.andrewq.planets;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.google.analytics.tracking.android.EasyTracker;

public class NeptuneActivity extends Activity {

    ImageView imgV;

    private Drawable mActionBarBackgroundDrawable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neptune_activity);

        mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background_neptune);
        mActionBarBackgroundDrawable.setAlpha(0);

        getActionBar().setBackgroundDrawable(mActionBarBackgroundDrawable);

        ((NotifyingScrollView) findViewById(R.id.scroll_view_9)).setOnScrollChangedListener(mOnScrollChangedListener);

        imgV = (ImageView) findViewById(R.id.image_header_neptune);

        View.OnTouchListener upDownListener = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgV.setAlpha(0.8f);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imgV.setAlpha(1.0f);
                    Intent i = new Intent(getBaseContext(), NeptuneImageView.class);
                    Bundle scaleBundle = ActivityOptions.makeScaleUpAnimation(
                            v, 0, 0, v.getWidth(), v.getHeight()).toBundle();
                    startActivity(i, scaleBundle);
                    return true;
                }

                return false;
            }

        };

        imgV.setOnTouchListener(upDownListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.source_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.source:
                String url = "http://space-facts.com/neptune/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        imgV = (ImageView) findViewById(R.id.image_header_neptune);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            imgV.setAlpha(0.8f);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            imgV.setAlpha(1.0f);
        }

        return super.onTouchEvent(event);
    }

    private NotifyingScrollView.OnScrollChangedListener mOnScrollChangedListener = new NotifyingScrollView.OnScrollChangedListener() {
        public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
            final int headerHeight = findViewById(R.id.image_header_neptune).getHeight() - getActionBar().getHeight();
            final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
            final int newAlpha = (int) (ratio * 255);
            mActionBarBackgroundDrawable.setAlpha(newAlpha);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
}