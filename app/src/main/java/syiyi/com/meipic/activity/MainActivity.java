package syiyi.com.meipic.activity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.viewpagerindicator.TabPageIndicator;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import syiyi.com.meipic.R;
import syiyi.com.meipic.adapter.MyPageAdapter;
import syiyi.com.meipic.bean.PageItem;
import syiyi.com.meipic.httpUtil.DownPageListUtil;
import syiyi.com.meipic.httpUtil.HandlerDownload;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.pager)
    ViewPager viewpage;
   // @Bind(R.id.drawer_layout)
    //DrawerLayout drawer_layout;
    @Bind(R.id.indicator)
    TabPageIndicator indicator;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private MyPageAdapter adapter;
    //private DrawerArrowDrawable drawerArrowDrawable;
    private DownPageListUtil util;
    private List<PageItem> mData;
    //boolean isopen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
//        drawerArrowDrawable = new DrawerArrowDrawable(getResources());
//        drawerArrowDrawable.setStrokeColor(Color.WHITE);
//        toolbar.setNavigationIcon(drawerArrowDrawable);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isopen) {
//                    drawer_layout.openDrawer(Gravity.LEFT);
//                    isopen = true;
//                } else {
//                    drawer_layout.closeDrawer(Gravity.LEFT);
//                    isopen = false;
//                }
//            }
//        });
//        drawer_layout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                float offset = slideOffset;
//
//                if (slideOffset >= .995) {
//                    isopen = true;
//                    drawerArrowDrawable.setFlip(isopen);
//                } else if (slideOffset <= .005) {
//                    isopen = false;
//                    drawerArrowDrawable.setFlip(isopen);
//                }
//
//                drawerArrowDrawable.setParameter(offset);
//
//            }
//        });
        util=new DownPageListUtil();
        mData = new ArrayList<>();
        adapter = new MyPageAdapter(getSupportFragmentManager(), mData);
        viewpage.setAdapter(adapter);
        indicator.setViewPager(viewpage);
        loadData();

    }

    private void loadData() {
        util.getDirItmList("http://pic.yesky.com/", new HandlerDownload<List<PageItem>>() {
            @Override
            public void onSuccess(List<PageItem> data) {
                mData.clear();
                mData.addAll(data);
                adapter.notifyDataSetChanged();
                indicator.notifyDataSetChanged();
            }
            @Override
            public void onFail() {

            }
        });
    }

}
