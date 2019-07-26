package com.voronin.reporter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ReportLoadingException extends Exception {
    ReportLoadingException(String s) {
        super(s);
    }
}
