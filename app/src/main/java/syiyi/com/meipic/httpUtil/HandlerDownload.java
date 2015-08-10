package syiyi.com.meipic.httpUtil;

/**
 * Created by songlintao on 15/8/6.
 */

public interface HandlerDownload< T> {
    void onSuccess(T data);

    void onFail();
}
