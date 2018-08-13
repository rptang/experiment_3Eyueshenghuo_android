package com.zyzs.ewin.experiment_3eyueshenghuo.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import com.squareup.picasso.Picasso;
import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.base.BaseActivity;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.bean.Movie;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.data.https.Fault;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.data.https.MovieLoader;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.LogUtil;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.RoundImageView;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.MZBannerView;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder.MZHolderCreator;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder.MZViewHolder;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Action1;

public class BannerCardActivity extends BaseActivity {

    private MZBannerView mMZBannerView;
    private ImageButton btnBack;
    private MovieLoader mMovieLoader;
    private List<Movie> mMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_card);

        onCreatInit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mMovies.size() > 0 && mMZBannerView != null){
            mMZBannerView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mMZBannerView != null){
            mMZBannerView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mMZBannerView != null){
            mMZBannerView.pause();
        }
    }

    @Override
    protected void onInitView() {
        mMZBannerView = findViewById(R.id.banner);
        btnBack = findViewById(R.id.btn_back);
    }

    @Override
    protected void onInitData() {

        mMovieLoader = new MovieLoader();
        getMovieList();
    }

    private void getMovieList() {
        mMovieLoader.getMovie(0, 10).subscribe(new Action1<List<Movie>>() {
            @Override
            public void call(List<Movie> movies) {
                mMovies = movies;
                Log.e("zhouwei", "get data suceess");
                setBanner(movies);
            }

        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e("TAG", "error message:" + throwable.getMessage());
                if (throwable instanceof Fault) {
                    Fault fault = (Fault) throwable;
                    if (fault.getErrorCode() == 404) {
                        //错误处理
                    } else if (fault.getErrorCode() == 500) {
                        //错误处理
                    } else if (fault.getErrorCode() == 501) {
                        //错误处理
                    }
                }
            }
        });
    }

    private void setBanner(List<Movie> movies){

        mMZBannerView.setPages(movies, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBannerView.start();
    }

    @Override
    protected void onAddEventListener() {

        if(mMZBannerView != null){
            //添加Page点击事件
            mMZBannerView.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                @Override
                public void onPageClick(View view, int i) {
                    LogUtil.d("current position:"+i);
                    showToast("current position:"+i);
                }
            });
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<Movie> {

        private RoundImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item,null);
            mImageView = (RoundImageView) view.findViewById(R.id.remote_item_image);
            mImageView.setType(RoundImageView.TYPE_ROUND);
            mImageView.setRoundRadius(16);
            return view;
        }

        @Override
        public void onBind(Context context, int i, Movie movie) {
            //Log.e("zhouwei","current position:"+i);
            Picasso.with(context).load(movie.images.large).into(mImageView);
        }
    }
}
