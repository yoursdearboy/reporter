package com.voronin.reporter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibreOfficePDFConverterTests {
    @Value("${libreoffice.path}")
    private String libreofficePath;

    @Test
    public void converts() throws IOException, ConversionException {
        PDFConverter converter = new LibreOfficePDFConverter(libreofficePath);
        Resource resource = new ClassPathResource("test.docx");
        File result = converter.convert(resource.getFile());
        assertEquals(Files.probeContentType(result.toPath()), "application/pdf");
    }

    @After
    public void removePDF() throws IOException {
        Resource resource = new ClassPathResource("test.pdf");
        if (resource.exists()) resource.getFile().delete();
    }
}
