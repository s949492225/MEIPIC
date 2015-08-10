package syiyi.com.meipic.httpUtil;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import syiyi.com.meipic.bean.DirItem;

public class DownloadPageItemListUtil implements Handler.Callback {

    private Handler mHandler;
    private HandlerDownload handlerDownload;
    public static final int GET_DATA_OK = 0X001;
    public static final int GET_DATA_ERROR = 0X001 + 1;

    public DownloadPageItemListUtil() {

        mHandler = new Handler(this);
    }

    public void getDirItmList(final String url, HandlerDownload handler) {
        handlerDownload = handler;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<DirItem> datas = new ArrayList<DirItem>();
                    Document doc = Jsoup.connect(url).userAgent("iphone")
                            .timeout(3000).get();
                    Elements eles = doc.select(".lb_box").select("dl dt a");
                    String nextPageURL = doc.select(".flym a").last().absUrl("href");
                    for (int i = 0; i < eles.size(); i++) {
                        String href = eles.get(i).absUrl("href");
                        String imageURL = eles.get(i).select("img").first().absUrl("src");
                        String title=eles.get(i).select("img").first().attr("alt");
                        DirItem item = new DirItem(imageURL, href, nextPageURL,title,eles.get(i).parent().select("span").text());
                        datas.add(item);

                    }
                    Message msg = mHandler.obtainMessage();
                    msg.what = GET_DATA_OK;
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("itemList", datas);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                } catch (Exception e) {

                    mHandler.sendEmptyMessage(0);
                }
            }
        }).start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case GET_DATA_OK:
                ArrayList<DirItem> datas = msg.getData().getParcelableArrayList("itemList");
                handlerDownload.onSuccess(datas);
                break;
            case GET_DATA_ERROR:
                handlerDownload.onFail();
                break;
        }

        return false;
    }

}


