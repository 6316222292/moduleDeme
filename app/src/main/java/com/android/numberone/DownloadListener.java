package com.android.numberone;

/**
 * Created by lenovo on 2018/3/27.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
