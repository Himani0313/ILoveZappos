package com.example.hjshah2.ilovezappos;

/**
 * Created by hjshah2 on 2/5/2017.
 */


import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class ProductDescriptionModel extends BaseObservable{
    private String name;
    private String price;
    private String brandname;
    private String thumbnailImageUrl;
    private String originalPrice;
    private String off;
    private boolean exists;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    @Bindable
    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Bindable
    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    @Bindable
    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    @Bindable
    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }


}
