package com.allure.lbanners.transformer.vertical;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by lm on 2016/7/8.
 */
public class VerticalPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View view, float position) {

       if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(view, 0);

        } else if (position <= 1) { // [-1,1]
            ViewHelper.setAlpha(view, 1);

            // Counteract the default slide transition
            ViewHelper.setTranslationX(view, view.getWidth() * -position);

            //set Y position to swipe in from top
            float yPosition = position * view.getHeight();
            ViewHelper.setTranslationY(view,yPosition);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewHelper.setAlpha(view, 0);
        }
    }
}
