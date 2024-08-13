package com.epris.homepage.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* S3 */
    FILE_CONVERT_ERROR(HttpStatus.BAD_REQUEST,"파일 전환에 실패하였습니다."),
    FILE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST,"이미지 업로드에 실패하였습니다."),
    FILE_DELETE_ERROR(HttpStatus.BAD_REQUEST,"이미지 삭제에 실패하였습니다."),


    /* input */
    INPUT_IS_NULL(HttpStatus.BAD_REQUEST,"입력으로 null이 들어왔습니다."),

    /* null 반환 */
    NO_CONTENT_EXIST(HttpStatus.BAD_REQUEST,"데이터가 존재하지 않습니다.")



    ;


    private final HttpStatus status;
    private final String message;
}