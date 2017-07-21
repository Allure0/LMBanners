package com.allure.lmbanners;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.transformer.TransitionEffect;
import com.allure.lmbanners.adapter.LocalImgAdapter;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class GuideActivity extends AppCompatActivity {
    private LMBanners lmBanners;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initImageLoader();
        addLocalImg();
        addUrilImg();
        initGuide();


    }

    private void addLocalImg() {
        localImages.add(R.mipmap.img1);
        localImages.add(R.mipmap.img2);
        localImages.add(R.mipmap.img3);
        localImages.add(R.mipmap.img4);
        localImages.add(R.mipmap.img5);
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
    private void initGuide() {
        lmBanners = (LMBanners) findViewById(R.id.banners);
        lmBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        lmBanners.isGuide(true);
//        lmBanners.setVertical(true);
        lmBanners.setAutoPlay(false);
        lmBanners.setCanLoop(false);
        lmBanners.setScrollDurtion(1200);
        lmBanners.setIndicatorBottomPadding(30);
        lmBanners.setIndicatorWidth(10);
//      lmBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);
        lmBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));
        lmBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);
        //本地用法
        lmBanners.setAdapter(new LocalImgAdapter(GuideActivity.this), localImages);

        /**
         * 若btnBgColor为-1，则表示不需要任何背景色
         * textColor 文字颜色
         * 点击事件
         */
//        lmBanners.setOnStartListener(-1,getResources().getColor(R.color.colorAccent),****);
        lmBanners.setOnStartListener(R.drawable.button_shape,0XFFAACCBB,new LMBanners.onStartListener() {
            @Override
            public void startOpen() {
                //回调跳转的逻辑
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                Toast.makeText(GuideActivity.this, "进入Banners", 1).show();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        lmBanners.stopImageTimerTask();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lmBanners.startImageTimerTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lmBanners.clearImageTimerTask();
    }


}
