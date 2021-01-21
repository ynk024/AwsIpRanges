package de.otto.awsipranges.AwsIpRanges;

import de.otto.awsipranges.AwsIpRanges.model.DataWrapper;
import de.otto.awsipranges.AwsIpRanges.model.Prefix;
import de.otto.awsipranges.AwsIpRanges.service.DataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ServiceUnitTest {

    @Autowired
    private DataService dataService;

    @MockBean
    private RestTemplate restTemplate;

    private DataWrapper dummyData;

    @BeforeEach
    public void setup(){
        initDummyData();
    }

    @AfterEach
    public void clear(){
        dummyData = null;
    }

    @Test
    void shouldTestDataIsNull(){
        when(restTemplate.getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class)).thenReturn(null);
        assertNull(dataService.getPrefixByRegion("ALL"));
        verify(restTemplate,times(1)).getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class);
    }

    @Test
    void shouldTestRegionNotValid(){
        when(restTemplate.getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class)).thenReturn(dummyData);
        assertNull(dataService.getPrefixByRegion("NOTVALID"));
        verify(restTemplate,times(1)).getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "EU", "US", "CN", "AP", "SA", "AF", "CA"})
    void shouldTestRegionIsValid(String region) {

        when(restTemplate.getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class)).thenReturn(dummyData);
        // Liste mit einem Element
        assertEquals(1, dataService.getPrefixByRegion(region).size());
        verify(restTemplate,times(1)).getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class);
    }

    @Test
    void shouldTestRegionAll() {
        when(restTemplate.getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class)).thenReturn(dummyData);
        assertEquals(dummyData.getPrefixes().size(), dataService.getPrefixByRegion("ALL").size());
        verify(restTemplate,times(1)).getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class);
    }

    @Test
    void shouldTestRegionIsCorrect() {
        when(restTemplate.getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class)).thenReturn(dummyData);
        assertEquals("eu-west-3", dataService.getPrefixByRegion("EU").get(0).getRegion());
        verify(restTemplate,times(1)).getForObject("https://ip-ranges.amazonaws.com/ip-ranges.json", DataWrapper.class);
    }

    private void initDummyData(){
        List<String> validRegions = Arrays.asList("eu-west-3", "ap-northeast-2", "us-west-1", "cn-northwest-1", "sa-east-1", "af-south-1", "ca-central-1", "GLOBAL");
        dummyData = new DataWrapper();
        List<Prefix> prefixes = new ArrayList<>();
        validRegions.forEach(region -> {
            Prefix prefix = new Prefix();
            prefix.setRegion(region);
            prefixes.add(prefix);
        });
        dummyData.setPrefixes(prefixes);
    }
}