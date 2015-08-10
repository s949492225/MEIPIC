package syiyi.com.meipic.httpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import syiyi.com.meipic.bean.PageItem;

/**
 * Created by songlintao on 15/8/6.
 */
public final class DownPageListUtil implements Handler.Callback{
    private Handler mHandler;
    private HandlerDownload handlerDownload;
    public final static int DOWNLOAD_OK=0X001;
    public final static int DOWNLOAD_ERROR=0X001+1;

    public DownPageListUtil() {
        this.mHandler = new Handler(this);
    }

    public  void getDirItmList(final String url,HandlerDownload handlerDownload) {
        this.handlerDownload=handlerDownload;
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ArrayList<PageItem> datas = new ArrayList<PageItem>();
                    Document doc = Jsoup.connect(url).userAgent("Mozilla")
                            .timeout(3000).get();
                    Elements eles = doc.select(".nav_left").select("li a");
                    for (int i = 0; i < eles.size(); i++) {

                        String url = eles.get(i).absUrl("href");
                        String title = eles.get(i).ownText();
                        PageItem item = new PageItem(url,title);
                        datas.add(item);
                    }
                    Message msg=mHandler.obtainMessage(DOWNLOAD_OK);
                    Bundle bundle=new Bundle();
                    bundle.putParcelableArrayList("pageList",datas);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                } catch (Exception e) {

                    mHandler.sendEmptyMessage(DOWNLOAD_ERROR);
                }

            }
        }).start();

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case DOWNLOAD_OK:
                handlerDownload.onSuccess(msg.getData().getParcelableArrayList("pageList"));
                break;
            case DOWNLOAD_ERROR:
                handlerDownload.onFail();

                break;
        }
        return false;
    }

}
