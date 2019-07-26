package com.voronin.reporter;

class Report {
    private final String content;
    private final String engine;

    Report(String content, String engine) {
        this.content = content;
        this.engine = engine;
    }

    String getContent() {
        return content;
    }

    String getEngine() {
        return engine;
    }
}
