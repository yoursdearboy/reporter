package com.voronin.reporter;

import java.io.File;

interface PDFConverter {
    File convert(File document) throws ConversionException;
}
