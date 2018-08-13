package com.zyzs.ewin.experiment_3eyueshenghuo.model.bean;

/**
 * Created by lcodecore on 2016/12/6.
 */

public class Food {
    public String title;
    public String info;
    public int imageSrc;
    public int avatar_id;

    public Food(String title, String info, int imageSrc, int avatar_id) {
        this.title = title;
        this.info = info;
        this.imageSrc = imageSrc;
        this.avatar_id = avatar_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    public void setAvatar_id(int avatar_id) {
        this.avatar_id = avatar_id;
    }
}
