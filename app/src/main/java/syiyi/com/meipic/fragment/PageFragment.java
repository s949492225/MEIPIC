package syiyi.com.meipic.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.fichardu.circleprogress.CircleProgress;
import syiyi.com.meipic.R;
import syiyi.com.meipic.adapter.PageItemAdapter;
import syiyi.com.meipic.bean.DirItem;
import syiyi.com.meipic.bean.PageItem;
import syiyi.com.meipic.httpUtil.DownloadPageItemListUtil;
import syiyi.com.meipic.httpUtil.HandlerDownload;


/**

 */
public class PageFragment extends android.support.v4.app.Fragment {
    @Bind(R.id.listview)
    PullToRefreshGridView listview;
    @Bind(R.id.progress)
    CircleProgress progress;
    private String nextUrl;
    private PageItem mPageInfo;
    private PageItemAdapter adapter;
    private DownloadPageItemListUtil util;
    private List<DirItem> mData;
    private View contentView;
    private boolean isInited;

    public static PageFragment newInstance(PageItem pageInfo) {
        PageFragment fragment = new PageFragment(pageInfo);
        return fragment;
    }

    public PageFragment(PageItem pageInfo) {
        mPageInfo=pageInfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (contentView==null){
            contentView = inflater.inflate(R.layout.fragment_page, null, false);
        }
        ViewGroup parent= (ViewGroup) contentView.getParent();
        if (parent!=null){
            parent.removeView(contentView);

        }
        ButterKnife.bind(this, contentView);
        init();
        return contentView;
    }
    public void init(){
        if (!isInited){
            mData=new ArrayList<>();
            adapter=new PageItemAdapter(mData,getActivity());
            listview.setAdapter(adapter);
            util=new DownloadPageItemListUtil();
            listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
                @Override
                public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                    loadData();
                }
            });
            listview.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
                @Override
                public void onLastItemVisible() {
                    loadMoreData();
                }
            });
            loadData();
        }

    }
    
    private void loadData() {
        progress.setVisibility(View.VISIBLE);
        progress.startAnim();
        progress.setDuration(1000);
        util.getDirItmList(mPageInfo.getUrl(), new HandlerDownload<List<DirItem>>() {
            @Override
            public void onSuccess(List<DirItem> data) {
                for (DirItem i:data){
                    Log.i("myurl",i.getImgeUrl());
                }
                nextUrl = data.get(0).getNextPageURL();
               // mData.clear();
                mData.addAll(data);
                adapter.notifyDataSetChanged();
                listview.onRefreshComplete();
                isInited=true;
                progress.stopAnim();
                progress.setVisibility(View.GONE);
                progress.reset();
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void loadMoreData(){
        util.getDirItmList(nextUrl,new HandlerDownload<List<DirItem>>() {
            @Override
            public void onSuccess(List<DirItem> data) {
                nextUrl=data.get(0).getNextPageURL();
                mData.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFail() {

            }
        });
    }



}
