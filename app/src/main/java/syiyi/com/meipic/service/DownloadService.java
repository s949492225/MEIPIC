package syiyi.com.meipic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import org.apache.http.Header;
import java.io.File;

public class DownloadService extends Service {
    private AsyncHttpClient client;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        client = new AsyncHttpClient();
        client.setTimeout(10000);
        client.setUserAgent("iphone");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        downLoadImage(intent);
        return START_STICKY;
    }

    public void downLoadImage(Intent intent) {
        final String url = intent.getStringExtra("url");
        final File saveFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MEIPIC");
        if (!saveFile.exists())
                    saveFile.mkdir();
        client.get(url, new FileAsyncHttpResponseHandler(saveFile) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(DownloadService.this, "图片下载失败,请稍候重试!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                Toast.makeText(DownloadService.this, "图片存储在"+file.getPath(), Toast.LENGTH_LONG).show();
            }
        });


    }

}
