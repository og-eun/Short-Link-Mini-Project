package com.test.short_link.domain;

import lombok.Data;

@Data
public class AliasCustom {

    private String aliasName;   // 사용자 지정 alias name

    private String shortId;     // 기존 변환된 short url id

}
