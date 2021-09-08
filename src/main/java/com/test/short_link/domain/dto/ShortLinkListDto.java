package com.test.short_link.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * Short Link List 조회할 때 사용
 */
@Data
@NoArgsConstructor
public class ShortLinkListDto {
    private final int DEFAULT_PAGE = 1;  // 조회할 페이지
    private final int DEFAULT_SIZE = 10; // 한번에 가져올 ShortLink 개수

    private int startOffset = 0;
    private int endOffset   = 10;

    @Min(value = 1, message = "페이지는 0보다 커야합니다.")
    private int page = DEFAULT_PAGE;

    @Min(value = 1, message = "조회 개수는 0보다 커야합니다.")
    private int size = DEFAULT_SIZE;

    public void calculate() {
        this.startOffset = (this.page - 1) * this.size;
        this.endOffset = this.size;
    }
}
