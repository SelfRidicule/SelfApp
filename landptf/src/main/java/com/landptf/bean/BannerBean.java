package com.landptf.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by landptf on 2017/03/06.
 * 轮播图实体类
 */
public class BannerBean implements Parcelable{

    private String text;
    private String url;
    private String linkUrl;

    public BannerBean(String text, String url, String linkUrl){
        this.text = text;
        this.url = url;
        this.linkUrl = linkUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    protected BannerBean(Parcel in) {
        text = in.readString();
        url = in.readString();
        linkUrl = in.readString();
    }

    public static final Creator<BannerBean> CREATOR = new Creator<BannerBean>() {
        @Override
        public BannerBean createFromParcel(Parcel in) {
            return new BannerBean(in);
        }

        @Override
        public BannerBean[] newArray(int size) {
            return new BannerBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(url);
        dest.writeString(linkUrl);
    }
}
