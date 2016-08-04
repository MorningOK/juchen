package domain.example.com.viewpager;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by asus1 on 2016/7/21.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    public MyFragmentAdapter(FragmentManager fm,ArrayList<Fragment> list)
    {
        super(fm);
        this.list = list;

    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
