package com.hlj.burning.wechat.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddNewsRequest {
    private List<News> articles;
}
