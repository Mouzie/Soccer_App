package com.soccer.api.soccer_app.com.soccer.api.soccer_app.svg;

import android.annotation.TargetApi;
import android.graphics.drawable.PictureDrawable;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

/**
 * Created by admin on 2017/09/04.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SvgSoftwareLayerSetter<T> implements RequestListener<T,PictureDrawable> {
    @Override
    public boolean onException(Exception e, T model, Target<PictureDrawable> target, boolean isFirstResource) {
        ImageView imageView = ((ImageViewTarget<?>)target).getView();
        if(Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT){
            imageView.setLayerType(ImageView.LAYER_TYPE_NONE,null);
        }
        return false;
    }

    @Override
    public boolean onResourceReady(PictureDrawable resource, T model, Target<PictureDrawable> target,
                                   boolean isFromMemoryCache, boolean isFirstResource) {
        ImageView view = ((ImageViewTarget<?>) target).getView();
        if (Build.VERSION_CODES.HONEYCOMB <= Build.VERSION.SDK_INT) {
            view.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null);
        }
        return false;
    }
}

