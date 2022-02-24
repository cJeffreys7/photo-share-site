package com.chrisjeffreys.photosharesite.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AmazonConfig {

    @Autowired
    private Environment env;

    @Value("#{environment.awsaccesskey}")
    private String awsAccessKey;

    @Value("#{environment.awssecretkey}")
    private String awsSecretKey;

    @Bean
    public AmazonS3 s3() {
        // System.out.println("Access key: " + awsAccessKey + ", Secret key: " + awsSecretKey);
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                awsAccessKey,
                awsSecretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_2)
                .build();
    }

}
