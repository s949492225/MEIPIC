package syiyi.com.meipic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

import syiyi.com.meipic.fragment.PageFragment;
import syiyi.com.meipic.bean.PageItem;

/**
 * Created by songlintao on 15/8/6.
 */
public class MyPageAdapter extends FragmentPagerAdapter {
    List<PageItem> mDatas;
    public MyPageAdapter(FragmentManager fm, List<PageItem> data) {
        super(fm);
        mDatas=data;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(mDatas.get(position));
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=mDatas.get(position).getTitle();
        Log.i("pagetitle",title);
        return title;
    }
}
