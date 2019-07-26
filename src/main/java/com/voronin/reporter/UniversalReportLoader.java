package com.voronin.reporter;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UniversalReportLoader implements ReportLoader {
    private final List<ReportLoader> loaders;

    public UniversalReportLoader(List<ReportLoader> loaders) {
        this.loaders = loaders;
    }

    @Override
    public Report load(String uri) throws ReportLoadingException {
        Report report;
        for (ReportLoader loader : loaders) {
            try {
                report = loader.load(uri);
                if (report != null) {
                    return report;
                }
            } catch (ReportLoadingException ignored) { }
        }
        throw new ReportLoadingException("Report not found");
    }
}
