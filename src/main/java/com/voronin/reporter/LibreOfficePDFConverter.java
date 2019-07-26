package com.voronin.reporter;

import java.io.File;
import java.io.IOException;

class LibreOfficePDFConverter implements PDFConverter {
    private String libreofficePath;

    LibreOfficePDFConverter(String libreofficePath) {
        this.libreofficePath = libreofficePath;
    }

    public File convert(File document) throws ConversionException {
        int exitCode;
        try {
            Process process = buildProcess(document);
            exitCode = process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new ConversionException(e.getMessage());
        }
        if (exitCode != 0) throw new ConversionException("Can't convert file.");
        return new File(getPDFName(document));
    }

    private Process buildProcess(File file) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(libreofficePath, "--convert-to", "pdf", file.getAbsolutePath());
        builder.directory(file.getParentFile());
        return builder.start();
    }

    private String getPDFName(File file) {
        String path = file.getAbsolutePath();
        return String.format("%s.pdf", path.substring(0, path.length() - 5));
    }
}
