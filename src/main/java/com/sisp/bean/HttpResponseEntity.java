package com.sisp.bean;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class HttpResponseEntity {
    private String code;
    private Object data;
    private String message;
}
