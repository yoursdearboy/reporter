package com.voronin.reporter;

import org.apache.commons.io.FilenameUtils;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;

class Reporter {
    private static final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private final Report report;
    private ScriptEngine engine;
    private HashMap<String,Object> vars = new HashMap<>();

    public Reporter(Report report) {
        this.report = report;
    }

    private void initEngine() throws ReporterException {
        if (report.getEngine() != null) {
            engine = scriptEngineManager.getEngineByName(report.getEngine());
        } else if (report.getURI() != null) {
            engine = scriptEngineManager.getEngineByExtension(FilenameUtils.getExtension(report.getURI()));
        } else {
            throw new ReporterException("Reports without URI must set engine explicitly.");
        }
        if (engine == null) {
            throw new ReporterException("Engine not found.");
        }
        engine.getContext().setAttribute("vars", vars, ScriptContext.ENGINE_SCOPE);
    }

    public void build() throws ReporterException {
        initEngine();
        try {
            engine.eval(report.getContent());
        } catch(ScriptException e) {
            throw new ReporterException(e.getMessage());
        }
    }

    public Object getDocument() {
        return vars.get("document");
    }
}
