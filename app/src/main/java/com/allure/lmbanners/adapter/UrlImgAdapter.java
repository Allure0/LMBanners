package com.allure.lmbanners.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.allure.lbanners.LMBanners;
import com.allure.lbanners.adapter.LBaseAdapter;
import com.allure.lmbanners.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by luomin on 16/7/12.
 */
public class UrlImgAdapter implements LBaseAdapter<String> {
    private Context mContext;

    public UrlImgAdapter(Context context) {
        mContext=context;
    }



    @Override
    public View getView(final LMBanners lBanners, final Context context, int position, String data) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_image);
        ImageLoader.getInstance().displayImage(data,imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        MainActivity.this.startActivity(new Intent(MainActivity.this,SeconedAc.class));
            }
        });
        return view;
    }



}
