package com.allure.lbanners.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by lm on 2016/7/8.
 */
public class ZoomOutPageTransformer extends LMPageTransformer {

    private static float MIN_SCALE = 0.85f;

    private static float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(view,0);
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to
            // shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                ViewHelper.setTranslationX(view,horzMargin - vertMargin / 2);
            } else {
                ViewHelper.setTranslationX(view,-horzMargin + vertMargin / 2);
            }
            // Scale the page down (between MIN_SCALE and 1)
            ViewHelper.setScaleX(view,scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);


            // Fade the page relative to its size.
            ViewHelper.setAlpha(view,MIN_ALPHA + (scaleFactor - MIN_SCALE)
                    / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewHelper.setAlpha(view,0);
        }
    }

    @Override
    public void scrollInvisible(View view, float position) {

    }

    @Override
    public void scrollLeft(View view, float position) {

    }

    @Override
    public void scrollRight(View view, float position) {

    }
}
