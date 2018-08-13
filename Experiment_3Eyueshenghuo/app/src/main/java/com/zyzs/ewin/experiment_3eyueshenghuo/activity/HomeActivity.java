package com.zyzs.ewin.experiment_3eyueshenghuo.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.gyf.barlibrary.ImmersionBar;
import com.squareup.picasso.Picasso;
import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.base.BaseActivity;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.bean.Movie;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.data.https.Fault;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.data.https.MovieLoader;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.LogUtil;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.MeasureUtil;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.MyScrollView;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.RoundImageView;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.MZBannerView;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder.MZHolderCreator;
import com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class HomeActivity extends BaseActivity {

    private MZBannerView mMZBannerView;
    private ImageButton btnBack;
    private MovieLoader mMovieLoader;
    private List<Movie> mMovies = new ArrayList<>();
    private MyScrollView scrollview;
    //头部导航栏
    private RelativeLayout navigation_bar;
    //头部导航栏背景
    private View bg_navigation;
    //
    private View empty_view;
    //状态栏高度
    private int statusBarHeight;
    //设置向下滚动临界值，头部导航栏背景不透明
    private int opaqueHeight;
    //轮播栏高度
    private int bannerHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        onCreatInit();
        initImmersionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mMovies.size() > 0 && mMZBannerView != null) {
            mMZBannerView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mMZBannerView != null) {
            mMZBannerView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMZBannerView != null) {
            mMZBannerView.pause();
        }
    }



    @Override
    protected void onInitView() {
        mMZBannerView = findViewById(R.id.banner);
        btnBack = findViewById(R.id.btn_back);
        scrollview = findViewById(R.id.scrollview);
        navigation_bar = findViewById(R.id.navigation_bar);
        bg_navigation = findViewById(R.id.bg_navigation);
        empty_view = findViewById(R.id.empty_view);

        // 获得状态栏高度，用于判断屏幕顶端到控件顶部的距离
        //statusBarHeight = this.getResources().getDimensionPixelSize(this.getResources().getIdentifier("status_bar_height", "dimen", "android"));
        //获取标题高度
        //int navigationBarHeight = MeasureUtil.getViewHeight(navigation_bar,true);
        //获取轮播栏高度
        bannerHeight = MeasureUtil.getViewHeight(mMZBannerView,true);

        //System.out.println("navigationBarHeight="+navigationBarHeight+"====="+"bannerHeight="+bannerHeight);

        //设置向下滚动临界值，头部导航栏背景不透明
        opaqueHeight = -(bannerHeight / 4);

        // 首次进入页面，将头部导航栏背景设置透明
        bg_navigation.getBackground().mutate().setAlpha(0);
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);

        ImmersionBar.with(this)
                .fullScreen(true)
                .init();
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

    private void setBanner(List<Movie> movies) {

        mMZBannerView.setPages(movies, new MZHolderCreator<HomeActivity.BannerViewHolder>() {
            @Override
            public HomeActivity.BannerViewHolder createViewHolder() {
                return new HomeActivity.BannerViewHolder();
            }
        });
        mMZBannerView.start();
    }

    @Override
    protected void onAddEventListener() {

        if (mMZBannerView != null) {
            //添加Page点击事件
            mMZBannerView.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                @Override
                public void onPageClick(View view, int i) {
                    LogUtil.d("current position:" + i);
                    showToast("current position:" + i);

                    mImmersionBar.statusBarDarkFont(true);
                }
            });
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        scrollview.setOnScrollistener(new MyScrollView.OnScrollistener() {
            @Override
            public void onScroll(int startY, int endY) {
                //根据scrollview滑动更改标题栏透明度
                changeAphla(startY, endY);
            }
        });
    }


    public static class BannerViewHolder implements MZViewHolder<Movie> {

        //        private RoundImageView mImageView;
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item, null);
            mImageView = view.findViewById(R.id.remote_item_image);
            /*mImageView.setType(RoundImageView.TYPE_ROUND);
            mImageView.setRoundRadius(16);*/
            return view;
        }

        @Override
        public void onBind(Context context, int i, Movie movie) {
            Log.e("zhouwei", "current position:" + i);
            Picasso.with(context).load(movie.images.large).into(mImageView);
        }
    }

    /**
     * 根据内容窗体的移动改变标题栏背景透明度
     * @param startY scrollview开始滑动的y坐标（相对值）
     * @param endY scrollview结束滑动的y坐标（相对值）
     */
    private void changeAphla(int startY, int endY) {

        //获取控件的绝对位置坐标
        int[] location = new int[2];
        mMZBannerView.getLocationInWindow(location);

        //从屏幕顶部到控件顶部的坐标位置Y
        int currentY = location[1];

        //表示回到原位（滑动到顶部）
        if (currentY == 0) {
            bg_navigation.getBackground().mutate().setAlpha(0);
            mImmersionBar.statusBarColorTransform(R.color.c_3D3D3D)
                    .statusBarDarkFont(false)
                    .barAlpha(0)
                    .init();
        }else if(currentY <= opaqueHeight){
            bg_navigation.getBackground().mutate().setAlpha(255);
            mImmersionBar.statusBarColorTransform(R.color.c_3D3D3D)
                    .statusBarDarkFont(true)
                    .barAlpha(1)
                    .init();
        }

        System.out.println("startY="+startY+"====="+"endY="+endY+"====="+"currentY="+currentY+"====="+"statusBarHeight="+statusBarHeight);

//        LogUtil.d("currentY"+currentY);

        //颜色透明度改变
        /*if (currentY > opaqueHeight && currentY < 0) {

            double y = currentY * 1.0;
            double value = 4 * y/ bannerHeight;

            float a = (float) (value * 255);
            if(!String.valueOf(a).contains(".")){
                int changeValue = (int) (-value * 255);
                bg_navigation.getBackground().mutate().setAlpha((int) (-4 * y/ bannerHeight * 255));

                System.out.println("0-255 = "+(int) (-4 * y/ bannerHeight * 255));
                System.out.println("0-1 = "+(float) -value);

                mImmersionBar.statusBarColorTransform(R.color.c_3D3D3D)
                        .barAlpha((float) -value)
                        .init();
            }
        }*/
    }
}
