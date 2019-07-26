package com.voronin.reporter;

interface ReportLoader {
    Report load(String uri) throws ReportLoadingException;
}
