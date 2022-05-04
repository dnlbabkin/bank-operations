package com.example.operations.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "external")
@Getter
@Setter
public class ExternalProperties {

    private String bankservice;
    private String cbr;

}
