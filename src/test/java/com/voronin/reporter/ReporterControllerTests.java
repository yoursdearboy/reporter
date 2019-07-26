package com.voronin.reporter;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReporterControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createReport() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/reports/test.groovy"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("word/document.xml")));
    }

    @Test
    public void returnsNotFound() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/reports/no_such_report"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }
}
