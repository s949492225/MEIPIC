package com.syiyi.inf;

/**
 * Created by songlintao on 15/8/6.
 */
public interface DownloadHandler<T> {
        void onSuccess(T mDatas);

        void onFail(Exception e);
}

