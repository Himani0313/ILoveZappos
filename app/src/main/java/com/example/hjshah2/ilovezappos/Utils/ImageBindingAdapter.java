package com.example.hjshah2.ilovezappos.Utils;

/**
 * Created by hjshah2 on 2/5/2017.
 */

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("")) {
            Picasso.with(imageView.getContext()).load(url).resize(200, 200).into(imageView);
        }
    }
}
