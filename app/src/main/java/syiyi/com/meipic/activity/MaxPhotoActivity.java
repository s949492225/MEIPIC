package syiyi.com.meipic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import syiyi.com.meipic.adapter.MaxPhotoAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import syiyi.com.meipic.R;
import syiyi.com.meipic.bean.PicItem;

public class MaxPhotoActivity extends AppCompatActivity implements Handler.Callback {


    @Bind(R.id.viewPage)
    ViewPager viewPage;
    @Bind(R.id.tv_count)
    TextView tvCount;

    private MaxPhotoAdapter adapter;
    private int index;
    private ArrayList<PicItem> data;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_photo);
        ButterKnife.bind(this);
        handler = new Handler(this);
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        data = intent.getParcelableArrayListExtra("data");
        adapter = new MaxPhotoAdapter(data, tvCount, handler, this);
        viewPage.setAdapter(adapter);
        tvCount.setText(index + 1 + "/" + data.size() + "");
        viewPage.setCurrentItem(index, false);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvCount.setText(position + 1 + "/" + data.size() + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean handleMessage(Message msg) {
        tvCount.setVisibility(View.GONE);
        return false;
    }


}
