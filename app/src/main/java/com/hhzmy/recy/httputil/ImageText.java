package com.hhzmy.recy.httputil;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by qiao on 2016/11/10.
 */
public class ImageText extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration incf=new ImageLoaderConfiguration.Builder(this).memoryCacheSize(2*1024*1024).build();
        ImageLoader.getInstance().init(incf);
      // ImageLoaderConfiguration.createDefault(this);
    }
}
