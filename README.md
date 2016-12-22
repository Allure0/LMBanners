
---
#LBanners
-------------

> 关于我，欢迎关注  
      QQ群:[482906631]()


最近在朋友[bingoogolapple](https://github.com/bingoogolapple)群里看依然有童鞋需求Banners的库。
有的又需要引导页，为帮助更多的童鞋更好更快的快速完成项目开发而开源此库
####Banners示例:  
![image](https://github.com/Allure0/LMBanners/blob/master/app/LMBanners.gif)

####引导页示例：
![image](https://github.com/Allure0/LMBanners/blob/master/app/guide.jpeg)
###特性（可选）
- 支持设置为Guide模式或者Banners模式
- 支持是否循环播放
- 支持是否自动播放
- 支持页面切换间隔时间
- 支持两页动画过渡时间
- 支持自定义原点的样式
- 支持自定义原点大小（设置的宽度即为高度）
- 支持多种动画过渡方式
- 支持自定义动画过渡方式
- 支持原点位置底部居中或者底部居右
- 支持横向纵向播放（纵向暂只支持一种动画过渡）
- 支持设置原点距离底部的距离


###原理说明
项目基于ViewPager实现,ViewPager使用方式不用多说。

需求1：如何循环播放？

实现方式采用假设当前Pager总页数为X，假设页数为Y页,
首次进入处于0页,向右滑动自然没问题，向左滑时候0------1,显然不成立,此时可在finshUpdate函数中手动将第0页的下标变为X,如此我们的
ViewPager即可向左滑动.但是当向右的时候呢?原理一样,当下标为X的时候，将其替换为0即可。

需求2:如何自动播放？

其实这对于稍微有点经验的人来说不算难点了,方式很多。本项目采用handler的方式,每次切换进行一次发送即可,在页面切换时记得Stop,回来
继续Start,页面销毁时进行Remove即可。

需求3：手指按住或者拖动时如何停止滑动。

本项目采用在ViewPager的dispatch进行事件分发的拦截处理。 当然也可以手动去处理Image的Ontouch事件。


### Gradle引与使用
Gradle:  
``` xml
dependencies {
  compile 'com.allure0:LMBanners:1.0.6'
}
```

###使用方法
Config in xml:
``` xml
 <com.allure.lbanners.LMBanners
            android:id="@+id/banners"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:canLoop="true"
            app:isVertical="false"
            app:auto_play="true"
            app:durtion="5000"
            app:scroll_duration="66666"
            app:indicator_select="@drawable/page_indicator_select"
            app:indicator_unselect="@drawable/page_indicator_unselect"
            app:horizontal_transitionEffect="accordion"
            app:indicator_position="bottom_mid"
             app:indicator_width="5"
            >
        </com.allure.lbanners.LMBanners>
```
Config in Java:  
``` xml
 mLBanners = (LMBanners) findViewById(R.id.banners);
        //设置Banners高度
        mLBanners.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(this, 200)));
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
        mLBanners.setIndicatorWidth(5);//默认为5dp
//        mLBanners.setHoriZontalTransitionEffect(TransitionEffect.Default);//选中喜欢的样式
        mLBanners.setHoriZontalCustomTransformer(new ParallaxTransformer(R.id.id_image));//自定义样式
        mLBanners.setDurtion(2000);//切换时间
        mLBanners.hideIndicatorLayout();//隐藏原点
        mLBanners.showIndicatorLayout();//显示原点
        mLBanners.setIndicatorPosition(LMBanners.IndicaTorPosition.BOTTOM_MID);//设置原点显示位置
        
        //停止事件,节省资源
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
    
    /-------------Adapter-----------/
     @Override
    public View getView(final LMBanners lBanners, final Context context, int position, String data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        //可行选择喜欢的图片加载库。
        ImageLoader.getInstance().displayImage(data,imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
```


### 注意事项
-  纵向播放时暂只支持一种效果（后续可能增加）
-  使用纵向播放后代码不要设置setHoriZontalTransitionEffect（）、setHoriZontalCustomTransformer（）, XML内不要调用自定义属性horizontal_transitionEffect
 XML内不要调用自定义属性horizontal_transitionEffect
-  若setIndicatorBottomPadding（）动态代码自定义了原点距离底部的距离，需要在setIndicatorWidth()之前调用
###TODO
 XML内不要调用自定义属性horizontal_transitionEffect
若有BUG或者疑问,请提交Issues。者QQ群:[482906631]()

## License
Copyright 2016 Allure

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
