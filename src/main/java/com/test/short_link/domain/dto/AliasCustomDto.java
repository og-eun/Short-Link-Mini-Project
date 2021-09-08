package com.test.short_link.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Alias 등록할 때 사용
 */
@Data
@NoArgsConstructor
public class AliasCustomDto {

    @NotNull(message = "필수값을 입력해주세요.")
    private String aliasName;

    private String shortId;

}
