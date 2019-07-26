package com.voronin.reporter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportLoaderTests {
    @Autowired
    private UniversalReportLoader loader;

    @Test
    public void loadsReportFromClasspath() throws IOException, ReportLoadingException {
        String uri = "reports/test.groovy";

        Resource testResource = new ClassPathResource(uri);
        String testContent = new String(Files.readAllBytes(testResource.getFile().toPath()));

        Report report = loader.load(uri);

        assertEquals(report.getContent(), testContent);
    }
}
