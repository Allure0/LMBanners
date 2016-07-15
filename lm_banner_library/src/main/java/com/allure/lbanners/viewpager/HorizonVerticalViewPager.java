package com.allure.lbanners.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.allure.lbanners.transformer.vertical.VerticalPageTransformer;


public class HorizonVerticalViewPager extends MyViewPager{
	private boolean isVertical = false;

    public boolean isVertical() {
        return isVertical;
    }

    public void setIsVertical(boolean isVertical) {
        this.isVertical = isVertical;
    }

    public HorizonVerticalViewPager(Context context) {
        super(context);
        init();
    }

    public HorizonVerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initIsVertical(attrs, 0);
        init();
    }
    
    public HorizonVerticalViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
//        initIsVertical(attrs, defStyle);
        init();
    }
    
//    private void initIsVertical(AttributeSet attrs, int defStyle){
//    	final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewPager, defStyle, 0);
//    	isVertical = a.getBoolean(R.styleable.CustomViewPager_isVertical, false);
//    	System.out.println("isVertical=>" + isVertical);
//    	a.recycle();
//    }

    public void init() {
    	if(isVertical){
    		// The majority of the magic happens here
//    		setPageTransformer(true, new ParallaxTransformer(R.id.id_image));
            setPageTransformer(true, new VerticalPageTransformer());
    		// The easiest way to get rid of the overscroll drawing that happens on the left and right
    		setOverScrollMode(OVER_SCROLL_NEVER);
    	}
    }


    /**
     * Swaps the X and Y coordinates of your touch event.
     */
    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
    	if (isVertical) {
    		boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
    		swapXY(ev); // return touch coordinates to original reference frame for any child views
    		return intercepted;
		}else {
			return super.onInterceptTouchEvent(ev);
		}
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
    	if (isVertical) {
    		return super.onTouchEvent(swapXY(ev));
		}else {
			return super.onTouchEvent(ev);
		}
    }
}
