package com.allure.lmbanners;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.allure.lmbanners.adapter.UrlImgAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LayoutInflater mInflater;
    private LMBanners mLBanners;
    //本地图片
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    //网络图片
    private List<String> networkImages = new ArrayList<>();
    private int[] mImagesSrc = {
            R.mipmap.img1,
            R.mipmap.img2,
            R.mipmap.img3,
            R.mipmap.img4,
            R.mipmap.img5
    };

    private static final String[] strs = new String[]{
            "defaultEffect", "alpha", "rotate", "cube", "flip", "accordion", "zoomFade",
            "fade", "zoomCenter", "zoomStack", "stack", "depth", "zoom", "zoomOut", "parallax"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageLoader();
        mInflater = this.getLayoutInflater();

        addLocalImg();
        addUrilImg();

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs));

        mLBanners = (LMBanners) findViewById(R.id.banners);
        //设置Banners高度
//      mLBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(this, 200)));
        mLBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        //本地用法
//        mLBanners.setAdapter(new LocalImgAdapter(MainActivity.this),localImages);
        //网络图片
        mLBanners.setAdapter(new UrlImgAdapter(MainActivity.this), networkImages);


        //参数设置

        mLBanners.isGuide(true);//是否为引导页
        mLBanners.setAutoPlay(true);//自动播放
        mLBanners.setVertical(false);//是否可以垂直
        mLBanners.setScrollDurtion(222);//两页切换时间
        mLBanners.setCanLoop(true);//循环播放
        mLBanners.setSelectIndicatorRes(R.drawable.page_indicator_select);//选中的原点
        mLBanners.setUnSelectUnIndicatorRes(R.drawable.page_indicator_unselect);//未选中的原点
        //若自定义原点到底部的距离,默认20,必须在setIndicatorWidth之前调用
        mLBanners.setIndicatorBottomPadding(30);
        mLBanners.setIndicatorWidth(5);//原点默认为5dp
//        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//选中喜欢的样式
        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(5000);//切换时间
        mLBanners.hideIndicatorLayout();//隐藏原点
        mLBanners.showIndicatorLayout();//显示原点
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);//设置原点显示位置

        mLBanners.setOnStartListener(new LMBanners.onStartListener() {
            @Override
            public void startOpen() {
                //回调跳转的逻辑
                Toast.makeText(MainActivity.this,"我要进入主界面",1).show();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        addUrilImg2();
                        mLBanners.setAdapter(new UrlImgAdapter(MainActivity.this), networkImages);
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//Default
                        break;
                    case 1:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Alpha);//Alpha
                        break;
                    case 2:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Rotate);//Rotate
                        break;
                    case 3:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Cube);//Cube
                        break;
                    case 4:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Flip);//Flip
                        break;
                    case 5:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Accordion);//Accordion
                        break;
                    case 6:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomFade);//ZoomFade
                        break;
                    case 7:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Fade);//Fade
                        break;
                    case 8:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomCenter);//ZoomCenter
                        break;
                    case 9:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomStack);//ZoomStack
                        break;
                    case 10:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Stack);//Stack
                        break;
                    case 11:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Depth);//Depth
                        break;
                    case 12:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Zoom);//Zoom
                        break;
                    case 13:
                        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.ZoomOut);//ZoomOut
                        break;
                    case 14:
                        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//Parallax
                        break;

                }
            }
        });


    }

    private void addUrilImg2() {
        networkImages.clear();
        networkImages.add("http://h.hiphotos.baidu.com/image/h%3D300/sign=ff62800b073b5bb5a1d726fe06d2d523/a6efce1b9d16fdfa7807474eb08f8c5494ee7b23.jpg");
        networkImages.add("http://g.hiphotos.baidu.com/image/h%3D300/sign=0a9ac84f89b1cb1321693a13ed5556da/1ad5ad6eddc451dabff9af4bb2fd5266d0163206.jpg");

    }


    private void addUrilImg() {

        networkImages.add("http://h.hiphotos.baidu.com/image/h%3D300/sign=ff62800b073b5bb5a1d726fe06d2d523/a6efce1b9d16fdfa7807474eb08f8c5494ee7b23.jpg");
        networkImages.add("http://g.hiphotos.baidu.com/image/h%3D300/sign=0a9ac84f89b1cb1321693a13ed5556da/1ad5ad6eddc451dabff9af4bb2fd5266d0163206.jpg");
        networkImages.add("http://a.hiphotos.baidu.com/image/h%3D300/sign=61660ec2207f9e2f6f351b082f31e962/500fd9f9d72a6059e5c05d3e2f34349b023bbac6.jpg");
        networkImages.add("http://c.hiphotos.baidu.com/image/h%3D300/sign=f840688728738bd4db21b431918a876c/f7246b600c338744c90c3826570fd9f9d62aa09a.jpg");

    }

    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.ic_launcher)
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    private void addLocalImg() {
        localImages.add(R.mipmap.img1);
        localImages.add(R.mipmap.img2);
        localImages.add(R.mipmap.img3);
        localImages.add(R.mipmap.img4);
        localImages.add(R.mipmap.img5);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLBanners.stopImageTimerTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLBanners.startImageTimerTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLBanners.clearImageTimerTask();
    }


}
