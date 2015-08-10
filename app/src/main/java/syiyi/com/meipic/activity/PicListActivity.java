package syiyi.com.meipic.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.fichardu.circleprogress.CircleProgress;
import syiyi.com.meipic.R;
import syiyi.com.meipic.adapter.PicItemAdapter;
import syiyi.com.meipic.bean.PicItem;
import syiyi.com.meipic.httpUtil.DownPicListUtil;
import syiyi.com.meipic.httpUtil.HandlerDownload;

public class PicListActivity extends AppCompatActivity {

    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progress)
    CircleProgress progress;
    
    PicItemAdapter adapter;
    List<PicItem> data;
    DownPicListUtil util;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_list);
        ButterKnife.bind(this);
        toolbar.setTitle("图片列表");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        url = getIntent().getStringExtra("url");
        data = new ArrayList<>();
        adapter = new PicItemAdapter(data, this);
        listview.setAdapter(adapter);
        util = new DownPicListUtil();
        initData(url);

    }

    private void initData(String url) {
        progress.setVisibility(View.VISIBLE);
        progress.startAnim();
        progress.setDuration(1000);
        util.getPicItmList(url, new HandlerDownload<PicItem>() {
            @Override
            public void onSuccess(PicItem data) {
                
                PicListActivity.this.data.add(data);
                adapter.notifyDataSetChanged();

                progress.stopAnim();
                progress.setVisibility(View.GONE);
                progress.reset();
            }

            @Override
            public void onFail() {

            }
        });
    }


}
