package com.hlj.burning.wechat.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class TokenResponse {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Long expiresIn;
}
