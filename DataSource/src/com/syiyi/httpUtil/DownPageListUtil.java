package com.syiyi.httpUtil;

import com.syiyi.bean.PageItem;
import com.syiyi.inf.DownloadHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songlintao on 15/8/6.
 */
public class DownPageListUtil {
    public static void getDirItmList(final String url, final DownloadHandler<List<PageItem>> handler) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    List<PageItem> datas = new ArrayList<>();
                    Document doc = Jsoup.connect(url).userAgent("Mozilla")
                            .timeout(3000).get();
                    Elements eles = doc.select(".nav_left").select("li a");
                    for (int i = 0; i < eles.size(); i++) {

                        String url = eles.get(i).absUrl("href");
                        String title = eles.get(i).text();
                        System.out.println(url);
                        System.out.println(title);
                        PageItem item = new PageItem(url,title);
                        item.setTitle(url);
                        datas.add(item);
                        handler.onSuccess(datas);
                    }
                } catch (Exception e) {
                    handler.onFail(e);
                }

            }
        }).start();

    }
}
