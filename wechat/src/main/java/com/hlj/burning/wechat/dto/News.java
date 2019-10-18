package com.hlj.burning.wechat.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class News {
    private String title;
    @SerializedName("thumb_media_id")
    private String thumbMediaId;
    private String author;
    @SerializedName("show_cover_pic")
    private Integer showCoverPic;
    private String content;
    @SerializedName("content_source_url")
    private String contentSourceUrl;
    @SerializedName("need_open_comment")
    private Integer needOpenComment;
    @SerializedName("only_fans_can_comment")
    private Integer onlyFansCanComment;
}
