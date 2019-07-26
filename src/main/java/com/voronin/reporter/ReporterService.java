package com.voronin.reporter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

@Service
class ReporterService {
    @Value("${libreoffice.path}")
    private String libreofficePath;

    private final ReportLoader loader;

    ReporterService(ReportLoader loader) {
        this.loader = loader;
    }

    byte[] create(String uri, boolean convert)
            throws ReportLoadingException, ReporterException, ConversionException, IOException {
        Report report = loader.load(uri);
        Reporter reporter = new Reporter(report);
        reporter.build();
        XWPFDocument document = (XWPFDocument) reporter.getDocument();
        if (convert) {
            PDFConverter pdfConverter = new LibreOfficePDFConverter(libreofficePath);
            File tempFile = writeToTemp(document);
            File pdfFile = pdfConverter.convert(tempFile);
            return Files.readAllBytes(pdfFile.toPath());
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.write(out);
        return out.toByteArray();
    }

    private File writeToTemp(XWPFDocument document) throws IOException {
        File file = File.createTempFile("report", ".docx");
        OutputStream outputStream = new FileOutputStream(file);
        document.write(outputStream);
        return file;
    }
}
