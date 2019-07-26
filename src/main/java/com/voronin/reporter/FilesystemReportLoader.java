package com.voronin.reporter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FilesystemReportLoader implements ReportLoader {
    @Value("${reporter.path}")
    private String path;

    public FilesystemReportLoader() { }

    @Override
    public Report load(String uri) throws ReportLoadingException {
        if (path == null) {
            throw new ReportLoadingException("No reporter.path set.");
        }

        String content;
        try {
            content = new String(Files.readAllBytes(Paths.get(path, uri)));
        } catch (IOException e) {
            throw new ReportLoadingException(e.getMessage());
        }
        return new Report(content, uri, null);
    }
}
