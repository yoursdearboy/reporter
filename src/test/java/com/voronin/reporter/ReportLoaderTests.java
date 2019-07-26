package com.voronin.reporter;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class ReportLoaderTests {
    @Test
    public void loadsReport() throws IOException, ReportLoadingException {
        String uri = "reports/test.groovy";

        Resource testResource = new ClassPathResource(uri);
        String testContent = new String(Files.readAllBytes(testResource.getFile().toPath()));

        ReportLoader loader = new ReportLoader();
        Report report = loader.load(uri);

        assertEquals(report.getContent(), testContent);
    }
}
