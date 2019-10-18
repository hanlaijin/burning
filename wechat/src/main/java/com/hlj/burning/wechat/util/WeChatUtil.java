package com.hlj.burning.wechat.util;

import com.hlj.burning.util.SecurityUtil;
import com.hlj.burning.wechat.api.WeChatAPI;
import com.hlj.burning.wechat.config.WeChatConfig;
import com.hlj.burning.wechat.dto.AddNewsRequest;
import com.hlj.burning.wechat.dto.TokenResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WeChatUtil {

    private static WeChatAPI weChatAPI;

    static {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeChatConfig.HOST)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weChatAPI = retrofit.create(WeChatAPI.class);
    }

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[]{WeChatConfig.TOKEN, timestamp, nonce};
        Arrays.sort(arr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        String tmpStr = null;
        try {
            tmpStr = SecurityUtil.sha1(content.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    public static String refreshAccessToken() throws IOException {
        Call<TokenResponse> call = weChatAPI.getAccessToken("client_credential", WeChatConfig.APP_ID, WeChatConfig.SECRET);
        TokenResponse response = call.execute().body();
        return response.getAccessToken();
    }

    public static String addNews(String accessToken, AddNewsRequest request) throws IOException {
        Call<TokenResponse> call = weChatAPI.addNews(accessToken, request);
        TokenResponse response = call.execute().body();
        return response.getAccessToken();
    }
}
