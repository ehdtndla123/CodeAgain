package com.note.plannerweb.config.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponse {

    SUCCESS(0,"성공 하였습니다."),
    FAIL(-1,"실패 하였습니다.");

    private int code;
    private String msg;

}
