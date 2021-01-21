package de.otto.awsipranges.AwsIpRanges.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DataWrapper {
    private String syncToken;
    @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
    private Date createDate;
    private List<Prefix> prefixes = new ArrayList<>();
}
