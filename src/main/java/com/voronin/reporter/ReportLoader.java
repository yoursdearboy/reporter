package com.voronin.reporter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
class ReportLoader {
    Report load(String uri) throws ReportLoadingException {
        Resource resource = new ClassPathResource(uri);
        String content;
        try {
            content = new String(Files.readAllBytes(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new ReportLoadingException(e.getMessage());
        }
        return new Report(content, uri, null);
    }
}
