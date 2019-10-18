package com.hlj.burning.wechat.api;

import com.hlj.burning.wechat.dto.AddNewsRequest;
import com.hlj.burning.wechat.dto.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeChatAPI {

    @GET("/cgi-bin/token")
    Call<TokenResponse> getAccessToken(
            @Query("grant_type") String grantType,
            @Query("appid") String appid,
            @Query("secret") String secret
    );

    @POST("/cgi-bin/material/add_news")
    Call<TokenResponse> addNews(
            @Query("access_token") String AccessToken,
            @Body() AddNewsRequest request
    );
}
