package com.example.hjshah2.ilovezappos.Utils;

/**
 * Created by hjshah2 on 2/5/2017.
 */

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.media.Image;
import android.widget.ImageView;
import android.net.Uri;
import com.example.hjshah2.ilovezappos.R;
import com.squareup.picasso.Picasso;


public class ImageBindingAdapter {

    @BindingAdapter({"bind:imgUrl"})
    public static void downloadImage(ImageView view, String url){
        if(url == null || url.isEmpty())
            view.setImageResource(R.mipmap.ic_launcher);
        else
            Picasso.with(view.getContext()).load(url).into(view);
    }
//    @BindingAdapter("android:src")
//    public static void setImageUri(ImageView view, String url){
//        if( url == null){
//            view.setImageURI(null);
//        }
//        else{
//            view.setImageURI(Uri.parse(url));
//        }
//    }
}
