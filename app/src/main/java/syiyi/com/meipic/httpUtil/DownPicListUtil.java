package syiyi.com.meipic.httpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import syiyi.com.meipic.bean.PicItem;

/**
 * Created by songlintao on 15/8/6.
 */
public final class DownPicListUtil implements Handler.Callback{
    private Handler mHandler;
    private HandlerDownload handlerDownload;
    public final static int DOWNLOAD_OK=0X001;
    public final static int DOWNLOAD_ERROR=0X001+1;
    private final Executor executor;

    public DownPicListUtil() {
        this.mHandler = new Handler(this);
        executor= Executors.newFixedThreadPool(6);
    }

    public  void getPicItmList(final String url,HandlerDownload<PicItem> handlerDownload) {
        this.handlerDownload=handlerDownload;

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).userAgent("Mozilla")
                            .timeout(3000).get();
                    Elements eles = doc.select(".l_effect_bottom").select("ul li a");
                    for (int i = 0; i < eles.size(); i++) {

                        final String picurl = eles.get(i).absUrl("href");
                        Thread th=new Thread(new Runnable() {
                            Lock lock = new ReentrantLock();

                            @Override
                            public void run() {
                                try {
                                    Document doc = Jsoup.connect(picurl).userAgent("Mozilla")
                                            .timeout(3000).get();
                                    Element ele = doc.select(".l_effect_img_mid a img").first();
                                    Element eletitle = doc.select(".ll_img h2 a").first();
                                    Element elecontent = doc.select(".tjms").first();

                                    PicItem item = new PicItem(ele.attr("src"), eletitle.text());
                                    item.setContent(elecontent.text());
                                    lock.lock();
                                    Message msg = mHandler.obtainMessage(DOWNLOAD_OK);
                                    Bundle bundle = new Bundle();
                                    bundle.putParcelable("pic", item);
                                    msg.setData(bundle);
                                    mHandler.sendMessage(msg);
                                    lock.unlock();
                                } catch (IOException e) {
                                    lock.lock();
                                    mHandler.sendEmptyMessage(DOWNLOAD_ERROR);
                                    lock.unlock();

                                }

                            }
                        });
                        executor.execute(th);

                    }

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
                handlerDownload.onSuccess(msg.getData().getParcelable("pic"));
                break;
            case DOWNLOAD_ERROR:
                handlerDownload.onFail();

                break;
        }
        return false;
    }

}
