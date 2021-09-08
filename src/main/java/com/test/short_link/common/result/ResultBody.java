package com.test.short_link.common.result;

import lombok.Data;

@Data
public class ResultBody {
    private Object data;
    private int code = 200;

    public ResultBody() {}
    public ResultBody(Object data) {
        this.data = data;
    }
}
