package de.otto.awsipranges.AwsIpRanges.controller;

import de.otto.awsipranges.AwsIpRanges.service.DataService;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RangeController {
    // @Autowired
    private final DataService dataService;

    public RangeController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping(value = "/data", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getData(@RequestParam(value = "region", defaultValue = "ALL") String region) {
        return StringUtils.collectionToDelimitedString(dataService.getPrefixByRegion(region), "\n");
    }
}