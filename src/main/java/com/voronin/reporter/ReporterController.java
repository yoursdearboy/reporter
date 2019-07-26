package com.voronin.reporter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Controller
public class ReporterController {
    private static final String DOCX_MIMETYPE =
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private static final String PDF_MIMETYPE = "application/pdf";

    private final ReporterService service;

    public ReporterController(ReporterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/**", method = RequestMethod.POST)
    public void createReport(HttpServletRequest request, HttpServletResponse response)
            throws ReportLoadingException, ReporterException, ConversionException, IOException {
        String uri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String accept = request.getHeader("Accept");
        boolean makePDF = !Objects.equals(accept, DOCX_MIMETYPE);
        byte[] result = service.create(uri, makePDF);
        response.getOutputStream().write(result);
        if (makePDF) {
            response.setHeader("Content-Type", PDF_MIMETYPE);
        } else {
            response.setHeader("Content-Type", DOCX_MIMETYPE);
        }
    }
}
