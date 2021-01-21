package de.otto.awsipranges.AwsIpRanges.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Prefix {
    @JsonProperty("ip_prefix")
    private String ipPrefix;
    private String region;
    private String service;
    @JsonProperty("network_border_group")
    private String netWorkBorderGroup;
}