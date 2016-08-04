package domain.example.com.viewpager;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList fragments;
    private TextView view1,view2,view3;
    private int currIndex;
    private ImageView image;
    private static int bmpW;//图片宽度
    private static int offset;//图片移动的偏移量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        initTextView();
        initImage();
    }

    public void initImage() {
        image = (ImageView) findViewById(R.id.cusor);
        bmpW = BitmapFactory.decodeResource(getResources(),R.mipmap.main).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW/3-bmpW)/2;
        Log.e("offset",offset+"");
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset,0);//进行平移
        image.setImageMatrix(matrix);
    }


    public void initTextView() {
       view1 = (TextView)findViewById(R.id.tv_hot);
        view2 = (TextView) findViewById(R.id.tv_news);
        view3 = (TextView) findViewById(R.id.tv_fav);
        view1.setOnClickListener(new txtListener(0));
        view2.setOnClickListener(new txtListener(1));
        view3.setOnClickListener(new txtListener(2));
    }

    class txtListener implements View.OnClickListener{
        private int index = 0;

        public txtListener(int i){
            this.index = i;
        }
        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

      public void initViewPager() {
          mViewPager = (ViewPager) findViewById(R.id.myViewPager);
          fragments = new ArrayList<Fragment>();
          Fragment fragmentHot = new Fragmenthot();
          Fragment fragmentNews = new FragementNews();
          Fragment fragmentFav = new Fragmentfav();
          fragments.add(fragmentHot);
          fragments.add(fragmentNews);
          fragments.add(fragmentFav);
          mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragments));
          mViewPager.setCurrentItem(0);
          mViewPager.addOnPageChangeListener(new MyOnchangePagetListener());



      }
    public class MyOnchangePagetListener implements ViewPager.OnPageChangeListener{
        private int one = offset*2+bmpW;//两个相邻界面需要移动的偏移量


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
           // Log.i("onPageScrolled",position+"#    "+positionOffset+"#    "+positionOffsetPixels);

        }

        @Override
        public void onPageSelected(int position) {
           // Log.e("onPageSelected",position+"");
            Animation animation = new TranslateAnimation(currIndex*one,position*one,0,0);
            currIndex = position;
            animation.setFillAfter(true);//动画终止时停留在最后一帧
            animation.setDuration(2000);//动画持续时间0.2秒
            image.startAnimation(animation);//使用imageview来显示动画的
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Log.e("state",state+"");

        }
    }

    

}
