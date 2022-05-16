package br.edu.utfpr.jfile.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public abstract class AmazonService {

    private static String awsId = "";
    private static String awsKey = "";
    private static String region = "sa-east-1";
    private static String bucketName = "spring-matheus";

    private static AmazonS3 s3client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
        AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(
                new AWSStaticCredentialsProvider(awsCredentials)).build();

        return s3client;
    }

    public static String uploadFile(File actualFile) {
        File file = actualFile;
        s3client().putObject(new PutObjectRequest(bucketName, nameFormat(file.getName()) + ".jpg", file));

        return "https://spring-matheus.s3.sa-east-1.amazonaws.com/"+file.getName();
    }

    public static void teste() {
    }

    private static String nameFormat(String fileName) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < fileName.length(); i++) {
            if(fileName.charAt(i) == '.')
                break;

            sb.append(fileName.charAt(i));
        }

        return sb.toString();
    }
}
