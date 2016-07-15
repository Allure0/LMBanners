package com.allure.lbanners.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;


/**
 * Created by lm on 2016/7/8.
 */
public abstract class LMPageTransformer implements ViewPager.PageTransformer {

    /**
     * position ==  0 ：当前界面位于屏幕中心的时候
     * position ==  1 ：当前Page刚好滑出屏幕右侧
     * position == -1 ：当前Page刚好滑出屏幕左侧
     * @param view
     * @param position
     */
    public void transformPage(View view, float position) {
        if (position < -1.0f) {
            // [-Infinity,-1)
            // This page is way off-screen to the left.
            scrollInvisible(view, position);
        } else if (position <= 0.0f) {
            // [-1,0]
            // Use the default slide transition when moving to the left page
            scrollLeft(view, position);
        } else if (position <= 1.0f) {
            // (0,1]
            scrollRight(view, position);
        } else {
            // (1,+Infinity]
            // This page is way off-screen to the right.
            scrollInvisible(view, position);
        }
    }

    public abstract void scrollInvisible(View view, float position);

    public abstract void scrollLeft(View view, float position);

    public abstract void scrollRight(View view, float position);

    public static LMPageTransformer getPageTransformer(TransitionEffect effect) {
        switch (effect) {
            case Default:
                return new DefaultPageTransformer();
            case Alpha:
                return new AlphaPageTransformer();
            case Rotate:
                return new RotatePageTransformer();
            case Cube:
                return new CubePageTransformer();
            case Flip:
                return new FlipPageTransformer();
            case Accordion:
                return new AccordionPageTransformer();
            case ZoomFade:
                return new ZoomFadePageTransformer();
            case Fade:
                return new FadePageTransformer();
            case ZoomCenter:
                return new ZoomCenterPageTransformer();
            case ZoomStack:
                return new ZoomStackPageTransformer();
            case Stack:
                return new StackPageTransformer();
            case Depth:
                return new DepthPageTransformer();
            case Zoom:
                return new ZoomPageTransformer();
            case ZoomOut:
                return  new ZoomOutPageTransformer();
            case Parallax:
                new ZoomOutPageTransformer();
//                return   new ParallaxTransformer(R.id.id_image);
            default:
                return new DefaultPageTransformer();
        }
    }
}