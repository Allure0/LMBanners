package com.allure.lbanners.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by lm on 2016/7/8.
 */
public class CubePageTransformer extends LMPageTransformer {
    private float mMaxRotation = 90.0f;

    public CubePageTransformer() {
    }

    public CubePageTransformer(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override
    public void scrollInvisible(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth());
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, 0);
    }

    @Override
    public void scrollLeft(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth());
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, mMaxRotation * position);
    }

    @Override
    public void scrollRight(View view, float position) {
        ViewHelper.setPivotX(view, 0);
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, mMaxRotation * position);
    }

    public void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 90.0f) {
            mMaxRotation = maxRotation;
        }
    }

}
