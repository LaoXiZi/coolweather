package com.sq.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 *  * Author：Elt on 2017/6/29 09:43
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
