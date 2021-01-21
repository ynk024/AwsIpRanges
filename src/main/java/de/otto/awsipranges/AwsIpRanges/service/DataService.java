package de.otto.awsipranges.AwsIpRanges.service;

import de.otto.awsipranges.AwsIpRanges.model.DataWrapper;
import de.otto.awsipranges.AwsIpRanges.model.Prefix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataService {
    //@Autowired
    RestTemplate restTemplate;

    public DataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final String SOURCE_URL = "https://ip-ranges.amazonaws.com/ip-ranges.json";
    private final List<String> validRegions = Arrays.asList("EU", "US", "AP", "CN", "SA", "AF", "CA");

    public List<Prefix> getPrefixByRegion(String region) {
        DataWrapper data = restTemplate.getForObject(SOURCE_URL, DataWrapper.class);
        if (data != null) {
            if (region.equals("ALL"))
                return data.getPrefixes();
            if (validRegions.contains(region))
                return data.getPrefixes().stream().filter(prefix -> prefix.getRegion().toUpperCase().startsWith(region)).collect(Collectors.toList());
        }
        return null;
    }
}