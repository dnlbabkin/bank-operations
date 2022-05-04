package com.example.operations.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external")
@Getter
public class ExternalProperties {

    private String bankservice;
    private String cbr;

}
