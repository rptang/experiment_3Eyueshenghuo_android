package com.zyzs.ewin.experiment_3eyueshenghuo.model.bean;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.model.bean
 * Movie
 * <p>
 * Created by Stiven on 2018/6/4.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public class Movie {

    public Rate rating;
    public String title;
    public String collect_count;
    public String original_title;
    public String subtype;
    public String year;
    public MovieImage images;

    public static class Rate{

        public int max;
        public float average;
        public String stars;
        public int min;
    }

    public static class MovieImage{

        public String small;
        public String large;
        public String medium;
    }
}
