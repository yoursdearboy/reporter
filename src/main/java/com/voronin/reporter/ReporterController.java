package com.voronin.reporter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ReporterController {
    private final ReportLoader loader;

    public ReporterController(ReportLoader loader) {
        this.loader = loader;
    }

    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public void createReport(HttpServletRequest request, HttpServletResponse response)
            throws ReportLoadingException, ReporterException, IOException {
        String uri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        Report report = loader.load(uri);
        Reporter reporter = new Reporter(report);
        reporter.build();
        XWPFDocument document = (XWPFDocument) reporter.getDocument();
        document.write(response.getOutputStream());
    }
}
