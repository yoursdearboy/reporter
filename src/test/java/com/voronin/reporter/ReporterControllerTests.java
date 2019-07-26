package com.voronin.reporter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReporterControllerTests {
    private static final String DOCX_MIMETYPE =
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private static final String PDF_MIMETYPE = "application/pdf";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPDFReport() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/reports/test.groovy"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(startsWith("%PDF-1.5")))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", PDF_MIMETYPE));
    }

    @Test
    public void createWordReport() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                            .post("/reports/test.groovy")
                            .header("Accept", DOCX_MIMETYPE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("word/document.xml")))
                .andExpect(MockMvcResultMatchers.header().string("Content-Type", DOCX_MIMETYPE));
    }

    @Test
    public void returnsNotFound() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/reports/no_such_report"))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }
}
