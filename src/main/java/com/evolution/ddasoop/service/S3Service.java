package com.evolution.ddasoop.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.evolution.ddasoop.domain.Forest;
import com.evolution.ddasoop.domain.ForestImage;
import com.evolution.ddasoop.domain.ForestImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3Service {
    private AmazonS3 s3Client;
    public static final String CLOUD_FRONT_DOMAIN_NAME = "d117ehama2apgt.cloudfront.net";

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }


    public ForestImage upload(MultipartFile photo, Forest forest) throws IOException {
        if (photo != null) {
            String contentType = photo.getContentType();
            String originalFileExtension;

            //확장자명 없을 경우
            if (ObjectUtils.isEmpty(contentType)) {
                return null;
            } else {
                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                } else if(contentType.contains("image/jpeg")){
                    originalFileExtension = ".jpeg";
                }
                // 다른 파일 명이면 아무 일 하지 않는다
                else {
                    return null;
                }
            }
          
            SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
            String fileName = FilenameUtils.getBaseName(photo.getOriginalFilename()) + "_" + date.format(new Date()) + "." + FilenameUtils.getExtension(photo.getOriginalFilename());


            s3Client.putObject(new PutObjectRequest(bucket, fileName, photo.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            String filePath = "http://" + CLOUD_FRONT_DOMAIN_NAME + "/" + fileName;
            ForestImage forestImage = new ForestImage(photo.getOriginalFilename(), forest, filePath, photo.getSize());
            return forestImage;
        }
        else return null;
    }

    public String update(String currentFilePath, MultipartFile file) throws IOException {
        int isOk = 1;

        if(file!=null){
            String contentType = file.getContentType();
            if(ObjectUtils.isEmpty(contentType)){
                isOk = 0;
            } else {
                if(contentType.contains("image/jpeg")) {
                    isOk = 1;
                }
                else if(contentType.contains("image/png")) {
                    isOk = 1;
                }
                else{
                    isOk = 0;
                }
            }
        }
        if(isOk == 1) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = FilenameUtils.getBaseName(file.getOriginalFilename()) + "_" + date.format(new Date()) + "." + FilenameUtils.getExtension(file.getOriginalFilename());

            if ("".equals(currentFilePath) == false && currentFilePath != null) {
                boolean isExistObject = s3Client.doesObjectExist(bucket, currentFilePath);

                if (isExistObject == true) {
                    s3Client.deleteObject(bucket, currentFilePath);
                }
            }

            s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            return fileName;
        } else {
            return null;
        }
    }

    public String delete(String currentFilePath) {
        int idx = currentFilePath.lastIndexOf("/");
        String fileName = currentFilePath.substring(idx+1);
        try {
            s3Client.deleteObject(bucket, fileName);
            return "deleted";
        }
        catch(Exception e) {
            return "error occured" + e.getMessage();
        }

    }

}
