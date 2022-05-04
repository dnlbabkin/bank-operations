package com.example.operations.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external")
@Data
public class ExternalProperties {

    private String bankservice;
    private String cbr;

}
