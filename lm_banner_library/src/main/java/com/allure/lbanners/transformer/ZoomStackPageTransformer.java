package com.allure.lbanners.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by lm on 2016/7/8.
 */
public class ZoomStackPageTransformer extends LMPageTransformer {

    @Override
    public void scrollInvisible(View view, float position) {
    }

    @Override
    public void scrollLeft(View view, float position) {
        ViewHelper.setTranslationX(view, -view.getWidth() * position);

        ViewHelper.setPivotX(view, view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view, view.getHeight() * 0.5f);
        ViewHelper.setScaleX(view, 1 + position);
        ViewHelper.setScaleY(view, 1 + position);

        if (position < -0.95f) {
            ViewHelper.setAlpha(view, 0);
        } else {
            ViewHelper.setAlpha(view, 1);
        }
    }

    @Override
    public void scrollRight(View view, float position) {
        ViewHelper.setTranslationX(view, -view.getWidth() * position);

        ViewHelper.setPivotX(view, view.getWidth() * 0.5f);
        ViewHelper.setPivotY(view, view.getHeight() * 0.5f);
        ViewHelper.setScaleX(view, 1 + position);
        ViewHelper.setScaleY(view, 1 + position);

        if (position > 0.95f) {
            ViewHelper.setAlpha(view, 0);
        } else {
            ViewHelper.setAlpha(view, 1);
        }
    }


}