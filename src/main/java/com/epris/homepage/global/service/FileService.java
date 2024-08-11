package com.epris.homepage.global.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.epris.homepage.global.exception.CustomException;
import com.epris.homepage.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URL;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class FileService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;
    private final AmazonS3Client amazonS3Client;


    /* presigned url 발급 */
    public String getPreSignedUrl(String fileName) throws Exception {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(bucket, fileName);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return url.toString();
    }

    /* 파일 업로드용(PUT) presigned url 생성 */
    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String bucket, String fileName) throws Exception {
        /* 파일 이름으로 null 이 들어왔을 경우, 예외 처리 */
        if(fileName.equals(null)) throw new CustomException(ErrorCode.INPUT_IS_NULL);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());

        return generatePresignedUrlRequest;
    }

    /* S3에서 파일 삭제 */
    public void deleteImage(String imageUrl) throws IOException {
        String imageName = getFileNameFromURL(imageUrl);
        try {
            amazonS3Client.deleteObject(bucket,imageName);
        }catch (SdkClientException e){
            throw new CustomException(ErrorCode.FILE_DELETE_ERROR);
        }
    }

    /* presigned url 유효 기간 설정 */
    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 2;
        expiration.setTime(expTimeMillis);

        return expiration;
    }

    /* 파일 이름에서 파일 객체 식별을 위한 key 추출 */
    public static String getFileNameFromURL(String url) {
        return url.substring(url.lastIndexOf('/') + 1, url.length());
    }
}
