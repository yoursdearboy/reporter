package com.voronin.reporter;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

public class ReporterTests {
    @Test
    public void builds() throws ReporterException {
        Report report = new Report("", null, "groovy");
        Reporter reporter = new Reporter(report);
        reporter.build();
    }

    @Test
    public void buildsWithScript() throws ReporterException {
        Report report = new Report("println new Date()", null, "groovy");
        Reporter reporter = new Reporter(report);
        reporter.build();
    }

    @Test
    public void importsPOI() throws ReporterException {
        Report report = new Report(
                "import org.apache.poi.xwpf.usermodel.XWPFDocument",
                null,
                "groovy");
        Reporter reporter = new Reporter(report);
        reporter.build();
    }

    @Test
    public void buildsReport() throws IOException, ReporterException {
        String uri = "reports/test.groovy";
        Resource testResource = new ClassPathResource(uri);
        String testContent = new String(Files.readAllBytes(testResource.getFile().toPath()));
        Report report = new Report(testContent,  uri, null);
        Reporter reporter = new Reporter(report);
        reporter.build();
    }

    @Test
    public void returnsReport() throws IOException, ReporterException {
        String uri = "reports/test.groovy";
        Resource testResource = new ClassPathResource(uri);
        String testContent = new String(Files.readAllBytes(testResource.getFile().toPath()));
        Report report = new Report(testContent, uri, null);
        Reporter reporter = new Reporter(report);
        reporter.build();
        assertNotNull(reporter.getDocument());
    }
}
