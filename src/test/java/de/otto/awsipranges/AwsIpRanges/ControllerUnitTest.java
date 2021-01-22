package de.otto.awsipranges.AwsIpRanges;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.otto.awsipranges.AwsIpRanges.controller.RangeController;
import de.otto.awsipranges.AwsIpRanges.service.DataService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(RangeController.class)
public class ControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataService dataService;
    @MockBean
    private RestTemplate restTemplate;
    
    @Test
    public void getDataShouldReturnMimePlainText() throws Exception {
        when(dataService.getPrefixByRegion("ALL")).thenReturn(null);
        this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    @Test
    public void getDataShouldReturnString() throws Exception {
        when(dataService.getPrefixByRegion("ALL")).thenReturn(null);
        this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }
}