package com.example.frequencychar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FrequencyCharApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharCounterService charCounterService;

    @Test
    void contextLoads() throws Exception{
        mockMvc.perform(post("/frequency_char")
                .contentType(MediaType.APPLICATION_JSON).param("input", "aaaaabcccc"))
                .andExpect(status().isOk())
                .andExpect(content().string(new String("[{\"a\":5},{\"c\":4},{\"b\":1}]")));
    }

    @Test
    void orderedResult() throws Exception {
        mockMvc.perform(post("/frequency_char")
                .contentType(MediaType.APPLICATION_JSON).param("input", "aaabbbcccc"))
                .andExpect(status().isOk())
                .andExpect(content().string(new String("[{\"c\":4},{\"a\":3},{\"b\":3}]")));
    }

    @Test
    void excessLengthString() throws Exception {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(generate()).forEach(value -> sb.append((char) value));
        String generatedString = sb.toString();

        String content = mockMvc.perform(post("/frequency_char").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).param("input", generatedString))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        assertThat(content).isGreaterThanOrEqualTo("{\"status\":400,\"message\":\"Длина строки превышает максимально допустимое значение\"");
    }

    private int[] generate() {
        return new Random().ints(97, 122)
                .limit(10_001)
                .map(value -> (char) value)
                .toArray();
    }

    @Test
    void digitInString() {
        assertThat(charCounterService.charCounter("someStringWithDi5it").getBody().toString())
                .isGreaterThanOrEqualTo("[i=4, t=3, D=1, e=1, g=1, h=1, m=1, n=1, o=1, r=1, s=1, S=1, 5=1, W=1]");
    }

    @Test
    void emptyString() {
        assertThat(charCounterService.charCounter("").getBody().toString()).isEqualTo("[]");
    }
}
